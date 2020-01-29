package common.base.rxjava;

public interface ResultControlListener<T> {

    void onNext(T t);

    void onError(Throwable e);


}
