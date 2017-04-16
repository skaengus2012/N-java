package Njava.concurrent.priority;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Priority Future define.
 *
 * Created by Doohyun on 2017. 4. 16..
 */
public final class PriorityFuture<T> implements RunnableFuture<T>, IPriorityAble {

    private RunnableFuture<T> src;
    private Integer priority;

    public PriorityFuture(RunnableFuture<T> other, IPriorityAble priorityAble) {
        this.src = other;
        this.priority = priorityAble.getPriority();
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return src.cancel(mayInterruptIfRunning);
    }

    public boolean isCancelled() {
        return src.isCancelled();
    }

    public boolean isDone() {
        return src.isDone();
    }

    public T get() throws InterruptedException, ExecutionException {
        return src.get();
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return src.get();
    }

    public void run() {
        src.run();
    }
}
