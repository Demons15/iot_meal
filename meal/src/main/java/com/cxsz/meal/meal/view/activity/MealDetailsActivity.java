package com.cxsz.meal.meal.view.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxsz.meal.meal.component.DaggerMealDetailComponent;
import com.cxsz.meal.meal.component.MealDetailPresenterModule;
import com.cxsz.meal.meal.presenter.presenterImpl.MealDetailPresenterImpl;
import com.cxsz.meal.meal.view.viewInterface.MealDetailView;
import com.cxsz.meal.R;
import com.cxsz.meal.R2;
import com.cxsz.meal.meal.bean.ConfirmOrderResultBean;
import com.cxsz.meal.meal.bean.CreateOrderResultBean;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import common.base.BaseActivity;
import common.constant.KeyConstants;
import common.model.MealGoodsBean;
import common.model.MealInfoBaseManager;
import common.model.ModuleHelper;
import common.util.ToastUtil;

public class MealDetailsActivity extends BaseActivity implements MealDetailView, CompoundButton.OnCheckedChangeListener {

    @Inject
    MealDetailPresenterImpl mealDetailPresenter;

    @BindView(R2.id.base_left_iv)
    View leftIcon;

    @BindView(R2.id.pay_right_now)
    LinearLayout payRightNow;

    @BindView(R2.id.phone_number)
    TextView phoneNumber;

    @BindView(R2.id.package_title)
    TextView packageTitle;

    @BindView(R2.id.price_amount_area)
    View priceAmountArea; //叠加包显示区域

    @BindView(R2.id.price_amount)
    TextView priceAmount;


    @BindView(R2.id.monthly_average_cost_area)
    View monthlyAverageCostArea; //主套餐显示区域

    @BindView(R2.id.monthly_average_cost)
    TextView monthlyAverageCost;


    @BindView(R2.id.voice_contain_area)
    View voiceContainArea; //语音显示区域

    @BindView(R2.id.voice_package_detail_info)
    TextView voicePackageDetailInfo;

    @BindView(R2.id.flow_contain_area)
    View flowContainArea; //流量显示区域

    @BindView(R2.id.flow_package_detail_info)
    TextView flowPackageDetailInfo;


    @BindView(R2.id.term_of_validity_area)
    View termOfValidityArea; //主套餐才显示得有限期区域

    @BindView(R2.id.term_of_validity)
    TextView termOfValidity;

    @BindView(R2.id.to_be_paid)
    TextView toBePaid;

    @BindView(R2.id.confirmation_check_area)
    LinearLayout confirmationCheckArea;

    @BindView(R2.id.confirmation_check)
    CheckBox confirmationCheck;
    @BindView(R2.id.card_number)
    TextView cardNumber;
    protected Unbinder unbinder;

    private MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean;
    private IWXAPI api;

    @Override
    protected int getLayoutId() {
        return R.layout.package_details_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        api = ModuleHelper.getInstance().iwxapi;
        mealGoodsBodyBean = (MealGoodsBean.MealGoodsBodyBean) getIntent().getSerializableExtra(KeyConstants.PACKET_INFO);
        cardNumber.setText(mealGoodsBodyBean.getCardNumber());
        String customType = mealGoodsBodyBean.getCustomType();
        String goodsType = mealGoodsBodyBean.getGoodsType();
        if (customType.equals("1")) {
            termOfValidityArea.setVisibility(View.VISIBLE);
            priceAmountArea.setVisibility(View.GONE);
            monthlyAverageCostArea.setVisibility(View.VISIBLE);
        } else if (customType.equals("3")) {
            termOfValidityArea.setVisibility(View.GONE);
            priceAmountArea.setVisibility(View.VISIBLE);
            monthlyAverageCostArea.setVisibility(View.GONE);
            if (goodsType.equals("O2")) {
                flowContainArea.setVisibility(View.GONE);
            } else if (goodsType.equals("O1")) {
                voiceContainArea.setVisibility(View.GONE);
            }
        }
        initPackageInfo(mealGoodsBodyBean);
        DaggerMealDetailComponent.builder()
                .appComponent(getAppComponent()).mealDetailPresenterModule(new MealDetailPresenterModule(this, new MealInfoBaseManager(mDataManager)))
                .build()
                .injectMealDetailsActivity(this);
        confirmationCheck.setOnCheckedChangeListener(this);
        confirmationCheckArea.setBackground(getResources().getDrawable(R.drawable.purchase_confirmation_box));
    }

