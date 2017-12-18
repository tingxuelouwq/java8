package com.kevin.stream;

/**
 * @class: WordCounter
 * @package: com.kevin.stream
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/18 15:33
 * @version: 1.0
 * @desc:
 */
public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
