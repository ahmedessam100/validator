package com.example.validator;

import com.example.validator.data.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@SpringBootApplication
@RestController
public class ValidatorApplication {
    public static void main(String[] args) { SpringApplication.run(ValidatorApplication.class, args); }
}
