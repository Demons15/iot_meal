package common.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import common.AppComponent;
import common.GlobalAppComponent;
import common.model.DataManager;
import common.widget.LoadingDialog;
import fragmentation.SupportFragment;

public abstract class BaseFragment extends SupportFragment {
    protected Activity mActivity;
    protected Unbinder unbinder;
    protected Context mContext;
    private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据
    private Bundle bundle;
    public View rootView;
    protected DataManager mDataManager;
    private LoadingDialog loadingWindows;
    /**
     * gif_logo进度dialog
     */
    private Dialog dialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mContext = getAppComponent().getContext();
        mDataManager = getAppComponent().getDataManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        this.initView(savedInstanceState);
        isViewPrepared = true;
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyFetchDataIfPrepared();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        lazyFetchData();
    }

    private void lazyFetchDataIfPrepared() {
        if (isViewPrepared && getUserVisibleHint() && !hasFetchData) {
            hasFetchData = true;
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void lazyFetchData();

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    protected void showShortToast(String message) {
        Toast.makeText(mActivity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message) {
        Toast.makeText(mActivity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    protected AppComponent getAppComponent() {
        return GlobalAppComponent.getAppComponent();
    }

    protected void showLoadingDialog() {
        if (loadingWindows == null) {
            loadingWindows = new LoadingDialog(getActivity());
        }
        loadingWindows.show();
    }

    protected void hideLoadingDialog() {
        if (loadingWindows != null) {
            loadingWindows.dismiss();
            loadingWindows = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (dialog != null) {
            if (dialog.isShowing()) dialog.dismiss();
            dialog = null;
        }
    }
}
