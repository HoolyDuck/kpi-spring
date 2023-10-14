package com.example.lab2_3.services;

import com.example.lab2_3.entities.Currency;
import com.example.lab2_3.entities.DateEntity;

import java.util.List;

public interface DateService {
    List<DateEntity> getAll();

    DateEntity create(DateEntity dateEntity);

    List<DateEntity> createList(List<DateEntity> dateEntityList);

    void delete(Long id);

    void update(Long id, DateEntity dateEntity);

    void deleteAll();
}
