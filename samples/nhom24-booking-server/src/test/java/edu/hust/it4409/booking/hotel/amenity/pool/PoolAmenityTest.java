package edu.hust.it4409.booking.hotel.amenity.pool;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

import edu.hust.it4409.common.jackson.AbstractJacksonTest;

class PoolAmenityTest extends AbstractJacksonTest {
    
    @Test
    void testDatabaseSerialization() throws JsonProcessingException {
        String json = hibernateMapper.writeValueAsString(PoolAmenity.builder()
            .seasonalPools(ImmutableList.of()).build());
        System.out.println(json);
    }
}
