package com.cxsz.meal.main.component;

import com.cxsz.meal.main.view.viewInterface.SearchCardView;

import common.model.MealInfoBaseManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class SearchCardInfoPresenterModule {
    private SearchCardView view;

    private MealInfoBaseManager getMealInfoManager;

    public SearchCardInfoPresenterModule(SearchCardView view, MealInfoBaseManager mainDataManager) {
        this.view = view;
        this.getMealInfoManager = mainDataManager;
    }

    @Provides
    SearchCardView providerSearchCardView() {
        return view;
    }

    @Provides
    MealInfoBaseManager providerSearchCardManager() {
        return getMealInfoManager;
    }
}
