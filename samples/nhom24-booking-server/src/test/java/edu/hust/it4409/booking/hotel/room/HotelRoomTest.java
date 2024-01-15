package edu.hust.it4409.booking.hotel.room;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hust.it4409.ServerConfiguration;
import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.HotelRepository;
import edu.hust.it4409.common.jackson.WebObjectMapper;

@ActiveProfiles({"pg", "dev"})
@Import(ServerConfiguration.class)
@ExtendWith(SpringExtension.class)
class HotelRoomTest {
    
    @Autowired
    HotelRepository hotelRepository;
    
    @Autowired
    @WebObjectMapper
    ObjectMapper webMapper;
    
    @Test
    void testContextLoad() {
        Hotel hotel = hotelRepository.findAll().get(0);
        assertNotNull(hotel);
    }
    
    @Test
    void testSerialization() throws JsonProcessingException {
        String json = webMapper.writeValueAsString(hotelRepository.findAll().get(0));
        System.out.println(json);
    }
}
