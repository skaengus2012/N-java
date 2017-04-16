package Njava.concurrent.priority;

import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

import java.util.concurrent.Callable;

/**
 * Priority able Callable.
 *
 * Created by Doohyun on 2017. 4. 16..
 */
public final class PriorityCallable<T> extends PrioritySleepTask implements Callable<Maybe<T>>{

    private Callable<T> callable;

    /**
     * Construct
     *
     * @param callable
     * @param sleepMilliSecond
     * @param priority
     */
    public PriorityCallable(@NonNull Callable<T> callable, @NonNull Integer sleepMilliSecond, @NonNull Integer priority) {
        super(sleepMilliSecond, priority);
        this.callable = callable;
    }

    @Override
    public Maybe<T> call() throws Exception {
        sleep();
        return MaybeUtil.JustNullable(callable.call());
    }
}
