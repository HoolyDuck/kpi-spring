package com.example.lab2_3.services;

import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.entities.DateEntity;
import com.example.lab2_3.entities.ExchangeRate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ExchangeService {
    List<ExchangeRate> createExchangeRates(List<ExchangeRate> rates);

    List<CurrencyDTO> getAllCurrenciesByDate(DateEntity dateFrom, DateEntity dateTo, String currency1, String currency2);

    List<CurrencyDTO> getAllTodayCurrencies();

    CurrencyDTO getTodayCurrency(String currency1, String currency2);

    void deleteAllRates();
}
