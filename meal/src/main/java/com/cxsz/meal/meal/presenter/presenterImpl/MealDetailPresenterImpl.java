package com.cxsz.meal.meal.presenter.presenterImpl;

import com.cxsz.meal.meal.presenter.presenterInterface.MealDetailPresenter;
import com.cxsz.meal.meal.bean.ConfirmOrderResultBean;
import com.cxsz.meal.meal.bean.CreateOrderResultBean;
import com.cxsz.meal.meal.model.modelImpl.MealDetailModelImpl;
import com.cxsz.meal.meal.model.modelInterface.MealDetailModel;
import com.cxsz.meal.meal.view.viewInterface.MealDetailView;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import javax.inject.Inject;

import common.model.MealGoodsBean;
import common.model.MealInfoBaseManager;
import common.model.ResponseCallBack;
import common.model.callback.BaseCallBack;

public class MealDetailPresenterImpl implements MealDetailPresenter {
    private MealDetailView mMealDetailView;
    private final MealDetailModel mealDetailModel;
    private MealInfoBaseManager mealDetailManager;

    @Inject
    public MealDetailPresenterImpl(MealInfoBaseManager mealDetailManager, MealDetailView mealDetailView) {
        this.mealDetailManager = mealDetailManager;
        this.mMealDetailView = mealDetailView;
        mealDetailModel = new MealDetailModelImpl();
    }

    @Override
    public void RequestConfirmOrder(MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean) {
        mMealDetailView.showUiLoadingView();
        mealDetailModel.RequestConfirmOrder(mealDetailManager, mealGoodsBodyBean, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                mMealDetailView.ResponseConfirmOrder(response);
                mMealDetailView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    mMealDetailView.showUiErrorInfo(error);
                }
                mMealDetailView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestCreateOrder(MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean, ConfirmOrderResultBean confirmOrderResultBean) {
        mMealDetailView.showUiLoadingView();
        mealDetailModel.RequestCreateOrder(mealDetailManager, mealGoodsBodyBean, confirmOrderResultBean, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                mMealDetailView.ResponseCreateOrder(response);
                mMealDetailView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    mMealDetailView.showUiErrorInfo(error);
                }
                mMealDetailView.closeUiLoadingView();
            }

        });
    }

    @Override
    public void RequestPayForOrder(IWXAPI api, String weChatAppId, CreateOrderResultBean createOrderResultBean) {
        mealDetailModel.RequestPayForOrder(api, weChatAppId, createOrderResultBean, new ResponseCallBack() {

            @Override
            public void onResponse(String info) {
                mMealDetailView.ResponsePayForOrder(info);
            }
        });
    }

    @Override
    public void onRemoveInfo() {
        mealDetailModel.onRemoveInfo();
    }
}
