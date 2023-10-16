package com.example.lab2_3.services;

import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.dtos.DateWithExchangeRatesDTO;
import com.example.lab2_3.entities.ExchangeRate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExchangeService {
    List<ExchangeRate> createExchangeRates(List<ExchangeRate> rates);

    List<DateWithExchangeRatesDTO> getAllCurrenciesByDate(String dateFrom, String dateTo, String currency1, String currency2);

    List<CurrencyDTO> getAllTodayCurrencies();

    CurrencyDTO getTodayCurrency(String currency1, String currency2);

    void deleteAllRates();

    List<ExchangeRate> getAll();

    void updateRate(Long id, Double rate);

    ExchangeRate getById(Long id);

    ExchangeRate create(ExchangeRate exchangeRate);

    void delete(Long id);

    ExchangeRate update(Long id, ExchangeRate exchangeRate);




}
