package com.cxsz.meal.main.component;

import com.cxsz.meal.main.view.activity.MealMainUi;

import common.AppComponent;
import common.PerActivity;
import dagger.Component;

/**
 * Created by admin on 2017/3/12.
 */
@PerActivity
@Component(dependencies = AppComponent.class , modules = SearchCardInfoPresenterModule.class)
public interface SearchCardInfoComponent {
    void inject(MealMainUi activity);
}
