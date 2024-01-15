package edu.hust.it4409.booking.hotel.jackson;

import com.fasterxml.jackson.databind.JsonNode;

import edu.hust.it4409.booking.hotel.amenity.internet.CommonInternetAmenity;
import edu.hust.it4409.booking.hotel.amenity.internet.InternetAmenity;

public class InternetAmenityDeserializer extends RecognizableAmenityDeserializer<InternetAmenity> {
    public InternetAmenityDeserializer() {
        super(InternetAmenity.class);
    }
    
    @Override
    protected InternetAmenity deserializeFromRecognizableName(String recognizableName, JsonNode rootNode) {
        InternetAmenity result = CommonInternetAmenity.fromRecognizableName(recognizableName);
        if (result != null) {
            return result;
        }
        throw new UnsupportedOperationException("Can only work with CommonInternetAmenity");
    }
    
}
