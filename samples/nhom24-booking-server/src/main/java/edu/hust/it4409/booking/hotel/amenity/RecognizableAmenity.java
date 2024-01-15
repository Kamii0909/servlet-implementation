package edu.hust.it4409.booking.hotel.amenity;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.hust.it4409.common.model.interfaces.ValueObject;

/**
 * Amenity that has a clear textual definition that can easily summarize the
 * amenity. 
 */
public interface RecognizableAmenity extends ValueObject {
    @JsonProperty("recognizableName")
    String recognizableName();
}
