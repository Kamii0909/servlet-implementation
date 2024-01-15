package com.kien.servlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.*;
import jakarta.servlet.ServletRegistration.Dynamic;
import jakarta.servlet.descriptor.JspConfigDescriptor;

public class ServletContextFacade implements ServletContext {
    private static final Logger log = LoggerFactory.getLogger(ServletContextFacade.class);
    
    @Override
    public String getContextPath() {
        throw new UnsupportedOperationException("Unimplemented method 'getContextPath'");
    }
    
    @Override
    public ServletContext getContext(String uripath) {
        log.debug("Servlet Context required for {}", uripath);
        return this;
    }
    
    @Override
    public int getMajorVersion() {
        return 0;
    }
    
    @Override
    public int getMinorVersion() {
        return 0;
    }
    
    @Override
    public int getEffectiveMajorVersion() {
        return 6;
    }
    
    @Override
    public int getEffectiveMinorVersion() {
        return 0;
    }
    
    @Override
    public String getMimeType(String file) {
        throw new UnsupportedOperationException("Unimplemented method 'getMimeType'");
    }
    
    @Override
    public Set<String> getResourcePaths(String path) {
        throw new UnsupportedOperationException("Unimplemented method 'getResourcePaths'");
    }
    
    @Override
    public URL getResource(String path) throws MalformedURLException {
        throw new UnsupportedOperationException("Unimplemented method 'getResource'");
    }
    
    @Override
    public InputStream getResourceAsStream(String path) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(path);
    }
    
    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'getRequestDispatcher'");
    }
    
    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getNamedDispatcher'");
    }
    
    @Override
    public void log(String msg) {
        log.info(msg);
    }
    
    @Override
    public void log(String message, Throwable throwable) {
        throwable.printStackTrace();
        System.out.println(message);
    }
    
    @Override
    public String getRealPath(String path) {
        throw new UnsupportedOperationException("Unimplemented method 'getRealPath'");
    }
    
    @Override
    public String getServerInfo() {
        return "Custom Ha Trung Kien";
    }
    
    @Override
    public String getServletContextName() {
        return "unknown";
    }
    
    @Override
    public Dynamic addServlet(String servletName, String className) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        throw new UnsupportedOperationException("Unimplemented method 'addServlet'");
    }
    
    @Override
    public Dynamic addJspFile(String servletName, String jspFile) {
        throw new UnsupportedOperationException("Unimplemented method 'addJspFile'");
    }
    
    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException {
        throw new UnsupportedOperationException("Unimplemented method 'createServlet'");
    }
    
    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        throw new UnsupportedOperationException("Unimplemented method 'getServletRegistration'");
    }
    
    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        throw new UnsupportedOperationException("Unimplemented method 'getServletRegistrations'");
    }
    
    @Override
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
        throw new UnsupportedOperationException("Unimplemented method 'addFilter'");
    }
    
    @Override
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName,
        Class<? extends Filter> filterClass) {
        throw new UnsupportedOperationException("Unimplemented method 'addFilter'");
    }
    
    @Override
    public <T extends Filter> T createFilter(Class<T> clazz) throws ServletException {
        throw new UnsupportedOperationException("Unimplemented method 'createFilter'");
    }
    
    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        throw new UnsupportedOperationException("Unimplemented method 'getFilterRegistration'");
    }
    
    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        throw new UnsupportedOperationException("Unimplemented method 'getFilterRegistrations'");
    }
    
    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        throw new UnsupportedOperationException("Unimplemented method 'getSessionCookieConfig'");
    }
    
    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        throw new UnsupportedOperationException("Unimplemented method 'setSessionTrackingModes'");
    }
    
    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        throw new UnsupportedOperationException("Unimplemented method 'getDefaultSessionTrackingModes'");
    }
    
    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        throw new UnsupportedOperationException("Unimplemented method 'getEffectiveSessionTrackingModes'");
    }
    
    @Override
    public void addListener(String className) {
        throw new UnsupportedOperationException("Unimplemented method 'addListener'");
    }
    
    @Override
    public <T extends EventListener> void addListener(T t) {
        throw new UnsupportedOperationException("Unimplemented method 'addListener'");
    }
    
    @Override
    public void addListener(Class<? extends EventListener> listenerClass) {
        throw new UnsupportedOperationException("Unimplemented method 'addListener'");
    }
    
    @Override
    public <T extends EventListener> T createListener(Class<T> clazz) throws ServletException {
        throw new UnsupportedOperationException("Unimplemented method 'createListener'");
    }
    
    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        throw new UnsupportedOperationException("Unimplemented method 'getJspConfigDescriptor'");
    }
    
    @Override
    public ClassLoader getClassLoader() {
        throw new UnsupportedOperationException("Unimplemented method 'getClassLoader'");
    }
    
    @Override
    public void declareRoles(String... roleNames) {
        throw new UnsupportedOperationException("Unimplemented method 'declareRoles'");
    }
    
    @Override
    public String getVirtualServerName() {
        throw new UnsupportedOperationException("Unimplemented method 'getVirtualServerName'");
    }
    
    @Override
    public int getSessionTimeout() {
        throw new UnsupportedOperationException("Unimplemented method 'getSessionTimeout'");
    }
    
    @Override
    public void setSessionTimeout(int sessionTimeout) {
        throw new UnsupportedOperationException("Unimplemented method 'setSessionTimeout'");
    }
    
    @Override
    public String getRequestCharacterEncoding() {
        throw new UnsupportedOperationException("Unimplemented method 'getRequestCharacterEncoding'");
    }
    
    @Override
    public void setRequestCharacterEncoding(String encoding) {
        throw new UnsupportedOperationException("Unimplemented method 'setRequestCharacterEncoding'");
    }
    
    @Override
    public String getResponseCharacterEncoding() {
        throw new UnsupportedOperationException("Unimplemented method 'getResponseCharacterEncoding'");
    }
    
    @Override
    public void setResponseCharacterEncoding(String encoding) {
        throw new UnsupportedOperationException("Unimplemented method 'setResponseCharacterEncoding'");
    }
    
    @Override
    public String getInitParameter(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getInitParameter'");
    }
    
    @Override
    public Enumeration<String> getInitParameterNames() {
        throw new UnsupportedOperationException("Unimplemented method 'getInitParameterNames'");
    }
    
    @Override
    public boolean setInitParameter(String name, String value) {
        throw new UnsupportedOperationException("Unimplemented method 'setInitParameter'");
    }
    
    @Override
    public Object getAttribute(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getAttribute'");
    }
    
    @Override
    public Enumeration<String> getAttributeNames() {
        throw new UnsupportedOperationException("Unimplemented method 'getAttributeNames'");
    }
    
    @Override
    public void setAttribute(String name, Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'setAttribute'");
    }
    
    @Override
    public void removeAttribute(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAttribute'");
    }
    
    @Override
    public Dynamic addServlet(String servletName, Servlet servlet) {
        throw new UnsupportedOperationException("Unimplemented method 'addServlet'");
    }
    
    @Override
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        throw new UnsupportedOperationException("Unimplemented method 'addFilter'");
    }
    
}
