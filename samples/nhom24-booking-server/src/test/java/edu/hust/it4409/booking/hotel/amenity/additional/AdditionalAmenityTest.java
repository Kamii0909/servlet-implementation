package edu.hust.it4409.booking.hotel.amenity.additional;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

import edu.hust.it4409.common.jackson.AbstractJacksonTest;

class AdditionalAmenityTest extends AbstractJacksonTest {
    
    @Test
    void serializeToDatabase() throws JsonProcessingException {
        String json = hibernateMapper.writeValueAsString(new AdditionalAmenity(ImmutableList.of()));
        System.out.println(json);
    }
}
