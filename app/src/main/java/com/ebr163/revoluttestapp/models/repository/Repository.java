package com.ebr163.revoluttestapp.models.repository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public interface Repository<T> {

    Single<T> get();
}
