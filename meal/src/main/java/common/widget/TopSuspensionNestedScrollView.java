package common.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.widget.NestedScrollView;

/**
 * 监听ScrollView的滑动数据
 */
public class TopSuspensionNestedScrollView extends NestedScrollView {

    public TopSuspensionNestedScrollView(Context context) {
        this(context, null);
    }

    public TopSuspensionNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopSuspensionNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private OnObservableScrollViewScrollChanged mOnObservableScrollViewScrollChanged;

    public void setOnObservableScrollViewScrollChanged(OnObservableScrollViewScrollChanged mOnObservableScrollViewScrollChanged) {
        this.mOnObservableScrollViewScrollChanged = mOnObservableScrollViewScrollChanged;
    }


    public interface OnObservableScrollViewScrollChanged {
        void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt);
    }

    /**
     * @param l    Current horizontal scroll origin. 当前滑动的x轴距离
     * @param t    Current vertical scroll origin. 当前滑动的y轴距离
     * @param oldl Previous horizontal scroll origin. 上一次滑动的x轴距离
     * @param oldt Previous vertical scroll origin. 上一次滑动的y轴距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnObservableScrollViewScrollChanged != null) {
            mOnObservableScrollViewScrollChanged.onObservableScrollViewScrollChanged(l, t, oldl, oldt);
        }
    }
}
