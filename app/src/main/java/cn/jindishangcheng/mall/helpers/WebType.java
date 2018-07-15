package cn.jindishangcheng.mall.helpers;

public enum WebType {
    INDEX(0), CATES(1), ANNO(2), CART(3), USER_CENTER(4), OUT(5), LOGOUT(6), OTHERS(7);
    private int val = 0;

    WebType(int val) {
        this.val = val;
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
                return OUT;
            case 6:
                return OUT;
            default:
                return OTHERS;
        }
    }

    public int value() {
        return val;
    }
}