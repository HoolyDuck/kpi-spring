package com.example.lab2_3.services;

import com.example.lab2_3.entities.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAll();

    Currency getById();

    Currency getByName(String name);

    Currency create(Currency currency);

    void delete(Long id);

    void update(Long id, Currency currency);
}
