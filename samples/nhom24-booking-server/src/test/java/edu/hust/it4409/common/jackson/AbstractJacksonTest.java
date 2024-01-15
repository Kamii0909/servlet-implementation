package edu.hust.it4409.common.jackson;

import static org.mockito.Mockito.mock;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.hust.it4409.booking.hotel.jackson.ExtraJacksonConfiguration;
import edu.hust.it4409.common.jackson.AbstractJacksonTest.ImportJackson;

@ExtendWith(SpringExtension.class)
@Import(ImportJackson.class)
public abstract class AbstractJacksonTest {
    
    @TestConfiguration
    @Import({JacksonConfiguration.class, ExtraJacksonConfiguration.class})
    static class ImportJackson {
        @Bean
        @ConditionalOnMissingBean
        SessionFactory mockSessionFactory(){
            return mock(SessionFactory.class);
        }
        @Bean
        @HibernateObjectMapper
        @WebObjectMapper
        Jackson2ObjectMapperBuilderCustomizer customizer() {
            return builder -> builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT);
        }
    }
    
    @Autowired
    @HibernateObjectMapper
    protected ObjectMapper hibernateMapper;

    @Autowired
    @WebObjectMapper
    protected ObjectMapper webMapper;
}
