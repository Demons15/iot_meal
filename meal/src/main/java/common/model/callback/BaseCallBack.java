package common.model.callback;

public interface BaseCallBack<T> {
    void onSuccess(T response);

    void onError(String error);

}
