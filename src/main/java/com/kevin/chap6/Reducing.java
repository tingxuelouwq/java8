package main.java.com.kevin.chap6;

import static main.java.com.kevin.chap6.Dish.menu;
import static java.util.stream.Collectors.reducing;

/**
 * @类名: Reducing
 * @包名：com.kevin.chap6
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/27 10:14
 * @版本：1.0
 * @描述：
 */
public class Reducing {

    public static void main(String[] args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
    }

    private static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }
}
