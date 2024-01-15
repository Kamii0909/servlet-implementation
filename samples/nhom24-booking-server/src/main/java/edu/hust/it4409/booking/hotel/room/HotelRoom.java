package edu.hust.it4409.booking.hotel.room;

import java.util.Comparator;

import javax.money.MonetaryAmount;

import org.hibernate.annotations.CompositeTypeRegistration;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.room.amenity.bed.BedAmenity;
import edu.hust.it4409.common.model.skeleton.AbstractLocalEntity;
import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "room")
@CompositeTypeRegistration(embeddableClass = MonetaryAmount.class, userType = MonetaryAmountType.class)
public class HotelRoom extends AbstractLocalEntity<Hotel> {
    public static final Comparator<HotelRoom> COMPARATOR =
        (o1, o2) -> o1.getCostPerNight().compareTo(o2.getCostPerNight());
    @Enumerated(EnumType.STRING)
    private RoomType type;
    @Enumerated(EnumType.STRING)
    private RoomTier tier;
    private String roomName;
    @Enumerated(EnumType.STRING)
    private RoomView view;
    @Transient
    private double reviewRanking;
    @JdbcTypeCode(SqlTypes.JSON)
    private BedAmenity bed;
    private String size;
    private MonetaryAmount costPerNight;
    @ManyToOne(optional = false)
    @JsonBackReference
    private Hotel hotel;
    
    @Override
    @JsonIgnore
    protected void setRoot(Hotel hotel) {
        this.hotel = hotel;
    }
    
    @Override
    @JsonIgnore
    public Hotel getAggregateRoot() {
        return hotel;
    }
}
