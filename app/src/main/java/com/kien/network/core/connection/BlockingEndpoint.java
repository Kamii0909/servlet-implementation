package com.kien.network.core.connection;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockingEndpoint implements Endpoint {
    private static final Logger log = LoggerFactory.getLogger(BlockingEndpoint.class);
    private final Socket socket;
    private final InputStream socketIn;
    private final OutputStream socketOut;
    
    public BlockingEndpoint(Socket socket) throws IOException {
        this.socket = socket;
        this.socketIn = socket.getInputStream();
        this.socketOut = socket.getOutputStream();
    }
    
    public InputStream getSocketIn() {
        return socketIn;
    }
    
    public OutputStream getSocketOut() {
        return socketOut;
    }
    
    /**
     * {@inheritDoc} The read won't read more than what immediately available in the
     * socket buffer, making this method nonblocking.
     */
    @Override
    public int read(ByteBuffer buffer) throws IOException {
        if (buffer.isDirect()) {
            log.warn("BlockingEndpoint can't use DirectByteBuffer");
            return 0;
        }
        int available = socketIn.available();
        int bytesRead = socketIn.read(buffer.array(), buffer.position(), Math.min(available, buffer.remaining()));
        buffer.position(buffer.position() + bytesRead);
        return bytesRead;
    }
    
    /**
     * Initiate a blocking read (block until some data is available)
     *
     * @return the amount of bytes read
     */
    public int read(byte[] buffer) throws IOException {
        return socketIn.read(buffer);
    }
    
    /**
     * Write in a blocking fashion (the call block until all data is passed)
     */
    public void write(byte[] buffer) throws IOException {
        socketOut.write(buffer);
    }
    
    @Override
    public int write(ByteBuffer buffer) throws IOException {
        if (buffer.isDirect()) {
            log.warn("BlockingEndpoint can't use DirectByteBuffer");
            return 0;
        }
        socketOut.write(buffer.array(), buffer.position(), buffer.limit());
        int pos = buffer.position();
        buffer.position(buffer.limit());
        flush();
        return buffer.limit() - pos;
    }
    
    public void flush() throws IOException {
        socketOut.flush();
    }
    
    @Override
    public void close() throws IOException {
        socket.close();
        socketIn.close();
        socketOut.close();
    }
    
    @Override
    public Socket getSocket() {
        return socket;
    }
    
    @Override
    public InetAddress getRemoteAddress() {
        return ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress();
    }
    
    @Override
    public int getRemotePort() {
        return ((InetSocketAddress) socket.getRemoteSocketAddress()).getPort();
    }
}
