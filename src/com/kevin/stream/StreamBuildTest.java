package com.kevin.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @class: StreamBuildTest
 * @package: com.kevin.lambda
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/13 15:38
 * @version: 1.0
 * @desc: 构建流
 */
public class StreamBuildTest {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Java", "Python", "C++");
        stream.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).sum();

        long uniqueWords = 0;
        String filePath = "C:\\Users\\kevin\\Desktop\\测试.txt";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            uniqueWords = lines.map(line -> line.split(" "))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(() -> (int) (Math.random() * 10))
                .limit(10)
                .sorted(Integer::compareTo)
                .forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
