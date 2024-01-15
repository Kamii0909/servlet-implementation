package edu.hust.it4409.booking.hotel.jackson;

import com.fasterxml.jackson.databind.JsonNode;

import edu.hust.it4409.booking.hotel.amenity.parking.CommonParkingAmenity;
import edu.hust.it4409.booking.hotel.amenity.parking.ParkingAmenity;

public class ParkingAmenityDeserializer extends RecognizableAmenityDeserializer<ParkingAmenity> {
    
    public ParkingAmenityDeserializer() {
        super(ParkingAmenity.class);
    }
    
    @Override
    protected ParkingAmenity deserializeFromRecognizableName(String recognizableName, JsonNode rootNode) {
        ParkingAmenity result = CommonParkingAmenity.fromRecognizableName(recognizableName);
        if (result != null) {
            return result;
        }
        throw new UnsupportedOperationException("Can only works with CommonParkingAmenity");
    }
    
}
