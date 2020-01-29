package com.cxsz.meal.contact.view;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxsz.meal.R;
import com.cxsz.meal.R2;
import com.cxsz.meal.contact.bean.ContactBean;
import com.cxsz.meal.contact.component.ContactPresenterModule;
import com.cxsz.meal.contact.component.DaggerContactComponent;
import com.cxsz.meal.contact.presenter.presenterImpl.ContactPresenterImpl;
import com.cxsz.meal.contact.view.adapter.ContactListRecyclerAdapter;
import com.cxsz.meal.contact.view.viewInterface.ContactView;
import com.cxsz.meal.contact.view.widget.AddContactShowPopWindow;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import common.base.BaseFragment;
import common.constant.KeyConstants;
import common.model.MealCodeData;
import common.model.MealInfoBaseManager;
import common.model.MealInfoBean;
import common.model.ModuleHelper;
import common.util.ToastUtil;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class ContactFragment extends BaseFragment implements ContactView, AddContactShowPopWindow.SureContactListener {

    @BindView(R2.id.contact_notice_area)
    View contactNoticeArea;
    @BindView(R2.id.contact_notice_info)
    TextView contactNoticeInfo;
    @BindView(R2.id.contact_list)
    RecyclerView contactList;

    @BindView(R2.id.contact_control_area)
    View contactControlArea;
    @BindView(R2.id.all_choose)
    CheckBox allChoose;
    @BindView(R2.id.refresh_contact)
    LinearLayout refreshContact;
    @BindView(R2.id.delete_contact)
    LinearLayout deleteContact;

    @BindView(R2.id.add_contact)
    LinearLayout addContact;

    @Inject
    ContactPresenterImpl contactPresenter;

    public String controlContactType;
    private AddContactShowPopWindow addContactShowPopWindow;
    private ContactListRecyclerAdapter contactListRecyclerAdapter;

    private ArrayList<ContactBean> mobiles = new ArrayList<ContactBean>();

    public static ContactFragment getInstance(MealInfoBean.BodyBean mealInfoBeans) {
        ContactFragment contactFragment = new ContactFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KeyConstants.MEAL_INFO_BEAN, mealInfoBeans);
        contactFragment.setArguments(bundle);
        return contactFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.contact_list_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        addContactShowPopWindow = new AddContactShowPopWindow(getActivity());
        addContactShowPopWindow.setOnSureContactListener(this);
        contactListRecyclerAdapter = new ContactListRecyclerAdapter(mobiles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        contactList.setLayoutManager(linearLayoutManager);
        contactList.setAdapter(contactListRecyclerAdapter);
    }

    @Override
    protected void lazyFetchData() {
        DaggerContactComponent.builder()
                .appComponent(getAppComponent())
                .contactPresenterModule(new ContactPresenterModule(this, MealInfoBaseManager.getInstance(mDataManager)))
                .build()
                .injectContactFragment(this);
        contactPresenter.RequestQueryVoiceWhiteList("1", ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
        contactPresenter.RequestQueryAddWhiteCount("1", ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
    }

    @OnClick({R2.id.refresh_contact, R2.id.delete_contact, R2.id.add_contact})
    public void onViewClick(View view) {
        if (view.getId() == R.id.refresh_contact) {
            contactPresenter.RequestQueryVoiceWhiteList("1", ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
        } else if (view.getId() == R.id.delete_contact) {
            deleteWhiteList();
        } else if (view.getId() == R.id.add_contact) {
            addContactShowPopWindow.showPopupWindow();
        }
    }

    private void deleteWhiteList() {
        StringBuilder stringBuilder = new StringBuilder();
        Flowable.fromIterable(contactListRecyclerAdapter.mobiles).filter(new Predicate<ContactBean>() {
            @Override
            public boolean test(ContactBean contactBean) throws Exception {
                return contactBean.isCheck();
            }
        }).subscribe(new Consumer<ContactBean>() {
            @Override
            public void accept(ContactBean contactBean) throws Exception {
                stringBuilder.append(contactBean.getContactPhoneNumber()).append(",");
            }
        });
        if (stringBuilder.length() > 0) {
            setControlContactType(KeyConstants.DELETE_CONTACT);
            contactPresenter.RequestAddOrDelVoiceWhiteManager(KeyConstants.CONTACT_INPUT_TYPE, ModuleHelper.getInstance().getMealInfoBeans().getCardNumber(), KeyConstants.DELETE_CONTACT, stringBuilder.substring(0, stringBuilder.length() - 1));
        } else {
            ToastUtil.show(getActivity(), "暂无选中要删除的数据!");
        }
    }

    @OnCheckedChanged({R2.id.all_choose})
    public void onCheckChange(CompoundButton compoundButton, boolean isCheck) {
        if (compoundButton.getId() == R.id.all_choose) {
            if (isCheck) {
                ToastUtil.show(getActivity(), "勾选了全部!");
                contactListRecyclerAdapter.checkAll();
            } else {
                ToastUtil.show(getActivity(), "取消勾选全部!");
                contactListRecyclerAdapter.unCheckAll();
            }
        }
    }

    @Override
    public void ResponseAddOrDelVoiceWhiteManager(MealCodeData mealCodeData) {
        if (getControlContactType().equals(KeyConstants.ADD_CONTACT)) {
            addContactShowPopWindow.dismiss();
        } else if (getControlContactType().equals(KeyConstants.DELETE_CONTACT)) {
            contactListRecyclerAdapter.deleteContact();
        }
        contactPresenter.RequestQueryVoiceWhiteList("1", ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
        contactPresenter.RequestQueryAddWhiteCount("1", ModuleHelper.getInstance().getMealInfoBeans().getCardNumber());
    }

    @Override
    public void ResponseQueryVoiceWhiteList(MealCodeData mealCodeData) {
        if (null != mealCodeData.getBody()) {
            ArrayList<String> mobiles = (ArrayList<String>) (((LinkedTreeMap) mealCodeData.getBody()).get("mobile"));
            if (null != mobiles) {
                ArrayList<ContactBean> contactBeans = new ArrayList<ContactBean>();
                Flowable.fromIterable(mobiles).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        contactBeans.add(new ContactBean(s, false));
                    }
                });
                contactListRecyclerAdapter.refreshContacts(contactBeans);
                contactControlArea.setVisibility(View.VISIBLE);
            } else {
                contactControlArea.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void ResponseQueryAddWhiteCount(MealCodeData mealCodeData) {
        if (((LinkedTreeMap) mealCodeData.getBody()) != null) {
            Double aDouble = (Double) ((LinkedTreeMap) mealCodeData.getBody()).get("count");
            double doubleValue = aDouble.doubleValue();
            String number = 20 - (int)doubleValue + "";
            String allNumber = "20";
            String numberCount = 20 - (int)doubleValue + "";
            String allNumberCount = "20";
            String noticeInfo = String.format("目前通讯录支持 <font color='#FFAD28'>" + number + "</font>/" + allNumber + "人，每月开通 " + " <font color='#FFAD28'>" + numberCount + "</font>" + "/ " + allNumberCount + "次");
            contactNoticeInfo.setText(Html.fromHtml(noticeInfo));
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
        ToastUtil.show(getActivity(), info);
    }

    @Override
    public void showUiErrorInfo(String info) {
        ToastUtil.show(getActivity(), info);
    }

    @Override
    public void showUiErrorInfo(String tag, String info) {

    }

    @Override
    public void sureContact(ContactBean messageBean) {
        if (messageBean.getContactName().isEmpty()) {
            ToastUtil.show(getActivity(), "联系人不能为空!");
        } else if (messageBean.getContactPhoneNumber().isEmpty()) {
            ToastUtil.show(getActivity(), "手机号不能为空!");
        } else {
            setControlContactType(KeyConstants.ADD_CONTACT);
            contactPresenter.RequestAddOrDelVoiceWhiteManager(KeyConstants.CONTACT_INPUT_TYPE, ModuleHelper.getInstance().getMealInfoBeans().getCardNumber(), KeyConstants.ADD_CONTACT, messageBean.getContactPhoneNumber());
        }
    }

    public String getControlContactType() {
        return controlContactType;
    }

    public void setControlContactType(String controlContactType) {
        this.controlContactType = controlContactType;
    }
}
