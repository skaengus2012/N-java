package Njava.function.extension.combineFactory;

import Njava.function.IBiPredicate;
import Njava.function.exceptionLambda.IExBiPredicate;
import io.reactivex.annotations.NonNull;

/**
 * BiPredicate combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 7..
 */

public class BiPredicateFactory<T, U> extends CombineFactory<IBiPredicate<T, U>, IExBiPredicate<T, U>>{

    public BiPredicateFactory(@NonNull IBiPredicate<T, U> biPredicate) {
        super(biPredicate);
    }

    /**
     * Return Rx style BiPredicate.
     *
     * @return
     */
    @Override
    public IExBiPredicate<T, U> getRx() {
        return new IExBiPredicate<T, U>() {
            public boolean test(@NonNull T t, @NonNull U u) throws Exception {
                return get().test(t, u);
            }
        };
    }

    /**
     * Predicate 에 or 상태를 추가한다.
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> or(@NonNull final IBiPredicate<? super T, ? super U> other) {
        NullCheck(other);
        return new BiPredicateFactory<>(new IBiPredicate<T, U>() {
            @Override
            public boolean test(@NonNull T t, @NonNull U u) {
                return get().test(t, u) || other.test(t, u);
            }
        });
    }

    /**
     * Support BiPredicateFactory
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> or(@NonNull BiPredicateFactory<? super T, ? super U> other) {
        NullCheck(other);
        return or(other.get());
    }

    /**
     * predicate 에 and 상태를 추가한다.
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> and(@NonNull final IBiPredicate<? super T, ? super U> other) {
        NullCheck(other);
        return new BiPredicateFactory<>(new IBiPredicate<T, U>() {
            @Override
            public boolean test(@NonNull T t, @NonNull U u) {
                return get().test(t, u) && other.test(t, u);
            }
        });
    }

    /**
     * Support BiPredicateFactory
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> and(@NonNull BiPredicateFactory<? super T, ? super U> other) {
        NullCheck(other);
        return and(other.get());
    }

    /**
     * negative 상태 predicate 를 생성한다.
     *
     * @return
     */
    public BiPredicateFactory<T, U> negate() {
        return new BiPredicateFactory<>(new IBiPredicate<T, U>() {
            @Override
            public boolean test(@NonNull T t, @NonNull U u) {
                return !get().test(t, u);
            }
        });
    }
}
