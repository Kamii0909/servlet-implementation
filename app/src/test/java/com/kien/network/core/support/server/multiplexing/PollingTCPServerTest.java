package com.kien.network.core.support.server.multiplexing;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.kien.network.core.connection.Callback;
import com.kien.network.core.socket.api.adapter.SocketAdapter;
import com.kien.network.core.socket.api.adapter.SocketChannelAdapterProvider;
import com.kien.network.core.socket.api.context.SocketContext;
import com.kien.network.core.socket.internal.handler.multiplex.MultiSelectorHandler;

import rawhttp.core.RawHttp;
import rawhttp.core.RawHttpRequest;

class PollingTCPServerTest {
    
    void testHelloWorld() throws InterruptedException {
        PollingTCPServer server = new PollingTCPServer(
            InetAddress.getLoopbackAddress(),
            8080, new MultiSelectorHandler(new SocketChannelAdapterProvider() {
                
                @Override
                public SocketAdapter get(SocketChannel socket) {
                    return new HelloWorldAdapter();
                }
                
            }, 1));
        Thread run = server.run();
        run.join();
    }
    
    static class HelloWorldAdapter implements SocketAdapter {
        private final RawHttp rawHttp;
        
        public HelloWorldAdapter() {
            rawHttp = new RawHttp();
        }
        
        @Override
        public void onActive(SocketContext context) {
            
        }
        
        @Override
        public void onInactive(SocketContext context) {
            
        }
        
        @Override
        public void onRead(SocketContext context, ByteBuffer data) {
            RawHttpRequest request = rawHttp.parseRequest(new String(data.array()));
            System.out.println(request.getStartLine());
            
            context.write(ByteBuffer.wrap("""
                HTTP/1.1 200 OK
                Accept-Ranges: bytes
                Cache-Control: max-age=604800
                Content-Type: text/html
                Server: HTK
                Vary: Accept-Encoding
                X-Cache: 404-HIT
                Content-Length: 143\r\n
                <!DOCTYPE html><html><head><title>Example</title></head><body><p>This is an example of a simple HTML page with one paragraph.</p></body></html>
                """.getBytes()));
            context.flush(new Callback() {
                
                @Override
                public void succeeded() {
                    
                    System.out.println("sent");
                }
                
                @Override
                public void failed(Throwable x) {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'failed'");
                }
                
            });
        }
        
        @Override
        public void onBound(SocketContext context) {
            
        }
        
        @Override
        public void onUnbound(SocketContext context) {
        }
        
    }
}
