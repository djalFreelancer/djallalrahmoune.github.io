package com.example.demo.domain;


import javax.persistence.*;

@Entity
public class Customer implements DomainObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String adresseLine1;
    private String adresseLine2;
    private String city;
    private String zipCode;


    @Override
    public Integer getId() {
        return id;
    }
    @Override
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
