package edu.hust.it4409.booking.hotel.amenity.service;

import javax.money.MonetaryAmount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Smoking {
    /**
     * <p>
     * negative -> not provided
     * <p>
     * 0 -> free smoking area
     * <p>
     * positive -> fined smoking area
     */
    private MonetaryAmount smokingFine;
    
    public boolean isProvided() {
        return smokingFine.isPositiveOrZero();
    }
    
    public boolean isFree() {
        return smokingFine.isZero();
    }
}
