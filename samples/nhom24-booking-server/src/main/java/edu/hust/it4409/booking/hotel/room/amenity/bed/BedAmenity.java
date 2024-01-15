package edu.hust.it4409.booking.hotel.room.amenity.bed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.collect.ImmutableList;

import edu.hust.it4409.booking.hotel.amenity.RecognizableAmenity;
import edu.hust.it4409.booking.hotel.room.RoomFeature;

public interface BedAmenity extends RoomFeature, RecognizableAmenity {
    /**
     * Amount of core (unchanged) bed in a room.
     */
    @JsonProperty("beds")
    ImmutableList<BedAmount> allBeds();

    @JsonProperty("recognizableName")
    String recognizableName();
    
    /**
     * Preferred amount of adults that can share sleep accomodation.
     */
    @JsonProperty("numberOfAdults")
    default int numberOfAdults() {
        return allBeds()
            .stream()
            .mapToInt(BedAmount::numberOfAdults)
            .sum();
    }
    
    @JsonUnwrapped
    ExtraBedPolicy extraBedPolicy();
    
    @Override
    @JsonIgnore
    default String getDescription() {
        return "Number of beds, bed sizes, number of preferred adults";
    }
}
