package com.kevin.chap6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kevin.chap6.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @类名: Partitioning
 * @包名：com.kevin.chap6
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/27 16:30
 * @版本：1.0
 * @描述：
 */
public class Partitioning {

    public static void main(String[] args) {
        System.out.println("Dishes partitioned by vegetarian: " + paritionByVegetarian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> paritionByVegetarian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }
}
