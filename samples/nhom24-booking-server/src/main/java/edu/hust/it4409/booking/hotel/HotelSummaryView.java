package edu.hust.it4409.booking.hotel;

import java.util.List;

import javax.money.MonetaryAmount;

import org.hibernate.annotations.*;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Immutable
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class HotelSummaryView {
    @Id
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private Long hotelId;
    private String name;
    private String description;
    @Type(value = ListArrayType.class)
    private List<String> images;
    @AttributeOverride(name = "amount", column = @Column(name = "minimal_cost"))
    private MonetaryAmount minimalCost;
    @Basic(fetch = FetchType.LAZY)
    private int star;
}
