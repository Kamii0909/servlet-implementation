package edu.hust.it4409.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.hust.it4409.booking.hotel.HotelRepository;
import edu.hust.it4409.booking.hotel.HotelSummaryRepository;
import edu.hust.it4409.booking.service.HotelMetadataService;
import edu.hust.it4409.booking.spi.ReviewRankingProvider;

@Configuration
public class BookingConfig {
    @Bean
    HotelMetadataService hotelMetadataService(HotelRepository hotelRepository,
        ReviewRankingProvider reviewRankingProvider,
        HotelSummaryRepository hotelSummaryRepository) {
        return new HotelMetadataServiceImpl(hotelRepository, reviewRankingProvider, hotelSummaryRepository);
    }
    
    @Bean
    ReviewRankingProvider reviewRankingProvider() {
        return new RandomReviewRankingProvider();
    }
    
}
