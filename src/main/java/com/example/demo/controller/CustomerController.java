package com.example.demo.controller;


import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/customer")
@Controller
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping({"/list","/"})
    public String allCustomers(Model model)
    {
        model.addAttribute("customers", customerService.listAll());
        return ("customer/list");
    }

    @RequestMapping("/show/{id}")
    public String listCustomer(@PathVariable Integer id, Model model)
    {
        model.addAttribute("customer", customerService.getById(id));
        return ("customer/show");
    }

    @RequestMapping("/new")
    public String addCustomer(Model model){

        model.addAttribute("customer", new Customer());
        return "customer/customerForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateProduct(Customer customer){
        Customer savedCustomer = customerService.saveOrUpdate(customer);
        return "redirect:customer/show/"+savedCustomer.getId();
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/customerForm";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id, Model model){
        customerService.delete(id);
        return "redirect:/customer/list";
    }

}
