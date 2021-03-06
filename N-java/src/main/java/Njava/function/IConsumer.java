package Njava.function;

/**
 * JAVA8 Consumer Support
 *
 * <pre>
 *     The Original Consumer in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IConsumer<T> {
    void accept(T t);
}
