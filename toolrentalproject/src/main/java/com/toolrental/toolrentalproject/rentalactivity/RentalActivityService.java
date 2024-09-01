package com.toolrental.toolrentalproject.rentalactivity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolrental.toolrentalproject.exceptions.InvalidDiscountPercentException;
import com.toolrental.toolrentalproject.exceptions.InvalidRentalDaysException;
import com.toolrental.toolrentalproject.tool.Tool;
import com.toolrental.toolrentalproject.tool.ToolRepository;

@Service
public class RentalActivityService {
    @Autowired
    private RentalActivityRepository rentalActivityRepository;

    @Autowired
    private ToolRepository toolRepository;

    public static LocalDate getObservedHolidayDate(LocalDate holiday) {
        DayOfWeek day = holiday.getDayOfWeek();
        boolean isSaturday = day.equals(DayOfWeek.SATURDAY);
        boolean isSunday = day.equals(DayOfWeek.SUNDAY);

        if (!(isSaturday || isSunday)) {
            return holiday;
        }

        return isSaturday ? holiday.minusDays(1) : holiday.plusDays(1);
    }

    public static LocalDate getJuly4thHoliday(int thisYear) {
        MonthDay july4 = MonthDay.of(Month.JULY, 4);

        return getObservedHolidayDate(july4.atYear(thisYear));
    }

    public static LocalDate getLaborDayHoliday(int thisYear) {
        LocalDate laborDay = LocalDate.now().withYear(thisYear)
                .withMonth(Month.SEPTEMBER.getValue())
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

        return getObservedHolidayDate(laborDay);
    }

    public static List<LocalDate> getAllHolidays(int year) {
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(getJuly4thHoliday(year));
        holidays.add(getLaborDayHoliday(year));

        return holidays;
    }

    public long countHolidaysWithinRangeInclusive(LocalDate checkoutDate, LocalDate dueDate) {
        return getAllHolidays(checkoutDate.getYear())
                .stream()
                .filter(holiday -> holiday.isAfter(checkoutDate)
                        && (holiday.isBefore(dueDate)))
                .count();
    }

    public boolean removeWeekendDates(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        return !(day.equals(DayOfWeek.SATURDAY)
                || day.equals(DayOfWeek.SUNDAY));
    }

    public long getNumberOfChargeDays(Tool tool, LocalDate checkoutDate, LocalDate dueDate) {
        var rate = tool.getToolType().getRate();
        var hasWeekdayCharge = rate.getHasWeekdayCharge();
        var hasWeekendCharge = rate.getHasWeekendCharge();
        var hasHolidayCharge = rate.getHasHolidayCharge();
        boolean everyDayIsCharged = hasWeekdayCharge && hasWeekendCharge && hasHolidayCharge;

        if (everyDayIsCharged) {
            return Period.between(checkoutDate, dueDate).getDays();
        }

        long numberOfHolidaysWithinRange = countHolidaysWithinRangeInclusive(checkoutDate, dueDate);

        long chargeDays = checkoutDate.datesUntil(dueDate)
                // if tool has weekend charges, chargeDays is not affected
                // if no weekend charges remove weekends from charge days
                .filter(date -> hasWeekendCharge || removeWeekendDates(date))
                .count();

        return chargeDays - numberOfHolidaysWithinRange;
    }

    public BigDecimal roundUpFractionPart(BigDecimal number) {
        MathContext mc = new MathContext(2);

        BigDecimal[] splitNumber = number.divideAndRemainder(BigDecimal.ONE);
        return splitNumber[0].add(splitNumber[1].round(mc));
    }

    public BigDecimal getPreDiscountCharge(long chargeDays, double dailyRate) {
        BigDecimal preDiscountCharge = BigDecimal.valueOf(chargeDays * dailyRate);
        return roundUpFractionPart(preDiscountCharge);
    }

    public BigDecimal getDiscountAmount(long discountPercent, BigDecimal preDiscountCharge) {
        BigDecimal discountPercentDecimal = BigDecimal.valueOf((double) discountPercent / 100);
        BigDecimal discountAmount = preDiscountCharge.multiply(discountPercentDecimal);

        return roundUpFractionPart(discountAmount);
    }

    public RentalAgreementFormat displayAgreementToUser(RentalActivity rentalActivity) {
        RentalAgreementFormat rentalAgreementFormat = new RentalAgreementFormat();
        rentalAgreementFormat.format(rentalActivity);

        System.out.println(rentalAgreementFormat.toString());
        return rentalAgreementFormat;
    }

    public RentalAgreementFormat checkout(ToolRentalRequest toolRentalRequest) {
        Tool tool = toolRepository.findByToolCode(toolRentalRequest.getToolCode()).get();
        long rentalDays = toolRentalRequest.getRentalDays();
        long discountPercent = toolRentalRequest.getDiscountPercent();
        LocalDate checkoutDate = toolRentalRequest.getCheckoutDate();

        // validate rentalDays
        if (rentalDays < 1) {
            throw new InvalidRentalDaysException(String.valueOf(rentalDays));
        }

        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidDiscountPercentException(String.valueOf(discountPercent));
        }

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        double dailyRate = tool.getToolType().getRate().getDailyCharge();
        long chargeDays = getNumberOfChargeDays(tool, checkoutDate, dueDate);

        BigDecimal preDiscountCharge = getPreDiscountCharge(chargeDays, dailyRate);
        BigDecimal discountAmount = getDiscountAmount(discountPercent, preDiscountCharge);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        RentalActivity rentalActivity = new RentalActivity();

        rentalActivity.setTool(tool);
        rentalActivity.setRentalDays(rentalDays);
        rentalActivity.setCheckoutDate(checkoutDate);
        rentalActivity.setDueDate(dueDate);
        rentalActivity.setChargeDays(chargeDays);
        rentalActivity.setPreDiscountCharge(preDiscountCharge.doubleValue());
        rentalActivity.setDiscountPercent(discountPercent);
        rentalActivity.setDiscountAmount(discountAmount.doubleValue());
        rentalActivity.setFinalCharge(finalCharge.doubleValue());

        rentalActivityRepository.save(rentalActivity);

        return displayAgreementToUser(rentalActivity);
    }
}
