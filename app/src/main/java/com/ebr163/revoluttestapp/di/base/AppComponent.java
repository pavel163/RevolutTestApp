package com.ebr163.revoluttestapp.di.base;

import com.ebr163.revoluttestapp.di.converter.ConverterComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bakht
 * on 17.02.2018.
 */
@Singleton
@Component
public interface AppComponent {

    ConverterComponent.Builder converterComponentBuilder();
}
