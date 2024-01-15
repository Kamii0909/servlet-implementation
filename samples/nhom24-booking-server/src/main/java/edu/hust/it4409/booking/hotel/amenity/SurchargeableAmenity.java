package edu.hust.it4409.booking.hotel.amenity;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.hust.it4409.common.model.interfaces.ValueObject;

/**
 * Marker interface for amenities that can be extra charged.
 */
public interface SurchargeableAmenity extends ValueObject {
    @JsonProperty("isFree")
    boolean isFree();
}
