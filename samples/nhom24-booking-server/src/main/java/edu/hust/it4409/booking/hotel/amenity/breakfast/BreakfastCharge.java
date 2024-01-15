package edu.hust.it4409.booking.hotel.amenity.breakfast;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

import edu.hust.it4409.booking.hotel.amenity.Surcharged;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class BreakfastCharge implements Surcharged {
    public static final CurrencyUnit DEFAULT_CURRENCY = Monetary.getCurrency("VND");
    private static final MonetaryAmount FREE_MONETARY_AMOUNT = FastMoney.zero(DEFAULT_CURRENCY);
    public static final BreakfastCharge FREE = new BreakfastCharge(FREE_MONETARY_AMOUNT, FREE_MONETARY_AMOUNT);
    private MonetaryAmount perAdult;
    private MonetaryAmount perChild;
    
    @Override
    public boolean isFree() {
        return perAdult.isZero() && perChild.isZero();
    }
}
