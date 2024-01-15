package edu.hust.it4409.booking.hotel.jackson;

import com.fasterxml.jackson.databind.JsonNode;

import edu.hust.it4409.booking.hotel.room.amenity.bed.BedType;
import edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedType;
import edu.hust.it4409.booking.hotel.room.amenity.bed.OtherBedType;

public class BedTypeDeserializer extends RecognizableAmenityDeserializer<BedType> {
    
    public BedTypeDeserializer() {
        super(BedType.class);
    }
    
    @Override
    protected BedType deserializeFromRecognizableName(String recognizableName, JsonNode root) {
        BedType result = CommonBedType.fromRecognizableName(recognizableName);
        if (result != null) {
            return result;
        }
        // Performs no explicit checks
        return new OtherBedType(
            Integer.parseInt(root.get("numberOfAdults").asText()),
            recognizableName,
            root.get("description") == null ? null : root.get("description").asText(),
            root.get("width") == null ? -1 : root.get("width").asDouble(),
            root.get("height") == null ? -1 : root.get("height").asDouble());
    }
    
}
