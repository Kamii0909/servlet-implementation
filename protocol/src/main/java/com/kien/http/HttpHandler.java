package com.kien.http;

import java.net.InetAddress;

import rawhttp.core.RawHttpRequest;
import rawhttp.core.RawHttpResponse;

public interface HttpHandler {
    RawHttpResponse<?> handle(RawHttpRequest request, InetAddress address, int port);
}
