package main.java.com.kevin.chap10;

import java.util.Optional;

/**
 * @类名: OptionalMain
 * @包名：com.kevin.chap10
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/24 10:13
 * @版本：1.0
 * @描述：
 */
public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
