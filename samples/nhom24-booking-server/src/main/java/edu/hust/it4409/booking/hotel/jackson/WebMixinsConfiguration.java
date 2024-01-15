package edu.hust.it4409.booking.hotel.jackson;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;

import edu.hust.it4409.booking.hotel.amenity.internet.CommonInternetAmenity;
import edu.hust.it4409.booking.hotel.amenity.internet.InternetAmenity;
import edu.hust.it4409.booking.hotel.amenity.parking.CommonParkingAmenity;
import edu.hust.it4409.booking.hotel.amenity.parking.ParkingAmenity;
import edu.hust.it4409.booking.hotel.room.amenity.bed.BedAmenity;
import edu.hust.it4409.booking.hotel.room.amenity.bed.BedType;
import edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedAmenity;
import edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedType;
import edu.hust.it4409.common.jackson.WebObjectMapper;

class WebMixinsConfiguration {
    
    @JsonSerialize(as = BedType.class)
    interface CommonBedTypeMixin {
    }
    
    @JsonSerialize(as = ParkingAmenity.class)
    interface CommonParkingAmenityMixIn {
    }
    
    @JsonSerialize(as = InternetAmenity.class)
    interface CommonInternetAmenityMixIn {
    }
    
    @JsonSerialize(as = BedAmenity.class)
    interface CommonBedAmenityMixIn {
    }
    
    @Bean
    @WebObjectMapper
    Module webMixinCustomizer() {
        SimpleModule module = new SimpleModule(
            "Mixin module for Web ObjectMapper",
            new Version(1, 0, 0,
                null, "edu.hust.it4409", "booking-server"));
        module.setMixInAnnotation(CommonBedType.class, CommonBedTypeMixin.class);
        module.setMixInAnnotation(CommonParkingAmenity.class, CommonParkingAmenityMixIn.class);
        module.setMixInAnnotation(CommonInternetAmenity.class, CommonInternetAmenityMixIn.class);
        module.setMixInAnnotation(CommonBedAmenity.class, CommonBedAmenityMixIn.class);
        return module;
    }
    
}
