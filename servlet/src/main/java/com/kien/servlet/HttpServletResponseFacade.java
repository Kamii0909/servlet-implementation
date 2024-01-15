package com.kien.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class HttpServletResponseFacade implements HttpServletResponse {
    
    @Override
    public String getCharacterEncoding() {
        throw new UnsupportedOperationException("Unimplemented method 'getCharacterEncoding'");
    }
    
    @Override
    public String getContentType() {
        throw new UnsupportedOperationException("Unimplemented method 'getContentType'");
    }
    
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'getOutputStream'");
    }
    
    @Override
    public PrintWriter getWriter() throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'getWriter'");
    }
    
    @Override
    public void setCharacterEncoding(String charset) {
        throw new UnsupportedOperationException("Unimplemented method 'setCharacterEncoding'");
    }
    
    @Override
    public void setContentLength(int len) {
        throw new UnsupportedOperationException("Unimplemented method 'setContentLength'");
    }
    
    @Override
    public void setContentLengthLong(long len) {
        throw new UnsupportedOperationException("Unimplemented method 'setContentLengthLong'");
    }
    
    @Override
    public void setContentType(String type) {
        throw new UnsupportedOperationException("Unimplemented method 'setContentType'");
    }
    
    @Override
    public void setBufferSize(int size) {
        throw new UnsupportedOperationException("Unimplemented method 'setBufferSize'");
    }
    
    @Override
    public int getBufferSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getBufferSize'");
    }
    
    @Override
    public void flushBuffer() throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'flushBuffer'");
    }
    
    @Override
    public void resetBuffer() {
        throw new UnsupportedOperationException("Unimplemented method 'resetBuffer'");
    }
    
    @Override
    public boolean isCommitted() {
        throw new UnsupportedOperationException("Unimplemented method 'isCommitted'");
    }
    
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
    @Override
    public void setLocale(Locale loc) {
        throw new UnsupportedOperationException("Unimplemented method 'setLocale'");
    }
    
    @Override
    public Locale getLocale() {
        throw new UnsupportedOperationException("Unimplemented method 'getLocale'");
    }
    
    @Override
    public void addCookie(Cookie cookie) {
        throw new UnsupportedOperationException("Unimplemented method 'addCookie'");
    }
    
    @Override
    public boolean containsHeader(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'containsHeader'");
    }
    
    @Override
    public String encodeURL(String url) {
        throw new UnsupportedOperationException("Unimplemented method 'encodeURL'");
    }
    
    @Override
    public String encodeRedirectURL(String url) {
        throw new UnsupportedOperationException("Unimplemented method 'encodeRedirectURL'");
    }
    
    @Override
    public void sendError(int sc, String msg) throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'sendError'");
    }
    
    @Override
    public void sendError(int sc) throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'sendError'");
    }
    
    @Override
    public void sendRedirect(String location) throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'sendRedirect'");
    }
    
    @Override
    public void setDateHeader(String name, long date) {
        throw new UnsupportedOperationException("Unimplemented method 'setDateHeader'");
    }
    
    @Override
    public void addDateHeader(String name, long date) {
        throw new UnsupportedOperationException("Unimplemented method 'addDateHeader'");
    }
    
    @Override
    public void setHeader(String name, String value) {
        throw new UnsupportedOperationException("Unimplemented method 'setHeader'");
    }
    
    @Override
    public void addHeader(String name, String value) {
        throw new UnsupportedOperationException("Unimplemented method 'addHeader'");
    }
    
    @Override
    public void setIntHeader(String name, int value) {
        throw new UnsupportedOperationException("Unimplemented method 'setIntHeader'");
    }
    
    @Override
    public void addIntHeader(String name, int value) {
        throw new UnsupportedOperationException("Unimplemented method 'addIntHeader'");
    }
    
    @Override
    public void setStatus(int sc) {
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }
    
    @Override
    public int getStatus() {
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }
    
    @Override
    public String getHeader(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getHeader'");
    }
    
    @Override
    public Collection<String> getHeaders(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getHeaders'");
    }
    
    @Override
    public Collection<String> getHeaderNames() {
        throw new UnsupportedOperationException("Unimplemented method 'getHeaderNames'");
    }
    
}
