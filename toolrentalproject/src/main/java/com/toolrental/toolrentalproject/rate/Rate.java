package com.toolrental.toolrentalproject.rate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Rate {
    @Id
    private String id;

    private Double dailyCharge;
    private Boolean hasWeekdayCharge;
    private Boolean hasWeekendCharge;
    private Boolean hasHolidayCharge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDailyCharge(Double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public Double getDailyCharge() {
        return dailyCharge;
    }

    public void setHasWeekendCharge(Boolean hasWeekendCharge) {
        this.hasWeekendCharge = hasWeekendCharge;
    }

    public Boolean getHasWeekendCharge() {
        return hasWeekendCharge;
    }

    public void setHasHolidayCharge(Boolean hasHolidayCharge) {
        this.hasHolidayCharge = hasHolidayCharge;
    }

    public Boolean getHasHolidayCharge() {
        return hasHolidayCharge;
    }

    public Boolean getHasWeekdayCharge() {
        return hasWeekdayCharge;
    }

    public void setHasWeekdayCharge(Boolean hasWeekdayCharge) {
        this.hasWeekdayCharge = hasWeekdayCharge;
    }
}
