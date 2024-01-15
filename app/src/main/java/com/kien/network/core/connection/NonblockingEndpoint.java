package com.kien.network.core.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NonblockingEndpoint implements Endpoint {
    private final SocketChannel socket;
    
    public NonblockingEndpoint(SocketChannel socket) throws IOException {
        if (socket.isBlocking()) {
            socket.configureBlocking(false);
        }
        this.socket = socket;
    }
    
    @Override
    public int read(ByteBuffer buffer) throws IOException {
        return socket.read(buffer);
    }
    
    @Override
    public int write(ByteBuffer buffer) throws IOException {
        return socket.write(buffer);
    }
    
    @Override
    public void close() throws IOException {
        socket.close();
    }
    
    @Override
    public SocketChannel getSocket() {
        return socket;
    }
    
    @Override
    public InetAddress getRemoteAddress() {
        try {
            return ((InetSocketAddress) socket.getRemoteAddress()).getAddress();
        } catch (IOException e) {
            // wont happen
            e.printStackTrace();
            return null;
        }
        
    }
    
    @Override
    public int getRemotePort() {
        try {
            return ((InetSocketAddress) socket.getRemoteAddress()).getPort();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
