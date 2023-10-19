package com.example.lab2_3.configs;

import com.example.lab2_3.services.impl.CurrencyServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyServiceConfig {
    @Bean
    public CurrencyServiceBean currencyServiceBean() {
        return new CurrencyServiceBean();
    }
}
