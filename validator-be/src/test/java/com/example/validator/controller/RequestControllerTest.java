package com.example.validator.controller;

import com.example.validator.data.Customer;
import com.example.validator.service.CustomerService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Assert;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static java.util.Collections.sort;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RequestController.class)
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestController requestController;

    @MockBean
    private CustomerService customerService;

    private Customer customer = new Customer("Walid Hammadi", "6007989253", "212", "Morocco");

    @Test
    public void init()
    {
        assertThat(requestController).isNotNull();

        assertThat(customerService).isNotNull();
    }

    @Test
    public void getDataSuccessfully() throws Exception {

        customer.setState("not valid");

        List<Customer> customers = singletonList(customer);

        given(requestController.getData()).willReturn(customers);

        /* Building the request */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getData")
                .accept(MediaType.APPLICATION_JSON);


        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(customer.getName())))
                .andExpect(jsonPath("$[0].country", is(customer.getCountry())))
                .andExpect(jsonPath("$[0].code", is(customer.getCode())))
                .andExpect(jsonPath("$[0].state", is(customer.getState())))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        /* Checking for non-empty body */
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() > 0);
    }

    @Test
    void getDataByCountrySuccessfully() throws Exception {

        customer.setState("not valid");

        List<Customer> customers = singletonList(customer);

        given(requestController.getDataByCountry(customer.getCountry())).willReturn(customers);

        /* Building the request */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getDataByCountry/Morocco")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(customer.getName())))
                .andExpect(jsonPath("$[0].country", is(customer.getCountry())))
                .andExpect(jsonPath("$[0].code", is(customer.getCode())))
                .andExpect(jsonPath("$[0].state", is(customer.getState())))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        /* Checking for non-empty body */
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() > 0);
    }

    @Test
    void getDataByCountryNotSuccessfully() throws Exception {

        customer.setState("not valid");

        List<Customer> customers = singletonList(customer);

        given(requestController.getDataByCountry(customer.getCountry())).willReturn(customers);

        /* Building the request with undefined country name */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getDataByCountry/test")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                /* Checking for empty body request */
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

    }

    @Test
    void getDataByStateSuccessfully() throws Exception {

        customer.setState("not valid");

        List<Customer> customers = singletonList(customer);

        given(requestController.getDataByState(customer.getState())).willReturn(customers);

        /* Building the request */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getDataByState/not valid")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(customer.getName())))
                .andExpect(jsonPath("$[0].country", is(customer.getCountry())))
                .andExpect(jsonPath("$[0].code", is(customer.getCode())))
                .andExpect(jsonPath("$[0].state", is(customer.getState())))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        /* Checking for non-empty body */
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() > 0);
    }

    @Test
    void getDataByStateNotSuccessfully() throws Exception {

        customer.setState("not valid");

        List<Customer> customers = singletonList(customer);

        given(requestController.getDataByState(customer.getState())).willReturn(customers);

        /* Building the request with undefined state */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getDataByState/x")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}