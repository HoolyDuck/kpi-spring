package com.example.lab2_3.services.impl;

import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.entities.ExchangeRate;
import com.example.lab2_3.repositories.ExchangeRateRepository;
import com.example.lab2_3.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeServiceBean implements ExchangeService {
    private final ExchangeRateRepository exchangeRepo;

    @Override
    public List<ExchangeRate> createExchangeRates(List<ExchangeRate> rates) {
        return (List<ExchangeRate>) exchangeRepo.saveAll(rates);
    }

    @Override
    public List<CurrencyDTO> getAllCurrenciesByDate(Date dateFrom, Date dateTo, String currency1, String currency2) {
        return exchangeRepo.findAll().stream().filter(exchangeRate -> {
            if (exchangeRate.getDate().after(dateFrom)
                    && exchangeRate.getDate().before(dateTo)
                    && exchangeRate.getSourceCurrency().getName().equals(currency1)
                    && exchangeRate.getTargetCurrency().getName().equals(currency2)) {
                return true;
            }
            return false;
        }).map(CurrencyMapper::toDto).toList();
    }

    @Override
    public CurrencyDTO getTodayCurrency(String currency1, String currency2) {
        return exchangeRepo.findAll().stream().filter(exchangeRate -> {
            Date today = new Date();
            if (exchangeRate.getDate().getDate() == today.getDate()
                    && exchangeRate.getDate().getMonth() == today.getMonth()
                    && exchangeRate.getSourceCurrency().getName().equals(currency1)
                    && exchangeRate.getTargetCurrency().getName().equals(currency2)) {
                return true;
            }
            return false;
        }).findFirst().map(CurrencyMapper::toDto).get();
    }

    @Override
    public void deleteAllRates() {
        exchangeRepo.deleteAll();
    }

    private static class CurrencyMapper {
        private static CurrencyDTO toDto(ExchangeRate exchangeRate) {
            return CurrencyDTO.builder()
                    .sourceCurrency(exchangeRate.getSourceCurrency().getName())
                    .targetCurrency(exchangeRate.getTargetCurrency().getName())
                    .rate(exchangeRate.getRate())
                    .exchangeDate(exchangeRate.getDate())
                    .build();
        }
    }

}
