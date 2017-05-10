package Njava.util.function;


import Njava.function.*;
import Njava.function.extension.combineFactory.*;
import Njava.util.business.CheckUtil;
import io.reactivex.annotations.NonNull;

import java.util.Comparator;


/**
 * Lambda Util Define.
 *
 * <pre>
 *     This class is supported default and static method in JAVA8 Lambda.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */

public class LambdaUtil {

    private LambdaUtil(){}

    /**
     * Create Predicate Builder
     *
     * @param iPredicate
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> PredicateFactory<T> PredicateBuilder(@NonNull IPredicate<T> iPredicate) {
        CheckUtil.NullCheck(iPredicate);

        return new PredicateFactory<>(iPredicate);
    }

    /**
     * Create BiPredicate Builder
     *
     * @param iBiPredicate
     * @param <T>
     * @param <U>
     * @return
     */
    @NonNull
    public static <T, U> BiPredicateFactory<T, U> PredicateBuilder(@NonNull IBiPredicate<T, U> iBiPredicate) {
        CheckUtil.NullCheck(iBiPredicate);

        return new BiPredicateFactory<>(iBiPredicate);
    }

    /**
     * Create ComparatorFactory for Lambda combination.
     *
     * @param comparator
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> ComparatorFactory<T> ComparatorBuilder(@NonNull Comparator<T> comparator){
        CheckUtil.NullCheck(comparator);

        return new ComparatorFactory<>(comparator);
    }

    /**
     * Create ComparatorFactory for Lambda combination.
     *
     * @param comparator
     * @param nullFirstYn
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> ComparatorFactory<T> ComparatorBuilder(
            @NonNull Comparator<T> comparator
            , @NonNull Boolean nullFirstYn){
        CheckUtil.NullCheck(comparator);
        CheckUtil.NullCheck(nullFirstYn);

        return new ComparatorFactory<>(comparator, nullFirstYn);
    }

    /**
     * Create Comparator for support "Object" key compare.
     *
     * @param keyExtractor
     * @param keyComparator
     * @param <T>
     * @param <U>
     * @return
     */
    @NonNull
    public static <T, U> ComparatorFactory<T> ComparatorBuilder(
            @NonNull final IFunction<? super T, ? extends U> keyExtractor
            , @NonNull final Comparator<? super U> keyComparator) {

        CheckUtil.NullCheck(keyComparator);
        CheckUtil.NullCheck(keyExtractor);

        return new ComparatorFactory<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return keyComparator.compare(keyExtractor.apply(o1), keyExtractor.apply(o2));
            }
        });
    }

    /**
     * Create Comparator for support "Object" key compare.
     *
     * <pre>
     *     ComparatorFactory support.
     * </pre>
     *
     * @param keyExtractor
     * @param keyComparator
     * @param <T>
     * @param <U>
     * @return
     */
    @NonNull
    public static <T, U> ComparatorFactory<T> ComparatorBuilder(
            @NonNull IFunction<? super T, ? extends U> keyExtractor
            , @NonNull ComparatorFactory<? super U> keyComparator) {
        CheckUtil.NullCheck(keyComparator);

        return ComparatorBuilder(keyExtractor, keyComparator.get());
    }

    /**
     * Create FunctionFactory for Lambda combination
     *
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    @NonNull
    public static <T, R> FunctionFactory<T, R> FunctionBuilder(@NonNull IFunction<T, R> function) {
        CheckUtil.NullCheck(function);

        return new FunctionFactory<>(function);
    }

    /**
     * Create FunctionFactory for Lambda combination
     *
     * @param function
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    @NonNull
    public static <T, U, R> BiFunctionFactory<T, U, R> FunctionBuilder(@NonNull IBiFunction<T, U, R> function) {
        CheckUtil.NullCheck(function);

        return new BiFunctionFactory<>(function);
    }

    /**
     * Return Identity.
     *
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> UnaryOperatorFactory<T> GetIdentity() {
        return new UnaryOperatorFactory<>(new IUnaryOperator<T>() {
            @Override
            public T apply(@NonNull T t) {
                return t;
            }
        });
    }

    /**
     * Return min value T
     *
     * @param comparator
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> BinaryOperatorFactory<T> MinBy(@NonNull final Comparator<? super T> comparator) {
        CheckUtil.NullCheck(comparator);

        return new BinaryOperatorFactory<>(new IBinaryOperator<T>() {
            @Override
            public T apply(@NonNull T a, @NonNull T b) {
                return comparator.compare(a, b) <= 0 ? a : b;
            }
        });
    }

    /**
     * Return max value T
     *
     * @param comparator
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> BinaryOperatorFactory<T> MaxBy(@NonNull final Comparator<? super T> comparator) {
        CheckUtil.NullCheck(comparator);

        return new BinaryOperatorFactory<>(new IBinaryOperator<T>() {
            @Override
            public T apply(@NonNull T a, @NonNull T b) {
                return comparator.compare(a, b) >= 0 ? a : b;
            }
        });
    }
}
