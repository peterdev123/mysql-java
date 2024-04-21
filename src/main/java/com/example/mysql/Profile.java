package com.example.mysql;

import java.sql.Date;

public class Profile {
    private String fullname;
    private String email;
    private String birthdate;
    private String gender;
    private String address;

    public Profile(String fullname, String email, String birthdate, String gender, String address) {
        this.fullname = fullname;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}