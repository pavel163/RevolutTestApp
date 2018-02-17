package com.ebr163.revoluttestapp.ui.view;

import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.ebr163.revoluttestapp.models.Currency;

import java.util.List;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public interface IConverterView extends MvpView {

    void setCurrencies(List<Currency> currencies);

    void showError();

    void moveItem(int from, int to);
}
