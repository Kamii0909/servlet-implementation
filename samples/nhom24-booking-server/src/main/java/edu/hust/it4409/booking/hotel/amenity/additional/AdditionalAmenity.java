package edu.hust.it4409.booking.hotel.amenity.additional;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({ @JsonCreator }))
public class AdditionalAmenity {
    @JsonValue
    private List<String> others;
}
