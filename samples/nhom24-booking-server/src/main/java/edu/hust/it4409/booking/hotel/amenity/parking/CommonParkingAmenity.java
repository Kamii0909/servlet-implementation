package edu.hust.it4409.booking.hotel.amenity.parking;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.hust.it4409.common.util.EnumNameUtils;

public enum CommonParkingAmenity implements ParkingAmenity {
    FLEXIBLE_PARKING_OPTIONS(true, true, true, true),
    LONG_TERM_ONSITE_AVAILABLE_SELF_PARKING(true, false, true, true),
    VALET_PARKING(false, true, false, false),
    NO_PARKING_AVAILABLE(false, false, false, false);
    
    private static final Map<String, CommonParkingAmenity> COMMON_NAME_MAP;
    
    static {
        COMMON_NAME_MAP = Stream.of(CommonParkingAmenity.values())
            .collect(Collectors.toMap(
                CommonParkingAmenity::recognizableName,
                Function.identity()));
    }
    
    public static CommonParkingAmenity fromRecognizableName(String value) {
        return COMMON_NAME_MAP.get(value);
    }
    
    private final boolean hasSelfParking;
    private final boolean hasValetParking;
    private final boolean hasLongTermParking;
    private final boolean hasOnSiteParking;
    private final String formattedName;
    
    private CommonParkingAmenity(boolean hasSelfParking,
        boolean hasValetParking,
        boolean hasLongTermParking,
        boolean hasOnSiteParking) {
        this.hasSelfParking = hasSelfParking;
        this.hasValetParking = hasValetParking;
        this.hasLongTermParking = hasLongTermParking;
        this.hasOnSiteParking = hasOnSiteParking;
        this.formattedName = EnumNameUtils.formatName(name());
    }
    
    @Override
    public boolean hasSelfParking() {
        return hasSelfParking;
    }
    
    @Override
    public boolean hasValetParking() {
        return hasValetParking;
    }
    
    @Override
    public boolean hasLongTermParking() {
        return hasLongTermParking;
    }
    
    @Override
    public boolean hasOnSiteParking() {
        return hasOnSiteParking;
    }
    
    @Override
    public String recognizableName() {
        return formattedName;
    }
    
    @Override
    public boolean isProvided() {
        return hasSelfParking || hasValetParking;
    }
    
}
