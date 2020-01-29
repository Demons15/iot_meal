package com.cxsz.meal.meal.component;

import com.cxsz.meal.meal.view.viewInterface.MealDetailView;
import javax.inject.Inject;

import common.model.MealInfoBaseManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class MealDetailPresenterModule {
    private MealDetailView view;

    private MealInfoBaseManager mealDetailManager;
    @Inject
    public MealDetailPresenterModule(MealDetailView view,MealInfoBaseManager mealDetailManager) {
        this.mealDetailManager = mealDetailManager;
        this.view = view;
    }

    @Provides
    MealDetailView providerMealDetailView() {
        return view;
    }

    @Provides
    MealInfoBaseManager providerMealDetailManager() {
        return mealDetailManager;
    }
}
