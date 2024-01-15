package com.kien.network.core.socket.internal.handler.blocking;

import java.net.Socket;

import com.kien.network.core.socket.api.adapter.BlockingSocketAdapterProvider;
import com.kien.network.core.socket.api.adapter.SocketAdapter;
import com.kien.network.core.socket.api.context.SocketContext;
import com.kien.network.core.socket.api.handler.BlockingSocketHandler;
import com.kien.network.core.socket.internal.context.DefaultSocketContext;
import com.kien.network.core.socket.internal.handler.AbstractSocketHandler;

/**
 * Handler that reuse the calling thread for blocking IO.
 */
public class ReuseThreadHandler extends AbstractSocketHandler<Socket, BlockingSocketAdapterProvider>
    implements BlockingSocketHandler {
    
    public ReuseThreadHandler(BlockingSocketAdapterProvider adapterProvider) {
        super(adapterProvider);
    }
    
    @Override
    public void handle(Socket socket) {
        
        SocketAdapter adapter = adapterProvider.get(socket);
        // TODO
        SocketContext context = new DefaultSocketContext(null, adapter);
        
        adapter.onActive(context);
        adapter.onBound(context);
        // if (!socket.isClosed()) {
        //     byte[] buffer = new byte[1024];
        //     do {
        //         // No op, context will call onRead
        //     } while (context.blockingRead(buffer) != -1);
        // }
        
        adapter.onInactive(context);
        
    }
}
