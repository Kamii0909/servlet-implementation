package edu.hust.it4409.booking.hotel.amenity.breakfast;

import edu.hust.it4409.common.model.interfaces.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Embeddable
public class BreakfastAvailability implements ValueObject {
    /**
     * Is it provided for free or surcharged
     */
    private BreakfastCharge charge;
    /**
     * Example: buffet/cooked-to-order/Vietnamese food/Western food
     * 
     * @implNote String for maximum flexibility
     */
    private String type;
    /**
     * Time range breakfast is provided
     */
    private BreakfastTime time;
    
    public boolean isFree() {
        return charge.isFree();
    }
}
