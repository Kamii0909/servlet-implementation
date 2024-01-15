package edu.hust.it4409.booking.hotel.amenity.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.hust.it4409.common.jackson.AbstractJacksonTest;
import edu.hust.it4409.common.jackson.JacksonViews;

class ParkingAmenityTest extends AbstractJacksonTest {
    @Test
    void testSerialization() throws JsonProcessingException {
        String json = hibernateMapper.writerWithView(JacksonViews.Short.class)
            .writeValueAsString(CommonParkingAmenity.LONG_TERM_ONSITE_AVAILABLE_SELF_PARKING);
        System.out.println(json);
        
        ParkingAmenity parkingAmenity = hibernateMapper.readerWithView(JacksonViews.Short.class)
            .forType(ParkingAmenity.class)
            .readValue(json);
        assertEquals(CommonParkingAmenity.LONG_TERM_ONSITE_AVAILABLE_SELF_PARKING, parkingAmenity);
    }
}
