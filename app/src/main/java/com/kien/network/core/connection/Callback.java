package com.kien.network.core.connection;

public interface Callback {
    /**
     * <p>
     * Callback invoked when the operation completes.
     * </p>
     *
     * @see #failed(Throwable)
     */
    void succeeded();
    
    /**
     * <p>
     * Callback invoked when the operation fails.
     * </p>
     *
     * @param x the reason for the operation failure
     */
    void failed(Throwable x);
}
