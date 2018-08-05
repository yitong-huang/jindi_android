package cn.jindishangcheng.mall.clients;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import cn.jindishangcheng.mall.helpers.Constant;
import cn.jindishangcheng.mall.helpers.WebType;

/**
 * Created by yitong on 2018/7/16.
 */

public abstract class BaseWebviewClient extends WebViewClient{

    protected Activity activity;
    public static String TAG = "WebviewClient";

    public BaseWebviewClient(Activity activity) {
        super();
        this.activity = activity;
    }

    protected abstract void onTabUrl(WebType type);

    protected abstract void onOthersUrl(String url);
    
    protected void onDownloadUrl(String url) {
        if (url.contains(Constant.VERSION)) {
            Toast.makeText(activity, "已经是最新版本",Toast.LENGTH_LONG).show();
        } else {
            openUrlInBrowser(url);
        }
    }

    protected void onOutsideUrl(String url) {
        openUrlInBrowser(url);
    }

    protected void onAlipayUrl(String url) {
        Log.i(TAG, "ali");
        openUrlInBrowser(url);
        /*
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
        } catch (Exception e) {
            new AlertDialog.Builder(activity)
                    .setMessage("未检测到支付宝客户端，请安装后重试。")
                    .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Uri alipayUrl = Uri.parse("https://d.alipay.com");
                            activity.startActivity(new Intent("android.intent.action.VIEW", alipayUrl));
                        }
                    }).setNegativeButton("取消", null).show();
        }*/
    }

    private void openUrlInBrowser(String url) {
        Log.i(TAG, "open browser with url: " + url);
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_VIEW);
        activity.startActivity(intent);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i(TAG, url);
        WebType type = WebType.url2Type(url);
        switch (type) {
            case INDEX:
            case CATES:
            case ANNO:
            case CART:
            case USER_CENTER:
                onTabUrl(type);
                return true;
            case DOWNLOAD:
                onDownloadUrl(url);
                return true;
            case OUTSIDE:
                onOutsideUrl(url);
                return true;
            case OTHERS:
            case LOGOUT:
                onOthersUrl(url);
                return true;
            case ALIPAY:
                onAlipayUrl(url);
                return true;
        }
        return true;
    }
}
