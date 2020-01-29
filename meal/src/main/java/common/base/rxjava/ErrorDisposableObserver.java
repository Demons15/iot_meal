package common.base.rxjava;

import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import common.GlobalAppComponent;
import common.model.http.NoNetWorkException;
import common.util.LogUtil;
import io.reactivex.observers.DisposableObserver;

/**
 * 处理接口回调异常信息的统一处理
 */

public  class ErrorDisposableObserver extends DisposableObserver{
    private ResultControlListener resultControlListener;

    public ErrorDisposableObserver(ResultControlListener errorControlListener) {
        this.resultControlListener = errorControlListener;
    }

    @Override
    public void onNext(Object o) {
        if (resultControlListener != null) {
            resultControlListener.onNext(o);
        }
    }

    @Override
    public void onError(Throwable e) {
        String errorInfo = "";
        if (e instanceof SocketTimeoutException) {
            errorInfo = "网络连接超时!";
            LogUtil.setTagE("NetError:", errorInfo);
            Toast.makeText(GlobalAppComponent.getAppComponent().getContext(), errorInfo, Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            errorInfo = "网络连接异常!";
            LogUtil.setTagE("NetError:", errorInfo);
            Toast.makeText(GlobalAppComponent.getAppComponent().getContext(), errorInfo, Toast.LENGTH_SHORT).show();
        } else if (e instanceof UnknownHostException) {
            errorInfo = "网络连接异常!";
            LogUtil.setTagE("NetError:", errorInfo);
            Toast.makeText(GlobalAppComponent.getAppComponent().getContext(), errorInfo, Toast.LENGTH_SHORT).show();
        } else if (e instanceof NoNetWorkException) {
            errorInfo = "网络不可用!";
            LogUtil.setTagE("NetError:", errorInfo);
            Toast.makeText(GlobalAppComponent.getAppComponent().getContext(), errorInfo, Toast.LENGTH_SHORT).show();
        } else {
            errorInfo = e.getMessage();
            LogUtil.setTagE("NetError:", errorInfo);
            Toast.makeText(GlobalAppComponent.getAppComponent().getContext(), errorInfo, Toast.LENGTH_SHORT).show();
        }
        resultControlListener.onError(new Throwable(errorInfo));
    }

    @Override
    public void onComplete() {

    }
}
