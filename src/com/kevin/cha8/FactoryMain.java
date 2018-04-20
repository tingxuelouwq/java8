package com.kevin.cha8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @类名: FactoryMain
 * @包名：com.kevin.cha8
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/18 9:04
 * @版本：1.0
 * @描述：
 */
public class FactoryMain {

    public static void main(String[] args) {
        Product p1 = ProductFactory.createProduct("loan");

        Supplier<Product> loadSupplier = Load::new;
        Product p2 = loadSupplier.get();

        Product p3 = ProductFactory.createProductLambda("loan");
    }

    private static class ProductFactory {
        public static Product createProduct(String name) {
            switch (name) {
                case "loan": return new Load();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createProductLambda(String name) {
            Supplier<Product> p = map.get(name);
            if (p != null) {
                return p.get();
            }
            throw new RuntimeException("No such product " + name);
        }
    }

    private static interface Product {}
    private static class Load implements Product {}
    private static class Stock implements Product {}
    private static class Bond implements Product {}

    private static final Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("load", Load::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}
