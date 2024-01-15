package edu.hust.it4409.booking.hotel.amenity.breakfast;

import java.time.LocalTime;

import edu.hust.it4409.common.model.interfaces.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BreakfastTime implements ValueObject {
    public static final String DAILY_TIME_UNIT = "Daily";
    public static final String WEEKDAYS_TIME_UNIT = "Weekdays";
    public static final String WEEKENDS_TIME_UNIT = "Weekend";
    private String timeUnit;
    private LocalTime start;
    private LocalTime end;
}
