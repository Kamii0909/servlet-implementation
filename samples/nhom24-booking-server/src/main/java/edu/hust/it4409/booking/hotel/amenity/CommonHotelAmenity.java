package edu.hust.it4409.booking.hotel.amenity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonView;

import edu.hust.it4409.booking.hotel.amenity.breakfast.BreakfastAmenity;
import edu.hust.it4409.booking.hotel.amenity.internet.InternetAmenity;
import edu.hust.it4409.booking.hotel.amenity.parking.ParkingAmenity;
import edu.hust.it4409.booking.hotel.amenity.pool.PoolAmenity;
import edu.hust.it4409.booking.hotel.amenity.service.GuestService;
import edu.hust.it4409.common.jackson.JacksonViews;
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
public class CommonHotelAmenity {
    @JdbcTypeCode(SqlTypes.JSON)
    @JsonView(JacksonViews.Short.class)
    private InternetAmenity internetAmenity;
    @JdbcTypeCode(SqlTypes.JSON)
    private ParkingAmenity parkingAmenity;
    @JdbcTypeCode(SqlTypes.JSON)
    private BreakfastAmenity breakfastAmenity;
    @JdbcTypeCode(SqlTypes.JSON)
    private PoolAmenity poolAmenity;
    @JdbcTypeCode(SqlTypes.JSON)
    private GuestService guestService;
}
