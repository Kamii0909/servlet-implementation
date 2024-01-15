package edu.hust.it4409.common.jackson;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.zalando.jackson.datatype.money.MoneyModule;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.hust.it4409.booking.hotel.jackson.ExtraJacksonConfiguration;

@Configuration
@Import(ExtraJacksonConfiguration.class)
public class JacksonConfiguration {
    
    // TODO due to bugs in Spring, we cannot use
    // builder.modules(Consumer<List<Module>>)
    
    @Configuration
    static class Hibernate {
        @Bean({ "hibernateObjectMapper", "jpaObjectMapper" })
        @HibernateObjectMapper
        ObjectMapper hibernateObjectMapper(
            ApplicationContext context,
            @HibernateObjectMapper List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
            return createObjectMapper(context, customizers);
        }
        
        @Bean
        @HibernateObjectMapper
        Jackson2ObjectMapperBuilderCustomizer jacksonHibernateModulesCustomizer(
            @HibernateObjectMapper List<Module> extraHibernateModules) {
            return builder -> builder.modules(extraHibernateModules);
        }
    }
    
    @Configuration
    static class Web {
        @Bean("webObjectMapper")
        @WebObjectMapper
        ObjectMapper webObjectMapper(
            ApplicationContext context,
            @WebObjectMapper List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
            return createObjectMapper(context, customizers);
        }
        
        @Bean
        @WebObjectMapper
        Jackson2ObjectMapperBuilderCustomizer jacksonWebModulesCustomizer(
            SessionFactory sessionFactory,
            @WebObjectMapper List<Module> webModules) {
            return builder -> builder.modules(webModules);
        }
        
        @Bean
        @WebObjectMapper
        Module jacksonWebHibernateModule(SessionFactory sessionFactory) {
            return new Hibernate6Module(sessionFactory);
        }
    }
    
    private static ObjectMapper createObjectMapper(
        ApplicationContext context,
        List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.applicationContext(context);
        for (Jackson2ObjectMapperBuilderCustomizer customizer : customizers) {
            customizer.customize(builder);
        }
        return builder.build();
    }
    
    @Bean("defaultCustomizer")
    @HibernateObjectMapper
    @WebObjectMapper
    Jackson2ObjectMapperBuilderCustomizer defaultCustomizer() {
        return builder -> builder
            .featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS)
            .featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }
    
    @Bean
    @WebObjectMapper
    @HibernateObjectMapper
    Module jacksonJavaTimeModule() {
        return new JavaTimeModule();
    }
    
    @Bean
    @WebObjectMapper
    @HibernateObjectMapper
    Module jacksonJdk8Module() {
        return new Jdk8Module();
    }
    
    @Bean
    @WebObjectMapper
    @HibernateObjectMapper
    Module jacksonMoneyModule() {
        return new MoneyModule();
    }
    
}
