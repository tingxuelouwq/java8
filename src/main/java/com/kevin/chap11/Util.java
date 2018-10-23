package com.kevin.chap11;

/**
 * 类名: Util<br/>
 * 包名：com.kevin.chap11<br/>
 * 作者：kevin[wangqi2017@xinhua.org]<br/>
 * 时间：2018/10/23 7:55<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class Util {

    public static void delay() {
        int delay = 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
