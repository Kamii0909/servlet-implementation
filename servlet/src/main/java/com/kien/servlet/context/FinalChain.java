package com.kien.servlet.context;

import java.io.IOException;

import jakarta.servlet.*;

public class FinalChain implements FilterChain {
    private final Servlet servlet;
    
    public FinalChain(Servlet servlet) {
        this.servlet = servlet;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        servlet.service(request, response);
    }
    
}
