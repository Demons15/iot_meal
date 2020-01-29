package com.cxsz.meal.meal.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.cxsz.meal.meal.component.DaggerMealInfoComponent;
import com.cxsz.meal.meal.component.MealCommonUtils;
import com.cxsz.meal.meal.component.MealInfoPresenterModule;
import com.cxsz.meal.meal.presenter.presenterImpl.BusinessManagementPresenterImpl;
import com.cxsz.meal.meal.presenter.presenterImpl.MineMealPresenterImpl;
import com.cxsz.meal.meal.view.viewInterface.BusinessManagementView;
import com.cxsz.meal.meal.view.viewInterface.MineMealView;
import com.cxsz.meal.R;
import com.cxsz.meal.R2;
import com.cxsz.meal.meal.bean.SimPackageBean;
import com.cxsz.meal.meal.view.adapter.NoUseMealRecycleAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import common.base.BaseFragment;
import common.constant.KeyConstants;
import common.model.EventBean;
import common.model.MealInfoBaseManager;
import common.model.MealInfoBean;
import common.model.ModuleHelper;
import common.util.ToastUtil;
import common.widget.TopSuspensionNestedScrollView;

/**
 * 套餐信息界面
 */
public class MealInfoFragment extends BaseFragment implements BusinessManagementView, MineMealView {
    @BindView(R2.id.top_suspension_area)
    TopSuspensionNestedScrollView topSuspensionNestedScrollView;//滑动所属整个区域
    @BindView(R2.id.tab_check_group_area)
    LinearLayout tabCheckGroupArea;
    @BindView(R2.id.tab_check_group)
    RadioGroup tabCheckGroup;
    @BindView(R2.id.top_suspension_tab)
    LinearLayout topSuspensionTab; //顶部标题区域

    @BindView(R2.id.operator_information)
    ImageView operatorInformation;
    @BindView(R2.id.current_card_number)
    TextView cardNumber;
    @BindView(R2.id.device_icc_id)
    TextView iccidNumber;
    @BindView(R2.id.card_state_info)
    TextView cardStateInfo;

    @BindView(R2.id.flow_state_info)
    TextView flowStateInfo;
    @BindView(R2.id.voice_state_info)
    TextView voiceStateInfo;


    @BindView(R2.id.update_time)
    TextView upDateTime;
    @BindView(R2.id.meal_data_info)
    TextView mealDataInfo;
    @BindView(R2.id.meal_end_time)
    TextView mealEndTime;

    @BindView(R2.id.meal_detail_area)
    View mealDetailArea;
    @BindView(R2.id.voice_remain)
    TextView voiceRemain;
    @BindView(R2.id.voice_remain_unit)
    TextView voiceRemainUnit;
    @BindView(R2.id.voice_detail_progress)
    ProgressBar voiceDetailProgress;
    @BindView(R2.id.voice_all)
    TextView voiceAll;
    @BindView(R2.id.voice_has_use)
    TextView voiceHasUse;

    @BindView(R2.id.flow_remain)
    TextView flowRemain;
    @BindView(R2.id.flow_remain_unit)
    TextView flowRemainUnit;
    @BindView(R2.id.flow_detail_progress)
    ProgressBar flowDetailProgress;
    @BindView(R2.id.flow_all)
    TextView flowAll;
    @BindView(R2.id.flow_have_use)
    TextView flowHasUse;

    @BindView(R2.id.no_user_area)
    LinearLayout noUseArea;
    @BindView(R2.id.no_use_list)
    RecyclerView noUseList;

    @Inject
    BusinessManagementPresenterImpl businessManagementPresenter;

    @Inject
    MineMealPresenterImpl mineMealPresenter;

    //用来记录内层固定布局到屏幕顶部的距离
    private int mHeight;
    private int mealShowModel;

