package edu.hust.it4409.booking;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import edu.hust.it4409.booking.hotel.*;
import edu.hust.it4409.booking.service.HotelMetadataService;
import edu.hust.it4409.booking.spi.ReviewRankingProvider;
import edu.hust.it4409.web.booking.AmenityKey;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HotelMetadataServiceImpl implements HotelMetadataService {
    private final HotelRepository hotelRepository;
    private final ReviewRankingProvider rankingProvider;
    private final HotelSummaryRepository hotelSummaryRepository;
    
    @Override
    public List<Hotel> getHotels(Pageable pageable) {
        return hotelRepository.findAll((Specification<Hotel>) null, pageable).toList();
    }
    
    @Override
    public List<HotelSummaryView> getHotelSummary(Integer minPrice,
        Integer maxPrice,
        List<Integer> stars,
        List<AmenityKey> amenities,
        Pageable pageable) {
        return hotelSummaryRepository
            .findAll(
                createSpecification(minPrice, maxPrice, stars, amenities),
                pageable)
            .toList();
    }
    
    @Override
    public Optional<Hotel> getFullHotelPage(Long id) {
        return hotelRepository.findById(id);
    }
    
    @Override
    public List<HotelSummaryView> getHotelSummary(Integer minPrice,
        Integer maxPrice,
        List<Integer> stars,
        List<AmenityKey> amenities) {
        return hotelSummaryRepository
            .findAll(createSpecification(minPrice, maxPrice, stars, amenities));
    }
    
    @Override
    public Hotel addNewHotel(Hotel hotel) {
        return hotelRepository.persist(hotel);
    }
    
    private Specification<HotelSummaryView> createSpecification(Integer minPrice,
        Integer maxPrice,
        List<Integer> stars,
        List<AmenityKey> amenities) {
        return Specification.allOf(
            minPriceSpecification(minPrice),
            maxPriceSpecification(maxPrice),
            starSpecification(stars),
            amenitySpecification(amenities));
        
    }
    
    private Specification<HotelSummaryView> minPriceSpecification(Integer minPrice) {
        return (root, cq, cb) -> minPrice == null ? //
            null : //
            cb.greaterThanOrEqualTo(root.get("minimalCost").get("amount"), minPrice);
    }
    
    private Specification<HotelSummaryView> maxPriceSpecification(Integer maxPrice) {
        return (root, cq, cb) -> maxPrice == null ? //
            null : //
            cb.lessThanOrEqualTo(root.get("minimalCost").get("amount"), maxPrice);
    }
    
    private Specification<HotelSummaryView> starSpecification(List<Integer> stars) {
        if (stars == null || stars.isEmpty()) {
            return null;
        }
        return (root, cq, cb) -> root.get("star").in(stars);
    }
    
    private Specification<HotelSummaryView> amenitySpecification(List<AmenityKey> amenity) {
        if (amenity == null || amenity.isEmpty()) {
            return null;
        }
        return Specification.allOf(amenity.stream().map(this::amenitySpecification).toList());
    }

    private Specification<HotelSummaryView> amenitySpecification(AmenityKey key) {
        return (root, cq, cb) -> cb.isTrue(cb
            .treat(root, HotelAmenityView.class) // HotelAmenity.internet == true
            .get(key.getEntityFieldName()));
    }
    
}
