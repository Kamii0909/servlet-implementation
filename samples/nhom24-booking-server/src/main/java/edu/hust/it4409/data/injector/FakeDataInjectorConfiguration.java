package edu.hust.it4409.data.injector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import edu.hust.it4409.booking.hotel.HotelRepository;

@Profile({"dev & fakeData"})
@Configuration
public class FakeDataInjectorConfiguration {
    
    @Bean
    HotelInjector databaseInjector(HotelRepository hotelRepository) {
        return new HotelInjector(hotelRepository);
    }
    
}
