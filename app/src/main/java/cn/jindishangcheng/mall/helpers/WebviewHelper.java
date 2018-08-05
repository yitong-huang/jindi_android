package cn.jindishangcheng.mall.helpers;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by yitong on 2018/7/1.
 */

public class WebviewHelper {

    public static void initWebview(WebView webView, WebViewClient webViewClient) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.addJavascriptInterface(new Js2Java(), "androidShare");
        webView.setWebViewClient(webViewClient);
    }

    public static boolean isBackToIndex(String url) {
        return false;
    }

    public static boolean isOpenInBrowser(String url) {
        return false;
    }

    public static boolean isAlipay(String url) {
        return false;
    }

}