    public static MealInfoFragment getInstance(int toBuyMeal) {
        MealInfoFragment mealInfoFragment = new MealInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KeyConstants.MEAL_SHOW_MODEL, toBuyMeal);
        mealInfoFragment.setArguments(bundle);
        return mealInfoFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.meal_info_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
//        topSuspensionNestedScrollView.fling(0);
//        topSuspensionNestedScrollView.smoothScrollTo(0, 0);
//        topSuspensionNestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RefreshMsg(EventBean eventBean) {
        if (eventBean.getCode() == KeyConstants.REFRESH_MEAL) {
            topSuspensionNestedScrollView.fling(0);
            topSuspensionNestedScrollView.smoothScrollTo(0, 0);
            //查询卡的基本信息
            mineMealPresenter.RequestSimCardMealInfo(ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
            //卡查询当前卡正使用以及未启用套餐
            mineMealPresenter.RequestSimCardMealList(ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
        }
    }

    @Override
    protected void lazyFetchData() {
        Bundle arguments = getArguments();
        mealShowModel = arguments.getInt(KeyConstants.MEAL_SHOW_MODEL);
        topSuspensionNestedScrollView.setOnObservableScrollViewScrollChanged(this::onObservableScrollViewScrollChanged);
        DaggerMealInfoComponent.builder()
                .appComponent(getAppComponent())
                .mealInfoPresenterModule(new MealInfoPresenterModule(this, this, MealInfoBaseManager.getInstance(mDataManager)))
                .build()
                .injectMealInfoFragment(this);
        //查询卡的基本信息
        mineMealPresenter.RequestSimCardMealInfo(ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
        //卡查询当前卡正使用以及未启用套餐
        mineMealPresenter.RequestSimCardMealList(ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
        businessManagementPresenter.RequestGetGoodsRelevance(ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
    }

    @OnClick({R2.id.meal_buy, R2.id.current_card_number_area})
    public void onViewClick(View view) {
        if (view.getId() == R.id.meal_buy) {
            topSuspensionNestedScrollView.scrollTo(0, mHeight);
        } else if (view.getId() == R.id.current_card_number_area) {
            getActivity().finish();
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt) {
        if (tabCheckGroup != null && mHeight > 0) {
            if (t >= mHeight) {
                if (tabCheckGroup.getParent() != topSuspensionTab) {
                    tabCheckGroupArea.removeView(tabCheckGroup);
                    topSuspensionTab.addView(tabCheckGroup);
                }
            } else {
                if (tabCheckGroup.getParent() != tabCheckGroupArea) {
                    topSuspensionTab.removeView(tabCheckGroup);
                    tabCheckGroupArea.addView(tabCheckGroup);
                }
            }
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.package_purchase_tab) {
            loadRootFragment(R.id.business_management_area, PackagePurchaseFragment.getInstance(), true, true);
        } else if (checkedId == R.id.flow_overlay_pack_tab) {
            loadRootFragment(R.id.business_management_area, FlowOverlayPackFragment.getInstance(), true, true);
        } else if (checkedId == R.id.voice_overlay_packet_tab) {
            loadRootFragment(R.id.business_management_area, VoiceOverlayPacketFragment.getInstance(), true, true);
        }
    }

    @Override
    public <T> void ResponseGetGoodsRelevance(T t) {
        if (tabCheckGroup != null) {
            if (mealShowModel == KeyConstants.TO_BUY_MEAL) {
                tabCheckGroup.check(R.id.package_purchase_tab);
                loadRootFragment(R.id.business_management_area, PackagePurchaseFragment.getInstance(), true, true);
            } else if (mealShowModel == KeyConstants.TO_BUY_VOICE_MEAL) {
                tabCheckGroup.check(R.id.voice_overlay_packet_tab);
                loadRootFragment(R.id.business_management_area, VoiceOverlayPacketFragment.getInstance(), true, true);
            } else if (mealShowModel == KeyConstants.TO_BUY_FLOW_MEAL) {
                tabCheckGroup.check(R.id.flow_overlay_pack_tab);
                loadRootFragment(R.id.business_management_area, FlowOverlayPackFragment.getInstance(), true, true);
            }
            tabCheckGroup.setOnCheckedChangeListener(this::onCheckedChanged);
        }
        if (null != tabCheckGroupArea && null != topSuspensionTab) {
            ViewTreeObserver viewTreeObserver = tabCheckGroupArea.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    tabCheckGroupArea.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mHeight = tabCheckGroupArea.getTop();
                }
            });
            topSuspensionTab.removeAllViews();
        }
    }


    @Override
    public void showUiLoadingView() {
        showLoadingDialog();
    }

    @Override
    public void closeUiLoadingView() {
        hideLoadingDialog();
    }

    @Override
    public void showSuccessInfo(String info) {
        if (getActivity() != null) {
            ToastUtil.show(getActivity(), info);
        }
    }

    @Override
    public void showUiErrorInfo(String info) {
        if (getActivity() != null) {
            ToastUtil.show(getActivity(), info);
        }
    }

    @Override
    public void showUiErrorInfo(String tag, String info) {

    }

    //语音流量详情
    private void initMealDetail(MealInfoBean.BodyBean mealInfoBean) {
        if (null != mealInfoBean.getSimFlow()) {
            double packageTraffic = mealInfoBean.getSimFlow().getPackageTraffic();//所有流量
            double useTraffic = mealInfoBean.getSimFlow().getUseTraffic();//已使用的流量
            double packageCallDuration = mealInfoBean.getSimFlow().getPackageCallDuration();//所有语音时长
            double useCallDuration = mealInfoBean.getSimFlow().getUseCallDuration();//已使用的语音时长
            if (packageCallDuration > 0 && packageTraffic > 0) {//流量语音都是固定的值
                flowDetailProgress.setProgress((int) (useTraffic / packageTraffic * 100));
                MealCommonUtils.formatFlowSize((packageTraffic - useTraffic), flowRemain);
                MealCommonUtils.formatFlowSizeAndUnit(packageTraffic, flowAll);
                if (packageTraffic < 1024) {
                    flowRemainUnit.setText("MB");
                } else if (packageTraffic > 1024) {
                    flowRemainUnit.setText("G");
                }
                MealCommonUtils.formatFlowSizeAndUnit(useTraffic, flowHasUse);
                voiceDetailProgress.setProgress((int) (useCallDuration / packageCallDuration * 100));
                voiceRemain.setText((int) (packageCallDuration - useCallDuration) + "");
                voiceAll.setText((int) packageCallDuration + "分钟");
                voiceHasUse.setText((int) useCallDuration + "分钟");
            } else if (packageTraffic == -1 && packageCallDuration > 0) {//无限流量,语音有数值
                flowDetailProgress.setProgress(0);
                flowRemain.setText("无限流量");
                flowAll.setText("无限流量");
                flowHasUse.setText("无限流量");
                voiceDetailProgress.setProgress((int) (useCallDuration / packageCallDuration * 100));
                voiceRemain.setText((int) (packageCallDuration - useCallDuration) + "");
                voiceAll.setText((int) packageCallDuration + "分钟");
                voiceHasUse.setText((int) useCallDuration + "分钟");
            } else if (packageTraffic == 0 && packageCallDuration > 0) {
                flowDetailProgress.setProgress(0);
                MealCommonUtils.formatFlowSize((packageTraffic - useTraffic), flowRemain);
                MealCommonUtils.formatFlowSizeAndUnit(packageTraffic, flowAll);
                if (packageTraffic < 1024) {
                    flowRemainUnit.setText("MB");

                } else if (packageTraffic > 1024) {
                    flowRemainUnit.setText("G");
                }
                MealCommonUtils.formatFlowSizeAndUnit(useTraffic, flowHasUse);

                voiceDetailProgress.setProgress((int) (useCallDuration / packageCallDuration * 100));
                voiceRemain.setText((int) (packageCallDuration - useCallDuration) + "");
                voiceAll.setText((int) packageCallDuration + "分钟");
                voiceHasUse.setText((int) useCallDuration + "分钟");

            } else if (packageTraffic > 0 && packageCallDuration == 0) {
                flowDetailProgress.setProgress((int) (useTraffic / packageTraffic * 100));
                MealCommonUtils.formatFlowSize((packageTraffic - useTraffic), flowRemain);
                MealCommonUtils.formatFlowSizeAndUnit(packageTraffic, flowAll);
                if (packageTraffic < 1024) {
                    flowRemainUnit.setText("MB");

                } else if (packageTraffic > 1024) {
                    flowRemainUnit.setText("G");
                }
                MealCommonUtils.formatFlowSizeAndUnit(useTraffic, flowHasUse);

                voiceDetailProgress.setProgress(0);
                voiceRemain.setText(0 + "");
                voiceAll.setText(0 + "分钟");
                voiceHasUse.setText(0 + "分钟");

            } else if (packageTraffic == 0 && packageCallDuration == 0) {
                flowDetailProgress.setProgress(0);
                MealCommonUtils.formatFlowSize((packageTraffic - useTraffic), flowRemain);
                MealCommonUtils.formatFlowSizeAndUnit(packageTraffic, flowAll);
                if (packageTraffic < 1024) {
                    flowRemainUnit.setText("MB");

                } else if (packageTraffic > 1024) {
                    flowRemainUnit.setText("G");
                }
                MealCommonUtils.formatFlowSizeAndUnit(useTraffic, flowHasUse);

                voiceDetailProgress.setProgress(0);
                voiceRemain.setText(0 + "");
                voiceAll.setText(0 + "分钟");
                voiceHasUse.setText(0 + "分钟");
            } else if (packageTraffic == -1 && packageCallDuration == 0) {
                flowDetailProgress.setProgress(0);
                flowRemain.setText("无限流量");
                flowAll.setText("无限流量");
                flowHasUse.setText("无限流量");

                voiceDetailProgress.setProgress(0);
                voiceRemain.setText(0 + "");
                voiceAll.setText(0 + "分钟");
                voiceHasUse.setText(0 + "分钟");
            }
        }
    }

    private void initCardState(MealInfoBean.BodyBean mealInfoBean) {
        String simState = mealInfoBean.getSimState();
        if (simState != null) {
            if (simState.equals("1")) {
                cardStateInfo.setText("未激活");
            } else if (simState.equals("2")) {
                cardStateInfo.setText("正使用");
            } else if (simState.equals("3")) {
                cardStateInfo.setText("停机");
            } else if (simState.equals("4")) {
                cardStateInfo.setText("欠费");
            } else if (simState.equals("5")) {
                cardStateInfo.setText("解除集团代付");
            } else if (simState.equals("6")) {
                cardStateInfo.setText("销号");
            } else if (simState.equals("7")) {
                cardStateInfo.setText("测试期");
            } else if (simState.equals("8")) {
                cardStateInfo.setText("沉默期");
            } else if (simState.equals("9")) {
                cardStateInfo.setText("库存");
            } else if (simState.equals("10")) {
                cardStateInfo.setText("出库停机");
            } else if (simState.equals("11")) {
                cardStateInfo.setText("审核停机");
            }
        }
    }

    @Override
    public <T> void ResponseSimCardMealInfo(T t) {
        MealInfoBean.BodyBean bodyBean = (MealInfoBean.BodyBean) t;
        if (null != cardNumber) {
            cardNumber.setText(bodyBean.getCardNumber() + "");
        }
        if (null != iccidNumber) {
            iccidNumber.setText(bodyBean.getIccid() + "");
        }
        if (null != cardStateInfo) {
            initCardState(bodyBean);
        }

        String networkState = bodyBean.getNetworkState();
        if (networkState != null) {
            if (networkState.equals("0.0") || networkState.equals("0")) {
                flowStateInfo.setText("停用");
            } else if (networkState.equals("1") || networkState.equals("1.0")) {
                flowStateInfo.setText("启用");
            }
        }
        String voiceState = bodyBean.getVoiceState();
        if (voiceState != null) {
            if (voiceState.equals("0.0") || voiceState.equals("0")) {
                voiceStateInfo.setText("停用");
            } else if (voiceState.equals("1") || voiceState.equals("1.0")) {
                voiceStateInfo.setText("启用");
            }
        }
        if (bodyBean.getCarrierOperatorName().contains("移动")) {
            operatorInformation.setImageResource(R.mipmap.china_mobile);
        } else if (bodyBean.getCarrierOperatorName().contains("联通")) {
            operatorInformation.setImageResource(R.mipmap.china_unicom);
        } else if (bodyBean.getCarrierOperatorName().contains("电信")) {
            operatorInformation.setImageResource(R.mipmap.china_telecom);
        }
        if (bodyBean.getSimFlow() != null) {
            upDateTime.setText("更新时间" + bodyBean.getSimFlow().getUpdateTime());
        }
        mealDataInfo.setText(bodyBean.getGoodsName());
        if (mealDetailArea.getVisibility() == View.VISIBLE) {
            initMealDetail(bodyBean);
        }
    }

    @Override
    public <T> void ResponseSimCardMealList(T t) {
        ArrayList<SimPackageBean.BodyBean> bodyBeans = (ArrayList<SimPackageBean.BodyBean>) t;
        SimPackageBean.BodyBean mealInfoBean = null;
        List<SimPackageBean.BodyBean> beans = new ArrayList<>();
        for (int i = 0; i < bodyBeans.size(); i++) {
            SimPackageBean.BodyBean bodyBean = bodyBeans.get(i);
            int packageState = bodyBean.getPackageState();
            if (packageState == 0) {
                beans.add(bodyBean);
            } else if (packageState == 1) {
                mealInfoBean = bodyBean;
            }
        }
        if (mealInfoBean == null) {
            mealDetailArea.setVisibility(View.GONE);
        } else {
            mealDetailArea.setVisibility(View.VISIBLE);
            mealEndTime.setText("套餐将于" + mealInfoBean.getPackageEndTime() + "到期");
        }

        if (beans.size() > 0) {
            LinearLayoutManager packagePurchaseLayoutManager = new LinearLayoutManager(getActivity());
            packagePurchaseLayoutManager.setOrientation(RecyclerView.VERTICAL);
            noUseList.setLayoutManager(packagePurchaseLayoutManager);
            NoUseMealRecycleAdapter packagePurchaseRecycleAdapter = new NoUseMealRecycleAdapter(getActivity(), beans);
            noUseList.setAdapter(packagePurchaseRecycleAdapter);
            noUseArea.setVisibility(View.VISIBLE);
        } else {
            noUseArea.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
