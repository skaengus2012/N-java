package Njava.util.function;

import Njava.function.exceptionLambda.IExConsumer;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

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
}
