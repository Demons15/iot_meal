package common.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.cxsz.meal.R;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class BaseDialogFragment extends DialogFragment {
    private Toast mToastShort;
    private Toast mToastLong;
    private boolean isShown = false;

    private Bundle bundle;

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.5f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        windowParams.windowAnimations = R.style.MyDialogTheme;
        window.setAttributes(windowParams);
    }

    public void showToastShort(String msg) {
        if (mToastShort != null) {
            mToastShort.setText(msg);
        } else {
            if (null != getActivity()) {
                mToastShort = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
            }
        }
        if(mToastShort != null){
            mToastShort.show();
        }
    }

    public void showToastShort(int msg) {
        showToastShort(getResources().getString(msg));
    }

    public void showToastLong(String msg) {
        if (mToastLong != null) {
            mToastLong.setText(msg);
        } else {
            if (null != getActivity()) {
                mToastLong = Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG);
            }
        }
        if(mToastLong != null){
            mToastLong.show();
        }
    }

    public void showToastLong(int msg) {
        showToastLong(getResources().getString(msg));
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if(isShowing()){
            return;
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();

        isShown = true;
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
        isShown = false;
    }

    public boolean isShowing() {
        return isShown;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        isShown = false;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
