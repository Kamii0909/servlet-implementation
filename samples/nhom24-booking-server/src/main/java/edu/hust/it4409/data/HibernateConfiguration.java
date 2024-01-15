package edu.hust.it4409.data;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hust.it4409.common.jackson.HibernateObjectMapper;
import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;

@EnableJpaRepositories(
    basePackages = "edu.hust.it4409",
    repositoryBaseClass = BaseJpaRepositoryImpl.class)
@ImportAutoConfiguration(HibernateJpaAutoConfiguration.class)
@EntityScan(basePackages = "edu.hust.it4409")
class HibernateConfiguration {
    
    @Bean
    HibernatePropertiesCustomizer hibernatePropertiesCustomizer(@HibernateObjectMapper ObjectMapper mapper) {
        return prop -> prop.put(AvailableSettings.JSON_FORMAT_MAPPER, new JacksonControlledFormatMapper(mapper));
    }

    
    
}
