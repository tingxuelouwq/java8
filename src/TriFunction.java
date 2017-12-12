/**
 * @class: TriFunction
 * @package: PACKAGE_NAME
 * @author: kevin[wangqi2017@xinhua.org]
 * @date: 2017/12/12 16:46
 * @version: 1.0
 * @desc:
 */
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T T, U U, V v);
}
