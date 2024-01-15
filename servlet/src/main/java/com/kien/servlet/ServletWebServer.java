package com.kien.servlet;

import java.net.InetAddress;
import java.util.Objects;

import com.kien.http.HttpAdapter;
import com.kien.network.core.WebServer;
import com.kien.network.core.socket.internal.handler.multiplex.MultiSelectorHandler;
import com.kien.network.core.support.server.multiplexing.PollingTCPServer;
import com.kien.servlet.context.MinimalServletContext;
import com.kien.servlet.context.ServletHandler;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class ServletWebServer implements WebServer {
    private final PollingTCPServer server;
    private Thread serverThread;
    private final MinimalServletContext context;
    
    public ServletWebServer(InetAddress address, int port) {
        this.context = new MinimalServletContext();
        server = new PollingTCPServer(
            Objects.requireNonNullElse(address, InetAddress.getLoopbackAddress()),
            port,
            new MultiSelectorHandler(
                sc -> new HttpAdapter(new ServletHandler(context)),
                Runtime.getRuntime().availableProcessors()));
    }
    
    public ServletContext getServletContext() {
        return context;
    }
    
    @Override
    public void close() throws Exception {
        serverThread.interrupt();
    }
    
    @Override
    public Thread run() {
        try {
            context.initialize();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return server.run();
    }
    
    @Override
    public void addShutdownHook() {
        server.addShutdownHook();
    }
    
}
