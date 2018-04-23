package com.kevin.chap10;

import java.util.Optional;

/**
 * @类名: Person
 * @包名：com.kevin.chap10
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/23 14:49
 * @版本：1.0
 * @描述：
 */
public class Person {

    Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
