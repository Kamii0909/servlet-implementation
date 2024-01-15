package com.kien.network.core.socket.api.context;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

import com.kien.network.core.connection.Callback;
import com.kien.network.core.connection.Endpoint;
import com.kien.network.core.socket.api.adapter.SocketAdapter;

/**
 * The SocketContext class provide various transport mechanism independent
 * interaction with the underlying socket. The socket context is valid for the
 * whole processing of a single socket.
 * <p>
 * Implementation should be thread safe.
 */
public interface SocketContext {
    
    /**
     * Get the underlying transportation mechanism.
     * 
     * @return the socket object
     */
    Endpoint getEndpoint();
    
    /**
     * @return the socket adapter configured for this socket.
     */
    SocketAdapter getSocketAdapter();
    
    /**
     * Write to an internal buffer. This will not flush the stream, to make data
     * transferred between socket, use {@link #flush()}.
     * 
     * @param data a ByteBuffer in write mode.
     */
    void write(ByteBuffer data);
    
    /**
     * Flush the stream, ensure data written through the socket. It is
     * implementation dependant whether this will block.
     */
    void flush(Callback callback);
    
    /**
     * Request a read operation from the underlying socket, triggering
     * {@link SocketAdapter#onRead(SocketContext, byte[])} to be called. This method
     * blocks until {@link SocketAdapter#onRead(SocketContext, byte[]) onRead}
     * returns if any data was read. Otherwise, this method returns immediately as a
     * noop, effectively making this call implicitly nonblocking.
     */
    void read();
    
    /**
     * Close the underlying socket.
     */
    void close();
    
    /**
     * Attach an object to the context. Replace the old value.
     * 
     * @param o the new value
     * @return the old value, or null if none.
     */
    Object attach(Object o);
    
    /**
     * Get the attachment if any. If there is no attachment, this method return
     * null.
     */
    Object getAttachment();
    
    /**
     * Action to be invoked if there is any attachment present.
     */
    void ifAttachmentPresent(Consumer<Object> action);
}
