package com.example.lab2_3.commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InsertDataRunner implements CommandLineRunner {
    private static final Map<String, String> currencyPairs = Map.of(
            "USD", "UAH",
            "EUR", "UAH",
            "GBP", "UAH"
    );

    @Override
    public void run(String... args) throws Exception {
        //clear database
        // List<Entity> create list of data, in order to insert it then in the database
        Map<String, String> map = new HashMap<>(Map.of("A", "1"));
        for (String pairKey : currencyPairs.keySet()) {
            map.put("C1", pairKey);
            map.put("C2", currencyPairs.get(pairKey));
            LocalDateTime dt = LocalDateTime.of(LocalDate.now(), LocalTime.now());  // Start date
            dt = dt.minusDays(30);
            for (int i = 0; i < 31; i++) {
                putDateInMap(dt, map);
                dt = dt.plusDays(1);
                parse(map);
                //create entity
                //fill it in the list
            }
            //insert all the data into database
        }
    }

    //method which insert list of entities into DB
    private void insertData(){
        //logic
    }

    private void putDateInMap(LocalDateTime dt, Map<String, String> map) {
        map.put("DD1", Integer.toString(dt.getDayOfMonth()));
        map.put("MM1", Integer.toString(dt.getMonthValue()));
        map.put("YYYY1", Integer.toString(dt.getYear()));
        map.put("DD2", Integer.toString(dt.getDayOfMonth()));
        map.put("MM2", Integer.toString(dt.getMonthValue()));
        map.put("YYYY2", Integer.toString(dt.getYear()));
    }

    private String parse(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://fxtop.com/en/historical-currency-converter.php?");
        for (String parameter : keySet) {
            stringBuilder.append("&");
            stringBuilder.append(parameter);
            stringBuilder.append("=");
            stringBuilder.append((map.get(parameter)));
        }
        stringBuilder.append("&B=1&P=&I=1&btnOK=Go%21");
        String url = stringBuilder.toString();
        Document doc = getDocumet(url);
        Elements elements = doc.getElementsByTag("tbody");
        Element element = elements.get(28);
        Elements childs = element.children().get(2).children();
        String mes = childs.get(1).toString();
        return getDigitfromString(mes);
    }

    private String getDigitfromString(String mes) {
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pat.matcher(mes.replaceAll("\\s+", ""));
        int k = 0;
        while (matcher.find()) {
            if (k == 2) {
                mes = matcher.group();
            }
            k++;
        }
        return mes;
    }

    private Document getDocumet(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
