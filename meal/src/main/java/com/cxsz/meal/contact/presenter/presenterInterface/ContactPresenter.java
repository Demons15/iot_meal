package com.cxsz.meal.contact.presenter.presenterInterface;


public interface ContactPresenter {

    void RequestAddOrDelVoiceWhiteManager(String type, String msisdn, String operType, String phone);

    void RequestQueryVoiceWhiteList(String type, String msisdn);

    void RequestQueryAddWhiteCount(String type, String msisdn);

    void onRemoveInfo();
}
