package com.example.lab2_3.services;

import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.entities.ExchangeRate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ExchangeService {
    List<ExchangeRate> createExchangeRates(List<ExchangeRate> rates);

    List<CurrencyDTO> getAllCurrenciesByDate(Date dateFrom, Date dateTo, String currency1, String currency2);

    CurrencyDTO getTodayCurrency(String currency1, String currency2);

    void deleteAllRates();
}
