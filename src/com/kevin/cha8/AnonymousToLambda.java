package com.kevin.cha8;

/**
 * @类名: AnonymousToLambda
 * @包名：com.kevin.cha8
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/12 9:48
 * @版本：1.0
 * @描述：
 */
public class AnonymousToLambda {

    public static void main(String[] args) {
        int a = 10;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int a = 20;
                System.out.println(a);
            }
        };
        r1.run();

        int b = 10;
        Runnable r2 = () -> System.out.println(b);
        r2.run();

        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Hello World");
            }
        });

        doSomething((Task)() -> System.out.println("Hello Java"));
    }

    public static void doSomething(Runnable runnable) {
        runnable.run();;
    }

    public static void doSomething(Task task) {
        task.execute();
    }

    private interface Task {
        void execute();
    }
}
