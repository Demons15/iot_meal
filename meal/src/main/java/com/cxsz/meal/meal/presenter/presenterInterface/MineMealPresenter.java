package com.cxsz.meal.meal.presenter.presenterInterface;


public interface MineMealPresenter {
    void RequestSimCardMealInfo( String iccid);

    void RequestSimCardMealList( String iccid);

    void onRemoveInfo();
}
