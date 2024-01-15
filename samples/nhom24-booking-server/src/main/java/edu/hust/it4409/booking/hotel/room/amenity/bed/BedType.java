package edu.hust.it4409.booking.hotel.room.amenity.bed;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import edu.hust.it4409.booking.hotel.amenity.RecognizableAmenity;
import edu.hust.it4409.common.jackson.JacksonViews;

public interface BedType extends RecognizableAmenity {
    /**
     * How many people/adults can sleep on that. Not the maximum, but the prefered
     * amount.
     * 
     * @return the preferred amount of people for a sleep.
     */
    @JsonProperty("numberOfAdults")
    @JsonView(JacksonViews.Full.class)
    int numberOfAdults();
    
    /**
     * @return The commonly known name of this bed type (used to serialize to users)
     */
    @JsonProperty("recognizableName")
    @JsonView(JacksonViews.Short.class)
    String recognizableName();
    
    /**
     * @return A short description about the bed size.
     */
    @JsonProperty("description")
    @JsonView(JacksonViews.Full.class)
    String getDescription();
    
    /**
     * @return the width (in centimeters). -1 if information not available.
     */
    @JsonView(JacksonViews.Full.class)
    double getWidth();
    
    /**
     * @return the height (in centimeters). -1 if information not available.
     */
    @JsonView(JacksonViews.Full.class)
    double getHeight();
    
    /**
     * An optional path to an image of the bed size.
     * 
     * @apiNote by default, this implementation returns empty (no image).
     */
    @JsonView(JacksonViews.Full.class)
    default Optional<String> image() {
        return Optional.empty();
    }
}
