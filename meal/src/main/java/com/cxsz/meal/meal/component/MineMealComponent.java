package com.cxsz.meal.meal.component;

import com.cxsz.meal.meal.view.fragment.MealInfoFragment;

import common.AppComponent;
import common.PerFragment;
import dagger.Component;

/**
 * Created by admin on 2017/3/12.
 */
@PerFragment
@Component(dependencies = AppComponent.class , modules = MealInfoPresenterModule.class)
public interface MineMealComponent {
    void injectMealInfoFragment(MealInfoFragment mealInfoFragment);
}
