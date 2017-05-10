package Njava.util.function;

import Njava.function.ISupplier;
import Njava.function.exceptionLambda.IExConsumer;
import Njava.function.exceptionLambda.IExFunction;
import Njava.util.business.CheckUtil;
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

    private MaybeUtil(){}

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
     * Get with defaultValueSupplier
     *
     * @param maybe
     * @param defaultValueSupplier
     * @param <T>
     * @return
     */
    public static <T> T BlockingGet(@NonNull Maybe<T> maybe, @NonNull ISupplier<T> defaultValueSupplier){
        if (MaybeUtil.IsEmpty(maybe)) {
            return defaultValueSupplier.accept();
        } else {
            return maybe.blockingGet();
        }
    }

    /**
     * Check empty.
     *
     * @param maybe
     * @return
     */
    public static boolean IsEmpty(@Nullable Maybe<? extends Object> maybe) {
        return MaybeUtil.JustNullable(maybe).
                    map(new IExFunction<Maybe<? extends Object>, Boolean>(){
                        @Override
                        public Boolean apply(@NonNull Maybe<?> maybe) throws Exception {
                            return maybe.isEmpty().blockingGet();
                        }
                    }).
                    blockingGet(true);
    }

    /**
     * Equals check in Maybe.
     *
     * @param m1
     * @param m2
     * @return
     */
    public static boolean Equals(@NonNull final Maybe<? extends Object> m1, @NonNull final Maybe<? extends Object> m2) {
        CheckUtil.NullCheck(m1);
        CheckUtil.NullCheck(m2);

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
        CheckUtil.NullCheck(m1);
        CheckUtil.NullCheck(m2);

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
        CheckUtil.NullCheck(m1);
        CheckUtil.NullCheck(m2);
        CheckUtil.NullCheck(comparator);

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
