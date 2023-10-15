package com.example.lab2_3.services.impl;

import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.entities.DateEntity;
import com.example.lab2_3.entities.ExchangeRate;
import com.example.lab2_3.repositories.ExchangeRateRepository;
import com.example.lab2_3.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
    public List<CurrencyDTO> getAllCurrenciesByDate(String dateFrom, String dateTo, String currency1, String currency2) {
        // Format - YYYY-MM-DD
        return exchangeRepo.findAll().stream().filter(exchangeRate -> {
            if (!exchangeRate.getSourceCurrency().getName().equals(currency1)
                    || !exchangeRate.getTargetCurrency().getName().equals(currency2)) {
                return false;
            }
            String stringExchangeRateDate = exchangeRate.getDate().getYear().toString() + "-"
                    + exchangeRate.getDate().getMonth().toString() + "-"
                    + exchangeRate.getDate().getDay().toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateFromDate = format.parse(dateFrom);
                Date dateToDate = format.parse(dateTo);
                Date exchangeRateDate = format.parse(stringExchangeRateDate);
                if (exchangeRateDate.before(dateToDate)
                        && exchangeRateDate.after(dateFromDate)) {
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return false;
        }).map(CurrencyMapper::toDto).toList();
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
