package cn.jindishangcheng.mall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jindishangcheng.mall.helpers.BottomNavigationViewHelper;
import cn.jindishangcheng.mall.helpers.Constant;
import cn.jindishangcheng.mall.helpers.MainOnNavigationItemSelectedListener;
import cn.jindishangcheng.mall.helpers.MainOnPageChangeListener;
import cn.jindishangcheng.mall.helpers.MainPagerAdapter;
import cn.jindishangcheng.mall.helpers.MainWebviewClient;
import cn.jindishangcheng.mall.helpers.WebType;
import cn.jindishangcheng.mall.helpers.WebviewHelper;

public class MainActivity extends AppCompatActivity implements KeyEvent.Callback{

    private WebType currentView = WebType.INDEX;
    private WebView indexWebview;
    private WebView catesWebview;
    private WebView annoWebview;
    private WebView cartWebview;
    private WebView userCenterWebview;
    private ViewPager viewPager;
    private List<View> viewList;
    private BottomNavigationView navigation;

    private WebViewClient mWebviewClient = new MainWebviewClient(MainActivity.this);
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new MainOnNavigationItemSelectedListener(MainActivity.this);
    private ViewPager.OnPageChangeListener mOnPageChangeListener
            = new MainOnPageChangeListener(MainActivity.this);
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch2Page(intent.getIntExtra(Constant.TYPE, 0));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        indexWebview = new WebView(this);
        WebviewHelper.initWebview(indexWebview, mWebviewClient);
        indexWebview.loadUrl(getResources().getString(R.string.url_index));

        catesWebview = new WebView(this);
        WebviewHelper.initWebview(catesWebview, mWebviewClient);
        catesWebview.loadUrl(getResources().getString(R.string.url_duobaos));

        annoWebview = new WebView(this);
        WebviewHelper.initWebview(annoWebview, mWebviewClient);
        annoWebview.loadUrl(getResources().getString(R.string.url_anno));

        cartWebview = new WebView(this);
        WebviewHelper.initWebview(cartWebview, mWebviewClient);
        cartWebview.loadUrl(getResources().getString(R.string.url_cart));

        userCenterWebview = new WebView(this);
        WebviewHelper.initWebview(userCenterWebview, mWebviewClient);

        viewList = new ArrayList<>(5);
        viewList.add(indexWebview);
        viewList.add(catesWebview);
        viewList.add(annoWebview);
        viewList.add(cartWebview);
        viewList.add(userCenterWebview);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainPagerAdapter(viewList));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);

        navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.FILTER);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void switch2Page(int index) {
        switch2Page(index, false);
    }

    public void switch2Page(int index, boolean fromViewPager) {
        currentView = WebType.valueOf(index);
        viewPager.setCurrentItem(index);
        if (fromViewPager) {
            navigation.setSelectedItemId(getNavID(WebType.valueOf(index)));
        }
    }

    public int getNavID(WebType type) {
        switch(type) {
            case INDEX:
                return R.id.navigation_index;
            case CATES:
                return R.id.navigation_cates;
            case ANNO:
                return R.id.navigation_anno;
            case CART:
                return R.id.navigation_cart;
            case USER_CENTER:
                return R.id.navigation_user_center;
            default:
                return 0;
        }
    }

    public WebView getWebview(WebType type) {
        switch (type) {
            case INDEX:
                return indexWebview;
            case CATES:
                return catesWebview;
            case ANNO:
                return annoWebview;
            case CART:
                return cartWebview;
            case USER_CENTER:
                return userCenterWebview;
            default:
                return null;
        }
    }

    private long time = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//监听返回键，如果可以后退就后退
            if (System.currentTimeMillis() - time > 2000) {
                time = System.currentTimeMillis();
                Toast.makeText(this, "再点击一次退出程序", Toast.LENGTH_SHORT).show();
            } else {
                super.onKeyDown(keyCode, event);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
