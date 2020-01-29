package com.cxsz.meal.contact.presenter.presenterImpl;

import com.cxsz.meal.contact.model.modelImpl.ContactModelImpl;
import com.cxsz.meal.contact.model.modelInterface.ContactModel;
import com.cxsz.meal.contact.presenter.presenterInterface.ContactPresenter;
import com.cxsz.meal.contact.view.viewInterface.ContactView;
import javax.inject.Inject;

import common.model.MealCodeData;
import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public class ContactPresenterImpl implements ContactPresenter {
    private MealInfoBaseManager mealInfoManager;
    private ContactView contactView;
    private ContactModel intelligentDiagnosisModel;

    @Inject
    public ContactPresenterImpl(MealInfoBaseManager mealInfoManager, ContactView contactView) {
        this.mealInfoManager = mealInfoManager;
        this.contactView = contactView;
        intelligentDiagnosisModel = new ContactModelImpl();
    }

    @Override
    public void RequestAddOrDelVoiceWhiteManager(String type, String msisdn, String operType, String phone) {
        contactView.showUiLoadingView();
        intelligentDiagnosisModel.RequestAddOrDelVoiceWhiteManager(mealInfoManager, type, msisdn, operType, phone, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                contactView.ResponseAddOrDelVoiceWhiteManager(bodyBean);
                contactView.closeUiLoadingView();
                if (bodyBean.getMessage() != null) {
                    contactView.showSuccessInfo(bodyBean.getMessage());
                }
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    contactView.showUiErrorInfo(error);
                }
                contactView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestQueryVoiceWhiteList(String type, String msisdn) {
        contactView.showUiLoadingView();
        intelligentDiagnosisModel.RequestQueryVoiceWhiteList(mealInfoManager, type, msisdn, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                contactView.ResponseQueryVoiceWhiteList(bodyBean);
                contactView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    contactView.showUiErrorInfo(error);
                }
                contactView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestQueryAddWhiteCount(String type, String msisdn) {
        contactView.showUiLoadingView();
        intelligentDiagnosisModel.RequestQueryAddWhiteCount(mealInfoManager, type, msisdn, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                contactView.ResponseQueryAddWhiteCount(bodyBean);
                contactView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    contactView.showUiErrorInfo(error);
                }
                contactView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void onRemoveInfo() {
        intelligentDiagnosisModel.onRemoveInfo();
    }
}
