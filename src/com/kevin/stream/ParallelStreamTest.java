package com.kevin.stream;

import com.kevin.forkjoin.ForkJoinSumCalculator;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @class: ParallelStreamTest
 * @package: com.kevin.stream
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/18 9:09
 * @version: 1.0
 * @desc: 并行流
 */
public class ParallelStreamTest {

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <=n; i++) {
            result += i;
        }
        return result;
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel()
                .reduce(0L, Long::sum);
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static long measureSumPerf(Function<Long, Long> func, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = func.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    public static void main(String[] args) {
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStreamTest::sequentialSum, 10_000_000) + "msecs");
        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelStreamTest::iterativeSum, 10_000_000) + "msecs");
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreamTest::parallelSum, 10_000_000) + "msecs");
        System.out.println("Random sum done in: " + measureSumPerf(ParallelStreamTest::rangedSum, 10_000_000) + "msecs");
        System.out.println("Parallel ranged sum done in: " + measureSumPerf(ParallelStreamTest::parallelRangedSum, 10_000_000) + "msecs");
        System.out.println("ForkJoin sum done in: " + measureSumPerf(ParallelStreamTest::forkJoinSum, 10_000_000) + "msecs");
    }
}
