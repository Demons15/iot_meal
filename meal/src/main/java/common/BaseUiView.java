package common;

public interface BaseUiView {
    void showUiLoadingView();

    void closeUiLoadingView();

    void showSuccessInfo(String info);

    void showUiErrorInfo(String info);

    void showUiErrorInfo(String tag, String info);
}
