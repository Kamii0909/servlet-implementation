package edu.hust.it4409.booking.hotel.room.amenity.bed;

import static com.google.common.collect.ImmutableList.of;
import static edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedAmount.*;
import static edu.hust.it4409.booking.hotel.room.amenity.bed.CommonExtraBedPolicyImpl.maxHeadCount;
import static edu.hust.it4409.booking.hotel.room.amenity.bed.CommonExtraBedPolicyImpl.notProvided;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;

import edu.hust.it4409.common.util.EnumNameUtils;

@JsonSerialize(as = BedAmenity.class)
public enum CommonBedAmenity implements BedAmenity {
    TWIN_BEDDED(of(DOUBLE_TWIN), notProvided()),
    TRIPLE_TWIN_BEDDED(of(TRIPLE_TWIN), notProvided()),
    ONE_QUEEN_ONE_TWIN_BED(of(SINGLE_QUEEN, SINGLE_TWIN), notProvided()),
    ONE_QUEEN_BED(of(SINGLE_QUEEN), notProvided()),
    ONE_KING_BED(of(SINGLE_KING), notProvided()),
    EXTRA_TWIN_BEDDED(of(DOUBLE_TWIN), maxHeadCount(1, 1)),
    EXTRA_ONE_QUEEN_BED(of(SINGLE_QUEEN), maxHeadCount(1, 1)),
    EXTRA_ONE_KING_BED(of(SINGLE_KING), maxHeadCount(1, 1));
    
    private static final Map<String, CommonBedAmenity> COMMON_NAME_MAP;
    
    static {
        COMMON_NAME_MAP = Stream.of(CommonBedAmenity.values())
            .collect(Collectors.toMap(
                CommonBedAmenity::recognizableName, 
                Function.identity()));
    }
    
    public static CommonBedAmenity fromRecognizableName(String name) {
        return COMMON_NAME_MAP.get(name);
    }
    
    private final ImmutableList<BedAmount> bedUnits;
    private final ExtraBedPolicy extraBedPolicy;
    private final String formattedName;
    
    private CommonBedAmenity(ImmutableList<BedAmount> bedUnits, ExtraBedPolicy extraBedPolicy) {
        this.bedUnits = bedUnits;
        this.extraBedPolicy = extraBedPolicy;
        this.formattedName = EnumNameUtils.formatName(name());
    }
    
    @Override
    public ImmutableList<BedAmount> allBeds() {
        return bedUnits;
    }
    
    @Override
    public String recognizableName() {
        return formattedName;
    }
    
    @Override
    public ExtraBedPolicy extraBedPolicy() {
        return extraBedPolicy;
    }
    
}
