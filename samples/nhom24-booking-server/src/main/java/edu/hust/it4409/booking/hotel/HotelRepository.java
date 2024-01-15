package edu.hust.it4409.booking.hotel;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

public interface HotelRepository extends BaseJpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
    @Query(nativeQuery = true, value = """
        SELECT * FROM hotel
            """)
    List<Hotel> findAll();
    
    @Query("""
        SELECT h FROM Hotel h
            WHERE h.starRating IN (:starRating)
            AND h in (
                SELECT r2.hotel FROM HotelRoom r2
                WHERE r2.costPerNight.amount BETWEEN :moneyFrom AND :moneyTo
                )
        """)
    List<Hotel> filterByRoom(@Param("starRating") List<Integer> starRatings,
        @Param("moneyFrom") BigDecimal moneyFrom,
        @Param("moneyTo") BigDecimal moneyTo,
        Pageable pageable);
}
