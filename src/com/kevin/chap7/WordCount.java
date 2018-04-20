package com.kevin.chap7;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @类名: WordCount
 * @包名：com.kevin.chap7
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/10 9:36
 * @版本：1.0
 * @描述：
 */
public class WordCount {

    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
                    "mi  ritrovai in una  selva oscura" +
                    " che la  dritta via era   smarrita the end.";

    public static void main(String[] args) {
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        System.out.println("Found " + countWordsWithStream(SENTENCE) + " words");
        System.out.println("Found " + countWordsWithSpliterator(SENTENCE) + " words");
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    public static int countWordsWithStream(String s) {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        return internalCountWords(stream);
    }

    public static int countWordsWithSpliterator(String s) {
        Spliterator<Character> spliterator = new WordCountSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        return internalCountWords(stream.parallel());
    }

    private static int internalCountWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    private static class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ?
                        this :
                        new WordCounter(counter, true);
            } else {
                return lastSpace ?
                        new WordCounter(counter + 1, false) :
                        this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter,
                    wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }

    private static class WordCountSpliterator implements Spliterator<Character> {

        private final String string;
        private int currentChar = 0;

        public WordCountSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if (currentSize < 10) { // 返回null表示要解析的String已经足够小，可以顺序处理
                return null;
            }
            for (int splitPos = currentSize / 2 + currentChar;
                 splitPos < string.length(); splitPos++) {
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    Spliterator<Character> spliterator =
                            new WordCountSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
}
