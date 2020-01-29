package com.cxsz.meal.main.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cxsz.meal.contact.view.ContactFragment;
import com.cxsz.meal.main.component.DaggerSearchCardInfoComponent;
import com.cxsz.meal.main.component.SearchCardInfoPresenterModule;
import com.cxsz.meal.main.presenter.presenterImpl.SearchCardPresenterImpl;
import com.cxsz.meal.main.view.viewInterface.SearchCardView;
import com.cxsz.meal.main.view.widget.BottomBar;
import com.cxsz.meal.meal.view.fragment.MealInfoFragment;
import com.cxsz.meal.R;
import com.cxsz.meal.R2;
import com.cxsz.meal.main.view.widget.BottomBarTab;
import com.cxsz.meal.main.view.widget.RealNameNoticeDialog;
import com.cxsz.meal.diagnosis.view.fragment.IntelligentDiagnosisFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.base.BaseActivity;
import common.constant.KeyConstants;
import common.model.EventBean;
import common.model.MealInfoBaseManager;
import common.model.MealInfoBean;
import common.model.ModuleHelper;
import common.util.ToastUtil;
import fragmentation.SupportFragment;

/**
 * 卡相关信息主界面
 */
public class MealMainUi extends BaseActivity implements RealNameNoticeDialog.RealNameListener, SearchCardView {
    @BindView(R2.id.assistant_base_title_name)
    TextView baseTitleName;
    @BindView(R2.id.meal_menu_group)
    BottomBar mealMenuGroup;
    private RealNameNoticeDialog realNameNoticeDialog;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private SupportFragment[] mFragments = new SupportFragment[3];
    @Inject
    SearchCardPresenterImpl searchCardPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.meal_main_ui_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this);
        baseTitleName.setText(R.string.app_name);
        mealMenuGroup.addItem(new BottomBarTab(MealMainUi.this, R.mipmap.meal_info, getString(R.string.meal_info)))
                .addItem(new BottomBarTab(MealMainUi.this, R.mipmap.white_list, getString(R.string.contact)))
                .addItem(new BottomBarTab(MealMainUi.this, R.mipmap.intel_diagnosis, getString(R.string.intelligent_diagnosis)));
        mealMenuGroup.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
            }
        });
        DaggerSearchCardInfoComponent.builder()
                .appComponent(getAppComponent()).searchCardInfoPresenterModule(new SearchCardInfoPresenterModule(this, MealInfoBaseManager.getInstance(mDataManager)))
                .build()
                .inject(this);

        searchCardPresenter.requestGetMealInfo(ModuleHelper.getInstance().getNumber());
    }

    private void initFragments() {
        SupportFragment firstFragment = findFragment(MealInfoFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = MealInfoFragment.getInstance(KeyConstants.TO_BUY_MEAL);
            mFragments[SECOND] = ContactFragment.getInstance(ModuleHelper.getInstance().getMealInfoBeans());
            mFragments[THIRD] = IntelligentDiagnosisFragment.getInstance(ModuleHelper.getInstance().getMealInfoBeans());

            loadMultipleRootFragment(R.id.meal_fragment, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(ContactFragment.class);
            mFragments[THIRD] = findFragment(IntelligentDiagnosisFragment.class);
        }
    }

    @OnClick({R2.id.assistant_base_left_icon})
    public void onClick(View v) {
        if (v.getId() == R.id.assistant_base_left_icon) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public void realName(MealInfoBean.BodyBean mealInfoBeans) {
        ModuleHelper.doStartApplicationWithPackageName(MealMainUi.this,KeyConstants.REAL_NAME,KeyConstants.MEAL_INFO_BEAN,mealInfoBeans);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RefreshMsg(EventBean eventBean) {
        if (eventBean.getCode() == KeyConstants.FINISH_MEAL_VIEW) {
            finish();
        }
    }

    @Override
    public void responseGetMealInfo(MealInfoBean.BodyBean bodyBean) {
        if (bodyBean.getIccid() != null) {
            ModuleHelper.getInstance().setNumber(bodyBean.getCardNumber());
            ModuleHelper.getInstance().setMealInfoBeans(bodyBean);
            initFragments();

            realNameNoticeDialog = new RealNameNoticeDialog(MealMainUi.this);
            realNameNoticeDialog.setOnRealNameListener(this);
            int realName = ModuleHelper.getInstance().getMealInfoBeans().getRealname();
            if (realName == 1) { //1为非定向 ,弹出实名认证弹窗
                realNameNoticeDialog.setRealNameInfo(ModuleHelper.getInstance().getMealInfoBeans());
                realNameNoticeDialog.show();
            } else if (realName == 2) { //定向,跳转通讯录界面

            }
        } else {
            ToastUtil.show(MealMainUi.this, "无效号码！");
        }
    }

    @Override
    public void showUiLoadingView() {

    }

    @Override
    public void closeUiLoadingView() {

    }

    @Override
    public void showSuccessInfo(String info) {

    }

    @Override
    public void showUiErrorInfo(String info) {

    }

    @Override
    public void showUiErrorInfo(String tag, String info) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (searchCardPresenter != null) {
            searchCardPresenter.onRemoveInfo();
        }
    }
}
