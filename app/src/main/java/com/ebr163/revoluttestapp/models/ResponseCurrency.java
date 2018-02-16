package com.ebr163.revoluttestapp.models;

import java.util.Map;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class ResponseCurrency {

    private final String base;
    private final String date;
    private final Map<String, Double> rates;

    public ResponseCurrency(String base, String date, Map<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
