package com.example.demo.controller;


import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String allCustomers(Model model)
    {
        model.addAttribute("customers", customerService.listAllCustomers());
        return ("customers");
    }


}
