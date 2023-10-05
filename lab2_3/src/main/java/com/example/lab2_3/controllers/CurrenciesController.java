package com.example.lab2_3.controllers;

import com.example.lab2_3.entities.Currency;
import com.example.lab2_3.services.CurrencyService;
import com.example.lab2_3.services.impl.CurrencyServiceBean;
import com.example.lab2_3.services.impl.ExchangeServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping(path = "/currencies")
public class CurrenciesController {
    @Autowired
    private CurrencyService currencyService;

    @PostMapping()
    public String addCurrency(Model model, @RequestBody Currency currency){
        currencyService.create(currency);
        List<Currency> currencies = currencyService.getAll();
        model.addAttribute("currencies", currencies);
        return "all_currencies";
    }

    @PutMapping()
    public String updateCurrency(Model model, @RequestBody Currency currency){
        currencyService.update(currency.getId(), currency);
        List<Currency> currencies = currencyService.getAll();
        model.addAttribute("currencies", currencies);
        return "all_currencies";
    }

    @DeleteMapping()
    public String deleteCurrency(Model model, @RequestBody Currency currency){
        currencyService.delete(currency.getId());
        List<Currency> currencies = currencyService.getAll();
        model.addAttribute("currencies", currencies);
        return "all_currencies";
    }
}
