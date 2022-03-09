package com.anuraagpotdaar.unitrials.HelperClasses;

public class HeartRate {
    String date,value;

    public HeartRate() {
    }

    public HeartRate(String date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
