package com.anuraagpotdaar.unitrials.HelperClasses;

public class MedsModel {

    String medName, morning, afternoon, evening, night;

    public MedsModel(String medName, String morning, String afternoon, String evening, String night) {
        this.medName = medName;
        this.morning = morning;
        this.afternoon = afternoon;
        this.evening = evening;
        this.night = night;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getEvening() {
        return evening;
    }

    public void setEvening(String evening) {
        this.evening = evening;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }
}
