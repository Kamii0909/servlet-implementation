package edu.hust.it4409.web.booking;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.HotelSummaryView;
import edu.hust.it4409.booking.service.HotelMetadataService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequiredArgsConstructor
@RestController
public class BookingController {
    
    private final HotelMetadataService hotelMetadataService;
    
    @GetMapping(value = "/hotel")
    List<Hotel> getHotelPaged(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {
        return hotelMetadataService.getHotels(PageRequest.of(page, pageSize));
    }
    
    @ResponseBody
    @GetMapping(value = "/hotel/filter")
    List<HotelSummaryView> filterHotels(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize,
        @RequestParam(name = "minPrice", required = false) Integer minPrice,
        @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
        @RequestParam(name = "star", required = false) List<Integer> stars,
        @RequestParam(name = "amenity", required = false) List<String> amenities) {
        List<AmenityKey> parsedAmenity = amenities == null ? null : amenities.stream()
            .map(AmenityKey::get)
            .filter(x -> x == null)
            .toList();
        
        return hotelMetadataService
            .getHotelSummary(minPrice, maxPrice, stars, parsedAmenity, PageRequest.of(page, pageSize));
    }
    
    @GetMapping("/hotel/{id}")
    Optional<Hotel> getHotel(@PathVariable("id") Long id) {
        return hotelMetadataService.getFullHotelPage(id);
    }
    
    @PostMapping(value = "/hotel/new")
    Hotel addNewHotel(@RequestBody Hotel hotel) {
        return hotelMetadataService.addNewHotel(hotel);
    }
}
