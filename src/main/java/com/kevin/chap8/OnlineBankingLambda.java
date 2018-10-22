package main.java.com.kevin.chap8;

import java.util.function.Consumer;

/**
 * @类名: OnlineBankingLambda
 * @包名：com.kevin.cha8
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/16 9:28
 * @版本：1.0
 * @描述：
 */
public class OnlineBankingLambda {

    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, c -> System.out.println("Hello!"));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    // dummy Customer class
    private static class Customer {}

    // dummy Database class
    private static class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }
}
