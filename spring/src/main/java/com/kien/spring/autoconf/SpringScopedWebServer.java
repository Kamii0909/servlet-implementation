package com.kien.spring.autoconf;

import java.net.InetAddress;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import com.kien.servlet.ServletWebServer;

import jakarta.servlet.ServletException;

class SpringScopedWebServer implements WebServer {
    private final int port;
    private final ServletWebServer server;
    private Thread serverThread;
    
    public SpringScopedWebServer(InetAddress address, int port, ServletContextInitializer[] initializers) {
        this.server = new ServletWebServer(address, port);
        this.port = port;
        for (ServletContextInitializer initializer : initializers) {
            try {
                initializer.onStartup(server.getServletContext());
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void start() throws WebServerException {
        serverThread = server.run();
    }
    
    @Override
    public void stop() throws WebServerException {
        serverThread.interrupt();
    }
    
    @Override
    public int getPort() {
        return port;
    }
    
}