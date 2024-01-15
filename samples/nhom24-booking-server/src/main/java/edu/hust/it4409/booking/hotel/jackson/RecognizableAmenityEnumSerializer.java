package edu.hust.it4409.booking.hotel.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.hust.it4409.booking.hotel.amenity.IndexableAmenity;
import edu.hust.it4409.booking.hotel.amenity.RecognizableAmenity;

public class RecognizableAmenityEnumSerializer<T extends Enum<T> & RecognizableAmenity> extends StdSerializer<T> {
    
    RecognizableAmenityEnumSerializer(Class<T> t) {
        super(t);
    }
    
    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("recognizableName", value.recognizableName());
        if (value instanceof IndexableAmenity ia) {
            gen.writeBooleanField("isProvided", ia.isProvided());
        }
        gen.writeEndObject();
    }
    
}
