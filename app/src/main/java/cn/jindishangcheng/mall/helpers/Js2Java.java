package cn.jindishangcheng.mall.helpers;

import android.util.Log;
import android.webkit.JavascriptInterface;

import cn.jindishangcheng.mall.MainActivity;

/**
 * Created by yitong on 2018/6/18.
 */

public class Js2Java {

    @JavascriptInterface
    public void setCartNum(String strCartNum) {
        Log.e("Js2Java", "购物车数量:" + strCartNum);//处理返回的结果
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
}
