package com.example.demo.service;

import com.example.demo.domain.Customer;

import com.example.demo.domain.DomainObject;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {

        return (Customer) super.saveOrUpdate(customer);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    protected void loadDomainObjects( ) {

        domainMap = new HashMap<>();

        Customer c1 = new Customer();
        c1.setId(1);
        c1.setFirstName("gul");
        c1.setLastName("chata");
        c1.setEmail("gul.chata");
        c1.setPhoneNumber("0761");
        c1.setCity("perp");
        c1.setAdresseLine1("perp1");
        c1.setAdresseLine2("perp2");
        c1.setZipCode("66");

        domainMap.put(1, c1);

        Customer c2 = new Customer();
        c2.setId(2);
        c2.setFirstName("gul2");
        c2.setLastName("chata2");
        c2.setEmail("gul.chata2");
        c2.setPhoneNumber("07612");
        c2.setAdresseLine1("perp12");
        c2.setAdresseLine2("perp22");
        c2.setZipCode("66");
        c2.setCity("perp");

        domainMap.put(2, c2);

        Customer c3 = new Customer();
        c3.setId(3);
        c3.setFirstName("gul23");
        c3.setLastName("chata23");
        c3.setEmail("gul.chata23");
        c3.setPhoneNumber("076123");
        c3.setAdresseLine1("perp123");
        c3.setAdresseLine2("perp223");
        c3.setZipCode("66");
        c3.setCity("perp");

        domainMap.put(3, c3);
    }

    }
