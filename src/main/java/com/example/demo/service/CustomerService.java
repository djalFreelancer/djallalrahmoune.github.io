package com.example.demo.service;

import com.example.demo.domain.Customer;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

public interface CustomerService {

    public List<Customer> listAllCustomers();

    Customer getCustomerById(Integer id);
}
