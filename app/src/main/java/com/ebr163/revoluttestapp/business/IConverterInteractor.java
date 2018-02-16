package com.ebr163.revoluttestapp.business;

import com.ebr163.revoluttestapp.models.Currency;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public interface IConverterInteractor {

    Single<List<Currency>> firstLoad();
}
