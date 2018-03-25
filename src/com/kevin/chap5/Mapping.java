package com.kevin.chap5;

import com.kevin.chap4.Dish;

import static com.kevin.chap4.Dish.menu;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @类名: Mapping
 * @包名：com.kevin.chap5
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/14 22:17
 * @版本：1.0
 * @描述：
 */
public class Mapping {

    public static void main(String[] args) {
        // map
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        dishNames.forEach(System.out::println);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        wordLengths.forEach(System.out::println);

        // flatMap
        words.stream().flatMap(line -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =  numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
