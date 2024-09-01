package com.toolrental.toolrentalproject.rentalactivity;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// public final class RentalAgreementFormat {
public class RentalAgreementFormat {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private String rentalDays;
    private String checkoutDate;
    private String dueDate;
    private String dailyRentalCharge;
    private String chargeDays;
    private String preDiscountCharge;
    private String discountPercent;
    private String discountAmount;
    private String finalCharge;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public String getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(String rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
    }

    public String getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(Double dailyRentalCharge) {
        this.dailyRentalCharge = formatCurrency(dailyRentalCharge);
    }

    public String getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(String chargeDays) {
        this.chargeDays = chargeDays;
    }

    public String getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(Double preDiscountCharge) {
        this.preDiscountCharge = formatCurrency(preDiscountCharge);
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Long discountPercent) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("en", "US"));
        this.discountPercent = percentFormat.format(discountPercent.doubleValue() / 100);
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = formatCurrency(discountAmount);
    }

    public String getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(Double finalCharge) {
        this.finalCharge = formatCurrency(finalCharge);
    }

    public static String formatCurrency(Double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        return currencyFormat.format(amount);
    }

    public void format(RentalActivity rentalActivity) {
        this.setToolCode(rentalActivity.getTool().getToolCode());
        this.setToolType(rentalActivity.getTool().getToolType().getName());
        this.setToolBrand(rentalActivity.getTool().getBrand().getName());
        this.setRentalDays(rentalActivity.getRentalDays().toString());
        this.setCheckoutDate(rentalActivity.getCheckoutDate());
        this.setDueDate(rentalActivity.getDueDate());
        this.setDailyRentalCharge(rentalActivity.getTool().getToolType().getRate().getDailyCharge());
        this.setChargeDays(rentalActivity.getChargeDays().toString());
        this.setPreDiscountCharge(rentalActivity.getPreDiscountCharge());
        this.setDiscountPercent(rentalActivity.getDiscountPercent());
        this.setDiscountAmount(rentalActivity.getDiscountAmount());
        this.setFinalCharge(rentalActivity.getFinalCharge());
    }

    @Override
    public String toString() {
        return "Tool code: " + this.getToolCode() + "\n"
                + "Tool type: " + this.getToolType() + "\n"
                + "Tool brand: " + this.getToolBrand() + "\n"
                + "Rental days: " + this.getRentalDays() + "\n"
                + "Check out date: " + this.getCheckoutDate() + "\n"
                + "Due Date: " + this.getDueDate() + "\n"
                + "Daily rental charge: " + this.getDailyRentalCharge() + "\n"
                + "Charge days: " + this.getChargeDays() + "\n"
                + "Pre-discount charge: " + this.getPreDiscountCharge() + "\n"
                + "Discount percent: " + this.getDiscountPercent() + "\n"
                + "Discount amount: " + this.getDiscountAmount() + "\n"
                + "Final charge: " + this.getFinalCharge() + "\n";
    }
}
