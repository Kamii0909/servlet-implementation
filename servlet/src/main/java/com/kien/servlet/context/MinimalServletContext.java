package com.kien.servlet.context;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Iterators;
import com.kien.servlet.ServletContextFacade;

import jakarta.servlet.Filter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;

/**
 * ServletContext implementation just enough for a typical Spring application.
 */
public class MinimalServletContext extends ServletContextFacade {
    
    // hopefully this will be dispatcher servlet
    private ServletHolder servlet;
    private List<FilterHolder> filters = new ArrayList<>();
    private Map<String, Object> attributes = new ConcurrentHashMap<>();
    private Map<String, String> initParams = new HashMap<>();
    
    Servlet getServlet() {
        return servlet.getServlet();
    }
    
    List<Filter> getFilters() {
        return filters.stream().map(holder -> holder.getFilter()).toList();
    }
    
    /**
     * Initialize this ServletContext, completing its configurations
     * 
     * @throws ServletException
     */
    public void initialize() throws ServletException {
        servlet.initialize();
        for (FilterHolder filter : filters) {
            filter.initialize();
        }
    }
    
    @Override
    public String getInitParameter(String name) {
        return initParams.get(name);
    }
    
    @Override
    public Enumeration<String> getInitParameterNames() {
        return Iterators.asEnumeration(initParams.keySet().iterator());
    }
    
    @Override
    public boolean setInitParameter(String name, String value) {
        if (initParams.get(name) != null) {
            return false;
        }
        initParams.put(name, value);
        return true;
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
    public void setAttribute(String name, Object object) {
        attributes.put(name, object);
    }
    
    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }
    
    @Override
    public Dynamic addServlet(String servletName, Servlet servlet) {
        ServletHolder holder = new ServletHolder(servlet, servletName, this);
        this.servlet = holder;
        return holder;
    }
    
    @Override
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        FilterHolder holder = new FilterHolder(filter, filterName, this);
        filters.add(holder);
        return holder;
    }
    
    @Override
    public String getContextPath() {
        return "/";
    }
    
    @Override
    public String getMimeType(String file) {
        log("Finding MIME type for: " + file);
        return null;
    }
}
