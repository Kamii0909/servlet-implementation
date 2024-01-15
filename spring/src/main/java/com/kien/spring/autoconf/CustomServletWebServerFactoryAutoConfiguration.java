package com.kien.spring.autoconf;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import com.kien.servlet.context.MinimalServletContext;

@AutoConfiguration(after = ServletWebServerFactoryAutoConfiguration.class)
@ConditionalOnClass({ MinimalServletContext.class })
@ConditionalOnMissingBean(value = ServletWebServerFactory.class, search = SearchStrategy.CURRENT)
public class CustomServletWebServerFactoryAutoConfiguration {
    
    @Bean
    ServletWebServerFactory minimalServletWebServerFactory() {
        return new AbstractServletWebServerFactory() {
            @Override
            public WebServer getWebServer(ServletContextInitializer... initializers) {
                return new SpringScopedWebServer(getAddress(), getPort(), initializers);
            };
        };
    }
    
}
