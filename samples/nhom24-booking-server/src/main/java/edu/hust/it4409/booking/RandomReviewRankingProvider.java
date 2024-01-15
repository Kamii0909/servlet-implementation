package edu.hust.it4409.booking;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.room.HotelRoom;
import edu.hust.it4409.booking.spi.ReviewRankingProvider;

public class RandomReviewRankingProvider implements ReviewRankingProvider {
    private static final Logger log = LoggerFactory.getLogger(RandomReviewRankingProvider.class);
    
    @Override
    public double getReviewRanking(Hotel hotel) {
        if (hotel.getReviewRanking() != 0) {
            double ranking = ThreadLocalRandom.current().nextDouble(5, 10);
            log.info("Randomizing review for hotel {} with result {}", hotel.getId(), ranking);
            hotel.setReviewRanking(ranking);
        }
        return hotel.getReviewRanking();
    }
    
    @Override
    public double getReviewRanking(HotelRoom hotelRoom) {
        return hotelRoom.getReviewRanking() != 0 ? hotelRoom.getReviewRanking() :
            ThreadLocalRandom.current().nextDouble(5, 10);
    }
    
}
