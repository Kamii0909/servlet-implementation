package edu.hust.it4409.web.booking;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AmenityKey {
    INTERNET,
    POOL,
    BREAKFAST,
    PARKING;
    
    private static final Map<String, AmenityKey> ENUM_MAP;
    static {
        ENUM_MAP = Stream.of(AmenityKey.values())
            .collect(Collectors.toMap(AmenityKey::getEntityFieldName, Function.identity()));
    }
    
    public static AmenityKey get(String value) {
        return ENUM_MAP.get(value);
    }
    
    private final String entityFieldName;
    
    private AmenityKey() {
        this.entityFieldName = name().toLowerCase();
    }
    
    @SuppressWarnings("unused constructor")
    private AmenityKey(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }
    
    public String getEntityFieldName() {
        return entityFieldName;
    }
}
