package com.example.lab2_3.services.impl;

import com.example.lab2_3.entities.Currency;
import com.example.lab2_3.entities.ExchangeRate;
import com.example.lab2_3.repositories.CurrencyRepository;
import com.example.lab2_3.services.CurrencyService;
import com.example.lab2_3.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CurrencyServiceBean implements CurrencyService {
    private final CurrencyRepository currencyRepo;

    private final ExchangeService exchangeService;

    @Override
    public List<Currency> getAll() {
        return currencyRepo.findAll();
    }

    @Override
    public Currency create(Currency currency) {
        return currencyRepo.save(currency);
    }

    @Override
    public List<Currency> createList(List<Currency> currencyList) {
        return (List<Currency>) currencyRepo.saveAll(currencyList);
    }

    @Override
    public void delete(Long id) {
        currencyRepo.deleteById(id);
    }

    @Override
    public void update(Long id, Currency currency) {
        currencyRepo.findById(id).map(toUpdate -> {
            toUpdate.setName(currency.getName());
            return currencyRepo.save(toUpdate);
        });
    }

//    private void refreshData() {
//        // List<Entity> create list of data, in order to insert it then in the database
//        List<ExchangeRate> rates = new ArrayList<>();
//        // pairs
//        for (Currency source : currencyRepo.findAll()) {
//            for (Currency target : currencyRepo.findAll()) {
//                if (!source.getName().equals(target.getName())) {
//                    Map<String, String> map = new HashMap<>(Map.of("A", "1"));
//                    map.put("C1", source.getName());
//                    map.put("C2", target.getName());
//                    LocalDateTime dt = LocalDateTime.of(LocalDate.now(), LocalTime.now());  // Start date
//                    dt = dt.minusDays(30);
//                    for (int i = 0; i < 31; i++) {
//                        putDateInMap(dt, map);
//                        dt = dt.plusDays(1);
//                        //create entity
//                        ExchangeRate exchangeRate = ExchangeRate.builder()
//                                .sourceCurrency(source)
//                                .targetCurrency(target)
//                                .date(Date.valueOf(dt.toLocalDate()))
//                                .rate(Double.parseDouble(parse(map)))
//                                .build();
//                        //fill it in the list
//                        rates.add(exchangeRate);
//                    }
//                }
//            }
//            //clear data
//            exchangeService.deleteAllRates();
//        }
//        //insert all the data into database
//        exchangeService.createExchangeRates(rates);
//    }
//
//    private void putDateInMap(LocalDateTime dt, Map<String, String> map) {
//        map.put("DD1", Integer.toString(dt.getDayOfMonth()));
//        map.put("MM1", Integer.toString(dt.getMonthValue()));
//        map.put("YYYY1", Integer.toString(dt.getYear()));
//        map.put("DD2", Integer.toString(dt.getDayOfMonth()));
//        map.put("MM2", Integer.toString(dt.getMonthValue()));
//        map.put("YYYY2", Integer.toString(dt.getYear()));
//    }
//
//    private String parse(Map<String, String> map) {
//        Set<String> keySet = map.keySet();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("https://fxtop.com/en/historical-currency-converter.php?");
//        for (String parameter : keySet) {
//            stringBuilder.append("&");
//            stringBuilder.append(parameter);
//            stringBuilder.append("=");
//            stringBuilder.append((map.get(parameter)));
//        }
//        stringBuilder.append("&B=1&P=&I=1&btnOK=Go%21");
//        String url = stringBuilder.toString();
//        Document doc = getDocumet(url);
//        Elements elements = doc.getElementsByTag("tbody");
//        Element element = elements.get(28);
//        Elements childs = element.children().get(2).children();
//        String mes = childs.get(1).toString();
//        return getDigitfromString(mes);
//    }
//
//    private String getDigitfromString(String mes) {
//        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
//        Matcher matcher = pat.matcher(mes.replaceAll("\\s+", ""));
//        int k = 0;
//        while (matcher.find()) {
//            if (k == 2) {
//                mes = matcher.group();
//            }
//            k++;
//        }
//        return mes;
//    }
//
//    private Document getDocumet(String url) {
//        Document doc = null;
//        try {
//            doc = Jsoup.connect(url).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return doc;
//    }
}
