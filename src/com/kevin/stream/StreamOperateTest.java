package com.kevin.stream;

import com.kevin.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @class: StreamOperateTest
 * @package: com.kevin.lambda
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/13 9:58
 * @version: 1.0
 * @desc: 使用流
 */
public class StreamOperateTest {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<Dish> vegeratianMenu = menu.stream()
                .filter(Dish::isVegetarion)
                .collect(Collectors.toList());

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4, 5);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        List<Dish> threeLowCaloricDishName = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        List<Dish> skipDishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> list = words.stream()
                .map(w -> w.split(""))
                .distinct()
                .collect(Collectors.toList());

        for (int i = 0, n = list.size(); i < n; i++) {
            String[] strings = list.get(i);
            for (int j = 0, m = strings.length; j < m; j++) {
                System.out.println(strings[j]);
            }
        }

        String[] arrayOfWords = {"Hello", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

        List<String> anotherWords = Arrays.asList("Hello", "World");
        List<Stream<String>> anotherList = anotherWords.stream()
                .map(word -> word.split(""))    // 将String转换为String[] --> Stream<String[]>
                .map(Arrays::stream)    // 将String[]中的每个元素转换成流，返回的是Stream<String>，但是有多个 --> Stream<String>
                .distinct() // 去重 --> Stream<String>
                .collect(Collectors.toList());  // 将多个Stream<String>对象转换成List对象，返回List<Steam<String>>

        List<String> yetAnotherWords = Arrays.asList("Hello", "World");
        List<String> yetAnotherList = yetAnotherWords.stream()
                .map(word -> word.split(""))    // Stream<String[]>
                .flatMap(Arrays::stream)    // Stream<String>，只有一个
                .distinct() // Stream<String>
                .collect(Collectors.toList());  // List<String>

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squares = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        List<int[]> pairs = nums1.stream()
                .flatMap(i -> nums2.stream()
                .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        if (menu.stream().anyMatch(Dish::isVegetarion)) {
            System.out.println("The menu is (somewhat) vegetarian friendly.");
        }

        if (menu.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("The menu is healthy.");
        }

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarion)
                .findAny();

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();

        Optional<Integer> max = someNumbers.stream().reduce(Integer::max);
        Optional<Integer> min = someNumbers.stream().reduce(Integer::min);

        max.ifPresent(System.out::println);
        min.ifPresent(System.out::println);

//        int calories = menu.stream()
//                .map(Dish::getCalories)
//                .reduce(0, Integer::sum);

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        IntStream eventNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(eventNumbers.count());
    }
}
