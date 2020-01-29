package com.cxsz.meal.diagnosis.component;

import com.cxsz.meal.diagnosis.view.fragment.IntelligentDiagnosisFragment;

import common.AppComponent;
import common.PerFragment;
import dagger.Component;

/**
 * Created by admin on 2017/3/12.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = IntelligentDiagnosisPresenterModule.class)
public interface IntelligentDiagnosisComponent {
    void injectIntelligentDiagnosisFragment(IntelligentDiagnosisFragment intelligentDiagnosisFragment);
}
