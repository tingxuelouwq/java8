package com.kevin.chap6;

import com.kevin.chap4.Dish;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @类名: Main
 * @包名：com.kevin
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/26 16:40
 * @版本：1.0
 * @描述：
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left, right) -> {left.addAll(right); return left;};
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,
                Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        List<Dish> dishes = Dish.menu.stream().collect(new ToListCollector<>());
        dishes.forEach(System.out::println);
        System.out.println("---------------");
        List<Dish> dishes1 = Dish.menu.stream().collect(Collectors.toList());
        dishes1.forEach(System.out::println);
    }
}
