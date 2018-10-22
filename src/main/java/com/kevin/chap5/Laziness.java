package main.java.com.kevin.chap5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @类名: Laziness
 * @包名：com.kevin.chap5
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/14 22:14
 * @版本：1.0
 * @描述：
 */
public class Laziness {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares = numbers.stream()
                .filter(n -> {
                    System.out.println("filtering " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("mapping " + n);
                    return n * n;
                })
                .limit(2)
                .collect(toList());
        twoEvenSquares.forEach(System.out::println);
    }
}
