package cn.jindishangcheng.mall.listeners;

import android.support.v4.view.ViewPager;

import cn.jindishangcheng.mall.MainActivity;

/**
 * Created by yitong on 2018/7/13.
 */

public class MainOnPageChangeListener implements ViewPager.OnPageChangeListener {

    MainActivity activity = null;

    public MainOnPageChangeListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        activity.switch2Page(position, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
