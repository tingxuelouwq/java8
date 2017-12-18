package com.kevin.stream;

import org.omg.PortableInterceptor.ACTIVE;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @class: WordCounterSpliterator
 * @package: com.kevin.stream
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/18 15:45
 * @version: 1.0
 * @desc:
 */
public class WordCounterSpliterator  implements Spliterator<Character> {

    private static final int THRESHOLD = 10;
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
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
        if (currentSize < THRESHOLD) {
            return null;
        }

        for (int splitPos = currentSize / 2 + currentChar;
                splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
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
