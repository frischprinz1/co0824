package com.toolrental.toolrentalproject.rentalactivity;

import java.time.LocalDate;

public class ToolRentalRequest {
    private String toolCode;
    private LocalDate checkoutDate;
    private Long rentalDays;
    private Long discountPercent;

    public ToolRentalRequest(String toolCode, LocalDate checkoutDate, Long rentalDays, Long discountPercent) {
        this.toolCode = toolCode;
        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Long getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Long rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Long getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Long discountPercent) {
        this.discountPercent = discountPercent;
    }
}
