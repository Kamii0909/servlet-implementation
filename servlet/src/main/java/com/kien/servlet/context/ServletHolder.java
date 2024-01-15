package com.kien.servlet.context;

import java.util.*;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;

import jakarta.servlet.*;
import jakarta.servlet.ServletRegistration.Dynamic;

public class ServletHolder implements Dynamic {
    
    private final Servlet servlet;
    private final String servletName;
    private final ServletContext context;
    private final Map<String, String> initParams = new HashMap<>();
    
    public ServletHolder(Servlet servlet, String servletName, ServletContext context) {
        this.servlet = servlet;
        this.servletName = servletName;
        this.context = context;
    }

    Servlet getServlet() {
        return servlet;
    }
    
    void initialize() throws ServletException {
        servlet.init(new ServletConfig() {
            
            @Override
            public String getServletName() {
                return servletName;
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
    public Set<String> addMapping(String... urlPatterns) {
        return Set.of("/");
    }
    
    @Override
    public Collection<String> getMappings() {
        return List.of("/");
    }
    
    @Override
    public String getRunAsRole() {
        throw new UnsupportedOperationException("Unimplemented method 'getRunAsRole'");
    }
    
    @Override
    public String getName() {
        return servletName;
    }
    
    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Unimplemented method 'getClassName'");
    }
    
    @Override
    public boolean setInitParameter(String name, String value) {
        if (initParams.get(name) != null){
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
        context.log("Servlet %s: Async is not supported!".formatted(servletName));
    }
    
    @Override
    public void setLoadOnStartup(int loadOnStartup) {
        context.log("Always load on startup!, value %s is ignored".formatted(loadOnStartup));
    }
    
    @Override
    public Set<String> setServletSecurity(ServletSecurityElement constraint) {
        throw new UnsupportedOperationException("Unimplemented method 'setServletSecurity'");
    }
    
    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfig) {
        context.log(
            "Servlet config ignored: " + servletName +
                "\n For MultipartConfigElement: [location: %s, maxFileSizeThreshold %s, maxFileSize:  %s, maxRequestSize: %s]"
                    .formatted(
                        multipartConfig.getLocation(),
                        multipartConfig.getFileSizeThreshold(),
                        multipartConfig.getMaxFileSize(),
                        multipartConfig.getMaxRequestSize()));
    }
    
    @Override
    public void setRunAsRole(String roleName) {
        throw new UnsupportedOperationException("Unimplemented method 'setRunAsRole'");
    }
    
}
