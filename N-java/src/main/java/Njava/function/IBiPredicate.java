package Njava.function;

/**
 * JAVA8 BiPredicate Support
 *
 * <pre>
 *     The Original BiPredicate in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IBiPredicate<T, R> {
    boolean test(T t, R r);
}
