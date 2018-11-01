package com.kevin.chap11.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 类名: BestPriceFinder<br/>
 * 包名：com.kevin.chap11<br/>
 * 作者：kevin[wangqi2017@xinhua.org]<br/>
 * 时间：2018/10/24 10:15<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy5"),
            new Shop("ShopEasy6"),
            new Shop("ShopEasy7"),
            new Shop("ShopEasy8"),
            new Shop("ShopEasy9"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });


    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        return priceFutures.stream()
                .map((CompletableFuture::join))
                .collect(Collectors.toList());
    }

    public List<String> findPricesExecutorFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() ->
                                String.format("%s price is %.2f",
                                        shop.getName(), shop.getPrice(product)), executor))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map((CompletableFuture::join))
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD(String product) {
        List<CompletableFuture<Double>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor)
                        .thenCombine(CompletableFuture.supplyAsync(() ->
                                        ExchangeService.getRate(ExchangeService.Money.EUR,
                                                ExchangeService.Money.USD), executor),
                                (price, rate) -> price * rate))
                    .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .map(price -> " price is " + price)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD2(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor)
                        .thenCombine(CompletableFuture.supplyAsync(() ->
                                        ExchangeService.getRate(ExchangeService.Money.EUR,
                                                ExchangeService.Money.USD), executor),
                                (price, rate) -> price * rate)
                        .thenApply(price -> shop.getName() + " price is " + price))
                .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD3(String product) {
        CompletableFuture<Double> rateFuture = CompletableFuture.supplyAsync(() ->
                ExchangeService.getRate(ExchangeService.Money.EUR,
                        ExchangeService.Money.USD));
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor)
                        .thenCombine(rateFuture, (price, rate) -> price * rate)
                        .thenApply(price -> shop.getName() + " price is " + price))
                .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() {
                    return ExchangeService.getRate(ExchangeService.Money.EUR,
                            ExchangeService.Money.USD);
                }
            });
            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                @Override
                public Double call() {
                    try {
                        double priceInEUR = shop.getPrice(product);
                        return priceInEUR * futureRate.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(" price is " + priceFuture.get());
            }
            catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }
}
