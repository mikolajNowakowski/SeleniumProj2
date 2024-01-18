package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Customer {
    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String streetName;
    private String localNumber;
    private String zipCode;
    private String city;
    private String phone;
    private String email;
    private String additionalInformation;


    public Customer(String firstName, String lastName, String companyName, String country, String streetName, String localNumber, String zipCode, String city, String phone, String email, String additionalInformation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.country = country;
        this.streetName = streetName;
        this.localNumber = localNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.additionalInformation = additionalInformation;
    }

    public Customer(String firstName, String lastName, String companyName, String country, String streetName, String localNumber, String zipCode, String city, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.country = country;
        this.streetName = streetName;
        this.localNumber = localNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.additionalInformation = null;
    }
}
