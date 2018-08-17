package cn.jindishangcheng.mall.helpers;

import android.util.Log;
import android.webkit.JavascriptInterface;

import cn.jindishangcheng.mall.MainActivity;
import cn.jindishangcheng.mall.OtherActivity;

/**
 * Created by yitong on 2018/6/18.
 */

public class Js2Java {

    public final static String TAG = "Js2Java";

    @JavascriptInterface
    public void setCartNum(String strCartNum) {
        Log.i(TAG, "购物车数量:" + strCartNum);
        int cartNum = 0;
        try {
            cartNum = Integer.valueOf(strCartNum);
        } catch (Exception e) {
        }

        MainActivity activity = (MainActivity) ActivityHelper.getHolder().getActivity(MainActivity.TAG);
        if (activity != null) {
            activity.setCartNum(cartNum);
        }
    }

    @JavascriptInterface
    public void back() {
        Log.i(TAG, "按下左上角返回键");
        OtherActivity activity = (OtherActivity) ActivityHelper.getHolder().getActivity(OtherActivity.TAG);
        if (activity != null) {
            if (activity.getWebView().canGoBack()) {
                activity.getWebView().goBack();
            } else {
                activity.finish();
            }
        } else {
            Log.e(TAG, "主界面中左上角不应该出现返回键");
        }
    }
}
