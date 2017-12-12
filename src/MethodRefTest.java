import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @class: MethodRefTest
 * @package: PACKAGE_NAME
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/12 10:42
 * @version: 1.0
 * @desc: Lambda表达式-方法引用
 */
public class MethodRefTest {

    public static void main(String[] args) {
        List<String> str = Arrays.asList("e", "b", "c", "d", "A");
//        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);
        forEach(str, s -> System.out.println(s));

//        Function<String, Integer> strToInt = s -> Integer.parseInt(s);
        Function<String, Integer> strToInt = Integer::parseInt;
        Integer i = strToInt.apply("123");
        System.out.println(i);

        ToIntFunction<String> stringToIntFunction = Integer::parseInt;
        Integer j = stringToIntFunction.applyAsInt("123");
        System.out.println(j);

//        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains = List::contains;
        boolean flag = contains.test(str, "A");
        System.out.println(flag);

//        Supplier<Apple> supplier = () -> new Apple();
//        Supplier<Apple> supplier = new Supplier<Apple>() {
//            @Override
//            public Apple get() {
//                return new Apple();
//            }
//        }
        Supplier<Apple> supplier = Apple::new;
        Apple apple = supplier.get();

//        IntFunction<Apple> f = (weight) -> new Apple(weight);
        IntFunction<Apple> f = Apple::new;
        Apple anotherApple = f.apply(70);

//        BiFunction<Integer, String, Apple> bf = (weight, color) -> new Apple(weight, color);
        BiFunction<Integer, String, Apple> bf = Apple::new;
        Apple yetAnotherApple = bf.apply(70, "red");

    }

    private static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }
}


