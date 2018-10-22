package main.java.com.kevin.chap10;

import java.util.Optional;

/**
 * @类名: Car
 * @包名：com.kevin.chap10
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/24 9:55
 * @版本：1.0
 * @描述：
 */
public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
