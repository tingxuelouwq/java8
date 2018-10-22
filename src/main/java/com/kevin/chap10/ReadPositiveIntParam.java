package main.java.com.kevin.chap10;

import java.util.Optional;
import java.util.Properties;

/**
 * @类名: ReadPositiveIntParam
 * @包名：com.kevin.chap10
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/24 10:31
 * @版本：1.0
 * @描述：
 */
public class ReadPositiveIntParam {

    public static int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {

            }
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
