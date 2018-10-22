package main.java.com.kevin.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @类名: StreamVsCollection
 * @包名：com.kevin.chap4
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/13 15:28
 * @版本：1.0
 * @描述：
 */
public class StreamVsCollection {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        // uncommenting this line will result in an IllegalStateException
        // because streams can be consumed only once
        //s.forEach(System.out::println);
    }
}
