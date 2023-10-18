package com.example.lab2_3.dtos;

import com.example.lab2_3.entities.DateEntity;
import com.example.lab2_3.entities.ExchangeRate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DateWithExchangeRatesDTO {

    @JsonProperty("date")
    private DateEntity date;

    @JsonProperty("exchangeRates")
    private List<ExchangeRate> exchangeRates;
}
