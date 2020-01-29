package com.cxsz.meal.meal.component;

import com.cxsz.meal.meal.view.viewInterface.MineMealView;

import common.model.MealInfoBaseManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class MineMealPresenterModule {
    private MineMealView view;

    private MealInfoBaseManager mealInfoManager;

    public MineMealPresenterModule(MineMealView view, MealInfoBaseManager mealInfoManager) {
        this.view = view;
        this.mealInfoManager = mealInfoManager;
    }

    @Provides
    MineMealView providerMealInfoView() {
        return view;
    }

    @Provides
    MealInfoBaseManager providerMealInfoManager() {
        return mealInfoManager;
    }
}
