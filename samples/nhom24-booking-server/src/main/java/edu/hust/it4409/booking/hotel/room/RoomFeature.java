package edu.hust.it4409.booking.hotel.room;

import edu.hust.it4409.common.model.interfaces.ValueObject;

public interface RoomFeature extends ValueObject {
    /**
     * A sensible description about the feature
     */
    String getDescription();
}
