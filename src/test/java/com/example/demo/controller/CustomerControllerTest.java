package com.example.demo.controller;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Product;
import com.example.demo.service.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);//init mock and controller
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }


    @Test
    public void testList() throws Exception{
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));

    }

    @Test
    public void testShow() throws Exception{

        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));

    }

    @Test
    public void testEdit() throws Exception{

        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));

    }

    @Test
    public void testNewProduct() throws Exception{

        Integer id = 1;

        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));

    }

    @Test
    public void testSaveOrUpdate() throws Exception{
        Integer id = 1;
        String fisrtName = "djallal";
        String lastName = "rahmoune";
        String city = "perpignan";
        String phoneNumber = "0755328091";

        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setFirstName(fisrtName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setCity(city);
        returnCustomer.setPhoneNumber(phoneNumber);

        when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("firstName", fisrtName)
                .param("lastName", lastName)
                .param("phoneNumber", phoneNumber)
                .param("city", city))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:customer/show/1"))
                    .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                    .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                    .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                    .andExpect(model().attribute("customer", hasProperty("firstName", is(fisrtName))))
                    .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                    .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))));

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(fisrtName, boundCustomer.getValue().getFirstName());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(city, boundCustomer.getValue().getCity());
        assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());

    }

    @Test
    public void testDelete() throws Exception{

        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }
}
