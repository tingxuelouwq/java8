package com.kevin.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @class: CollectorTest
 * @package: com.kevin.lambda
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/13 16:53
 * @version: 1.0
 * @desc: 收集器
 */
public class CollectorTest {

    public static void main(String[] args) {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction t : transactions) {
            Currency currency = t.getCurrency();
            List<Transaction> tmp = transactionsByCurrencies.get(currency);
            if (tmp == null) {
                tmp = new ArrayList<>();
                transactionsByCurrencies.put(currency, tmp);
            }
            tmp.add(t);
        }

        Map<Currency, List<Transaction>> transactionsGroupByCurrency =
                transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));

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

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream()
                .collect(Collectors.maxBy(dishCaloriesComparator));

        int totalCalories = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
    }
}


