package main.java.com.kevin.chap5;

import main.java.com.kevin.chap4.Dish;

import static main.java.com.kevin.chap4.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @类名: NumericStreams
 * @包名：com.kevin.chap5
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/18 21:59
 * @版本：1.0
 * @描述：
 */
public class NumericStreams {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(calories);

        // max and OptionalInt
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int max;
        if(maxCalories.isPresent()){
            max = maxCalories.getAsInt();
        }
        else {
            // we can choose a default value
            max = 1;
        }
        System.out.println(max);

        // numeric ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0);
        System.out.println(evenNumbers.count());

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
