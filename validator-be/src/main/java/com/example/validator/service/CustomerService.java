package com.example.validator.service;

import com.example.validator.data.Customer;
import com.example.validator.data.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Customer getCustomer(String name, String phone) {
        /* Parsing to get the phone number and code */
        String[] temp = phone.split(" ");
        String phoneNumber = temp[1];
        String code = temp[0].replace("(", "").replace(")", "");

        Customer customer = new Customer(name, phoneNumber, code, Mapper.codeToCountry.get(code));

        /* Check the validation of the phone number according to the code */
        String state = customer.isValid(phone);

        customer.setState(state);

        return customer;
    }


    public List<Customer> getAllCustomers() {

        /* Fetch all the customers records */
        return jdbcTemplate.query("SELECT * FROM customer",
                (resultSet, rowNum) -> {
                    return getCustomer(resultSet.getString("name"), resultSet.getString("phone"));
                });
    }

    public List<Customer> getCustomerByCountry(String countries) {
        List<String> codes = new ArrayList<String>();

        /* Get all the codes of countries keys */
        Mapper.countryToCode.keySet().forEach((key) -> {
            if (key.toLowerCase().startsWith(countries.toLowerCase())) {
                codes.add(Mapper.countryToCode.get(key));
            }
        });

        /* Building the query of the filtration by country */
        StringBuilder filtrationQuery = new StringBuilder("SELECT * FROM customer WHERE");

        for (int i = 0; i < codes.size(); i++) {
            if (i < codes.size() - 1)
                filtrationQuery.append(" phone LIKE '").append(codes.get(i)).append("%' OR ");
            else
                filtrationQuery.append(" phone LIKE '").append(codes.get(i)).append("%'");
        }

        return jdbcTemplate.query(filtrationQuery.toString(), (resultSet, rowNum) -> {
            return getCustomer(resultSet.getString("name"), resultSet.getString("phone"));
        });
    }

    public List<Customer> getCustomerByState(String state) {
        /* Filter by the state given */
        return jdbcTemplate.query("SELECT * FROM customer", (resultSet, rowNum) -> {
            return getCustomer(resultSet.getString("name"), resultSet.getString("phone"));
        }).stream().filter((customer -> {
            return customer.getState().toLowerCase().startsWith(state.toLowerCase());
        })).collect(Collectors.toList());
    }
}
