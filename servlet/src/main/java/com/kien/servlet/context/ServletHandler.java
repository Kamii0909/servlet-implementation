package com.kien.servlet.context;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kien.http.HttpHandler;

import jakarta.servlet.*;
import rawhttp.core.RawHttpRequest;
import rawhttp.core.RawHttpResponse;

public class ServletHandler implements HttpHandler {
    private static Logger log = LoggerFactory.getLogger(ServletHandler.class);
    // hopefully the dispatcherServlet
    private final Servlet servlet;
    /**
     * hopefully all /* filters, reversed ordered (last one before servlet is at
     * position 0)
     */
    private final List<Filter> filters;
    private final ServletContext context;
    
    public ServletHandler(MinimalServletContext context) {
        this.servlet = context.getServlet();
        this.filters = context.getFilters();
        this.context = context;
    }
    
    @Override
    public RawHttpResponse<?> handle(RawHttpRequest request, InetAddress address, int port) {
        MinimalServletResponse response = new MinimalServletResponse();
        try {
            createFilterChain().doFilter(createRequest(request, address, port), (response = createResponse()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException | RuntimeException e) {
            response.setStatus(500);
            log.warn("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return response.commit();
    }
    
    private MinimalServletRequest createRequest(RawHttpRequest request, InetAddress address, int port) {
        return new MinimalServletRequest(request, context, address, port);
    }
    
    private MinimalServletResponse createResponse() {
        return new MinimalServletResponse();
    }
    
    private FilterChain createFilterChain() {
        FinalChain finalChain = new FinalChain(servlet);
        if (filters.size() == 0) {
            return finalChain;
        }
        FilterChain chain = finalChain;
        for (Filter filter : filters) {
            chain = new Chain(filter, chain);
        }
        return chain;
    }
    
}
