package cn.jindishangcheng.mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.jindishangcheng.mall.helpers.Constant;
import cn.jindishangcheng.mall.clients.OtherWebviewClient;
import cn.jindishangcheng.mall.helpers.WebviewHelper;

public class OtherActivity extends AppCompatActivity implements KeyEvent.Callback{

    private WebView webView;
    private MainActivity activity;

    private WebViewClient mWebviewClient = new OtherWebviewClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_other);

        String url = getIntent().getStringExtra(Constant.URL);


        webView = findViewById(R.id.webview_other);
        WebviewHelper.initWebview(webView, mWebviewClient);
        webView.loadUrl(url);
    }

    public WebView getWebView() {
        return webView;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
