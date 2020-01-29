package com.cxsz.meal.meal.component;

import com.cxsz.meal.meal.view.activity.MealDetailsActivity;

import common.AppComponent;
import common.PerActivity;
import dagger.Component;

/**
 * Created by admin on 2017/3/12.
 */
@PerActivity
@Component(dependencies = AppComponent.class , modules = MealDetailPresenterModule.class)
public interface MealDetailComponent {
    void injectMealDetailsActivity(MealDetailsActivity mealDetailsActivity);
}
