package com.ebr163.revoluttestapp.ui.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.ebr163.revoluttestapp.business.IConverterInteractor;
import com.ebr163.revoluttestapp.models.Currency;
import com.ebr163.revoluttestapp.ui.view.IConverterView;

/**
 * Created by Bakht
 * on 17.02.2018.
 */
@InjectViewState
public class ConverterPresenter extends MvpPresenter<IConverterView> {

    private final IConverterInteractor converterInteractor;

    public ConverterPresenter(IConverterInteractor converterInteractor) {
        this.converterInteractor = converterInteractor;
        this.converterInteractor.setErrorListener(() -> getViewState().showError());
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        converterInteractor.firstLoad()
                .subscribe(currencies -> getViewState().setCurrencies(currencies),
                        throwable -> getViewState().showError());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        converterInteractor.unbind();
    }

    public void moveItem(int from, int to) {
        getViewState().moveItem(from, to);
    }

    public void setCurrentCurrency(Currency currentCurrency) {
        converterInteractor.setCurrentCurrency(currentCurrency);
    }
}
