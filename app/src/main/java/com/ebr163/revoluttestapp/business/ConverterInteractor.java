package com.ebr163.revoluttestapp.business;

import com.ebr163.revoluttestapp.models.Currency;
import com.ebr163.revoluttestapp.models.ResponseCurrency;
import com.ebr163.revoluttestapp.models.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class ConverterInteractor implements IConverterInteractor {

    private final Repository<ResponseCurrency> repository;
    private Map<String, Currency> currencies = new HashMap<>();
    private Disposable disposable;

    public ConverterInteractor(Repository<ResponseCurrency> repository) {
        this.repository = repository;
    }

    private void updateAll() {
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .switchMap(aLong -> repository.get()
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess(responseCurrency -> updateCurrency(responseCurrency))
                        .toObservable())
                .subscribe();

    }

    private void updateCurrency(ResponseCurrency responseCurrency) {
        for (Map.Entry<String, Double> entry : responseCurrency.getRates().entrySet()) {
            currencies.get(entry.getKey()).setRate(entry.getValue());
        }
    }

    @Override
    public Single<List<Currency>> firstLoad() {
        return repository.get()
                .subscribeOn(Schedulers.io())
                .flatMap(responseCurrency -> {
                    List<Currency> currencyList = new ArrayList<>();
                    for (Map.Entry<String, Double> entry : responseCurrency.getRates().entrySet()) {
                        Currency currency = new Currency(entry.getKey(), entry.getValue());
                        currencies.put(entry.getKey(), currency);
                        currencyList.add(currency);
                    }
                    return Single.just(currencyList);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess(currencies -> updateAll());
    }

    @Override
    public void unbind() {
        disposable.dispose();
    }
}
