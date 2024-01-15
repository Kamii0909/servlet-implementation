package edu.hust.it4409.booking.hotel;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class HotelAmenityView extends HotelSummaryView {
    @Basic(fetch = FetchType.LAZY)
    private boolean internet;
    private boolean parking;
    private boolean breakfast;
    private boolean pool;
}
