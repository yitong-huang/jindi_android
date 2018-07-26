package cn.jindishangcheng.mall.helpers;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yitong on 2018/7/26.
 */

public class ActivityHolder {

    private static ActivityHolder holder = new ActivityHolder();
    private static Map<String, Activity> activities = new HashMap<>();


    private ActivityHolder() {

    }

    public void addActivity(String TAG, Activity activity) {
        if (!activities.containsKey(TAG)) {
            activities.put(TAG, activity);
        }
    }

    public Activity getActivity(String TAG) {
        if (activities.containsKey(TAG)) {
            return activities.get(TAG);
        }
        return null;
    }

    public void removeActivity(String TAG) {
        if (activities.containsKey(TAG)) {
            activities.remove(TAG);
        }
    }

    public static ActivityHolder getHolder() {
        return holder;
    }

}
