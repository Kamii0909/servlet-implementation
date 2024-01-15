package edu.hust.it4409.booking.hotel;

import java.util.List;

import javax.money.MonetaryAmount;

import org.hibernate.annotations.CompositeTypeRegistration;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.hust.it4409.booking.hotel.amenity.HotelAmenity;
import edu.hust.it4409.booking.hotel.room.HotelRoom;
import edu.hust.it4409.common.model.skeleton.AbstractAggregateRoot;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "hotel")
@CompositeTypeRegistration(userType = MonetaryAmountType.class, embeddableClass = MonetaryAmount.class)
public class Hotel extends AbstractAggregateRoot {
    private String name;
    private String description;
    private int starRating;
    @Transient
    private double reviewRanking;
    @Type(value = ListArrayType.class)
    private List<String> images;
    private String location;
    private HotelAmenity hotelAmenity;
    @OneToMany(
        mappedBy = "hotel", 
        fetch = FetchType.EAGER,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JsonManagedReference
    private List<HotelRoom> rooms;
    
    public Hotel(String name,
        String description,
        int starRating,
        List<String> images,
        String location,
        HotelAmenity hotelAmenity) {
        this.name = name;
        this.description = description;
        this.starRating = starRating;
        this.images = images;
        this.location = location;
        this.hotelAmenity = hotelAmenity;
    }
    
}
