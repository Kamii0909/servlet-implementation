package com.kien.servlet.context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

import com.kien.servlet.HttpServletResponseFacade;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import rawhttp.core.HttpVersion;
import rawhttp.core.RawHttpHeaders;
import rawhttp.core.RawHttpResponse;
import rawhttp.core.StatusLine;
import rawhttp.core.body.EagerBodyReader;

public class MinimalServletResponse extends HttpServletResponseFacade {
    private static final DateTimeFormatter dateFmt = DateTimeFormatter
        .ofPattern("EEE, dd MMM yyyy HH:mm:ss O", Locale.ENGLISH);
    private static final ZoneId GMT_ZONE_ID = ZoneId.of("GMT");
    private int status = 200;
    private RawHttpHeaders.Builder headersBuilder = RawHttpHeaders.newBuilderSkippingValidation();
    private ByteArrayOutputStream outputStream;
    //
    // HEADER BASED SERVLET FEATURES
    //
    private Charset characterEncoding;
    private String contentType;
    private long contentLength = -1;
    private Locale locale = Locale.getDefault();
    //
    // Commit
    //
    private boolean committed = false;
    
    @Override
    public String getHeader(String name) {
        return headersBuilder.get(name).stream().findFirst().orElse(null);
    }
    
    @Override
    public Collection<String> getHeaders(String name) {
        return headersBuilder.get(name);
    }
    
    @Override
    public void addHeader(String name, String value) {
        headersBuilder.with(name, value);
    }
    
    @Override
    public void setHeader(String name, String value) {
        headersBuilder.overwrite(name, value);
    }
    
    @Override
    public boolean containsHeader(String name) {
        return headersBuilder.get(name) != null;
    }
    
    @Override
    public String getContentType() {
        return contentType;
    }
    
    @Override
    public void setContentType(String type) {
        this.contentType = type;
    }
    
    @Override
    public String getCharacterEncoding() {
        return characterEncoding == null ? null : characterEncoding.name();
    }
    
    @Override
    public void setCharacterEncoding(String charset) {
        this.characterEncoding = Charset.forName(charset);
    }
    
    @Override
    public void setContentLengthLong(long len) {
        this.contentLength = len;
    }
    
    @Override
    public void setLocale(Locale loc) {
        if (loc == null) {
            this.locale = Locale.getDefault();
        }
        this.locale = loc;
    }
    
    @Override
    public Locale getLocale() {
        return this.locale;
    }
    
    @Override
    public void resetBuffer() {
        outputStream().reset();
    }
    
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            
            @Override
            public boolean isReady() {
                return true;
            }
            
            @Override
            public void setWriteListener(WriteListener writeListener) {
                throw new UnsupportedOperationException("Unimplemented method 'setWriteListener'");
            }
            
            @Override
            public void write(int b) throws IOException {
                outputStream().write(b);
            }
            
        };
    }

    @Override
    public int getBufferSize() {
        return 8192;
    }
    
    @Override
    public void setStatus(int sc) {
        this.status = sc;
    }
    
    @Override
    public int getStatus() {
        return status;
    }
    
    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(
            getOutputStream(), false,
            characterEncoding == null ? StandardCharsets.ISO_8859_1 : characterEncoding);
    }
    
    @Override
    public String encodeURL(String url) {
        return url;
    }
    
    @Override
    public void setDateHeader(String name, long date) {
        headersBuilder.with(name, dateFmt.format(Instant.ofEpochMilli(date).atZone(GMT_ZONE_ID)));
    }
    
    @Override
    public void sendError(int sc) throws IOException {
        this.status = sc;
    }
    
    @Override
    public void sendError(int sc, String msg) throws IOException {
        this.status = sc;
        this.contentType = "text/html";
        this.contentLength = -1;
        outputStream().reset();
        getWriter().append(msg);
    }
    
    RawHttpResponse<MinimalServletResponse> commit() {
        this.committed = true;
        if (contentType != null && headersBuilder.get("Content-Type").isEmpty()) {
            String ce = characterEncoding == null ? "" : (";charset=" + characterEncoding.name());
            headersBuilder.with("Content-Type", contentType + ce);
        }
        byte[] bodyArr = outputStream == null ? new byte[0] : outputStream.toByteArray();
        EagerBodyReader body = new EagerBodyReader(bodyArr);
        long cl = contentLength != -1 ? contentLength : bodyArr.length;
        if (headersBuilder.get("Content-Length").isEmpty()) {
            headersBuilder.with("Content-Length", String.valueOf(cl));
        }
        headersBuilder.with("Accept-Language", locale.getLanguage());
        return new RawHttpResponse<MinimalServletResponse>(this, null,
            new StatusLine(HttpVersion.HTTP_1_1, status, ""),
            headersBuilder.build(),
            body);
    }
    
    @Override
    public boolean isCommitted() {
        return committed;
    }
    
    private ByteArrayOutputStream outputStream() {
        return outputStream == null ? (outputStream = new ByteArrayOutputStream(8192)) : outputStream;
    }
}
