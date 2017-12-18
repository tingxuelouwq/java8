package com.kevin.entity;

/**
 * @class: Apple
 * @package: com.kevin.lambda
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/12 16:46
 * @version: 1.0
 * @desc:
 */
public class Apple {

    private int weight;

    private String color;

    public Apple() {
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
