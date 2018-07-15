package cn.jindishangcheng.mall.helpers;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import cn.jindishangcheng.mall.MainActivity;
import cn.jindishangcheng.mall.R;

/**
 * Created by yitong on 2018/7/13.
 */

public class MainOnNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    MainActivity activity = null;

    public MainOnNavigationItemSelectedListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_index:
                activity.switch2Page(WebType.INDEX.value());
                return true;
            case R.id.navigation_cates:
                activity.switch2Page(WebType.CATES.value());
                return true;
            case R.id.navigation_anno:
                activity.switch2Page(WebType.ANNO.value());
                return true;
            case R.id.navigation_cart:
                activity.switch2Page(WebType.CART.value());
                activity.getWebview(WebType.CART).reload();
                return true;
            case R.id.navigation_user_center:
                activity.switch2Page(WebType.USER_CENTER.value());
                activity.getWebview(WebType.USER_CENTER).loadUrl(activity.getResources().getString(R.string.url_user_center));
                return true;
        }
        return false;
    }
}
