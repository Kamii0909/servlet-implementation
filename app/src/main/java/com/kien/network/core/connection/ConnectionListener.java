package com.kien.network.core.connection;

import java.nio.ByteBuffer;

public interface ConnectionListener {
    
    /**
     * Callback run when new data (not EOF) is read.
     */
    void onRead(ByteBuffer data);

    void onEOF();
}
