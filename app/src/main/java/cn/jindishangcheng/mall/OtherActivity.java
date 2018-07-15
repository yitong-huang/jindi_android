package cn.jindishangcheng.mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.jindishangcheng.mall.helpers.Constant;
import cn.jindishangcheng.mall.helpers.WebviewHelper;

public class OtherActivity extends AppCompatActivity {

    private WebView webView;

    private WebViewClient mWebviewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (WebviewHelper.isAlipay(url)) {

            }
            if (WebviewHelper.isBackToIndex(url)) {
                OtherActivity.this.finish();
                return true;
            }
            view.loadUrl(url);
            return true;
        }
    };

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
}
