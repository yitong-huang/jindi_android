package cn.jindishangcheng.mall.helpers;

import android.util.Log;

public enum WebType {

    INDEX(0), CATES(1), ANNO(2), CART(3), USER_CENTER(4), OUTSIDE(5),
    LOGOUT(6), DOWNLOAD(7), OTHERS(8), ALIPAY(9);
    private int val = 0;

    WebType(int val) {
        this.val = val;
    }

    public static WebType url2Type(String url) {
        if (url.endsWith("wap/index.php") || url.contains("wap/index.php?show_prog=1")) {
            return INDEX;
        }
        if (url.contains("wap/index.php?ctl=cate&show_prog=1")) {
            return CATES;
        }
        if (url.contains("wap/index.php?ctl=cart&show_prog=1")) {
            return CART;
        }
        if (url.contains("wap/index.php?ctl=anno&show_prog=1")) {
            return ANNO;
        }
        if (url.contains(Constant.VERSION)) {
            return DOWNLOAD;
        }
        if (url.startsWith("alipays://platformapi") || url.contains("qr.alipay.com")) {
            return ALIPAY;
        }
        return OTHERS;
    }

    public static WebType valueOf(int value) {
        switch (value) {
            case 0:
                return INDEX;
            case 1:
                return CATES;
            case 2:
                return ANNO;
            case 3:
                return CART;
            case 4:
                return USER_CENTER;
            case 5:
                return OUTSIDE;
            case 6:
                return LOGOUT;
            case 7:
                return DOWNLOAD;
            case 8:
                return OTHERS;
            case 9:
                return ALIPAY;
            default:
                return OTHERS;
        }
    }

    public int value() {
        return val;
    }
}