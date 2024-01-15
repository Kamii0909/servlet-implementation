package com.kien.servlet.context;

import java.util.*;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;

import jakarta.servlet.*;
import jakarta.servlet.FilterRegistration.Dynamic;

/**
 * FilterRegistration that only accept /* mapping
 */
public class FilterHolder implements Dynamic {
    private final Filter filter;
    private final String filterName;
    private final ServletContext context;
    private final Map<String, String> initParams = new HashMap<>();
    
    public FilterHolder(Filter filter, String filterName, ServletContext context) {
        this.filter = filter;
        this.filterName = filterName;
        this.context = context;
    }
    
    Filter getFilter() {
        return filter;
    }
    
    void initialize() throws ServletException {
        filter.init(new FilterConfig() {
            @Override
            public String getFilterName() {
                return filterName;
            }
            
            @Override
            public ServletContext getServletContext() {
                return context;
            }
            
            @Override
            public String getInitParameter(String name) {
                return initParams.get(name);
            }
            
            @Override
            public Enumeration<String> getInitParameterNames() {
                return Iterators.asEnumeration(initParams.keySet().iterator());
            }
        });
    }
    
    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes,
        boolean isMatchAfter,
        String... servletNames) {
        throw new UnsupportedOperationException("Unimplemented method 'addMappingForServletNames'");
    }
    
    @Override
    public Collection<String> getServletNameMappings() {
        throw new UnsupportedOperationException("Unimplemented method 'getServletNameMappings'");
    }
    
    @Override
    public void addMappingForUrlPatterns(
        EnumSet<DispatcherType> dispatcherTypes,
        boolean isMatchAfter,
        String... urlPatterns) {
        if (urlPatterns.length > 1) {
            throw new UnsupportedOperationException();
        }
        if (urlPatterns[0] != "/*") {
            throw new UnsupportedOperationException();
        }
        if (!dispatcherTypes.contains(DispatcherType.REQUEST)) {
            context.log("Ignoring filter registration: [FilterName: %s, Class: %s]"
                .formatted(filterName, filter.getClass()));
        }
        context.log("Filter %s:[isMatchAfter: %s]".formatted(filterName, isMatchAfter));
    }
    
    @Override
    public Collection<String> getUrlPatternMappings() {
        throw new UnsupportedOperationException("Unimplemented method 'getUrlPatternMappings'");
    }
    
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
    
    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Unimplemented method 'getClassName'");
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
    public String getInitParameter(String name) {
        return initParams.get(name);
    }
    
    @Override
    public Set<String> setInitParameters(Map<String, String> initParameters) {
        initParams.clear();
        initParams.putAll(initParameters);
        return Set.copyOf(initParams.keySet());
    }
    
    @Override
    public Map<String, String> getInitParameters() {
        return ImmutableMap.copyOf(initParams);
    }
    
    @Override
    public void setAsyncSupported(boolean isAsyncSupported) {
        context.log("Async is not supported!");
    }
    
}
