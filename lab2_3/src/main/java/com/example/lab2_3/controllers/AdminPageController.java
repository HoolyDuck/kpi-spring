package com.example.lab2_3.controllers;

import com.example.lab2_3.entities.Currency;
import com.example.lab2_3.entities.DateEntity;
import com.example.lab2_3.entities.ExchangeRate;
import com.example.lab2_3.services.CurrencyService;
import com.example.lab2_3.services.DateService;
import com.example.lab2_3.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping(path = "/admin")
public class AdminPageController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private DateService dateService;

    @GetMapping("/index")
    public String getAdminPage(Model model) {
        List<Currency> currencies = currencyService.getAll();
        List<ExchangeRate> exchangeRates = exchangeService.getAll();
        model.addAttribute("currencies", currencies);
        model.addAttribute("exchangeRates", exchangeRates);
        return "admin_interface";
    }

    @GetMapping("/currency/add")
    public String getAddCurrencyPage() {
        return "add_currency";
    }

    @PostMapping("/currency/add")
    public String addCurrency(Currency currency) {
        currencyService.create(currency);
        return "redirect:/admin/index";
    }

    @GetMapping("/currency/update/{id}")
    public String getUpdateCurrencyPage(Model model, @PathVariable("id") Long id) {
        Currency currency = currencyService.getById(id);
        model.addAttribute("currency", currency);
        return "update_currency";
    }

    @PostMapping("/currency/update/{id}")
    public String updateCurrency(Currency currency, @PathVariable("id") Long id) {
        currencyService.update(id, currency);
        return "redirect:/admin/index";

    }

    @GetMapping("/currency/delete/{id}")
    public String deleteCurrency(@PathVariable("id") Long id) {
        currencyService.delete(id);
        return "redirect:/admin/index";
    }

    @GetMapping("/exchange/add")
    public String getAddExchangeRatePage(Model model) {
        List<Currency> currencies = currencyService.getAll();
        List<DateEntity> dates = dateService.getAll();
        model.addAttribute("dates", dates);
        model.addAttribute("currencies", currencies);
        return "add_exchange_rate";
    }

    @PostMapping("/exchange/add")
    public String addExchangeRate(ExchangeRate exchangeRate) {
        exchangeService.create(exchangeRate);
        return "redirect:/admin/index";
    }

    @GetMapping("/exchange/update/{id}")
    public String getUpdateExchangeRatePage(Model model, @PathVariable("id") Long id) {
        ExchangeRate exchangeRate = exchangeService.getById(id);
        List<Currency> currencies = currencyService.getAll();
        List<DateEntity> dates = dateService.getAll();
        model.addAttribute("dates", dates);
        model.addAttribute("currencies", currencies);
        model.addAttribute("exchangeRate", exchangeRate);
        return "update_exchange_rate";
    }

    @PostMapping("/exchange/update/{id}")
    public String updateExchangeRates(ExchangeRate exchangeRate, @PathVariable("id") Long id) {
        exchangeService.update(id, exchangeRate);
        return "redirect:/admin/index";
    }

    @GetMapping("/exchange/delete/{id}")
    public String deleteExchangeRate(@PathVariable("id") Long id) {
        exchangeService.delete(id);
        return "redirect:/admin/index";
    }


}
