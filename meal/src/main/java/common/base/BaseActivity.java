package common.base;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import butterknife.Unbinder;
import common.AppComponent;
import common.GlobalAppComponent;
import common.model.DataManager;
import common.model.LoginManager;
import common.receiver.NetWorkChangeBroadcastReceiver;
import common.widget.LoadingDialog;
import fragmentation.SupportActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportHelper;


public abstract class BaseActivity extends SupportActivity {
    protected DataManager mDataManager;
    protected LoginManager loginManager;
    protected Context mContext;
    protected Dialog loadingDialog;
    private NetWorkChangeBroadcastReceiver receiver;
    private LoadingDialog loadingWindows;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        mDataManager = getAppComponent().getDataManager();
        loginManager = getAppComponent().getLoginManager();
        mContext = getAppComponent().getContext();
        this.initView(savedInstanceState);
        registerNetChangeReceiver();
    }

    //获取布局
    protected abstract int getLayoutId();

    //初始化布局和监听
    protected abstract void initView(Bundle savedInstanceState);

    private void registerNetChangeReceiver() {
        receiver = new NetWorkChangeBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }
    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findChildFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }
    protected AppComponent getAppComponent() {
        return GlobalAppComponent.getAppComponent();
    }

    protected void showShortToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    protected void showProgressDialog() {
        this.showProgressDialog(null, null);
    }

    protected void showProgressDialog(String msg) {
        this.showProgressDialog(msg, null);
    }

    protected void showProgressDialog(DialogInterface.OnCancelListener listener) {
        this.showProgressDialog(null, listener);
    }

    protected void showProgressDialog(String msg, DialogInterface.OnCancelListener listener) {
        if (loadingWindows == null) {
            loadingWindows = new LoadingDialog(this);
        }
        loadingWindows.show();
    }

    protected void hiddenProgressDialog() {
        if (loadingWindows != null) {
            loadingWindows.dismiss();
            loadingWindows = null;
        }
    }

    private CompositeDisposable disposables;

    /**
     * 添加观察者
     *
     * @param disposable d
     */
    public void addDisposable(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }

    /**
     * 注销观察者，防止泄露
     */
    public void clearDisposable() {
        if (disposables != null) {
            disposables.clear();
            disposables = null;
        }
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }

        clearDisposable();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (null != receiver) {
            receiver.onDestroy();
            unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
