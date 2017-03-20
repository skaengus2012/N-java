package Njava.function;

import io.reactivex.annotations.NonNull;

/**
 * JAVA8 BiFunction Support
 *
 * <pre>
 *     The Original BiFunction in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IBiFunction<T, U, R> {
    R apply(@NonNull T t, @NonNull U u);
}
