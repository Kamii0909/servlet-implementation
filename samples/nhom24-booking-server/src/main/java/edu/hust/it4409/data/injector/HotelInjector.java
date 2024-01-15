package edu.hust.it4409.data.injector;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.javamoney.moneta.Money;
import org.springframework.boot.CommandLineRunner;

import com.google.common.collect.ImmutableList;

import edu.hust.it4409.booking.hotel.Hotel;
import edu.hust.it4409.booking.hotel.HotelRepository;
import edu.hust.it4409.booking.hotel.amenity.CommonHotelAmenity;
import edu.hust.it4409.booking.hotel.amenity.HotelAmenity;
import edu.hust.it4409.booking.hotel.amenity.additional.AdditionalAmenity;
import edu.hust.it4409.booking.hotel.amenity.breakfast.BreakfastAmenity;
import edu.hust.it4409.booking.hotel.amenity.breakfast.BreakfastAvailability;
import edu.hust.it4409.booking.hotel.amenity.breakfast.BreakfastCharge;
import edu.hust.it4409.booking.hotel.amenity.breakfast.BreakfastTime;
import edu.hust.it4409.booking.hotel.amenity.internet.CommonInternetAmenity;
import edu.hust.it4409.booking.hotel.amenity.parking.CommonParkingAmenity;
import edu.hust.it4409.booking.hotel.amenity.pool.NearbyPool;
import edu.hust.it4409.booking.hotel.amenity.pool.PoolAmenity;
import edu.hust.it4409.booking.hotel.amenity.pool.PoolHour;
import edu.hust.it4409.booking.hotel.amenity.pool.UncommonPoolAmenity;
import edu.hust.it4409.booking.hotel.amenity.service.GuestService;
import edu.hust.it4409.booking.hotel.room.HotelRoom;
import edu.hust.it4409.booking.hotel.room.RoomTier;
import edu.hust.it4409.booking.hotel.room.RoomType;
import edu.hust.it4409.booking.hotel.room.RoomView;
import edu.hust.it4409.booking.hotel.room.amenity.bed.CommonBedAmenity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class HotelInjector implements CommandLineRunner {
    
    private static final List<String> BREAKFAST_TYPE = ImmutableList.of("buffet", "vietnamese", "cooked-to-order");
    private static final List<String> BREAKFAST_TIMEUNIT = ImmutableList.of(
        BreakfastTime.DAILY_TIME_UNIT,
        BreakfastTime.WEEKDAYS_TIME_UNIT,
        BreakfastTime.WEEKENDS_TIME_UNIT);
    private final HotelRepository hotelRepository;
    
    @Override
    public void run(String... args) throws Exception {
        IntStream.range(0, 5).forEachOrdered(i -> {
            Hotel hotel = randomizeNewHotel(i);
            hotel.setRooms(IntStream
                .range(0, ThreadLocalRandom.current().nextInt(1, 5))
                .boxed()
                .map(j -> randomizeNewRoom(hotel))
                .toList());
            hotelRepository.persist(hotel);
        });
    }
    
    private HotelRoom randomizeNewRoom(Hotel hotel) {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        return HotelRoom.builder()
            .type(RoomType.values()[randomizer.nextInt(RoomType.values().length)])
            .tier(RoomTier.values()[randomizer.nextInt(RoomTier.values().length)])
            .roomName("A randomly generated room")
            .view(RoomView.values()[randomizer.nextInt(RoomView.values().length)])
            .bed(CommonBedAmenity.ONE_QUEEN_BED)
            .size("20 sq ft")
            .costPerNight(Money.of(randomizer.nextInt(10_000_000), "VND"))
            .hotel(hotel)
            .build();
    }
    
    private Hotel randomizeNewHotel(int index) {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        return Hotel.builder()
            .name("A randomly generated Hotel" + index)
            .description("A randomly generated description")
            .starRating(randomizer.nextInt(3, 5))
            .images(List.of("Image URL for Hotel pic 1"))
            .location("Non existant location")
            .hotelAmenity(randomizeHotelAmenity())
            .build();
    }
    
    private HotelAmenity randomizeHotelAmenity() {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        return HotelAmenity.builder()
            .commonHotelAmenity(CommonHotelAmenity.builder()
                .breakfastAmenity(randomizeBreakfast())
                .internetAmenity(CommonInternetAmenity.FREE_ALL)
                .parkingAmenity(CommonParkingAmenity.values()[randomizer.nextInt(CommonParkingAmenity.values().length)])
                .poolAmenity(PoolAmenity.builder()
                    .indoorPool(randomizer.nextInt(3))
                    .outdoorPool(randomizer.nextInt(3))
                    .nearbyPool(new NearbyPool(randomizer.nextBoolean(), randomizer.nextBoolean()))
                    .seasonalPools(ImmutableList.of())
                    .poolHour(new PoolHour(
                        LocalTime.of(randomizer.nextInt(5, 7), 0),
                        LocalTime.of(randomizer.nextInt(16, 20), 0)))
                    .others(UncommonPoolAmenity.builder()
                        .hasFenceAround(randomizer.nextBoolean())
                        .hasOnsiteLifeguard(randomizer.nextBoolean())
                        .hasPoolSunLoungers(randomizer.nextBoolean())
                        .hasPoolToys(randomizer.nextBoolean())
                        .hasPoolUmbrellas(randomizer.nextBoolean())
                        .hasSauna(randomizer.nextBoolean())
                        .hasSteamRoom(randomizer.nextBoolean())
                        .build())
                    .build())
                .guestService(GuestService.builder()
                    .hasComputerStation(randomizer.nextBoolean())
                    .hasConciergeService(randomizer.nextBoolean())
                    .hasHairSalon(randomizer.nextBoolean())
                    .hasLaundryService(randomizer.nextBoolean())
                    .hasLuggageStorage(randomizer.nextBoolean())
                    .hasMultilingualStaff(randomizer.nextBoolean())
                    .hasPorterOrBellhop(randomizer.nextBoolean())
                    .hasWeddingService(randomizer.nextBoolean())
                    .build())
                .build())
            .additionalAmenity(new AdditionalAmenity(ImmutableList.of()))
            .build();
    }
    
    private BreakfastAmenity randomizeBreakfast() {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        return new BreakfastAmenity(IntStream
            .range(0, randomizer.nextInt(2))
            .boxed()
            .map(i -> randomizeBreakfastAvailability())
            .toList());
    }
    
    private BreakfastAvailability randomizeBreakfastAvailability() {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        return BreakfastAvailability.builder()
            .charge(BreakfastCharge.FREE)
            .type(BREAKFAST_TYPE.get(randomizer.nextInt(BREAKFAST_TYPE.size())))
            .time(BreakfastTime.builder()
                .timeUnit(BREAKFAST_TIMEUNIT.get(randomizer.nextInt(BREAKFAST_TIMEUNIT.size())))
                .start(LocalTime.of(5, 30))
                .end(LocalTime.of(10, 0))
                .build())
            .build();
    }
    
}
