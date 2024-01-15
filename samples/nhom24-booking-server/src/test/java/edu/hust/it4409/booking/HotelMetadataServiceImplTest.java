package edu.hust.it4409.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import edu.hust.it4409.ServerConfiguration;
import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.HotelSummaryView;
import edu.hust.it4409.booking.service.HotelMetadataService;
import edu.hust.it4409.web.booking.AmenityKey;

@SpringBootTest(
    classes = ServerConfiguration.class,
    properties = {
        "spring.jpa.properties.hibernate.format_sql=true",
        "logging.level.org.hibernate.orm.jdbc.bind=trace",
        "logging.level.org.hibernate.SQL=debug"
    })
@ActiveProfiles({ "dev", "pg", "test" })
class HotelMetadataServiceImplTest {
    
    @Autowired
    HotelMetadataService hotelMetadataService;
    
    @Test
    void testGetFullHotelPage() {
        Hotel hotel = hotelMetadataService
            .getFullHotelPage(12L)
            .get();
        assertNotNull(hotel);
    }
    
    @Test
    void testGetHotelSummary() {
        List<HotelSummaryView> hotelSummary = hotelMetadataService.getHotelSummary(0, null, List.of(3, 5),
            List.of(AmenityKey.BREAKFAST, AmenityKey.POOL), PageRequest.ofSize(3));
        assertEquals(3, hotelSummary.size());
    }
    
    @Test
    void testGetHotelSummary2() {
        
    }
    
    @Test
    void testGetHotels() {
        
    }
}
