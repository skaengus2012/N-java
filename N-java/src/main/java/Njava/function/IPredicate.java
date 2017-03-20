package Njava.function;

import io.reactivex.annotations.NonNull;

/**
 * JAVA8 Predicate Support
 *
 * <pre>
 *     The Original Predicate in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IPredicate<T> {
    boolean test(@NonNull T t);
}
