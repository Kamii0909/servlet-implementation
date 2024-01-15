package edu.hust.it4409.booking.hotel.jackson;

import com.fasterxml.jackson.databind.JsonNode;

import edu.hust.it4409.booking.hotel.room.amenity.bed.BedAmenity;
import edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedAmenity;

public class BedAmenityDeserializer extends RecognizableAmenityDeserializer<BedAmenity> {
    
    public BedAmenityDeserializer() {
        super(BedAmenity.class);
    }
    
    @Override
    protected BedAmenity deserializeFromRecognizableName(String recognizableName, JsonNode rootNode) {
        BedAmenity result = CommonBedAmenity.fromRecognizableName(recognizableName);
        if (result != null) {
            return result;
        }
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }
    
}
