package com.cxsz.meal.contact.component;

import com.cxsz.meal.contact.view.ContactFragment;

import common.AppComponent;
import common.PerFragment;
import dagger.Component;

/**
 * Created by admin on 2017/3/12.
 */
@PerFragment
@Component(dependencies = AppComponent.class , modules = ContactPresenterModule.class)
public interface ContactComponent {
    void injectContactFragment(ContactFragment contactFragment);
}
