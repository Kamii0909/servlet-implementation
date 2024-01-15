package com.kien.http;

import java.net.InetAddress;

import org.junit.jupiter.api.Test;

import com.kien.network.core.WebServer;
import com.kien.network.core.support.WebServerFactory;

import rawhttp.core.*;
import rawhttp.core.body.EagerBodyReader;

class HttpAdapterTest {
    
    @Test
    void testBasic() throws InterruptedException {
        class HttpHandlerImpl implements HttpHandler {
            
            
            @Override
            public RawHttpResponse<?> handle(RawHttpRequest request, InetAddress address, int port) {
                byte[] bytes = null;
                bytes = request.toString().getBytes();
                
                return new EagerHttpResponse<Object>(null, request,
                    new StatusLine(HttpVersion.HTTP_1_1, 200, "OK"),
                    RawHttpHeaders.newBuilderSkippingValidation()
                        .with("Content-Length", String.valueOf(bytes.length))
                        .with("Connection", "keep-alive")
                        .with("Content-Type", "text/html")
                        .build(),
                    new EagerBodyReader(bytes));
            }
            
        }
        
        WebServer ws = WebServerFactory.multiplex(sc -> new HttpAdapter(new HttpHandlerImpl()),
            InetAddress.getLoopbackAddress(),
            8080,
            1);
        ws.run().join();
    }
}
