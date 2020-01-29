package com.cxsz.meal.meal.presenter.presenterImpl;

import com.cxsz.meal.meal.presenter.presenterInterface.BusinessManagementPresenter;
import com.cxsz.meal.meal.model.modelImpl.BusinessManagementModelImpl;
import com.cxsz.meal.meal.model.modelInterface.BusinessManagementModel;
import com.cxsz.meal.meal.view.viewInterface.BusinessManagementView;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import common.model.MealGoodsBean;
import common.model.MealInfoBaseManager;
import common.model.ModuleHelper;
import common.model.callback.BaseCallBack;

public class BusinessManagementPresenterImpl implements BusinessManagementPresenter {
    private BusinessManagementView mBusinessManagementView;
    private BusinessManagementModel businessManagementModel;
    private MealInfoBaseManager mealInfoManager;
    private List<MealGoodsBean.MealGoodsBodyBean> mainMealGoodsList = new ArrayList();//主套餐列表
    private List<MealGoodsBean.MealGoodsBodyBean> voiceMealGoodsList = new ArrayList();//语音叠加包列表
    private List<MealGoodsBean.MealGoodsBodyBean> flowMealGoodsList = new ArrayList();//流量叠加包列表

    @Inject
    public BusinessManagementPresenterImpl(MealInfoBaseManager mealInfoManager, BusinessManagementView businessManagementView) {
        this.mealInfoManager = mealInfoManager;
        this.mBusinessManagementView = businessManagementView;
        businessManagementModel = new BusinessManagementModelImpl();
    }

    @Override
    public void RequestGetGoodsRelevance( String iccid) {
        if (iccid != null) {
            mBusinessManagementView.showUiLoadingView();
            businessManagementModel.RequestGetGoodsRelevance(mealInfoManager, iccid, new BaseCallBack() {
                @Override
                public void onSuccess(Object response) {
                    ArrayList<MealGoodsBean.MealGoodsBodyBean> body = (ArrayList<MealGoodsBean.MealGoodsBodyBean>) response;
                    if (!mainMealGoodsList.isEmpty()) {
                        mainMealGoodsList.clear();
                    }
                    if (!voiceMealGoodsList.isEmpty()) {
                        voiceMealGoodsList.clear();
                    }
                    if (!flowMealGoodsList.isEmpty()) {
                        flowMealGoodsList.clear();
                    }
                    for (int i = 0; i < body.size(); i++) {
                        MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean = body.get(i);
                        if (mealGoodsBodyBean.getCustomType().equals("1")) {
                            mainMealGoodsList.add(mealGoodsBodyBean);
                        } else if (mealGoodsBodyBean.getCustomType().equals("3")) {
                            if (mealGoodsBodyBean.getGoodsType().equals("O2")) {
                                voiceMealGoodsList.add(mealGoodsBodyBean);
                            } else if (mealGoodsBodyBean.getGoodsType().equals("O1")) {
                                flowMealGoodsList.add(mealGoodsBodyBean);
                            }
                        }
                    }
                    ModuleHelper.getInstance().setMainMealGoodsList(mainMealGoodsList);
                    ModuleHelper.getInstance().setFlowMealGoodsList(flowMealGoodsList);
                    ModuleHelper.getInstance().setVoiceMealGoodsList(voiceMealGoodsList);
                    mBusinessManagementView.ResponseGetGoodsRelevance(response);
                    mBusinessManagementView.closeUiLoadingView();
                }

                @Override
                public void onError(String error) {
                    if (error != null) {
                        mBusinessManagementView.showUiErrorInfo(error);
                    }
                    mBusinessManagementView.closeUiLoadingView();
                }
            });
        } else {
            mBusinessManagementView.showUiErrorInfo("iccid不能为空！");
        }
    }
}
