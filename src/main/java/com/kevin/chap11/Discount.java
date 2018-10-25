package com.kevin.chap11;

import static com.kevin.chap11.Util.delay;
import static com.kevin.chap11.Util.randomDelay;

/**
 * 类名: Discount<br/>
 * 包名：com.kevin.chap11<br/>
 * 作者：kevin[wangqi2017@xinhua.org]<br/>
 * 时间：2018/10/24 11:14<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return String.format("%s price is %.2f", quote.getShopName(),
                Discount.apply(quote.getPrice(), quote.getDiscountCode()));
    }

    private static double apply(double price, Code code) {
//        delay();
        randomDelay();
        return price * (100 - code.percentage) / 100;
    }
}
