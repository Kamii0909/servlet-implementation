package com.kien.servlet.context;

import java.io.*;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.kien.servlet.HttpServletRequestFacade;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import rawhttp.core.RawHttpRequest;

public class MinimalServletRequest extends HttpServletRequestFacade {
    private static final DateTimeFormatter dateFmt =
        DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss O", Locale.ENGLISH);
    private final Map<String, Object> attributes = new HashMap<>();
    private final Map<String, List<String>> params;
    private final RawHttpRequest request;
    private final ServletContext context;
    private final InetAddress address;
    private final int port;
    
    public MinimalServletRequest(RawHttpRequest request, ServletContext context, InetAddress address, int port) {
        this.request = request;
        this.params = Optional
            .ofNullable(request.getUri().getQuery())
            .map(query -> Pattern.compile("&")
                .splitAsStream(query)
                .map(s -> s.split("="))
                .collect(Collectors.groupingBy(s -> s[0],
                    Collectors.mapping(s -> s.length > 1 ? s[1] : "", Collectors.toList()))))
            .orElse(ImmutableMap.of());
        this.context = context;
        this.address = address;
        this.port = port;
    }
    
    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }
    
    @Override
    public Enumeration<String> getAttributeNames() {
        return Iterators.asEnumeration(attributes.keySet().iterator());
    }
    
    @Override
    public String getCharacterEncoding() {
        var header = request.getHeaders().get("Content-Type");
        if (header.size() == 1) {
            String contentType = header.getFirst();
            int charsetIdx = contentType.indexOf("charset");
            if (charsetIdx == -1) {
                return null;
            }
            int semicolonIdx = contentType.indexOf(";", charsetIdx);
            return header.get(0).substring(charsetIdx, semicolonIdx == -1 ? contentType.length() : semicolonIdx);
        }
        return null;
    }
    
    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        if (env != "UTF-8") {
            throw new UnsupportedOperationException();
        }
    }
    
    @Override
    public int getContentLength() {
        long cl = getContentLengthLong();
        return cl > Integer.MAX_VALUE ? -1 : (int) cl;
    }
    
    @Override
    public long getContentLengthLong() {
        var header = request.getHeaders().get("Content-Length");
        if (header.size() == 1) {
            return Long.parseLong(header.getFirst());
        }
        return -1;
    }
    
    @Override
    public String getContentType() {
        var header = request.getHeaders().get("Content-Type");
        if (header.size() == 1) {
            return header.getFirst();
        }
        return null;
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStream() {
            private InputStream input = request.getBody().get().asRawStream();
            
            @Override
            public boolean isFinished() {
                try {
                    return input.available() == 0;
                } catch (IOException e) {
                    throw new IllegalStateException("Request is eager, can't IOException", e);
                }
            }
            
            @Override
            public boolean isReady() {
                return true;
            }
            
            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException("Unimplemented method 'setReadListener'");
            }
            
            @Override
            public int read() throws IOException {
                return input.read();
            }
            
        };
    }
    
    @Override
    public String getParameter(String name) {
        List<String> values = params.get(name);
        return values == null ? null : values.getFirst();
    }
    
    @Override
    public Enumeration<String> getParameterNames() {
        return Iterators.asEnumeration(params.keySet().iterator());
    }
    
    @Override
    public String[] getParameterValues(String name) {
        var result = params.get(name);
        return result != null ? result.toArray(String[]::new) : null;
    }
    
    @Override
    public Map<String, String[]> getParameterMap() {
        return Maps.transformValues(params, list -> list.toArray(String[]::new));
    }
    
    @Override
    public String getProtocol() {
        return request.getStartLine().getHttpVersion().toString();
    }
    
    @Override
    public String getScheme() {
        return request.getUri().getScheme();
    }
    
    @Override
    public String getServerName() {
        return request.getHeaders().get("Host").stream().findFirst().orElse(address.getHostAddress());
    }
    
    @Override
    public int getServerPort() {
        return port;
    }
    
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
    }
    
    @Override
    public String getRemoteAddr() {
        return "unknown";
    }
    
    @Override
    public String getRemoteHost() {
        throw new UnsupportedOperationException("Unimplemented method 'getRemoteHost'");
    }
    
    @Override
    public void setAttribute(String name, Object o) {
        attributes.put(name, o);
    }
    
    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }
    
    @Override
    public Locale getLocale() {
        return Locale.getDefault();
    }
    
    @Override
    public boolean isSecure() {
        return false;
    }
    
    @Override
    public ServletContext getServletContext() {
        return context;
    }
    
    @Override
    public String getServletPath() {
        return "";
    }
    
    @Override
    public String getPathInfo() {
        return request.getUri().getPath();
    }
    
    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        throw new UnsupportedOperationException("Unimplemented method 'startAsync'");
    }
    
    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
        throws IllegalStateException {
        throw new UnsupportedOperationException("Unimplemented method 'startAsync'");
    }
    
    @Override
    public boolean isAsyncStarted() {
        return false;
    }
    
    @Override
    public DispatcherType getDispatcherType() {
        return DispatcherType.REQUEST;
    }
    
    @Override
    public String getHeader(String name) {
        var header = request.getHeaders().get(name);
        return header.size() == 1 ? header.getFirst() : null;
    }
    
    @Override
    public Enumeration<String> getHeaders(String name) {
        return Iterators.asEnumeration(request.getHeaders().get(name).iterator());
    }
    
    @Override
    public Enumeration<String> getHeaderNames() {
        return Iterators.asEnumeration(request.getHeaders().getHeaderNames().iterator());
    }
    
    @Override
    public String getMethod() {
        return request.getMethod();
    }
    
    @Override
    public String getContextPath() {
        return "/";
    }
    
    @Override
    public String getQueryString() {
        return request.getUri().getQuery();
    }
    
    @Override
    public Principal getUserPrincipal() {
        return null;
    }
    
    @Override
    public String getRequestURI() {
        return request.getUri().getPath();
    }
    
    @Override
    public HttpSession getSession(boolean create) {
        if (create == true) {
            context.log("NEED TO IMPLEMENT");
        }
        return null;
    }
    
    @Override
    public long getDateHeader(String name) {
        List<String> list = request.getHeaders().get(name);
        if (list.isEmpty()) {
            return -1;
        }
        String header = list.get(0);
        try {
            TemporalAccessor accessor = dateFmt.parse(header);
            return Instant.EPOCH.until(Instant.from(accessor), ChronoUnit.MILLIS);
        } catch (Exception e) {
            return -1;
        }
    }
    
}
