package com.toolrental.toolrentalproject.rentalactivity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.toolrental.toolrentalproject.exceptions.InvalidRentalDaysException;
import com.toolrental.toolrentalproject.tool.Tool;
import com.toolrental.toolrentalproject.tool.ToolRepository;
import com.toolrental.toolrentalproject.brand.Brand;
import com.toolrental.toolrentalproject.tooltype.ToolType;
import com.toolrental.toolrentalproject.rate.Rate;

@DisplayName("Rental Activity Service")
@ExtendWith(MockitoExtension.class)
class RentalActivityServiceTest {
    @Mock
    private ToolRepository toolRepository;

    @Mock
    private RentalActivityRepository rentalActivityRepository;

    @InjectMocks
    private RentalActivityService rentalActivityService;

    private Tool mockTool;
    private ToolType mockToolType;
    private Rate mockToolRate;
    private Brand mockToolBrand;

    private ToolRentalRequest toolRentalRequest;

    private Tool getJackhammer(String toolCode) {
        mockToolRate.setDailyCharge(2.99);
        mockToolRate.setHasWeekdayCharge(true);
        mockToolRate.setHasWeekendCharge(false);
        mockToolRate.setHasHolidayCharge(false);

        mockToolType.setRate(mockToolRate);

        mockToolType.setName("Jackhammer");
        mockToolType.setPrefix("JAK");

        mockToolBrand.setName("Ridgid");
        mockToolBrand.setAbbreviation("R");
        mockTool.setBrand(mockToolBrand);

        mockTool.setToolType(mockToolType);
        mockTool.setToolCode(toolCode);

        return mockTool;
    }

    private Tool getChainsaw(String toolCode) {
        mockToolRate.setDailyCharge(1.49);
        mockToolRate.setHasWeekdayCharge(true);
        mockToolRate.setHasWeekendCharge(false);
        mockToolRate.setHasHolidayCharge(true);

        mockToolType.setRate(mockToolRate);

        mockToolType.setName("Chainsaw");
        mockToolType.setPrefix("CHN");

        mockToolBrand.setName("Stihl");
        mockToolBrand.setAbbreviation("S");
        mockTool.setBrand(mockToolBrand);

        mockTool.setToolType(mockToolType);
        mockTool.setToolCode(toolCode);

        return mockTool;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockToolBrand = new Brand();
        mockToolRate = new Rate();
        mockToolType = new ToolType();
        mockTool = new Tool();
    }

    @DisplayName("Test Checkout with invalid rental days throws InvalidRentalDaysException")
    @Test
    void testCheckoutWithInvalidRentalDays() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        long rentalDays = 0;
        long discountPercent = 10;
        String toolCode = "JAKR";

