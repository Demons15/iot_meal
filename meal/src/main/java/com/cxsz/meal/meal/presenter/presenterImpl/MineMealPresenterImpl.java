package com.cxsz.meal.meal.presenter.presenterImpl;

import com.cxsz.meal.meal.bean.SimPackageBean;
import com.cxsz.meal.meal.model.modelImpl.MineMealModelImpl;
import com.cxsz.meal.meal.model.modelInterface.MineMealModel;
import com.cxsz.meal.meal.presenter.presenterInterface.MineMealPresenter;
import com.cxsz.meal.meal.view.viewInterface.MineMealView;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;

import javax.inject.Inject;

import common.model.MealInfoBaseManager;
import common.model.MealInfoBean;
import common.model.callback.BaseCallBack;

public class MineMealPresenterImpl implements MineMealPresenter {
    private MineMealView mMineMealView;
    private MineMealModel mineMealModel;
    private MealInfoBaseManager mealInfoManager;
    private ArrayList<SimPackageBean.BodyBean> simPackageList = new ArrayList<SimPackageBean.BodyBean>();

    @Inject
    public MineMealPresenterImpl(MealInfoBaseManager mealInfoManager, MineMealView mineMealView) {
        this.mealInfoManager = mealInfoManager;
        mineMealModel = new MineMealModelImpl();
        this.mMineMealView = mineMealView;
    }

    @Override
    public void RequestSimCardMealInfo(String iccid) {
        mMineMealView.showUiLoadingView();
        mineMealModel.RequestSimCardMealInfo(mealInfoManager, iccid, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealInfoBean.BodyBean bodyBean = (MealInfoBean.BodyBean) response;
                mMineMealView.ResponseSimCardMealInfo(bodyBean);
                mMineMealView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    mMineMealView.showUiErrorInfo(error);
                }
                mMineMealView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestSimCardMealList(String iccid) {
        mMineMealView.showUiLoadingView();
        mineMealModel.RequestSimCardMealList(mealInfoManager, iccid, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                ArrayList dataBody = (ArrayList) response;
                if (!simPackageList.isEmpty()) {
                    simPackageList.clear();
                }
                for (int i = 0; i < dataBody.size(); i++) {
                    LinkedTreeMap mapData = (LinkedTreeMap) dataBody.get(i);
                    SimPackageBean.BodyBean bodyBean = new SimPackageBean.BodyBean();
                    bodyBean.getSimPackageInfo(mapData, bodyBean);
                    simPackageList.add(bodyBean);
                }
                mMineMealView.ResponseSimCardMealList(simPackageList);
                mMineMealView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    mMineMealView.showUiErrorInfo(error);
                }
                mMineMealView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void onRemoveInfo() {
        mineMealModel.onRemoveInfo();
    }
}
