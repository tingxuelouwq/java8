package com.kevin.chap8;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @类名: ChainOfReponsibilityMain
 * @包名：com.kevin.cha8
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/17 9:29
 * @版本：1.0
 * @描述：
 */
public class ChainOfReponsibilityMain {

    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result1 = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result1);

        UnaryOperator<String> headerProcessing = text -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = text -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }

    private abstract static class ProcessingObject<T> {
        private ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        abstract protected T handleWork(T input);
    }

    private static class HeaderTextProcessing
            extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }
    }

    private static class SpellCheckerProcessing
            extends ProcessingObject<String> {
        public String handleWork(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }
}
