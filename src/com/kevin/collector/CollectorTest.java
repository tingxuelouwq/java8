package com.kevin.collector;

import com.kevin.entity.CaloricLevel;
import com.kevin.entity.Currency;
import com.kevin.entity.Dish;
import com.kevin.entity.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
            com.kevin.entity.Currency currency = t.getCurrency();
            List<Transaction> tmp = transactionsByCurrencies.get(currency);
            if (tmp == null) {
                tmp = new ArrayList<>();
                transactionsByCurrencies.put(currency, tmp);
            }
            tmp.add(t);
        }

        Map<com.kevin.entity.Currency, List<Transaction>> transactionsGroupByCurrency =
                transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));

        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
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

        IntSummaryStatistics menuStatistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(",", "\"", "\""));
        System.out.println(shortMenu);

        totalCalories = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories);

        mostCalorieDish = menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }))
                );

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                ));

        Map<Dish.Type, Long> typesCount = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.counting()));

        Map<Dish.Type, Optional<Dish>> mostCaloricByType2 = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                ));

        Map<Dish.Type, Dish> mostCaloricByType3 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }, Collectors.toCollection(HashSet::new))));

        Map<Boolean, List<Dish>> partitionMenu = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarion));

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegeratianDishesByType =
                menu.stream().collect(
                        Collectors.partitioningBy(Dish::isVegetarion,
                                Collectors.groupingBy(Dish::getType))
                );
    }

    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        Collectors.partitioningBy(candidate -> isPrime(candidate))
                );
    }
}


