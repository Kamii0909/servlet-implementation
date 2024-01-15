package edu.hust.it4409.booking.hotel.room.amenity.bed;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import edu.hust.it4409.common.model.interfaces.ValueObject;

/**
 * A single instance of this class tell the amount of X type bed in a room. 
 */
public interface BedAmount extends ValueObject {
    @JsonUnwrapped
    BedType getBedType();
    
    @JsonProperty("numberOfBed")
    int numberOfBeds();
    
    default int numberOfAdults() {
        return numberOfBeds() * getBedType().numberOfAdults();
    }
}
