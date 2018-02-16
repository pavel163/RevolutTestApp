package com.ebr163.revoluttestapp.service;

import com.ebr163.revoluttestapp.models.ResponseCurrency;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public interface HttpApi {

    @GET("/latest")
    Single<ResponseCurrency> getCurrency(@Query("base") String baseCurrency);
}
