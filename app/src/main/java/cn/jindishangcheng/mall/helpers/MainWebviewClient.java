package cn.jindishangcheng.mall.helpers;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.jindishangcheng.mall.MainActivity;
import cn.jindishangcheng.mall.OtherActivity;

/**
 * Created by yitong on 2018/7/13.
 */

public class MainWebviewClient extends WebViewClient {

    private MainActivity activity;

    public MainWebviewClient(MainActivity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (url.contains("/wap/index.php?ctl=cate&show_prog=1")) {
            activity.switch2Page(1);
            return true;
        }



        Intent intent = new Intent(activity, OtherActivity.class);
        intent.putExtra(Constant.URL, url);
        activity.startActivity(intent);
        return true;
    }

}
