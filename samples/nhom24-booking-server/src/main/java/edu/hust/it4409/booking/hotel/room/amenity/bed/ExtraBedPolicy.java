package edu.hust.it4409.booking.hotel.room.amenity.bed;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface ExtraBedPolicy {
    /**
     * Probe for the availability of the set up.
     */
    @JsonIgnore
    boolean isAllowed(List<BedAmount> beds);
    
    /**
     * A description of this policy (to be serialized for end-user)
     */
    @JsonProperty("extraBedPolicy")
    String getDescription();
}
