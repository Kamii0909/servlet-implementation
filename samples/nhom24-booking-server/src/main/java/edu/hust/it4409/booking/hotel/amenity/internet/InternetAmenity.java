package edu.hust.it4409.booking.hotel.amenity.internet;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;

import edu.hust.it4409.booking.hotel.amenity.IndexableAmenity;
import edu.hust.it4409.booking.hotel.amenity.RecognizableAmenity;
import edu.hust.it4409.booking.hotel.amenity.SurchargeableAmenity;
import edu.hust.it4409.common.jackson.JacksonViews;

public interface InternetAmenity extends IndexableAmenity, SurchargeableAmenity, RecognizableAmenity {
    public enum RestrictionPeriod {
        PER_STAY,
        PER_DAY;
    }
    
    public interface WifiRestriction {
    }
    
    public record TimeRestriction(int amount, TimeUnit timeUnit, RestrictionPeriod restrictionPeriod)
        implements WifiRestriction {
        public TimeRestriction {
            Preconditions.checkArgument(timeUnit != TimeUnit.HOURS || timeUnit != TimeUnit.MINUTES,
                "Can't restrict by anything but Minutes and Hours");
            if (timeUnit == TimeUnit.HOURS) {
                Preconditions.checkArgument(amount > 0 && amount < 24,
                    "Hour length %s is not in valid (0,24) range", amount);
            } else {
                Preconditions.checkArgument(amount > 0 && amount < 1440,
                    "Minute length %s is not in valid (0,1440) range", amount);
            }
        }
    }
    
    public record WifiConnection(boolean isProvided, boolean isFree) implements IndexableAmenity, SurchargeableAmenity {
    }
    
    @JsonView(JacksonViews.Full.class)
    WifiConnection wifiInGuestRooms();
    
    @JsonView(JacksonViews.Full.class)
    WifiConnection wifiInPublicAreas();
    
    @JsonView(JacksonViews.Full.class)
    default boolean ethernetInGuestRooms() {
        return false;
    }
    
    @JsonView(JacksonViews.Full.class)
    default boolean ethernetInPublicAreas() {
        return false;
    }
    
    @JsonView(JacksonViews.Full.class)
    default boolean dialupInGuestRooms() {
        return false;
    }
    
    @JsonView(JacksonViews.Full.class)
    default boolean dialupInPublicAreas() {
        return false;
    }
}
