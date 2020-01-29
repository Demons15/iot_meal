package com.cxsz.meal.diagnosis.component;

import com.cxsz.meal.diagnosis.view.viewInterface.IntelligentDiagnosisView;

import common.model.MealInfoBaseManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class IntelligentDiagnosisPresenterModule {
    private IntelligentDiagnosisView intelligentDiagnosisView;

    private MealInfoBaseManager mealInfoManager;

    public IntelligentDiagnosisPresenterModule(IntelligentDiagnosisView intelligentDiagnosisView, MealInfoBaseManager mealInfoManager) {
        this.intelligentDiagnosisView = intelligentDiagnosisView;
        this.mealInfoManager = mealInfoManager;
    }

    @Provides
    IntelligentDiagnosisView providerMessageView() {
        return intelligentDiagnosisView;
    }

    @Provides
    MealInfoBaseManager providerMealInfoManager() {
        return mealInfoManager;
    }
}
