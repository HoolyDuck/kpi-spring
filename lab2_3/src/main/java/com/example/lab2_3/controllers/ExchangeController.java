package com.example.lab2_3.controllers;

import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

    private ExchangeService exchangeService;

    @Autowired
    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/latest")
    public String getAllExchanges(Model model) {
        List<CurrencyDTO> exchanges = exchangeService.getAllTodayCurrencies();
        model.addAttribute("exchanges", exchanges);
        return "all_exchanges";
    }

    @GetMapping("/by-date")
    public String getAllExchangesByDate(
            Model model,
            @RequestParam(value = "dateFrom", required = false) final String dateFrom,
            @RequestParam(value = "dateTo", required = false) final String dateTo,
            @RequestParam(value = "source_curr", required = false) final String source_curr,
            @RequestParam(value = "target_curr", required = false) final String target_curr
    ) {
        if (dateFrom == null || dateTo == null || source_curr == null || target_curr == null) {
            return "exchanges_by_date";
        }
        List<CurrencyDTO> exchanges = exchangeService.getAllCurrenciesByDate(dateFrom, dateTo, source_curr, target_curr);
        model.addAttribute("exchanges", exchanges);
        return "exchanges_by_date";
    }





}
