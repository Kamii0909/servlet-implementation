package edu.hust.it4409.data;

import org.hibernate.type.FormatMapper;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * FormatMapper that delegates the type control to Jackson instead of relying on
 * Hibernate data type (the strategy from Hibernate provided
 * {@link org.hibernate.type.jackson.JacksonJsonFormatMapper})
 */
@RequiredArgsConstructor
public class JacksonControlledFormatMapper implements FormatMapper {
    
    private final ObjectMapper objectMapper;
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T fromString(CharSequence charSequence, JavaType<T> javaType, WrapperOptions wrapperOptions) {
        if (javaType.getJavaType() == String.class) {
            // Unchecked cast here, javaType is String already
            return (T) charSequence.toString();
        }
        try {
            return objectMapper.readValue(charSequence.toString(), objectMapper.constructType(javaType.getJavaType()));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not deserialize string to java type: " + javaType, e);
        }
    }
    
    @Override
    public <T> String toString(T value, JavaType<T> javaType, WrapperOptions wrapperOptions) {
        if (javaType.getJavaType() == String.class) {
            return (String) value;
        }
        try {
            // Difference with Hibernate JacksonJsonFormatMapper is here, we provides to
            // type information to Jackson
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not serialize object of java type: " + javaType, e);
        }
    }
    
}
