package com.kevin.chap7;

import java.util.function.Function;

/**
 * @类名: ParallelStreamBenchmark
 * @包名：com.kevin.chap7
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/30 9:26
 * @版本：1.0
 * @描述：
 */
public class ParallelStreamBenchmark {

    private static final long N = 10_000_000L;

    public static void main(String[] args) {
        System.out.println("Iterative sum done in:" +
                measureSumPerf(ParallelStreams::iterativeSum, N) + " msecs");
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::sequentialSum, N) + " msecs");
        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParallelStreams::parallelSum, N) + " msecs" );
        System.out.println("Parallel range sum done in:" +
                measureSumPerf(ParallelStreams::rangedSum, N) + " msecs");
        System.out.println("Parallel range sum done in:" +
                measureSumPerf(ParallelStreams::parallelRangedSum, N) + " msecs");
        System.out.println("ForkJoin sum done in: " + measureSumPerf(
                ForkJoinSumCalculator::forkJoinSum, N) + " msecs" );
    }

    private static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}
