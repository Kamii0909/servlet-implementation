package edu.hust.it4409.booking.hotel.room.amenity.bed;

import java.util.List;

import lombok.RequiredArgsConstructor;

public class CommonExtraBedPolicyImpl {
    private CommonExtraBedPolicyImpl() {
    }
    
    private static final ExtraBedPolicy NO_EXTRA_BED = new NoExtraBed();
    
    public static ExtraBedPolicy notProvided() {
        return NO_EXTRA_BED;
    }
    
    public static ExtraBedPolicy maxHeadCount(int adultMax, int childrenMax) {
        return new OccupancyBased(adultMax, childrenMax);
    }
    
    public static ExtraBedPolicy
        penaltySystem(int perCrib, int perRollaway, int perTwinbed, int maxPenalty, String description) {
        return new PenaltyBasedSingleBedOnly(perCrib, perRollaway, perTwinbed, maxPenalty, description);
    }
    
    /**
     * Extra bed policy that refuse all extra bed request.
     */
    public static class NoExtraBed implements ExtraBedPolicy {
        
        @Override
        public boolean isAllowed(List<BedAmount> beds) {
            return false;
        }
        
        @Override
        public String getDescription() {
            return "No extra beds allowed";
        }
        
    }
    
    @RequiredArgsConstructor
    public static class OccupancyBased implements ExtraBedPolicy {
        private final int maxNumberOfAdult;
        private final int maxNumberOfChildren;
        
        @Override
        public boolean isAllowed(List<BedAmount> beds) {
            return beds.stream()
                .filter(bed -> bed.getBedType().numberOfAdults() == 0)
                .mapToInt(BedAmount::numberOfBeds)
                .sum() <= maxNumberOfChildren
                && beds.stream()
                    .filter(bed -> bed.getBedType().numberOfAdults() != 0)
                    .mapToInt(BedAmount::numberOfAdults)
                    .sum() <= maxNumberOfAdult;
        }
        
        @Override
        public String getDescription() {
            if (maxNumberOfAdult > 0 && maxNumberOfChildren > 0) {
                return "This room has enough extra spaces for %d adults and %d children"
                    .formatted(maxNumberOfAdult, maxNumberOfChildren);
            } else if (maxNumberOfAdult > 0) {
                return "This room has enough extra spaces for %d adults".formatted(maxNumberOfAdult);
            } else if (maxNumberOfChildren > 0) {
                return "This room has enough extra spaces for %d children".formatted(maxNumberOfChildren);
            } else {
                return "This room cannot host more people";
            }
        }
        
    }
    
    /**
     * Allows extra cribs/rollaway/twinbed. Anything else is refused. Each bed type
     * is configurable with a different penalty. Refuse the extra bed request if the
     * total penalty is reached.
     */
    @RequiredArgsConstructor
    public static class PenaltyBasedSingleBedOnly implements ExtraBedPolicy {
        private final int penaltyPerCribs;
        private final int penaltyPerRollaway;
        private final int penaltyPerTwinbed;
        private final int maxPenalty;
        private final String description;
        
        @Override
        public boolean isAllowed(List<BedAmount> beds) {
            int penalty = 0;
            
            for (BedAmount bed : beds) {
                if (!(bed.getBedType() instanceof CommonBedType cbt)) {
                    return false;
                }
                switch (cbt) {
                case CRIB:
                    penalty += penaltyPerCribs * bed.numberOfBeds();
                    break;
                
                case ROLLAWAY:
                    penalty += penaltyPerRollaway * bed.numberOfBeds();
                    break;
                
                case TWIN_BED:
                    penalty += penaltyPerTwinbed * bed.numberOfBeds();
                    break;
                
                default:
                    return false;
                }
            }
            
            return penalty <= maxPenalty;
        }
        
        @Override
        public String getDescription() {
            return description;
        }
        
    }
    
}
