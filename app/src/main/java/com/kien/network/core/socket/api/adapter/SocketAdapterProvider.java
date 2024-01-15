package com.kien.network.core.socket.api.adapter;

/**
 * A Socket Adapter Provider is used to get the Socket Adapter instance for each
 * Socket. Users are required to provide an implementation of this interface.
 * 
 * @param T Java socket type
 */
public sealed interface SocketAdapterProvider<T> permits BlockingSocketAdapterProvider, SocketChannelAdapterProvider {
    /**
     * Get the SocketAdapter instance
     */
    SocketAdapter get(T socket);
}
