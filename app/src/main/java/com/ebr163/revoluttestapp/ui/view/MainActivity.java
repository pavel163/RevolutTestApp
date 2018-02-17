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
import com.ebr163.revoluttestapp.ui.adapter.ConverterAction;
import com.ebr163.revoluttestapp.ui.adapter.ConverterAdapter;
import com.ebr163.revoluttestapp.ui.presenter.ConverterPresenter;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements IConverterView {

    private RecyclerView recyclerView;
    private ConverterComponent component;
    private ConverterAdapter adapter;

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
        recyclerView = findViewById(R.id.listView);
        recyclerView.setHasFixedSize(true);
        adapter = new ConverterAdapter(BR.currency, R.layout.item_currency);
        adapter.addAction(BR.listener, new ConverterAction(recyclerView, presenter));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setCurrencies(List<Currency> currencies) {
        adapter.setData(currencies);
    }

    @Override
    public void showError() {

    }

    @Override
    public void moveItem(int from, int to) {
        adapter.moveItem(from, to);
        recyclerView.scrollToPosition(to);
    }
}
