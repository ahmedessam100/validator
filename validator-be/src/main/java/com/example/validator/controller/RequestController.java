package com.example.validator.controller;
import lombok.EqualsAndHashCode;

import com.example.validator.data.Customer;
import com.example.validator.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getData")
    public List<Customer> getData()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getDataByCountry/{country}")
    public List<Customer> getDataByCountry(@PathVariable String country) {
        return customerService.getCustomerByCountry(country);
    }

    @GetMapping("/getDataByState/{state}")
    public List<Customer> getDataByState(@PathVariable String state) {
        return customerService.getCustomerByState(state);
    }

}