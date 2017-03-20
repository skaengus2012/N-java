package Njava.function.extension.combineFactory;

import Njava.function.IPredicate;
import Njava.function.exceptionLambda.IExPredicate;
import io.reactivex.annotations.NonNull;

/**
 * Predicate combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 1..
 */

public class PredicateFactory<T> extends CombineFactory<IPredicate<T>, IExPredicate<T>> {

    public PredicateFactory(@NonNull IPredicate<T> predicate) {
        super(predicate);
    }

    /**
     * Return Rx style Predicate.
     *
     * @return
     */
    @Override
    public IExPredicate<T> getRx() {
        return new IExPredicate<T>() {
            @Override
            public boolean test(@NonNull T t) throws Exception {
                return get().test(t);
            }
        };
    }

    /**
     * negative 상태 predicate 를 생성한다.
     *
     * @return
     */
    public PredicateFactory<T> negative(){
        return new PredicateFactory<>(new IPredicate<T>() {
            @Override
            public boolean test(@NonNull T t) {
                return !get().test(t);
            }
        });
    }

    /**
     * predicate 에 and 상태를 추가한다.
     *
     * @param andConditionPredicate
     * @return
     */
    public PredicateFactory<T> and(@NonNull final IPredicate<T> andConditionPredicate) {
        NullCheck(andConditionPredicate);

        return new PredicateFactory<>(new IPredicate<T>() {
            @Override
            public boolean test(@NonNull T t) {
                return get().test(t) && andConditionPredicate.test(t);
            }
        });
    }

    /**
     * PredicateFactory and support!
     *
     * @param andPredicateFactory
     * @return
     */
    public PredicateFactory<T> and(@NonNull final PredicateFactory<T> andPredicateFactory) {
        NullCheck(andPredicateFactory);
        return and(andPredicateFactory.get());
    }

    /**
     * predicate 에 or 상태를 추가한다.
     *
     * @param orConditionPredicate
     * @return
     */
    public PredicateFactory<T> or(@NonNull final IPredicate<T> orConditionPredicate) {
        NullCheck(orConditionPredicate);

        return new PredicateFactory<>(new IPredicate<T>() {
            @Override
            public boolean test(@NonNull T t) {
                return get().test(t) || orConditionPredicate.test(t);
            }
        });
    }

    /**
     * PredicateFactory and support!
     *
     * @param orPredicateFactory
     * @return
     */
    public PredicateFactory<T> or(@NonNull PredicateFactory<T> orPredicateFactory) {
        NullCheck(orPredicateFactory);
        return or(orPredicateFactory.get());
    }
}
