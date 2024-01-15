package com.kien.network.core.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;

/**
 * Represents a physical (can be lazy or batched) network connection with the
 * peer, using a Socket as the underlying (delegating) network transportation
 * infrastructure. 
 *
 * @apiNote A marker interface to unite operation across {@link java.net.Socket
 *          Socket}, {@link java.nio.channels.SocketChannel SocketChannel},
 *          {@link java.nio.channels.AsynchronousSocketChannel
 *          AsynchronousSocketChannel}
 */
public interface Endpoint {

    /**
     * Read into the buffer.
     * <ul>
     * <li>The read will start from {@code buffer.position()} to
     * {@code buffer.limit()}.</li>
     * <li>The read will read at most {@code buffer.remaining()} bytes</li>
     * <li>The buffer position will be set to the previous value plus the amount of
     * bytes read</li>
     * </ul>
     *
     * @return the amount of byte read
     */
    int read(ByteBuffer buffer) throws IOException;
    
    /**
     * Write to the stream.
     * <ul>
     * <li>The write will start from {@code buffer.position()} to
     * {@code buffer.limit()}</li>
     * <li>The write will write at most {@code buffer.remaining()} bytes</li>
     * <li>The buffer position will be set to the previous value plus the amount of
     * bytes read</li>
     * </ul>
     *
     * @return the amount of byte written
     */
    int write(ByteBuffer buffer) throws IOException;
    
    void close() throws IOException;
    
    /**
     * Get the underlying socket attached with this endpoint.
     */
    Object getSocket();

    InetAddress getRemoteAddress();

    int getRemotePort();
}
