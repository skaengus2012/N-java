package Njava.util.function;

import Njava.function.exceptionLambda.IExConsumer;
import Njava.function.exceptionLambda.IExFunction;
import Njava.modeler.NxModeler;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import java.util.Comparator;

/**
 * Rx Maybe Support.
 * <p>
 * <pre>
 *     Rx.Maybe is not strong compared java8.optional.
 *
 *     So, I support Rx.Maybe using MaybeUtil.
 * </pre>
 * <p>
 * Created by Doohyun on 2017. 3. 15..
 */

public class MaybeUtil {

    /**
     * Create Maybe null able.
     * <p>
     * <pre>
     *     If t is empty, create empty maybe.
     * </pre>
     *
     * @param t
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> Maybe<T> JustNullable(@Nullable T t) {
        if (t == null) {
            return Maybe.empty();
        } else {
            return Maybe.just(t);
        }
    }

    /**
     * If maybe is valid, consumer param will execute. But Empty maybe will be emptyRunnable;
     *
     * @param maybe
     * @param consumer
     * @param emptyRunnable
     * @param <T>
     */
    @NonNull
    public static <T> void Subscribe(
            @NonNull Maybe<T> maybe
            , @NonNull IExConsumer<T> consumer
            , @NonNull Runnable emptyRunnable) {
        // empty maybe.
        SubscribeEmpty(maybe, emptyRunnable);

        // valid maybe.
        maybe.subscribe(consumer);
    }

    /**
     * If maybe is empty, Runnable will be excute.
     *
     * @param maybe
     * @param run
     * @param <T>
     */
    @NonNull
    public static <T> void SubscribeEmpty(
            @NonNull Maybe<T> maybe
            , @NonNull final Runnable run) {
        maybe.isEmpty().subscribe(new IExConsumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean emptyYn) throws Exception {
                if (emptyYn) {
                    run.run();
                }
            }
        });
    }

    /**
     * Check empty.
     *
     * @param maybe
     * @return
     */
    public static boolean IsEmpty(@NonNull Maybe<? extends Object> maybe) {
        NxModeler.NullCheck(maybe);

        return maybe.isEmpty().blockingGet();
    }

    /**
     * Equals check in Maybe.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static boolean Equals(@NonNull final Maybe<? extends Object> m1, @NonNull final Maybe<? extends Object> m2) {
        NxModeler.NullCheck(m1);
        NxModeler.NullCheck(m2);

        return m1.flatMap(new IExFunction<Object, Maybe<Boolean>>(){
                                @Override
                                public Maybe<Boolean> apply(@NonNull final Object o1) throws Exception {
                                    return m2.map(new IExFunction<Object, Boolean>(){
                                                @Override
                                                public Boolean apply(@NonNull Object o2) throws Exception {
                                                    return o1.equals(o2);
                                                }
                                            });
                                }
                }).
                blockingGet(false);
    }

    /**
     * Maybe compareTo use.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static int CompareTo(@NonNull final Maybe<? extends Comparable> m1, @NonNull final Maybe<? extends Comparable> m2) {
        NxModeler.NullCheck(m1);
        NxModeler.NullCheck(m2);

        return m1.flatMap(new IExFunction<Comparable, Maybe<Integer>>(){
                    @Override
                    public Maybe<Integer> apply(@NonNull final Comparable o1) throws Exception {
                        return m2.map(new IExFunction<Comparable, Integer>(){
                            @Override
                            public Integer apply(@NonNull Comparable o2) throws Exception {
                                return o1.compareTo(o2);
                            }
                        });
                    }
                }).
                blockingGet(0);
    }

    /**
     * Maybe compareTo use (with Comparator).
     *
     * @param m1
     * @param m2
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> int CompareTo(
            @NonNull final Maybe<? extends T> m1
            , @NonNull final Maybe<? extends T> m2
            , @NonNull final Comparator<T> comparator) {
        NxModeler.NullCheck(m1);
        NxModeler.NullCheck(m2);
        NxModeler.NullCheck(comparator);

        return m1.flatMap(new IExFunction<T, Maybe<Integer>>(){
                    @Override
                    public Maybe<Integer> apply(@NonNull final T o1) throws Exception {
                        return m2.map(new IExFunction<T, Integer>(){
                            @Override
                            public Integer apply(@NonNull T o2) throws Exception {
                                return comparator.compare(o1, o2);
                            }
                        });
                    }
                }).
                blockingGet(0);
    }
}
