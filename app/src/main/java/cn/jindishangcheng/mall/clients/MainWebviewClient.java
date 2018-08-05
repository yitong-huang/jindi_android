package cn.jindishangcheng.mall.clients;

import android.content.Intent;

import cn.jindishangcheng.mall.MainActivity;
import cn.jindishangcheng.mall.OtherActivity;
import cn.jindishangcheng.mall.helpers.Constant;
import cn.jindishangcheng.mall.helpers.WebType;

/**
 * Created by yitong on 2018/7/13.
 */

public class MainWebviewClient extends BaseWebviewClient {

    @Override
    public void onTabUrl(WebType type) {
        ((MainActivity)activity).switch2Page(type.value());
    }

    @Override
    protected void onOthersUrl(String url) {
        Intent intent= new Intent(activity, OtherActivity.class);
        intent.putExtra(Constant.URL, url);
        activity.startActivity(intent);
    }

    public MainWebviewClient(MainActivity activity) {
        super(activity);
    }
}
