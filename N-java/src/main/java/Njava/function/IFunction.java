package Njava.function;

import io.reactivex.annotations.NonNull;

/**
 * JAVA8 Function Support
 *
 * <pre>
 *     The Original Function in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IFunction<T, R> {
    R apply(@NonNull T t);
}
