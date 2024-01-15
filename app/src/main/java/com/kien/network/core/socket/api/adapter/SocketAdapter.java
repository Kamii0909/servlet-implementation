package com.kien.network.core.socket.api.adapter;

import java.nio.ByteBuffer;

import com.kien.network.core.socket.api.context.SocketContext;

/**
 * A Socket Adapter listens to changes of the Endpoint.
 */
public interface SocketAdapter {
    /**
     * Callback run after the underlying transport is active (ready). This will be
     * call right after the SocketContext is ready, IO operation may not be
     * available.
     */
    void onActive(SocketContext context);
    
    /**
     * Callback run after the underlying transport is inactive (closed). IO
     * operation will be unavailable will throw exceptions.
     */
    void onInactive(SocketContext context);
    
    /**
     * Called after a read IO operation is completed and returns new data.
     * 
     * @param data a ByteBuffer in read mode.
     */
    void onRead(SocketContext context, ByteBuffer data);
    
    /**
     * Callback run right after picked up by an IO thread (after being accepted).
     * This method is guaranteed to be ran every time a thread attempt an IO
     * operation on the socket without prerequisite knowledge about that socket. In
     * most scenario, it is likely only the first one is used. In async context,
     * this method can be called multiple times in multiple threads.
     */
    @Deprecated
    void onBound(SocketContext context);
    
    /**
     * Callback run after a thread declare further IO operation is not guaranteed to
     * be ran in that thread.
     */
    @Deprecated
    void onUnbound(SocketContext context);
}
