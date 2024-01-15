package edu.hust.it4409.booking.hotel.amenity.convenience;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InRoomConvenience {
    private boolean hasLocalMap;
    private boolean hasSlippers;
    private boolean hasTurndownService;
    private boolean hasSoundproofRoom;
    private boolean hasInRoomSafe;
}
