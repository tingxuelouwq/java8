package main.java.com.kevin.chap10;

import java.util.Optional;

/**
 * @类名: Person
 * @包名：com.kevin.chap10
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/24 10:03
 * @版本：1.0
 * @描述：
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.ofNullable(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
