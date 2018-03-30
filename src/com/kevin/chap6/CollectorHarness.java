package com.kevin.chap6;

import java.util.function.Consumer;

/**
 * @类名: CollectorHarness
 * @包名：com.kevin.chap6
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/3/27 17:12
 * @版本：1.0
 * @描述：
 */
public class CollectorHarness {

    public static void main(String[] args) {
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimes) + " msecs");
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimesWithCustomCollector) + " msecs" );
    }

    private static long execute(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
            System.out.println("done in " + duration);
        }
        return fastest;
    }
}