        toolRentalRequest = new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent);

        Tool mockTool = getJackhammer(toolCode);

        when(toolRepository.findByToolCode(toolCode)).thenReturn(Optional.of(mockTool));

        assertThrows(InvalidRentalDaysException.class, () -> {
            rentalActivityService.checkout(toolRentalRequest);
        });

        verify(rentalActivityRepository, never()).save(any(RentalActivity.class));
    }

    @DisplayName("Test calculating the charge days for Chainsaw Tool")
    @Nested
    class TestNumberOfChargeDays {
        private Tool mockChainsawTool;

        @BeforeEach
        void setup() {
            mockChainsawTool = getChainsaw("CHNS");
        }

        @Test
        void testGetNumberOfChargeDays_NoHolidays() {
            LocalDate startDate = LocalDate.of(2023, 3, 1);
            LocalDate endDate = LocalDate.of(2023, 3, 10);

            long expectedChargeDays = 7;

            long actualChargeDays = rentalActivityService.getNumberOfChargeDays(mockChainsawTool, startDate, endDate);

            assertEquals(expectedChargeDays, actualChargeDays);

        }

        @Test
        void testGetNumberOfChargeDays_WithHolidays() {
            LocalDate startDate = LocalDate.of(2023, 7, 3);
            LocalDate endDate = LocalDate.of(2023, 7, 6);

            long expectedChargeDays = 2; // July 4th is a holiday
            long actualChargeDays = rentalActivityService.getNumberOfChargeDays(mockChainsawTool, startDate, endDate);

            assertEquals(expectedChargeDays, actualChargeDays);
        }
    }

    @DisplayName("Test that holiday on a weekday is observed on same day")
    @Test
    void testGetObservedHolidayDateForWeekday() {
        LocalDate holiday = LocalDate.of(2023, Month.JULY, 4);
        assertFalse(holiday.getDayOfWeek().equals(DayOfWeek.SATURDAY));
        assertFalse(holiday.getDayOfWeek().equals(DayOfWeek.SUNDAY));
        LocalDate observedHoliday = RentalActivityService.getObservedHolidayDate(holiday);
        assertEquals(holiday, observedHoliday);
    }

    @DisplayName("Test that the observed holiday date for Saturday July 4 is Friday")
    @Test
    void testGetJuly4thHoliday2015() {
        int year = 2015;
        LocalDate actualJuly4th = LocalDate.of(2015, 7, 4); // July 4th holiday observed on July 3rd
        assertTrue(actualJuly4th.getDayOfWeek().equals(DayOfWeek.SATURDAY));

        LocalDate observedJuly4th = RentalActivityService.getJuly4thHoliday(year);

        assertNotEquals(observedJuly4th, actualJuly4th);
        assertEquals(observedJuly4th.getDayOfWeek(), DayOfWeek.FRIDAY);
    }

    @DisplayName("Test the observed holiday date for Sunday July 4 is Monday")
    @Test
    void testGetJuly4thHolidayWhenJuly4thIsSunday() {
        int year = 2027; // July 4th is a Sunday in 2027
        LocalDate actualJuly4th = LocalDate.of(2027, 7, 4); // July 4th holiday observed on July 5th
        assertTrue(actualJuly4th.getDayOfWeek().equals(DayOfWeek.SUNDAY));

        LocalDate observedJuly4th = RentalActivityService.getJuly4thHoliday(year);

        assertNotEquals(actualJuly4th, observedJuly4th);
        assertEquals(observedJuly4th.getDayOfWeek(), DayOfWeek.MONDAY);
    }

    @DisplayName("Test Labor Day 2023 is correct")
    @Test
    void testGetLaborDayHoliday2023() {
        int year = 2023;
        LocalDate expectedLaborDay = LocalDate.of(2023, 9, 4); // Labor Day in 2023 is September 4th

        LocalDate actualLaborDay = RentalActivityService.getLaborDayHoliday(year);

        assertEquals(expectedLaborDay, actualLaborDay);
    }

    @DisplayName("Test Labor Day 2024 is correct")
    @Test
    void testGetLaborDayHoliday2024() {
        int year = 2024;
        LocalDate expectedLaborDay = LocalDate.of(2024, 9, 2); // Labor Day in 2024 is September 2nd

        LocalDate actualLaborDay = RentalActivityService.getLaborDayHoliday(year);

        assertEquals(expectedLaborDay, actualLaborDay);
    }

    @Test
    void testGetJuly4thHoliday2023() {
        int year = 2023;
        LocalDate expectedJuly4th = LocalDate.of(2023, 7, 4); // July 4th in 2023

        LocalDate actualJuly4th = RentalActivityService.getJuly4thHoliday(year);

        assertEquals(expectedJuly4th, actualJuly4th);
    }

    @Test
    void testCountHolidaysWithinRangeInclusive_NoHolidays() {
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        LocalDate endDate = LocalDate.of(2023, 3, 10);

        int expectedCount = 0;
        long actualCount = rentalActivityService.countHolidaysWithinRangeInclusive(startDate, endDate);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testCountHolidaysWithinRangeInclusive_OneHoliday() {
        LocalDate startDate = LocalDate.of(2023, 7, 3);
        LocalDate endDate = LocalDate.of(2023, 7, 5);

        int expectedCount = 1; // July 4th
        long actualCount = rentalActivityService.countHolidaysWithinRangeInclusive(startDate, endDate);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testCountHolidaysWithinRangeInclusive_MultipleHolidays() {
        LocalDate startDate = LocalDate.of(2023, 7, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 5);

        int expectedCount = 2; // Labor Day and observed July 4th holiday
        long actualCount = rentalActivityService.countHolidaysWithinRangeInclusive(startDate, endDate);

        assertEquals(expectedCount, actualCount);
    }

}