package com.kevin.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @class: com.kevin.lambda.StreamTest
 * @package: PACKAGE_NAME
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/12 21:58
 * @version: 1.0
 * @desc:
 */
public class StreamTest {

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

        List<String> threeHighCaloricDishName = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCaloricDishName);

        List<String> stringList = Arrays.asList("Java", "Python", "C++");
        Stream<String> stream = stringList.stream();
        stream.forEach(System.out::println);
        // 抛出java.lang.IllegalStateException异常，提示：stream has already been operated upon or closed
//        stream.forEach(System.out::println);

        // 外部迭代
        List<String> namesIterator = new ArrayList<>();
        for (Dish d : menu) {
            namesIterator.add(d.getName());
        }

        // 内部迭代
        List<String> nameStream = menu.stream()
                .map(Dish::getName) // 用getName方法参数化map，提取菜名
                .collect(Collectors.toList());  // 开始执行操作流水线；没有迭代

        List<String> names = menu.stream()
                .filter(d -> {
                    System.out.println("filtering: " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping: " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);

        menu.stream().forEach(System.out::println);

        List<Dish> vegetarianDishes = menu.stream()
                .filter(Dish::isVegetarion)
                .collect(Collectors.toList());

        List<Dish> dishes2 = menu.stream().collect(
                ArrayList::new, // supplier
                ArrayList::add, // accumulator
                List::addAll    // combiner
        );
    }
}


