package com.kien.network.core.socket.internal.context;

import java.nio.ByteBuffer;

import com.kien.network.core.connection.ConnectionListener;
import com.kien.network.core.socket.api.adapter.SocketAdapter;
import com.kien.network.core.socket.api.context.SocketContext;

public class ContextListener implements ConnectionListener {
    
    private final SocketAdapter adapter;
    private final SocketContext context;
    
    public ContextListener(SocketAdapter adapter, SocketContext context) {
        this.adapter = adapter;
        this.context = context;
    }
    
    @Override
    public void onRead(ByteBuffer data) {
        adapter.onRead(context, data);
    }
    
    @Override
    public void onEOF() {
        adapter.onInactive(context);
    }
    
}
