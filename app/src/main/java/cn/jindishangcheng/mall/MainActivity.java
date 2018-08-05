package cn.jindishangcheng.mall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;

import java.util.ArrayList;
import java.util.List;

import cn.jindishangcheng.mall.helpers.ActivityHelper;
import cn.jindishangcheng.mall.helpers.BottomNavigationViewHelper;
import cn.jindishangcheng.mall.helpers.Constant;
import cn.jindishangcheng.mall.listeners.MainOnNavigationItemSelectedListener;
import cn.jindishangcheng.mall.listeners.MainOnPageChangeListener;
import cn.jindishangcheng.mall.helpers.MainPagerAdapter;
import cn.jindishangcheng.mall.clients.MainWebviewClient;
import cn.jindishangcheng.mall.helpers.WebType;
import cn.jindishangcheng.mall.helpers.WebviewHelper;

public class MainActivity extends AppCompatActivity implements KeyEvent.Callback {

    public static String TAG = "MainActivity";

    private WebType currentView = WebType.INDEX;
    private WebView indexWebview;
    private WebView catesWebview;
    private WebView annoWebview;
    private WebView cartWebview;
    private WebView userCenterWebview;
    private ViewPager viewPager;
    private List<View> viewList;
    private BottomNavigationView navigation;
    private BadgeView badgeView;
    private BottomNavigationMenuView menu;
    private BottomNavigationItemView cartItem;
    private Button transparentButton;

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

        navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        menu = (BottomNavigationMenuView) navigation.getChildAt(0);
        cartItem = (BottomNavigationItemView)menu.getChildAt(WebType.CART.value());
        AppCompatImageView cartIcon = (AppCompatImageView)cartItem.getChildAt(0);
        transparentButton = findViewById(R.id.button);
        transparentButton.setWidth(getScreenWidth()*76/100);

        badgeView = BadgeFactory.createCircle(this).setBadgeCount(0).setHeight(15).setWidth(15);
        badgeView.setBadgeBackground(getResources().getColor(R.color.colorPrimary));
        //badgeView.bind(transparentButton);

        indexWebview = new WebView(this);
        WebviewHelper.initWebview(indexWebview, mWebviewClient);
        indexWebview.loadUrl(getResources().getString(R.string.url_index));

        catesWebview = new WebView(this);
        WebviewHelper.initWebview(catesWebview, mWebviewClient);
        catesWebview.loadUrl(getResources().getString(R.string.url_duobaos));

        annoWebview = new WebView(this);
        WebviewHelper.initWebview(annoWebview, mWebviewClient);
        annoWebview.getSettings().setJavaScriptEnabled(false);
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

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.FILTER);
        registerReceiver(receiver, filter);

        ActivityHelper.getHolder().addActivity(TAG, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);

        ActivityHelper.getHolder().removeActivity(TAG);
    }

    public void switch2Page(int index) {
        switch2Page(index, false);
    }

    public void switch2Page(int index, boolean fromViewPager) {
        WebType toType = WebType.valueOf(index);
        if (toType == currentView) {
            return;
        }
        currentView = WebType.valueOf(index);
        viewPager.setCurrentItem(index);
        if (fromViewPager) {
            navigation.setSelectedItemId(getNavID(WebType.valueOf(index)));
        }
        if (currentView != WebType.ANNO) {
            annoWebview.getSettings().setJavaScriptEnabled(false);
        } else {
            annoWebview.getSettings().setJavaScriptEnabled(true);
            annoWebview.reload();
        }
    }

    public int getNavID(WebType type) {
        switch (type) {
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

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public void setCartNum(final int num) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                badgeView.setBadgeCount(num);
                if (num > 0) {
                    badgeView.bind(transparentButton);
                } else {
                    badgeView.unbind();
                }
            }
        });
    }

    public WebType getCurrentType() {
        return currentView;
    }
}
