package com.toolrental.toolrentalproject.rentalactivity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.toolrental.toolrentalproject.exceptions.InvalidDiscountPercentException;
import com.toolrental.toolrentalproject.tool.Tool;
import com.toolrental.toolrentalproject.tool.ToolRepository;
import com.toolrental.toolrentalproject.brand.Brand;
import com.toolrental.toolrentalproject.tooltype.ToolType;
import com.toolrental.toolrentalproject.rate.Rate;

@DisplayName("Rental Activity Service")
@ExtendWith(MockitoExtension.class)
class RentalActivityServiceTestCases1To6 {

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockToolBrand = new Brand();
        mockToolRate = new Rate();
        mockToolType = new ToolType();
        mockTool = new Tool();
    }

    @DisplayName("Test Case 1: Checkout with valid input")
    @Test
    void testCheckoutWithInvalidDiscountPercentTest1() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        long rentalDays = 5;
        long discountPercent = 101;
        String toolCode = "JAKR";

        toolRentalRequest = new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent);

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

        when(toolRepository.findByToolCode(toolCode)).thenReturn(Optional.of(mockTool));

        assertThrows(InvalidDiscountPercentException.class, () -> {
            rentalActivityService.checkout(toolRentalRequest);
        });

        verify(rentalActivityRepository, never()).save(any(RentalActivity.class));
    }

    @DisplayName("Test Case 2: Checkout with valid input: LADW")
    @Test
    void testCheckoutWithValidInputTest2() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        long rentalDays = 3;
        long discountPercent = 10;
        String toolCode = "LADW";

        toolRentalRequest = new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent);

        mockToolRate.setDailyCharge(1.99);
        mockToolRate.setHasWeekdayCharge(true);
        mockToolRate.setHasWeekendCharge(true);
        mockToolRate.setHasHolidayCharge(false);

        mockToolType.setRate(mockToolRate);

        mockToolType.setName("Ladder");
        mockToolType.setPrefix("LAD");

        mockToolBrand.setName("Werner");
        mockToolBrand.setAbbreviation("W");
        mockTool.setBrand(mockToolBrand);

        mockTool.setToolType(mockToolType);
        mockTool.setToolCode(toolCode);

        when(toolRepository.findByToolCode(toolCode)).thenReturn(Optional.of(mockTool));
        RentalAgreementFormat agreementFormat = rentalActivityService.checkout(toolRentalRequest);

        assertNotNull(agreementFormat);
        assertEquals(agreementFormat.getToolCode(), "LADW");
        assertEquals(agreementFormat.getToolType(), "Ladder");
        assertEquals(agreementFormat.getCheckoutDate(), "07/02/20");
        assertEquals(agreementFormat.getDueDate(), "07/05/20");
        assertTrue(rentalDays > Long.parseLong(agreementFormat.getChargeDays()));
        assertEquals(agreementFormat.getChargeDays(), "2");
        assertEquals(agreementFormat.getDiscountPercent(), "10%");
        verify(rentalActivityRepository, times(1)).save(any(RentalActivity.class));
    }

    @DisplayName("Test Case 3: Checkout with valid input: CHNS")
    @Test
    void testCheckoutWithValidInputTest3() {
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        long rentalDays = 5;
        long discountPercent = 25;
        String toolCode = "CHNS";

        toolRentalRequest = new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent);

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

        when(toolRepository.findByToolCode(toolCode)).thenReturn(Optional.of(mockTool));
        RentalAgreementFormat agreementFormat = rentalActivityService.checkout(toolRentalRequest);

        assertNotNull(agreementFormat);
        assertEquals(agreementFormat.getToolCode(), "CHNS");
        assertEquals(agreementFormat.getToolType(), "Chainsaw");
        assertEquals(agreementFormat.getCheckoutDate(), "07/02/15");
        assertEquals(agreementFormat.getDueDate(), "07/07/15");
        assertTrue(rentalDays > Long.parseLong(agreementFormat.getChargeDays()));
        assertEquals(agreementFormat.getChargeDays(), "2");
        assertEquals(agreementFormat.getDiscountPercent(), "25%");
        verify(rentalActivityRepository, times(1)).save(any(RentalActivity.class));
    }

    // @Nested
    // class

    @DisplayName("Test Case 4: Checkout with valid input: JAKD")
    @Test
    void testCheckoutWithValidInputTest4() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        long rentalDays = 6;
        long discountPercent = 0;
        String toolCode = "JAKD";

        toolRentalRequest = new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent);

        mockToolRate.setDailyCharge(2.99);
        mockToolRate.setHasWeekdayCharge(true);
        mockToolRate.setHasWeekendCharge(false);
        mockToolRate.setHasHolidayCharge(false);

        mockToolType.setRate(mockToolRate);

        mockToolType.setName("Jackhammer");
        mockToolType.setPrefix("JAK");

        mockToolBrand.setName("DeWalt");
        mockToolBrand.setAbbreviation("D");
        mockTool.setBrand(mockToolBrand);

        mockTool.setToolType(mockToolType);
        mockTool.setToolCode(toolCode);

        when(toolRepository.findByToolCode(toolCode)).thenReturn(Optional.of(mockTool));
        RentalAgreementFormat agreementFormat = rentalActivityService.checkout(toolRentalRequest);

        assertNotNull(agreementFormat);
        assertEquals(agreementFormat.getToolCode(), "JAKD");
        assertEquals(agreementFormat.getToolType(), "Jackhammer");
        assertEquals(agreementFormat.getCheckoutDate(), "09/03/15");
        assertEquals(agreementFormat.getDueDate(), "09/09/15");
        assertTrue(rentalDays > Integer.parseInt(agreementFormat.getChargeDays()));
        assertEquals(agreementFormat.getChargeDays(), "3");
        assertEquals(agreementFormat.getDiscountPercent(), "0%");
        verify(rentalActivityRepository, times(1)).save(any(RentalActivity.class));
    }

    @DisplayName("Test Case 5: Checkout with valid input: JAKR")
    @Test
    void testCheckoutWithValidInputTest5() {
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        long rentalDays = 9;
        long discountPercent = 0;
        String toolCode = "JAKR";

        toolRentalRequest = new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent);

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

        when(toolRepository.findByToolCode(toolCode)).thenReturn(Optional.of(mockTool));
        RentalAgreementFormat agreementFormat = rentalActivityService.checkout(toolRentalRequest);

        assertNotNull(agreementFormat);
        assertEquals(agreementFormat.getToolCode(), "JAKR");
        assertEquals(agreementFormat.getToolType(), "Jackhammer");
        assertEquals(agreementFormat.getCheckoutDate(), "07/02/15");
        assertEquals(agreementFormat.getDueDate(), "07/11/15");
        assertTrue(rentalDays > Integer.parseInt(agreementFormat.getChargeDays()));
        assertEquals(agreementFormat.getChargeDays(), "6");
        assertEquals(agreementFormat.getDiscountPercent(), "0%");
        verify(rentalActivityRepository, times(1)).save(any(RentalActivity.class));
    }

    @DisplayName("Test Case 6: Checkout with valid input: JAKR")
    @Test
    void testCheckoutWithValidInputTest6() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        long rentalDays = 4;
        long discountPercent = 50;
        String toolCode = "JAKR";

        toolRentalRequest = new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent);
        toolRentalRequest.setToolCode(toolCode);
        toolRentalRequest.setRentalDays(rentalDays);
        toolRentalRequest.setDiscountPercent(discountPercent);
        toolRentalRequest.setCheckoutDate(checkoutDate);

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

        when(toolRepository.findByToolCode(toolCode)).thenReturn(Optional.of(mockTool));
        RentalAgreementFormat agreementFormat = rentalActivityService.checkout(toolRentalRequest);

        assertNotNull(agreementFormat);
        assertEquals(agreementFormat.getToolCode(), "JAKR");
        assertEquals(agreementFormat.getToolType(), "Jackhammer");
        assertEquals(agreementFormat.getCheckoutDate(), "07/02/20");
        assertEquals(agreementFormat.getDueDate(), "07/06/20");
        assertTrue(rentalDays > Integer.parseInt(agreementFormat.getChargeDays()));
        assertEquals(agreementFormat.getChargeDays(), "1");
        assertEquals(agreementFormat.getDiscountPercent(), "50%");
        verify(rentalActivityRepository, times(1)).save(any(RentalActivity.class));
    }
}