package com.cxsz.meal.meal.component;

import com.cxsz.meal.meal.view.viewInterface.BusinessManagementView;
import com.cxsz.meal.meal.view.viewInterface.MineMealView;

import common.model.MealInfoBaseManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class MealInfoPresenterModule {
    private BusinessManagementView businessManagementView;
    private MineMealView mineMealView;
    private MealInfoBaseManager mealInfoManager;

    public MealInfoPresenterModule(BusinessManagementView view, MineMealView mineMealView, MealInfoBaseManager mealInfoManager) {
        this.businessManagementView = view;
        this.mineMealView = mineMealView;
        this.mealInfoManager = mealInfoManager;
    }

    @Provides
    BusinessManagementView providerMealInfoView() {
        return businessManagementView;
    }

    @Provides
    MineMealView providerMineMealView() {
        return mineMealView;
    }

    @Provides
    MealInfoBaseManager providerMealInfoManager() {
        return mealInfoManager;
    }
}
