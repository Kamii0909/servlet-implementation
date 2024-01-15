package com.kien.network.core.socket.internal.context;

import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

import com.kien.network.core.connection.Callback;
import com.kien.network.core.connection.Endpoint;
import com.kien.network.core.connection.NioConnection;
import com.kien.network.core.socket.api.adapter.SocketAdapter;
import com.kien.network.core.socket.api.context.SocketContext;

public class DefaultSocketContext implements SocketContext {
    protected final SocketAdapter socketAdapter;
    private Object attachment;
    private final ReentrantLock attachmentLock = new ReentrantLock();
    private NioConnection connection;
    
    public DefaultSocketContext(NioConnection connection, SocketAdapter socketAdapter) {
        this.connection = connection;
        this.socketAdapter = socketAdapter;
    }
    
    public void initialize() {
        connection.addListener(new ContextListener(socketAdapter, this));
        socketAdapter.onActive(this);
    }
    
    @Override
    public Endpoint getEndpoint() {
        return connection.getEndpoint();
    }
    
    @Override
    public SocketAdapter getSocketAdapter() {
        return socketAdapter;
    }
    
    @Override
    public Object attach(Object newVal) {
        try {
            attachmentLock.lock();
            Object oldVal = attachment;
            attachment = newVal;
            return oldVal;
        } finally {
            attachmentLock.unlock();
        }
    }
    
    @Override
    public Object getAttachment() {
        try {
            attachmentLock.lock();
            return attachment;
        } finally {
            attachmentLock.unlock();
        }
    }
    
    @Override
    public void ifAttachmentPresent(Consumer<Object> action) {
        try {
            attachmentLock.lock();
            if (attachment == null) {
                return;
            }
            if (action == null) {
                throw new IllegalArgumentException("Action is null");
            }
            action.accept(attachment);
        } finally {
            attachmentLock.unlock();
        }
    }
    
    @Override
    public void close() {
        connection.close();
    }
    
    public void flush() {
        connection.flush();
    }
    
    @Override
    public void flush(Callback callback) {
        connection.flush(callback);
    }
    
    @Override
    public void read() {
        connection.isReadble();
    }
    
    @Override
    public void write(ByteBuffer data) {
        connection.write(data);
    }
    
}
