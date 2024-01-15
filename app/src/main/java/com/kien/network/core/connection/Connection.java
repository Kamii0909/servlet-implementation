package com.kien.network.core.connection;

import java.net.InetAddress;
import java.nio.ByteBuffer;

/**
 * Representing an organized message exchange with a single peer.
 */
public interface Connection {
    
    /**
     * Get the endpoint supporting this connection.
     */
    Endpoint getEndpoint();

    /**
     * Add a new listener for IO events.
     */
    void addListener(ConnectionListener listener);
    
    /**
     * Close the connection, disconnecting from peer.
     */
    void close();
    
    /**
     * Write to an internal buffer. This will not trigger a physical write, so
     * remember to call {@link #flush()}
     */
    void write(ByteBuffer data);
    
    /**
     * Physically write all pending messages to peer.
     * 
     * @param callback Callback to be ran after the flush complete.
     */
    void flush(Callback callback);

    InetAddress getRemoteAddress();

    int getRemotePort();
}
