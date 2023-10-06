package com.example.lab2_3.services;

import com.example.lab2_3.entities.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAll();

    Currency create(Currency currency);

    List<Currency> createList(List<Currency> currencyList);

    void delete(Long id);

    void update(Long id, Currency currency);
}
