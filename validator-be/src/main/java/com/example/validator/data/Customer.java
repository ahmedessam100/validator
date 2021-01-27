package com.example.validator.data;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Customer {

    private String name;
    private String phoneNumber;
    private String code;
    private String state;
    private String country;

    public Customer(String name, String phoneNumber, String code, String country)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.country = country;
    }

    public String getName()
    {
        return name;
    }

    public String getCountry() { return country; }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState() { return state; }

    public String getCode() {
        return code;
    }

    public String isValid(String phoneNumber)
    {
        return phoneNumber.matches(Mapper.codeToRegex.get(this.code)) ? "valid" : "not valid";
    }

    @Override
    public String toString()
    {
        return name + " " + phoneNumber;
    }
}
