package edu.hust.it4409.booking.hotel.room.amenity.bed;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.hust.it4409.common.util.EnumNameUtils;

@JsonSerialize(as = BedType.class)
public enum CommonBedType implements BedType {
    // Taken from
    // https://setupmyhotel.com/train-my-hotel-staff/hk/288-bed-sizes.html
    // See more: https://sleepopolis.com/guides/bed-size-dimensions/
    CRIB(0, "A caged-like tiny bed for babies", 71.12, 132.08),
    ROLLAWAY(1, "A single-person size extra bed that is fold-able (and portable)", 99.06, 190.5),
    MODERN_COT(1, "A single-person size cot (counts like extra bed)", 76, 188),
    TWIN_BED(1, "A standard bed that is comfortable for 1 adult", 99.06, 193.04),
    FULL(2, "A standard bed that fits 2 adults", 137.16, 193.04),
    QUEEN(2, "A standard bed that is comfortable for 2 adults", 152.04, 203.2),
    OLYMPIC_QUEEN(2, "A slightly bigger size queen bed", 168, 203.2),
    KING(2, "A standard bed that is comfortable for 2 adults and a children", 198.12, 203.2),
    CALIFORNIA_KING(2, "Slightly longer king size bed", 193, 213.36);
    
    private static final Map<String, CommonBedType> COMMON_NAME_MAP;
    
    static {
        COMMON_NAME_MAP = Stream
            .of(CommonBedType.values())
            .collect(Collectors.toMap(
                BedType::recognizableName,
                Function.identity()));
    }
    
    public static CommonBedType fromRecognizableName(String name) {
        return COMMON_NAME_MAP.get(name);
    }
    
    private CommonBedType(int numberOfAdults, String description, double width, double height) {
        this.numberOfAdults = numberOfAdults;
        this.description = description;
        this.width = width;
        this.height = height;
        this.formattedName = EnumNameUtils.formatName(name());
    }
    
    private final int numberOfAdults;
    private final String description;
    private final double width;
    private final double height;
    private final String formattedName;
    
    @Override
    public int numberOfAdults() {
        return numberOfAdults;
    }
    
    @Override
    public String recognizableName() {
        return formattedName;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public double getWidth() {
        return width;
    }
    
    @Override
    public double getHeight() {
        return height;
    }
}
