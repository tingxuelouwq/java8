package main.java.com.kevin.chap6;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static main.java.com.kevin.chap6.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @类名: Grouping
 * @包名：com.kevin.chap6
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/27 10:19
 * @版本：1.0
 * @描述：
 */
public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dish names grouped by type: " + groupDishNamesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOprionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        })));
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
    return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
        return menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, toSet())));
    }
}
