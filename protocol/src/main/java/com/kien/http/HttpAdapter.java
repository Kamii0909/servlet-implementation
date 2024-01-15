package com.kien.http;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.eclipse.jetty.http.HttpParser;
import org.eclipse.jetty.io.ByteBufferOutputStream2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kien.network.core.connection.Callback;
import com.kien.network.core.socket.api.adapter.SocketAdapter;
import com.kien.network.core.socket.api.context.SocketContext;

import rawhttp.core.RawHttpRequest;
import rawhttp.core.RawHttpResponse;

public class HttpAdapter implements SocketAdapter {
    private static final Logger log = LoggerFactory.getLogger(HttpAdapter.class);
    private RawHttpRequestHandler rawHttpRequestHandler = new RawHttpRequestHandler();
    private HttpParser parser = new HttpParser(rawHttpRequestHandler);
    private final HttpHandler handler;
    
    public HttpAdapter(HttpHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public void onActive(SocketContext context) {
        log.info("Started Http Adapter");
    }
    
    @Override
    public void onInactive(SocketContext context) {
        log.info("Stopped Http Adapter");
    }
    
    @Override
    public void onRead(SocketContext context, ByteBuffer data) {
        if (parser.parseNext(data)) {
            RawHttpRequest request = rawHttpRequestHandler.getRequest();
            RawHttpResponse<?> response = handler.handle(
                request,
                context.getEndpoint().getRemoteAddress(),
                context.getEndpoint().getRemotePort());
            ByteBufferOutputStream2 hack = new ByteBufferOutputStream2();
            try {
                response.writeTo(hack);
            } catch (IOException e) {
                // wont happen
                e.printStackTrace();
            }
            context.write(hack.takeByteBuffer().getByteBuffer());
            context.flush(new Callback() {
                
                @Override
                public void succeeded() {
                    log.info("Flushed");
                }
                
                @Override
                public void failed(Throwable x) {
                    log.error("Error in flushing", x);
                }
                
            });
            parser.reset();
        }
    }
    
    @Override
    public void onBound(SocketContext context) {
    }
    
    @Override
    public void onUnbound(SocketContext context) {
    }
    
}
