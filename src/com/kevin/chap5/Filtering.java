package com.kevin.chap5;

import com.kevin.chap4.Dish;

import java.util.Arrays;
import java.util.List;

import static com.kevin.chap4.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * @类名: Filtering
 * @包名：com.kevin.chap5
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/13 21:20
 * @版本：1.0
 * @描述：
 */
public class Filtering {

    public static void main(String[] args) {

        // Filtering with predicate
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        // Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // Truncating a stream
        List<Dish> dishesLimit3 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        dishesLimit3.forEach(System.out::println);

        // Skipping elements
        List<Dish> dishesSkip2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        dishesSkip2.forEach(System.out::println);
    }
}
