package com.kien.network.core.socket.thread;

import com.kien.network.core.connection.BlockingEndpoint;
import com.kien.network.core.connection.Endpoint;
import com.kien.network.core.connection.NonblockingEndpoint;

/**
 * EndpointReceptor receives the accepted socket from the server and decides what to do with it. It also chooses what
 * the IO strategy and threading model for this socket.
 *
 * @param <T> the socket type.
 */
public sealed interface EndpointReceptor<T extends Endpoint> permits EndpointReceptor.BlockingEndpointReceptor,
    EndpointReceptor.NonblockingEndpointReceptor {
    @FunctionalInterface
    non-sealed interface BlockingEndpointReceptor extends EndpointReceptor<BlockingEndpoint> {
    
    }
    
    @FunctionalInterface
    non-sealed interface NonblockingEndpointReceptor extends EndpointReceptor<NonblockingEndpoint> {
    
    }
    
    /**
     * Gain complete control over this socket. After passing the socket through this method, higher up component
     * shouldn't concern with this socket anymore.
     *
     * @param socket the accepted socket.
     */
    void receive(T socket);
}
