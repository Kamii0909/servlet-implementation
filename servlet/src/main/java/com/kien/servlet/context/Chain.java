package com.kien.servlet.context;

import java.io.IOException;

import jakarta.servlet.*;

/**
 * Invoke the chain as is
 */
class Chain implements FilterChain {
    private final Filter next;
    public Chain(Filter next, FilterChain chain) {
        this.next = next;
        this.chain = chain;
    }

    private final FilterChain chain;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        next.doFilter(request, response, chain);
    }
}
