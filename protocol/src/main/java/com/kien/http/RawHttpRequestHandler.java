package com.kien.http;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpParser.RequestHandler;
import org.eclipse.jetty.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rawhttp.core.RawHttpHeaders;
import rawhttp.core.RawHttpRequest;
import rawhttp.core.RequestLine;
import rawhttp.core.body.EagerBodyReader;

public class RawHttpRequestHandler implements RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(RawHttpRequestHandler.class);
    private RawHttpRequest request = null;
    private RequestLine requestLine;
    private RawHttpHeaders headers;
    private RawHttpHeaders.Builder headerBuilder;
    private List<ByteBuffer> body;
    private EagerBodyReader bodyReader;
    
    public RawHttpRequest getRequest() {
        return request;
    }
    
    @Override
    public boolean content(ByteBuffer item) {
        body.add(item);
        return false;
    }
    
    @Override
    public boolean headerComplete() {
        return false;
    }
    
    @Override
    public boolean contentComplete() {
        int size = 0;
        for (ByteBuffer byteBuffer : body) {
            size += byteBuffer.remaining();
        }
        ByteBuffer requestBody = ByteBuffer.allocate(size);
        for (ByteBuffer byteBuffer : body) {
            requestBody.put(byteBuffer);
        }
        bodyReader = new EagerBodyReader(requestBody.array());
        return false;
    }
    
    @Override
    public boolean messageComplete() {
        headers = headerBuilder.build();
        request = new RawHttpRequest(requestLine, headers, bodyReader, null);
        return true;
    }
    
    @Override
    public void parsedHeader(HttpField field) {
        headerBuilder.with(field.getName(), field.getValue());
    }
    
    @Override
    public void parsedTrailer(HttpField field) {
        log.warn("Merged trailer with header: {}: {}", field.getName(), field.getValue());
        headerBuilder.with(field.getName(), field.getValue());
    }
    
    @Override
    public void earlyEOF() {
        log.warn("Early EOF found");
        request = null;
        requestLine = null;
        headers = null;
        headerBuilder = null;
        body = null;
    }
    
    @Override
    public void startRequest(String method, String uri, HttpVersion version) {
        request = null;
        requestLine = new RequestLine(method, URI.create(uri), rawhttp.core.HttpVersion.HTTP_1_1);
        headerBuilder = RawHttpHeaders.newBuilderSkippingValidation();
        body = new ArrayList<>();
    }
    
}
