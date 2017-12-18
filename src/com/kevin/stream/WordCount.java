package com.kevin.stream;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @class: WordCount
 * @package: com.kevin.stream
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/18 15:28
 * @version: 1.0
 * @desc:
 */
public class WordCount {

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else if (lastSpace) {
                counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream) + " words");

        Stream<Character> parallelStream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt).parallel();
        System.out.println("Found " + countWords(parallelStream) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> parallelStream2 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(parallelStream2) + " words");
    }
}
