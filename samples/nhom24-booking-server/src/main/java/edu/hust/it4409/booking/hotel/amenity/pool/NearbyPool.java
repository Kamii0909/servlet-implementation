package edu.hust.it4409.booking.hotel.amenity.pool;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class NearbyPool {
    private boolean hasNearbyIndoorPool;
    private boolean hasNearbyOutdoorPool;
}
