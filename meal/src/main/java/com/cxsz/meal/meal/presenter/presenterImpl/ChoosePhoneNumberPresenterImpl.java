package com.cxsz.meal.meal.presenter.presenterImpl;

import com.cxsz.meal.meal.presenter.presenterInterface.ChoosePhoneNumberPresenter;
import com.cxsz.meal.meal.bean.ChooseNumberBean;
import com.cxsz.meal.meal.model.modelImpl.ChoosePhoneNumberModelImpl;
import com.cxsz.meal.meal.model.modelInterface.ChoosePhoneNumberModel;
import com.cxsz.meal.meal.view.viewInterface.ChoosePhoneNumberView;
import java.util.List;

import javax.inject.Inject;

import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public class ChoosePhoneNumberPresenterImpl implements ChoosePhoneNumberPresenter {
    private ChoosePhoneNumberView mChoosePhoneNumberView;
    private final ChoosePhoneNumberModel choosePhoneNumberModel;
    private MealInfoBaseManager mealInfoManager;

    @Inject
    public ChoosePhoneNumberPresenterImpl(MealInfoBaseManager mealInfoManager, ChoosePhoneNumberView choosePhoneNumberView) {
        this.mealInfoManager = mealInfoManager;
        this.mChoosePhoneNumberView = choosePhoneNumberView;
        choosePhoneNumberModel = new ChoosePhoneNumberModelImpl();
    }

    @Override
    public void RequestQueryChooseNumber(String tempIccid, String pageNumber, String number, String pageSize) {
        mChoosePhoneNumberView.showUiLoadingView();
        choosePhoneNumberModel.RequestQueryChooseNumber(mealInfoManager, tempIccid, pageNumber, number, pageSize, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                List<ChooseNumberBean.BodyBean.ListBean> listBeans = (List<ChooseNumberBean.BodyBean.ListBean>) response;
                mChoosePhoneNumberView.ResponseQueryChooseNumber(listBeans);
                mChoosePhoneNumberView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    mChoosePhoneNumberView.showUiErrorInfo(error);
                }
                mChoosePhoneNumberView.closeUiLoadingView();
            }
        });
    }
}
