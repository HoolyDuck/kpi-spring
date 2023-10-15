package com.example.lab2_3.controllers;
import com.example.lab2_3.dtos.CurrencyDTO;
import com.example.lab2_3.entities.DateEntity;
import com.example.lab2_3.services.ExchangeService;
import com.example.lab2_3.services.impl.ExchangeServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/exchanges")
public class ExchangesController {
    private ExchangeService exchangeService;

    @Autowired
    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/latest")
    public String getAllExchanges(Model model){
        List<CurrencyDTO> exchanges = exchangeService.getAllTodayCurrencies();
        model.addAttribute("exchanges", exchanges);
        return "all_exchanges";
    }

    @GetMapping("exchanges/{source_curr}/{target_curr}/{dateFrom}/{dateTo}")
    public String getAllExchanges(Model model,
                                  @PathVariable("source_curr") final String source_curr,
                                  @PathVariable("target_curr") final String target_curr,
                                  @PathVariable("dateFrom") final DateEntity dateFrom,
                                  @PathVariable("dateTo") final DateEntity dateTo
                                  ){

        List<CurrencyDTO> exchanges = exchangeService.getAllCurrenciesByDate(dateFrom, dateTo, source_curr, target_curr);
        model.addAttribute("exchanges", exchanges);
        return "exchange_by_date";
    }
}
