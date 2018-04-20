package com.kevin.cha8;

/**
 * @类名: OnlineBanking
 * @包名：com.kevin.cha8
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/16 9:19
 * @版本：1.0
 * @描述：
 */
public abstract class OnlineBanking {

    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    protected abstract void makeCustomerHappy(Customer c);

    // dummy Customer class
    private static class Customer {}

    // dummy Database class
    private static class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }
}
