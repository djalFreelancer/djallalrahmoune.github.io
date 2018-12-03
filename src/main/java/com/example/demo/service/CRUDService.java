package com.example.demo.service;

import java.util.List;

public interface CRUDService<T> {
    List<?> listAll();
    T getById(Integer id);
    T saveOrUpdate(T domainObject);
    void delete(Integer id);

}
