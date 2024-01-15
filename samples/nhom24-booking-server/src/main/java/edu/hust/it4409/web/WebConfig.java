package edu.hust.it4409.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kien.spring.autoconf.CustomServletWebServerFactoryAutoConfiguration;

import edu.hust.it4409.booking.service.HotelMetadataService;
import edu.hust.it4409.common.jackson.WebObjectMapper;
import edu.hust.it4409.web.booking.BookingController;
import edu.hust.it4409.web.security.SecurityConfiguration;

@ImportAutoConfiguration({
    ServletWebServerFactoryAutoConfiguration.class,
    DispatcherServletAutoConfiguration.class, 
    CustomServletWebServerFactoryAutoConfiguration.class 
})
@Import({
    DelegatingWebMvcConfiguration.class,
    SecurityConfiguration.class })
@Configuration
public class WebConfig {
    @Bean
    BookingController bookingController(HotelMetadataService hotelMetadataService) {
        return new BookingController(hotelMetadataService);
    }
    
    // TODO : Hax ALERT
    @Bean("$$jsonHackCommandLineRunner")
    CommandLineRunner jsonHack(
        RequestMappingHandlerAdapter adapter,
        @WebObjectMapper ObjectMapper mapper) {
        return args -> adapter.getMessageConverters().stream()
            .filter(messageConverter -> messageConverter instanceof MappingJackson2HttpMessageConverter mjhmc)
            .forEach(ms -> ((MappingJackson2HttpMessageConverter) ms).setObjectMapper(mapper));
        
    }
    
    @Bean
    WebMvcConfigurer staticResourceConfigurere() {
        return new WebMvcConfigurer() {
            private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
                "classpath:/META-INF/resources/", "classpath:/resources/",
                "classpath:/static/", "classpath:/public/" };
            
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                    .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
            }
        };
    }
}
