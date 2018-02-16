package com.ebr163.revoluttestapp.di.converter;

import com.ebr163.revoluttestapp.business.ConverterInteractor;
import com.ebr163.revoluttestapp.business.IConverterInteractor;
import com.ebr163.revoluttestapp.models.ResponseCurrency;
import com.ebr163.revoluttestapp.models.repository.CurrencyRepository;
import com.ebr163.revoluttestapp.models.repository.Repository;
import com.ebr163.revoluttestapp.service.HttpService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Bakht
 * on 17.02.2018.
 */
@Module
public class ConverterModule {

    @Provides
    @Converter
    IConverterInteractor provideConverterInteractor(Repository<ResponseCurrency> repository){
        return new ConverterInteractor(repository);
    }

    @Provides
    @Converter
    Repository<ResponseCurrency> provideRepository(HttpService httpService){
        return new CurrencyRepository(httpService);
    }
}
