package com.ebr163.revoluttestapp.ui.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.ebr163.revoluttestapp.BR;
import com.ebr163.revoluttestapp.BaseApp;
import com.ebr163.revoluttestapp.R;
import com.ebr163.revoluttestapp.di.converter.ConverterComponent;
import com.ebr163.revoluttestapp.di.converter.ConverterModule;
import com.ebr163.revoluttestapp.models.Currency;
import com.ebr163.revoluttestapp.ui.presenter.ConverterPresenter;
import com.ebr163.universalrecyclerview.BindingAction;
import com.ebr163.universalrecyclerview.UniversalRecyclerViewAdapter;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements IConverterView {

    private RecyclerView listView;
    private ConverterComponent component;
    private UniversalRecyclerViewAdapter<Currency, BindingAction<Currency>> adapter;

    @InjectPresenter
    ConverterPresenter presenter;

    @ProvidePresenter
    ConverterPresenter providePresenter() {
        return new ConverterPresenter(component.interactor());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        component = BaseApp.appComponent.converterComponentBuilder()
                .module(new ConverterModule())
                .build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
    }

    private void initList() {
        listView = findViewById(R.id.listView);
        adapter = new UniversalRecyclerViewAdapter<>(BR.currency, R.layout.item_currency);
        listView.setAdapter(adapter);
    }

    @Override
    public void setCurrencies(List<Currency> currencies) {
        adapter.setData(currencies);
    }

    @Override
    public void showError() {

    }
}
