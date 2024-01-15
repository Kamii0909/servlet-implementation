package edu.hust.it4409.booking.hotel.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import edu.hust.it4409.booking.hotel.amenity.RecognizableAmenity;

abstract class RecognizableAmenityDeserializer<T extends RecognizableAmenity> extends StdDeserializer<T> {
    
    protected RecognizableAmenityDeserializer(Class<T> clazz) {
        super(clazz);
    }
    
    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode root = p.readValueAsTree();
        JsonNode nameNode = root.get("recognizableName");
        if (nameNode == null || nameNode.isNull()) {
            throw new IllegalStateException("recognizableName not found on a RecognizableAmenity");
        }
        if (!nameNode.isTextual()) {
            throw new IllegalStateException("recognizableName should be a String");
        }
        return deserializeFromRecognizableName(nameNode.asText(), root);
    }
    
    protected abstract T deserializeFromRecognizableName(String recognizableName, JsonNode rootNode);
}
