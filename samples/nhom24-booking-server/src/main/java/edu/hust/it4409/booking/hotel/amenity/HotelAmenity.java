package edu.hust.it4409.booking.hotel.amenity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import edu.hust.it4409.booking.hotel.amenity.additional.AdditionalAmenity;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Embeddable
public class HotelAmenity {
    @JsonUnwrapped
    private CommonHotelAmenity commonHotelAmenity;
    @JdbcTypeCode(SqlTypes.JSON)
    private AdditionalAmenity additionalAmenity;
}
