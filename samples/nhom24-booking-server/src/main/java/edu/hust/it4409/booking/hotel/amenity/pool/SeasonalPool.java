package edu.hust.it4409.booking.hotel.amenity.pool;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class SeasonalPool {
    private LocalDate openFrom;
    private LocalDate openTo;
}
