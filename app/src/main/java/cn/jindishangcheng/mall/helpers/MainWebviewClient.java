package cn.jindishangcheng.mall.helpers;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import cn.jindishangcheng.mall.MainActivity;
import cn.jindishangcheng.mall.OtherActivity;

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
