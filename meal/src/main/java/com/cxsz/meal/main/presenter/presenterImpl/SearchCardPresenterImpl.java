package com.cxsz.meal.main.presenter.presenterImpl;

import com.cxsz.meal.main.model.modelImpl.SearchCardModelImpl;
import com.cxsz.meal.main.model.modelInterface.SearchCardModel;
import com.cxsz.meal.main.presenter.presenterInterface.SearchCardPresenter;
import com.cxsz.meal.main.view.viewInterface.SearchCardView;
import javax.inject.Inject;

import common.model.MealInfoBaseManager;
import common.model.MealInfoBean;
import common.model.callback.BaseCallBack;

public class SearchCardPresenterImpl implements SearchCardPresenter {

    private MealInfoBaseManager getMealInfoManager;
    private SearchCardView searchCardView;
    private final SearchCardModel searchCardModel;

    @Inject
    public SearchCardPresenterImpl(MealInfoBaseManager getMealInfoManager, SearchCardView searchCardView) {
        this.getMealInfoManager = getMealInfoManager;
        this.searchCardView = searchCardView;
        searchCardModel = new SearchCardModelImpl();
    }

    @Override
    public void requestGetMealInfo(String cardNumber) {
        searchCardView.showUiLoadingView();
        searchCardModel.requestGetMealInfo(getMealInfoManager, cardNumber, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealInfoBean.BodyBean bodyBean = (MealInfoBean.BodyBean) response;
                searchCardView.responseGetMealInfo(bodyBean);
                searchCardView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (null != error) {
                    searchCardView.showUiErrorInfo(error);
                }
                searchCardView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void onRemoveInfo() {
        searchCardModel.onRemoveInfo();
    }
}
