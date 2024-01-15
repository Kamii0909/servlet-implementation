package edu.hust.it4409.booking.hotel.amenity.breakfast;

import java.util.List;

import edu.hust.it4409.booking.hotel.amenity.IndexableAmenity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Encapsulate information about breakfast amenity provided to every visitors in
 * the hotel.
 * 
 * @param availability empty if breakfast is not provided as a hotel amenity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreakfastAmenity implements IndexableAmenity {
    private List<BreakfastAvailability> availability;
    
    public boolean isProvided() {
        return availability != null && !availability.isEmpty();
    }
}
