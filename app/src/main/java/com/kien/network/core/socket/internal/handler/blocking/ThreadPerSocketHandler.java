// package com.kien.network.core.socket.internal.handler.blocking;

// import java.net.Socket;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import com.kien.network.core.socket.api.adapter.BlockingSocketAdapter;
// import com.kien.network.core.socket.api.adapter.BlockingSocketAdapterProvider;
// import com.kien.network.core.socket.api.context.BlockingSocketContext;
// import com.kien.network.core.socket.api.handler.BlockingSocketHandler;
// import com.kien.network.core.socket.internal.handler.AbstractSocketHandler;

// /**
//  * Each socket will be bound into a thread (virtual or platform). The handle
//  * method in this class will not block as it use an internal thread pool.
//  */
// public final class ThreadPerSocketHandler extends AbstractSocketHandler<Socket, BlockingSocketAdapterProvider>
//     implements BlockingSocketHandler, AutoCloseable {
//     private static final Logger log = LoggerFactory.getLogger(ThreadPerSocketHandler.class);
//     private final ExecutorService executorService;
    
//     public ThreadPerSocketHandler(BlockingSocketAdapterProvider adapterProvider,
//         ExecutorService executorService) {
//         super(adapterProvider);
//         this.executorService = executorService;
//     }
    
//     /**
//      * Create a SocketHandler that use a thread pool and attach each Socket to a
//      * thread.
//      */
//     public ThreadPerSocketHandler(BlockingSocketAdapterProvider adapterProvider, int nThreads) {
//         this(adapterProvider, Executors.newFixedThreadPool(nThreads));
//     }
    
//     /**
//      * Create a SocketHandler that create a new Virtual Thread for each Socket.
//      */
//     @SuppressWarnings("preview")
//     public ThreadPerSocketHandler(BlockingSocketAdapterProvider adapterProvider) {
//         this(adapterProvider, Executors.newVirtualThreadPerTaskExecutor());
//     }
    
//     @Override
//     public void handle(Socket socket) {
//         BlockingSocketAdapter adapter = adapterProvider.get(socket);
//         BlockingSocketContext context = null;// TODO new DefaultBlockingSocketContext(socket, adapter);
        
//         adapter.onActive(context);
        
//         executorService.submit(new ThreadBoundSocketTask(context));
        
//     }
    
//     private static class ThreadBoundSocketTask implements Runnable {
//         BlockingSocketContext context;
        
//         public ThreadBoundSocketTask(BlockingSocketContext context) {
//             this.context = context;
//         }
        
//         @Override
//         public void run() {
//             BlockingSocketAdapter adapter = (BlockingSocketAdapter) context.getSocketAdapter();
//             context.getEndpoint();
//             adapter.onBound(context);
            
//             if (!((Socket) context.getEndpoint().getSocket()).isClosed()) {
//                 byte[] buffer = new byte[1024];
//                 do {
//                     // No op, context will call onRead
//                 } while (context.blockingRead(buffer) != -1);
//             }
//             adapter.onInactive(context);
//         }
        
//     }
    
//     @Override
//     public void close() throws Exception {
//         executorService.shutdown();
//     }
// }
