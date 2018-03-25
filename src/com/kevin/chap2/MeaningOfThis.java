package com.kevin.chap2;

/**
 * @类名: MeaningOfThis
 * @包名：com.kevin.chap2
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/11 9:30
 * @版本：1.0
 * @描述：
 */
public class MeaningOfThis {

    private final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(value);
                System.out.println(this.value);
                System.out.println(MeaningOfThis.this.value);
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
