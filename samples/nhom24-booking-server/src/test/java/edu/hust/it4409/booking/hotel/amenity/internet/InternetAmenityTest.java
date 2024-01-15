package edu.hust.it4409.booking.hotel.amenity.internet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.hust.it4409.common.jackson.AbstractJacksonTest;
import edu.hust.it4409.common.jackson.JacksonViews;

class InternetAmenityTest extends AbstractJacksonTest {
    
    @Test
    void testSerializeToDatabase() throws JsonProcessingException {
        String json = hibernateMapper
            .writeValueAsString(CommonInternetAmenity.FREE_ALL);
        System.out.println(json);
        InternetAmenity internetAmenity =
            hibernateMapper.readerFor(InternetAmenity.class)
                .readValue(json);
        assertEquals(CommonInternetAmenity.FREE_ALL, internetAmenity);
    }
    
    @Test
    void testSerializeToWeb() throws JsonProcessingException {
        String json = webMapper.writerWithView(JacksonViews.Short.class)
            .writeValueAsString(CommonInternetAmenity.FREE_ALL);
        System.out.println(json);
        
        InternetAmenity internetAmenity = webMapper.readerWithView(JacksonViews.Short.class)
            .forType(InternetAmenity.class)
            .readValue(json);
        
        assertEquals(CommonInternetAmenity.FREE_ALL, internetAmenity);
    }
}
