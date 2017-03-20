package Njava.function;

import io.reactivex.annotations.NonNull;

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
    void accept(@NonNull T t);
}
