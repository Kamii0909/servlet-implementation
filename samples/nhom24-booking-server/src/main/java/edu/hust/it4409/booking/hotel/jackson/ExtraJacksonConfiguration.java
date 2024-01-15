package edu.hust.it4409.booking.hotel.jackson;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.hust.it4409.booking.hotel.amenity.internet.CommonInternetAmenity;
import edu.hust.it4409.booking.hotel.amenity.internet.InternetAmenity;
import edu.hust.it4409.booking.hotel.amenity.parking.CommonParkingAmenity;
import edu.hust.it4409.booking.hotel.amenity.parking.ParkingAmenity;
import edu.hust.it4409.booking.hotel.room.amenity.bed.BedAmenity;
import edu.hust.it4409.booking.hotel.room.amenity.bed.BedType;
import edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedAmenity;
import edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedType;
import edu.hust.it4409.common.jackson.HibernateObjectMapper;
import edu.hust.it4409.common.jackson.WebObjectMapper;

@Configuration
@Import(WebMixinsConfiguration.class)
public class ExtraJacksonConfiguration {
    @Bean
    @WebObjectMapper
    @HibernateObjectMapper
    Jackson2ObjectMapperBuilderCustomizer deserializerCustomizer() {
        return builder -> builder
            .deserializerByType(InternetAmenity.class, new InternetAmenityDeserializer())
            .deserializerByType(BedType.class, new BedTypeDeserializer())
            .deserializerByType(BedAmenity.class, new BedAmenityDeserializer())
            .deserializerByType(ParkingAmenity.class, new ParkingAmenityDeserializer());
    }
    
    /**
     * In the database, to save storage, we will serialize common
     * RecognizableAmenity Enum as
     * 
     * <pre>
     * &#123;
     * "recognizableName": "FORMATTED ENUM NAME",
     * // If Enum is also IndexableAmenity
     * "isProvided": true/false
     * &#125;
     * </pre>
     */
    @Bean
    @HibernateObjectMapper
    Jackson2ObjectMapperBuilderCustomizer hibernateSerializerCustomizer() {
        return builder -> builder
            .serializerByType(
                CommonParkingAmenity.class,
                new RecognizableAmenityEnumSerializer<>(CommonInternetAmenity.class))
            .serializerByType(
                CommonBedType.class,
                new RecognizableAmenityEnumSerializer<>(CommonBedType.class))
            .serializerByType(
                CommonBedAmenity.class,
                new RecognizableAmenityEnumSerializer<>(CommonBedAmenity.class))
            .serializerByType(
                CommonInternetAmenity.class,
                new RecognizableAmenityEnumSerializer<>(CommonInternetAmenity.class));
    }
}
