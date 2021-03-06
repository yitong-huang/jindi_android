package cn.jindishangcheng.mall.clients;

import android.app.Activity;
import android.content.Intent;

import cn.jindishangcheng.mall.OtherActivity;
import cn.jindishangcheng.mall.helpers.Constant;
import cn.jindishangcheng.mall.helpers.WebType;

/**
 * Created by yitong on 2018/7/16.
 */

public class OtherWebviewClient extends BaseWebviewClient {

    public OtherWebviewClient(Activity activity) {
        super(activity);
    }

    @Override
    protected void onTabUrl(WebType type) {
        Intent intent = new Intent();
        intent.setAction(Constant.FILTER);
        intent.putExtra(Constant.TYPE, type.value());
        activity.sendBroadcast(intent);
        activity.finish();
    }

    @Override
    protected void onOthersUrl(String url) {
        ((OtherActivity)activity).getWebView().loadUrl(url);
    }
}
