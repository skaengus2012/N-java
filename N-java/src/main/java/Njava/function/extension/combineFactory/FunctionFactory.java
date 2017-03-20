package Njava.function.extension.combineFactory;


import Njava.function.IFunction;
import Njava.function.exceptionLambda.IExFunction;
import io.reactivex.annotations.NonNull;

/**
 * Function combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 1..
 */

public class FunctionFactory<T, R> extends CombineFactory<IFunction<T, R>, IExFunction<T, R>> {

    public FunctionFactory(@NonNull IFunction<T, R> iFunction) {
       super(iFunction);
    }

    /**
     * Return Rx style Function.
     *
     * @return
     */
    @Override
    public IExFunction<T, R> getRx() {
        return new IExFunction<T, R>() {
            @Override
            public R apply(@NonNull T t) throws Exception {
                return get().apply(t);
            }
        };
    }

    /**
     * Function combine : compose
     *
     * <pre>
     *     example
     *          instance (iFunction) : f(x)
     *          param (before) : g(x)
     *          return : f(g(x))
     * </pre>
     *
     * @param before
     * @param <V>
     * @return
     */
    public <V> FunctionFactory<V, R> compose(@NonNull final IFunction<? super V, ? extends T> before) {
        NullCheck(before);
        return new FunctionFactory<>(new IFunction<V, R>() {
            @Override
            public R apply(@NonNull V v) {
                return get().apply(before.apply(v));
            }
        });
    }

    /**
     * Function combine : compose
     *
     * <pre>
     *  FunctionFactory support
     * </pre>
     *
     * @param before
     * @param <V>
     * @return
     */
    public <V> FunctionFactory<V, R> compose(@NonNull FunctionFactory<? super V, ? extends T> before) {
        NullCheck(before);
        return compose(before.get());
    }

    /**
     * Function combine : andThen
     *
     * <pre>
     *     example (iFunction) : f(x)
     *     param (after) : g(x)
     *     return : g(f(x))
     * </pre>
     *
     * @param after
     * @param <V>
     * @return
     */
    public <V> FunctionFactory<T, V> andThen(@NonNull final IFunction<? super R, ? extends V> after) {
        NullCheck(after);

        return new FunctionFactory<>(new IFunction<T, V>() {
            @Override
            public V apply(@NonNull T t) {
                return after.apply(get().apply(t));
            }
        });
    }

    /**
     * Function combine : andThen
     *
     * <pre>
     *  FunctionFactory support
     * </pre>
     *
     * @param after
     * @param <V>
     * @return
     */
    public <V> FunctionFactory<T, V> andThen(@NonNull final FunctionFactory<? super R, ? extends V> after) {
        NullCheck(after);

        return andThen(after.get());
    }

}
