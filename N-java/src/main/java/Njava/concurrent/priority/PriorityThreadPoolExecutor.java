package Njava.concurrent.priority;

import java.util.concurrent.*;

/**
 * Priority Queue use Executor.
 *
 * Created by Doohyun on 2017. 4. 17..
 */
public final class PriorityThreadPoolExecutor extends ThreadPoolExecutor {

    public PriorityThreadPoolExecutor(
            int corePoolSize
            , int maximumPoolSize
            , long keepAliveTime
            , TimeUnit unit
            , PriorityBlockingQueue<Runnable> workQueue
            , ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    /**
     * eturn New Task (Callable Type)
     *
     * @param callable
     * @param <T>
     * @return
     */
    @Override
    protected final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        RunnableFuture<T> newTaskFor = super.newTaskFor(callable);
        return new PriorityFuture<>(newTaskFor, ((PriorityCallable) callable));
    }

    /**
     * Return New Task (Runnable Type)
     *
     * @param runnable
     * @param value
     * @param <T>
     * @return
     */
    @Override
    protected final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        RunnableFuture<T> newTaskFor = super.newTaskFor(runnable, value);
        return new PriorityFuture<>(newTaskFor, ((PriorityRunnable) runnable));
    }
}
