package com.ebr163.revoluttestapp.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bakht
 * on 17.02.2018.
 */
@Singleton
public class HttpService {

    private HttpApi httpApi;

    @Inject
    HttpService(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://revolut.duckdns.org/")
                .build();

        httpApi = retrofit.create(HttpApi.class);
    }

    public HttpApi getHttpApi() {
        return httpApi;
    }
}