    /**
     * 初始化套餐信息
     *
     * @param mealGoodsBodyBean
     */
    private void initPackageInfo(MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean) {
        phoneNumber.setText(mealGoodsBodyBean.getCardNumber());
        packageTitle.setText(mealGoodsBodyBean.getShortName());
        priceAmount.setText(mealGoodsBodyBean.getUnitPrice() + "元");
        monthlyAverageCost.setText((double) Math.round((mealGoodsBodyBean.getUnitPrice() / mealGoodsBodyBean.getValidityDuration()) * 100) / 100 + "元/月");
        if ((int) mealGoodsBodyBean.getPackageTraffic() == -1 && (int) mealGoodsBodyBean.getCallDuration() > 0) {
            flowPackageDetailInfo.setText("无限流量");
            voicePackageDetailInfo.setText((int) mealGoodsBodyBean.getCallDuration() + "分钟");
        } else if ((int) mealGoodsBodyBean.getPackageTraffic() == 0 && (int) mealGoodsBodyBean.getCallDuration() > 0) {
            flowPackageDetailInfo.setVisibility(View.GONE);
            voicePackageDetailInfo.setText((int) mealGoodsBodyBean.getCallDuration() + "分钟");
        } else if ((int) mealGoodsBodyBean.getPackageTraffic() > 0 && (int) mealGoodsBodyBean.getCallDuration() > 0) {
            flowPackageDetailInfo.setText((int) mealGoodsBodyBean.getPackageTraffic() + "MB");
            voicePackageDetailInfo.setText((int) mealGoodsBodyBean.getCallDuration() + "分钟");
        } else if ((int) mealGoodsBodyBean.getPackageTraffic() > 0 && (int) mealGoodsBodyBean.getCallDuration() == 0) {
            flowPackageDetailInfo.setText((int) mealGoodsBodyBean.getPackageTraffic() + "MB");
            voicePackageDetailInfo.setVisibility(View.GONE);
        } else if ((int) mealGoodsBodyBean.getPackageTraffic() == -1 && (int) mealGoodsBodyBean.getCallDuration() == 0) {
            flowPackageDetailInfo.setText("无限流量");
            voicePackageDetailInfo.setVisibility(View.GONE);
        }
        termOfValidity.setText("有效期至：" + mealGoodsBodyBean.getPackageEndTime());
        toBePaid.setText("需支付：" + mealGoodsBodyBean.getUnitPrice() + "元");
    }

    @OnClick({R2.id.base_left_iv, R2.id.pay_right_now})
    public void onClick(View v) {
        if (v.getId() == R.id.base_left_iv) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (v.getId() == R.id.pay_right_now) {
            if (confirmationCheck.isChecked()) {
                mealDetailPresenter.RequestConfirmOrder(mealGoodsBodyBean);
            } else {
                ToastUtil.show(MealDetailsActivity.this, "请确认卡号!");
            }
        }
    }

    /**
     * 确认订单结果回调
     *
     * @param t
     * @param <T>
     */
    @Override
    public <T> void ResponseConfirmOrder(T t) {
        ConfirmOrderResultBean confirmOrderResultBean = (ConfirmOrderResultBean) t;
        mealDetailPresenter.RequestCreateOrder(mealGoodsBodyBean, confirmOrderResultBean);
    }

    /**
     * 创建订单结果回调
     *
     * @param t
     * @param <T>
     */
    @Override
    public <T> void ResponseCreateOrder(T t) {
        CreateOrderResultBean createOrderResultBean = (CreateOrderResultBean) t;
//        EventBus.getDefault().post(createOrderResultBean);
        mealDetailPresenter.RequestPayForOrder(api, ModuleHelper.getInstance().getWeChatAppId(), createOrderResultBean);
    }

    /**
     * 支付接口回调
     *
     * @param t
     * @param <T>
     */
    @Override
    public <T> void ResponsePayForOrder(T t) {
        ToastUtil.show(MealDetailsActivity.this, (String) t);
        finish();
    }

    @Override
    public void showUiLoadingView() {
        showProgressDialog();
    }

    @Override
    public void closeUiLoadingView() {
        hiddenProgressDialog();
    }

    @Override
    public void showSuccessInfo(String info) {

    }

    @Override
    public void showUiErrorInfo(String info) {
        if (info != null) {
            ToastUtil.show(this, info);
        }
    }

    @Override
    public void showUiErrorInfo(String tag, String info) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            confirmationCheckArea.setBackground(getResources().getDrawable(R.drawable.purchase_confirmation_box));
        } else {
            confirmationCheckArea.setBackground(getResources().getDrawable(R.drawable.purchase_confirmation_box_no_check));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
