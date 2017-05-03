package Njava.function;

/**
 * JAVA8 BiConsumer Support
 *
 * <pre>
 *     The Original BiConsumer in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IBiConsumer<T, R> {
    void accept(T t, R r);
}
