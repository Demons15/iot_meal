package com.cxsz.meal.contact.view.viewInterface;


import common.BaseUiView;
import common.model.MealCodeData;

public interface ContactView extends BaseUiView {
    void ResponseAddOrDelVoiceWhiteManager(MealCodeData t);

    void ResponseQueryVoiceWhiteList(MealCodeData t);

    void ResponseQueryAddWhiteCount(MealCodeData t);
}
