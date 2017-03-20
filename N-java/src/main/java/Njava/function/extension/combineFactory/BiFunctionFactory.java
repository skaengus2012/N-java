package Njava.function.extension.combineFactory;

import Njava.function.IBiFunction;
import Njava.function.IFunction;
import Njava.function.exceptionLambda.IExBiFunction;
import io.reactivex.annotations.NonNull;

/**
 * BiFunction combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 7..
 */

public class BiFunctionFactory<T, U, R> extends CombineFactory<IBiFunction<T, U, R>, IExBiFunction<T, U, R>> {

    public BiFunctionFactory(IBiFunction<T, U, R> biFunction) {
        super(biFunction);
    }

    /**
     * Return Rx style BiFunction.
     *
     * @return
     */
    @Override
    public IExBiFunction<T, U, R> getRx() {
        return new IExBiFunction<T, U, R>() {
            @Override
            public R apply(@NonNull T t, @NonNull U u) throws Exception {
                return get().apply(t, u);
            }
        };
    }

    /**
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
    public <V> BiFunctionFactory<T, U, V> andThen(@NonNull final IFunction<? super R, ? extends V> after) {
        NullCheck(after);

        return new BiFunctionFactory<>(new IBiFunction<T, U, V>() {
            @Override
            public V apply(@NonNull T t, @NonNull U u) {
                return after.apply(get().apply(t, u));
            }
        });
    }

    /**
     * Function Factory support.
     *
     * @param after
     * @param <V>
     * @return
     */
    public <V> BiFunctionFactory<T, U, V> andThen(@NonNull FunctionFactory<? super R, ? extends V> after) {
        NullCheck(after);

        return andThen(after.get());
    }
}
