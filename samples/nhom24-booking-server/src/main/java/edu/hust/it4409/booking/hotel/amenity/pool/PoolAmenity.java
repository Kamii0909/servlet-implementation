package edu.hust.it4409.booking.hotel.amenity.pool;

import java.util.List;

import edu.hust.it4409.booking.hotel.amenity.IndexableAmenity;
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
public class PoolAmenity implements IndexableAmenity {
    private int outdoorPool;
    private int indoorPool;
    private List<SeasonalPool> seasonalPools;
    private PoolHour poolHour;
    private NearbyPool nearbyPool;
    private UncommonPoolAmenity others;
    
    @Override
    public boolean isProvided() {
        return indoorPool > 0 || !seasonalPools.isEmpty() || outdoorPool > 0;
    }
}
