package com.kien.network.core.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

import com.kien.network.AutoLock;
import com.kien.network.core.ByteBufferStrategy;

public class NioConnection implements Connection {
    static final ByteBufferStrategy strategy = ByteBufferStrategy.defaultStrategy();
    private final NonblockingEndpoint endpoint;
    private final SelectionKey key;
    private ByteBuffer readBuffer = strategy.allocate(8192);
    private ByteBuffer writeBuffer = strategy.allocateDirect(8192);
    private AutoLock writeLock = new AutoLock();
    private volatile ConnectionListener listener;
    private volatile Callback flushCallback;
    
    public NioConnection(NonblockingEndpoint endpoint, SelectionKey key) {
        this.endpoint = endpoint;
        this.key = key;
    }
    
    @Override
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @Override
    public void addListener(ConnectionListener listener) {
        this.listener = listener;
    }
    
    public void isReadble() {
        try {
            readBuffer.clear();
            int read = endpoint.read(readBuffer);
            if (read == -1) {
                listener.onEOF();
                key.cancel();
            }
            else if (read > 0) {
                readBuffer.flip();
                listener.onRead(readBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void close() {
        try {
            endpoint.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void write(ByteBuffer data) {
        try (var lock = writeLock.lock()) {
            writeBuffer = strategy.append(writeBuffer, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void flush() {
        flush(flushCallback);
    }
    
    @Override
    public void flush(Callback callback) {
        this.flushCallback = callback;
        try (var lock = writeLock.lock()) {
            writeBuffer.flip();
            // Write spin loop optimization, 16 is the default of Netty
            int i = 16;
            while (i-- > 0) {
                endpoint.write(writeBuffer);
                if (writeBuffer.remaining() == 0) {
                    // We have written everything
                    key.interestOps(SelectionKey.OP_READ);
                    break;
                }
            }
            if (writeBuffer.remaining() > 0) {
                // We will come back to write this later, not now
                key.interestOps(SelectionKey.OP_WRITE);
                writeBuffer.flip();
            } else {
                writeBuffer.clear();
                flushCallback.succeeded();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public InetAddress getRemoteAddress() {
        return endpoint.getRemoteAddress();
    }

    @Override
    public int getRemotePort() {
        return endpoint.getRemotePort();
    }
    
}
