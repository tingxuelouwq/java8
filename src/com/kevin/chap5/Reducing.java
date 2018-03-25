package com.kevin.chap5;

import com.kevin.chap4.Dish;

import static com.kevin.chap4.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @类名: Reducing
 * @包名：com.kevin.chap5
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/18 21:51
 * @版本：1.0
 * @描述：
 */
public class Reducing {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(calories);
    }
}
