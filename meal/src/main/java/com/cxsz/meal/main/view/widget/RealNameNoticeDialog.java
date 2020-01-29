package com.cxsz.meal.main.view.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cxsz.meal.R;
import org.greenrobot.eventbus.EventBus;

import common.constant.KeyConstants;
import common.model.EventBean;
import common.model.MealInfoBean;
import common.widget.BaseDialogView;


public class RealNameNoticeDialog extends BaseDialogView {
    private RealNameListener mRealNameListener;
    private MealInfoBean.BodyBean mealInfoBeans;

    public RealNameNoticeDialog(Activity activity) {
        super(activity, ShowType.CENTER);
    }

    @Override
    public void initViewInfo() {
        mDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.real_name_notice_layout, contentContainer);
        ImageView close = view.findViewById(R.id.close);//添加联系人取消按钮
        Button rightNowRealName = view.findViewById(R.id.right_now_real_name);//添加联系人下一步按钮
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismiss();
            }
        });
        rightNowRealName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRealNameListener.realName(mealInfoBeans);
            }
        });
        DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    EventBus.getDefault().post(new EventBean(KeyConstants.FINISH_MEAL_VIEW));
                    return true;
                } else {
                    return false;
                }
            }
        };
        mDialog.setOnKeyListener(keylistener);
        mDialog.setCancelable(false);
    }

    public void setOnRealNameListener(RealNameListener realNameListener) {
        mRealNameListener = realNameListener;
    }

    public void setRealNameInfo(MealInfoBean.BodyBean mealInfoBean) {
        mealInfoBeans = mealInfoBean;
    }

    public interface RealNameListener {
        void realName(MealInfoBean.BodyBean mealInfoBeans);
    }
}
