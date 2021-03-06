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
import io.reactivex.SingleSource;
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
    private Currency currentCurrency;
    private OnErrorListener errorListener;

    public ConverterInteractor(Repository<ResponseCurrency> repository) {
        this.repository = repository;
    }

    private void updateAll() {
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .switchMap(aLong -> repository.get()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(this::updateCurrency)
                        .toObservable())
                .subscribe(responseCurrency -> {},
                        throwable -> {
                            if (errorListener != null) {
                                errorListener.showError();
                            }
                        });

    }

    private void updateCurrency(ResponseCurrency responseCurrency) {
        if (!currentCurrency.getName().equals("EUR")) {
            currentCurrency.setRate(responseCurrency.getRates().get(currentCurrency.getName()));
        }
        for (Map.Entry<String, Double> entry : responseCurrency.getRates().entrySet()) {
            Currency currency = currencies.get(entry.getKey());
            currency.setRate(entry.getValue());
            if (!entry.getKey().equals(currentCurrency.getName())) {
                currency.setAmount(currentCurrency.getAmount() / currentCurrency.getRate() * entry.getValue());
            }
        }
        if (!currentCurrency.getName().equals("EUR")) {
            currencies.get("EUR").setAmount(currentCurrency.getAmount() / currentCurrency.getRate());
        }
    }

    @Override
    public Single<List<Currency>> firstLoad() {
        return repository.get()
                .subscribeOn(Schedulers.io())
                .flatMap(this::createCurrencyList)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::createEuroCurrency)
                .doAfterSuccess(currencies -> updateAll());
    }

    private void createEuroCurrency(List<Currency> currencies1) {
        currentCurrency = new Currency("EUR", 1);
        currentCurrency.setAmount(1);
        currencies1.add(0, currentCurrency);
        currencies.put("EUR", currentCurrency);
    }

    private SingleSource<List<Currency>> createCurrencyList(ResponseCurrency responseCurrency) {
        List<Currency> currencyList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : responseCurrency.getRates().entrySet()) {
            Currency currency = new Currency(entry.getKey(), entry.getValue());
            currencies.put(entry.getKey(), currency);
            currencyList.add(currency);
        }
        return Single.just(currencyList);
    }

    @Override
    public void unbind() {
        disposable.dispose();
    }

    @Override
    public void setCurrentCurrency(Currency currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    @Override
    public void setErrorListener(OnErrorListener errorListener) {
        this.errorListener = errorListener;
    }
}
