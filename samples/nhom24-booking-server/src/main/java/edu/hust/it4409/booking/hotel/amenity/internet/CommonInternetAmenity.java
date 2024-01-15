package edu.hust.it4409.booking.hotel.amenity.internet;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.hust.it4409.common.util.EnumNameUtils;

public enum CommonInternetAmenity implements InternetAmenity {
    FREE_ALL(true, true, true, true);
    
    private static final Map<String, CommonInternetAmenity> COMMON_NAME_MAP;
    
    static {
        COMMON_NAME_MAP = Stream.of(CommonInternetAmenity.values())
            .collect(Collectors.toMap(
                CommonInternetAmenity::recognizableName,
                Function.identity()));
    }
    
    public String toJson() {
        return "\"" + name() + "\"";
    }
    
    public static CommonInternetAmenity fromRecognizableName(String name) {
        return COMMON_NAME_MAP.get(name);
    }
    
    private final WifiConnection guestRoom;
    private final WifiConnection publicArea;
    private final String formattedName;
    
    private CommonInternetAmenity(
        boolean inGuestRoom,
        boolean isGuestFree,
        boolean inPublicArea,
        boolean isPublicAreaFree) {
        this.guestRoom = new WifiConnection(inGuestRoom, isGuestFree);
        this.publicArea = new WifiConnection(inPublicArea, isPublicAreaFree);
        this.formattedName = EnumNameUtils.formatName(name());
    }
    
    @Override
    public WifiConnection wifiInGuestRooms() {
        return guestRoom;
    }
    
    @Override
    public WifiConnection wifiInPublicAreas() {
        return publicArea;
    }
    
    @Override
    public boolean isProvided() {
        return guestRoom.isProvided() || publicArea.isProvided();
    }
    
    @Override
    public boolean isFree() {
        return guestRoom.isFree() && publicArea.isFree();
    }
    
    @Override
    public String recognizableName() {
        return formattedName;
    }
}
