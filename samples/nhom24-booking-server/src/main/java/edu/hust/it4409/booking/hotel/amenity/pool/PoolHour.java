package edu.hust.it4409.booking.hotel.amenity.pool;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoolHour {
    public static final PoolHour ALL_HOUR = new PoolHour(LocalTime.of(0, 0), LocalTime.of(23, 59));

    private LocalTime openFrom;
    private LocalTime openTo;

    public boolean isAvailableAt(LocalTime time) {
        return !time.isBefore(openFrom) && !time.isAfter(openTo);
    }
}
