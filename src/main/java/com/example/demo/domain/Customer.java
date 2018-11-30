package com.example.demo.domain;

public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String adresseLine1;
    private String adresseLine2;
    private String city;
    private String zipCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdresseLine1() {
        return adresseLine1;
    }

    public void setAdresseLine1(String adresseLine1) {
        this.adresseLine1 = adresseLine1;
    }

    public String getAdresseLine2() {
        return adresseLine2;
    }

    public void setAdresseLine2(String getAdresseLine2) {
        this.adresseLine2 = getAdresseLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
