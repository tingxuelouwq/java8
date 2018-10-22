package main.java.com.kevin.chap8;

/**
 * @类名: StrategyMain
 * @包名：com.kevin.cha8
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/16 9:06
 * @版本：1.0
 * @描述：
 */
public class StrategyMain {

    public static void main(String[] args) {
        // old school
        Validator v1 = new Validator(new isNumeric());
        System.out.println(v1.validate("aaa"));
        Validator v2 = new Validator(new isAllLowerCase());
        System.out.println(v2.validate("bbbb"));

        // with lambdas
        Validator v3 = new Validator(s -> s.matches("\\d+"));
        System.out.println(v3.validate("aaa"));
        Validator v4 = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(v4.validate("bbb"));
    }

    private interface ValidationStrategy {
        boolean execute(String s);
    }

    private static class isAllLowerCase implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    private static class isNumeric implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    private static class Validator {
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }
}
