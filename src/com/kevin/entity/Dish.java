package com.kevin.entity;

/**
 * @class: Dish
 * @package: com.kevin.lambda
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/12 21:59
 * @version: 1.0
 * @desc:
 */
public class Dish {
    private final String name;
    private final boolean vegetarion;
    private final int calories;
    private final Type type;

    public enum Type {
        MEAT, FISH, OTHER
    }

    public Dish(String name, boolean vegetarion, int calories, Type type) {
        this.name = name;
        this.vegetarion = vegetarion;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarion() {
        return vegetarion;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarion=" + vegetarion +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}