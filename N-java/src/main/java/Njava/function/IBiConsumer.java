package Njava.function;


import io.reactivex.annotations.NonNull;

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
    void accept(@NonNull T t, @NonNull R r);
}
