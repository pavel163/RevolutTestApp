package com.ebr163.revoluttestapp.models.repository;

import com.ebr163.revoluttestapp.models.ResponseCurrency;
import com.ebr163.revoluttestapp.service.HttpService;

import io.reactivex.Single;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class CurrencyRepository implements Repository<ResponseCurrency> {

    private final HttpService httpService;

    public CurrencyRepository(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public Single<ResponseCurrency> get() {
        return httpService.getHttpApi().getCurrency("EUR");
    }
}
