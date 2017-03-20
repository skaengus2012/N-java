package Njava.function.extension.combineFactory;


import Njava.function.extension.supportFunction.NullAbleComparator;
import io.reactivex.annotations.NonNull;

import java.util.Collections;
import java.util.Comparator;


/**
 * Comparator combination Factory
 *
 * <pre>
 *     Support default method in Comparator.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 1..
 */

public class ComparatorFactory<T> extends CombineFactory<NullAbleComparator<T>, Comparator<T>> {

    private Boolean nullFirstYn = false;            // null priority.

    public ComparatorFactory(@NonNull Comparator<T> comparator) {
        super(new NullAbleComparator<>(comparator));
    }

    /**
     * Comparator equals Rx and JAVA8.
     *
     * @return
     */
    @Override
    public Comparator<T> getRx() {
        return get();
    }

    /**
     * 생성자 정의
     *
     * <pre>
     *     null 우선순위를 정할 수 있음.
     * </pre>
     *
     * @param comparator
     * @param nullFirstYn
     */
    public ComparatorFactory(
            @NonNull Comparator<T> comparator
            , @NonNull Boolean nullFirstYn) {
        super(new NullAbleComparator<>(comparator, nullFirstYn));
        NullCheck(nullFirstYn);

        this.nullFirstYn = nullFirstYn;
    }

    /**
     * null first.
     *
     * @return
     */
    public ComparatorFactory<T> nullsFirst() {
        return new ComparatorFactory<>(get(), true);
    }

    /**
     * null last.
     *
     * @return
     */
    public ComparatorFactory<T> nullsLast() {
        return new ComparatorFactory<>(get(), false);
    }

    /**
     * Sort reversed
     *
     * @return
     */
    public ComparatorFactory<T> reversed(){
        return new ComparatorFactory<>(Collections.reverseOrder(get()), !nullFirstYn);
    }

    /**
     * add compare!
     *
     * @param thenComparator
     * @return
     */
    public ComparatorFactory<T> thenComparing(@NonNull final Comparator<T> thenComparator) {
        NullCheck(thenComparator);

        return new ComparatorFactory<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                int res = get().compare(o1, o2);
                return (res != 0) ? res : thenComparator.compare(o1, o2);
            }
        }, nullFirstYn);
    }

    /**
     * add Compare!
     *
     * <pre>
     *     Support ComparatorFactory!
     * </pre>
     *
     * @param comparatorFactory
     * @return
     */
    public ComparatorFactory<T> thenComparing(@NonNull ComparatorFactory<T> comparatorFactory) {
        NullCheck(comparatorFactory);
        return thenComparing(comparatorFactory.get());
    }
}
