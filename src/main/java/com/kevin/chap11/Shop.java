package com.kevin.chap11;

import java.util.Random;

import static com.kevin.chap11.Util.delay;
import static com.kevin.chap11.Util.randomDelay;

/**
 * 类名: Shop<br/>
 * 包名：com.kevin.chap11<br/>
 * 作者：kevin[wangqi2017@xinhua.org]<br/>
 * 时间：2018/10/24 11:17<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String prodcut) {
        double price = calculatePrice(prodcut);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public double calculatePrice(String product) {
//        delay();
        randomDelay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getName() {
        return name;
    }
}
