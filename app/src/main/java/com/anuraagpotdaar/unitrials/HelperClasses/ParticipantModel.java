package com.anuraagpotdaar.unitrials.HelperClasses;

public class ParticipantModel {
    String name,phone,email,address,gender,dob,medicalHistory;
    boolean expanded;

    public ParticipantModel() {
    }

    public ParticipantModel(String name, String phone, String email, String address, String gender, String dob, String medicalHistory) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.medicalHistory = medicalHistory;
        this.expanded=false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
