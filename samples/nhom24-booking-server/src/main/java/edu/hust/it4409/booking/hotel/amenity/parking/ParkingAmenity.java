package edu.hust.it4409.booking.hotel.amenity.parking;

import com.fasterxml.jackson.annotation.JsonView;

import edu.hust.it4409.booking.hotel.amenity.IndexableAmenity;
import edu.hust.it4409.booking.hotel.amenity.RecognizableAmenity;
import edu.hust.it4409.common.jackson.JacksonViews;

public interface ParkingAmenity extends RecognizableAmenity, IndexableAmenity {
    
    @JsonView(JacksonViews.Full.class)
    boolean hasSelfParking();
    
    @JsonView(JacksonViews.Full.class)
    boolean hasValetParking();
    
    @JsonView(JacksonViews.Full.class)
    boolean hasLongTermParking();
    
    @JsonView(JacksonViews.Full.class)
    boolean hasOnSiteParking();
    
}
