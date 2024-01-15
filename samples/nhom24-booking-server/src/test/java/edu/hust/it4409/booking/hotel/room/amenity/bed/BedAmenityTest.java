package edu.hust.it4409.booking.hotel.room.amenity.bed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.hust.it4409.common.jackson.AbstractJacksonTest;
import edu.hust.it4409.common.jackson.JacksonViews;

class BedAmenityTest extends AbstractJacksonTest {
    
    @Test
    void testShortViewSerializationForEnum() throws JsonProcessingException {
        String json = hibernateMapper
            .writerWithView(JacksonViews.Short.class)
            .writeValueAsString(CommonBedAmenity.EXTRA_ONE_KING_BED);
        
        System.out.println(json);
        BedAmenity bedAmenity = hibernateMapper.readerWithView(JacksonViews.Short.class)
            .forType(BedAmenity.class)
            .readValue(json);
        
        assertEquals(CommonBedAmenity.EXTRA_ONE_KING_BED, bedAmenity);
    }
    
    @Test
    void testOtherBedTypeSerialization() throws JsonProcessingException {
        BedType bedType = new OtherBedType(2, "teSt", null, -1, -1);
        String json = hibernateMapper.writerFor(BedType.class)
            .withView(JacksonViews.Full.class)
            .writeValueAsString(bedType);
        System.out.println(json);
        
        BedType bedType2 = hibernateMapper.readerFor(BedType.class).readValue(json);
        assertEquals(bedType, bedType2);
    }
}
