package com.kien.network.core.socket.api.handler;

/**
 * A socket handler is responsible for the IO strategy and thread model (which
 * IO operation runs on which thread).
 *
 * @param <T> the Socket class
 */
public interface SocketHandler<T> {
    
    /**
     * Handle the processing of an accepted socket. It is implementation
     * responsibility to query data from the socket, trigger any write or close the
     * socket.
     * 
     * @param socket the accepted socket
     */
    void handle(T socket);
}
