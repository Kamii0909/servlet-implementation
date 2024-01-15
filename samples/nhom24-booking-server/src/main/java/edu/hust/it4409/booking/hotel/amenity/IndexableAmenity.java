package edu.hust.it4409.booking.hotel.amenity;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.hust.it4409.common.model.interfaces.ValueObject;

/**
 * For an IndexableAmenity, {@link #isProvided()} is guaranteed to always
 * persisted and indexed in the database. This allows efficient filtering of
 * multiple amenities.
 */
public interface IndexableAmenity extends ValueObject {
    /**
     * Used for quick indexing from end-user
     * 
     * @return whether this amenity is provided by the hotel
     */
    @JsonProperty("isProvided")
    boolean isProvided();
}
