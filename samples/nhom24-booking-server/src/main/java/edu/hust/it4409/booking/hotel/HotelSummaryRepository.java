package edu.hust.it4409.booking.hotel;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

public interface HotelSummaryRepository
    extends BaseJpaRepository<HotelSummaryView, Long>, JpaSpecificationExecutor<HotelSummaryView> {
    
}
