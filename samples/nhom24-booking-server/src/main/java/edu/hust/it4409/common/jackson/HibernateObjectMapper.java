package edu.hust.it4409.common.jackson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Qualifier for anything related to the ObjectMapper used by Hibernate.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Qualifier("hibernateObjectMapper")
public @interface HibernateObjectMapper {
    
}
