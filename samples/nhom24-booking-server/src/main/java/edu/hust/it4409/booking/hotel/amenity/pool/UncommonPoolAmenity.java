package edu.hust.it4409.booking.hotel.amenity.pool;

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
public class UncommonPoolAmenity {
    public static final UncommonPoolAmenity NO_EXTRA_AMENITY = new UncommonPoolAmenity(
        false, false, false, false,
        false, false, false);
    private boolean hasSteamRoom;
    private boolean hasSauna;
    private boolean hasFenceAround;
    private boolean hasOnsiteLifeguard;
    private boolean hasPoolUmbrellas;
    private boolean hasPoolSunLoungers;
    private boolean hasPoolToys;
    
}
