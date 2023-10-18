package com.example.lab2_3.dtos;

import com.example.lab2_3.entities.DateEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDTO {
    @JsonProperty("exchangeDate")
    private DateEntity exchangeDate;
    @JsonProperty("sourceCurrency")
    private String sourceCurrency;
    @JsonProperty("targetCurrency")
    private String targetCurrency;
    @JsonProperty("rate")
    private double rate;
}
