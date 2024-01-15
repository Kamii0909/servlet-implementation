package edu.hust.it4409.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.HotelSummaryView;
import edu.hust.it4409.web.booking.AmenityKey;

public interface HotelMetadataService {

    Hotel addNewHotel(Hotel hotel);
    
    List<Hotel> getHotels(Pageable pageable);
    
    List<HotelSummaryView> getHotelSummary(
        Integer minPrice,
        Integer maxPrice,
        List<Integer> stars,
        List<AmenityKey> amenities,
        Pageable pageable);
    
    List<HotelSummaryView> getHotelSummary(
        Integer minPrice,
        Integer maxPrice,
        List<Integer> stars,
        List<AmenityKey> amenities);
    
    Optional<Hotel> getFullHotelPage(Long id);
    
}
