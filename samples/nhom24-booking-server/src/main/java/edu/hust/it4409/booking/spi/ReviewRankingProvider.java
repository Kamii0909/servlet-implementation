package edu.hust.it4409.booking.spi;

import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.room.HotelRoom;

public interface ReviewRankingProvider {
    // Get a star rating for a hotel
    double getReviewRanking(Hotel hotel);

    // Get a star rating for a hotel room
    double getReviewRanking(HotelRoom hotelRoom);
}
