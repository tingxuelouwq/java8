package com.kevin.lambda;

import com.kevin.entity.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @class: com.kevin.lambda.ComposeLambdaTest
 * @package: com.kevin.lambda
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/12 16:37
 * @version: 1.0
 * @desc: Lambda表达式-复合
 */
public class ComposeLambdaTest {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(70, "red"),
                new Apple(70, "green"),
                new Apple(60, "red"));

        inventory.sort(Comparator.comparing(Apple::getWeight));
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        Predicate<Apple> redApple = apple -> apple.getColor().equals("red");
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyOrGreenApple = redApple
                .and(apple -> apple.getWeight() > 150)
                .or(apple -> apple.getColor().equals("green"));

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println(result);

        Function<Integer, Integer> k = f.compose(g);
        System.out.println(k.apply(1));

    }
}

