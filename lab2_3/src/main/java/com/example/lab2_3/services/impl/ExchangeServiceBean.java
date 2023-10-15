package com.example.lab2_3.services.impl;

import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.dtos.DateWithExchangeRatesDTO;
import com.example.lab2_3.entities.DateEntity;
import com.example.lab2_3.entities.ExchangeRate;
import com.example.lab2_3.repositories.ExchangeRateRepository;
import com.example.lab2_3.services.DateService;
import com.example.lab2_3.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExchangeServiceBean implements ExchangeService {
    private final ExchangeRateRepository exchangeRepo;
    private final DateService dateService;

    @Override
    public List<ExchangeRate> createExchangeRates(List<ExchangeRate> rates) {
        return (List<ExchangeRate>) exchangeRepo.saveAll(rates);
    }

    @Override
    public List<DateWithExchangeRatesDTO> getAllCurrenciesByDate(String dateFrom, String dateTo, String currency1, String currency2) {
        // Format - YYYY-MM-DD
        List<DateEntity> dates = dateService.getDatesInRange(dateFrom, dateTo);
        List<DateWithExchangeRatesDTO> result = dates.stream().map(dateEntity -> {
            List<ExchangeRate> exchangeRates = exchangeRepo.findAll().stream().filter(exchangeRate -> {
                if (exchangeRate.getDate().getDay() == dateEntity.getDay()
                        && Objects.equals(exchangeRate.getDate().getMonth(), dateEntity.getMonth())
                        && Objects.equals(exchangeRate.getDate().getYear(), dateEntity.getYear())
                        && exchangeRate.getSourceCurrency().getName().equals(currency1)) {
                    return true;
                }
                return false;
            }).toList();
            return DateWithExchangeRatesDTO.builder()
                    .date(dateEntity)
                    .exchangeRates(exchangeRates)
                    .build();
        }).toList();
        return result;
    }

    @Override
    public List<CurrencyDTO> getAllTodayCurrencies() {
        return GetTodayCurrencies().stream().map(CurrencyMapper::toDto).toList();
    }

    @Override
    public CurrencyDTO getTodayCurrency(String currency1, String currency2) {
        return GetTodayCurrencies().stream().filter(exchangeRate -> {
                    if (exchangeRate.getSourceCurrency().getName().equals(currency1)
                            && exchangeRate.getTargetCurrency().getName().equals(currency2)) {
                        return true;
                    }
                    return false;

                }).
                findFirst().map(CurrencyMapper::toDto).get();
    }

    private List<ExchangeRate> GetTodayCurrencies() {
        LocalDate localDate = LocalDate.now();
        return exchangeRepo.findAll().stream().filter(exchangeRate -> {
            if (exchangeRate.getDate().getDay() == localDate.getDayOfMonth()
                    && exchangeRate.getDate().getMonth() == localDate.getMonthValue()) {
                return true;
            }
            return false;
        }).toList();
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
