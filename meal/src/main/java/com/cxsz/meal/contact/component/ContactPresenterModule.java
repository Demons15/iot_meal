package com.cxsz.meal.contact.component;

import com.cxsz.meal.contact.view.viewInterface.ContactView;

import common.model.MealInfoBaseManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/12.
 */
@Module
public class ContactPresenterModule {
    private ContactView view;

    private MealInfoBaseManager mealInfoManager;

    public ContactPresenterModule(ContactView view, MealInfoBaseManager mealInfoManager) {
        this.view = view;
        this.mealInfoManager = mealInfoManager;
    }

    @Provides
    ContactView providerContactView() {
        return view;
    }

    @Provides
    MealInfoBaseManager providerContactlistManager() {
        return mealInfoManager;
    }
}
