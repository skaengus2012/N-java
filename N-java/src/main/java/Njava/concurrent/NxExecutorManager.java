package Njava.concurrent;

import Njava.concurrent.priority.*;
import Njava.function.exceptionLambda.IExRunnable;
import Njava.modeler.NxModeler;
import Njava.util.function.LambdaUtil;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 * Managing ExecuteService
 * <p>
 * <pre>
 *     This Manager aim Supplier-Consumer pattern.
 * </pre>
 * <p>
 * Created by Doohyun on 2017. 4. 16..
 */
public class NxExecutorManager extends NxModeler {

    /**
     * PRIORITY CONST.
     */
    private static final class PRIORITY {
        private static final int EMERGENCY = 1;
        private static final int STANDARD = 2;
    }

    private ExecutorService executorService;

    /**
     * Create Execute Service.
     *
     * @param executeIngredient
     */
    private NxExecutorManager(@NonNull NxExecuteIngredient executeIngredient) {

        // Nullable Comparator
        final Comparator<Runnable> runnableComparator = LambdaUtil.ComparatorBuilder(new Comparator<Runnable>() {
            @Override
            public int compare(Runnable o1, Runnable o2) {

                Integer p1 = ((IPriorityAble) o1).getPriority();
                Integer p2 = ((IPriorityAble) o2).getPriority();

                return p1.compareTo(p2);
            }
        }).get();

        // only use Daemon Thread
        final ThreadFactory daemonThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = Executors.defaultThreadFactory().newThread(r);

                // When main thread kill, Thread will be killed.
                thread.setDaemon(true);

                return thread;
            }
        };

        executorService = new PriorityThreadPoolExecutor(
                executeIngredient.corePoolSize
                , executeIngredient.maxPoolSize
                , executeIngredient.keepArriveTime
                , executeIngredient.timeUnit
                , new PriorityBlockingQueue<>(executeIngredient.queueSize, runnableComparator)
                , daemonThreadFactory);
    }

    /**
     * Create ExecuteManager.
     *
     * @param executeIngredient
     * @return
     */
    public static NxExecutorManager Create(@NonNull NxExecuteIngredient executeIngredient) {
        NullCheck(executeIngredient);

        NxExecutorManager instance = new NxExecutorManager(executeIngredient);

        return instance;
    }

    /**
     * Run Emergency
     *
     * @param runnable
     */
    public void submitEmergency(final @NonNull IExRunnable runnable) {
        submitEmergency(runnable, 0);
    }

    /**
     * Run Emergency priority. (with sleepTime)
     *
     * @param runnable
     * @param sleepTime
     */
    public void submitEmergency(final @NonNull IExRunnable runnable, @NonNull final Integer sleepTime) {
        submit(runnable, sleepTime, PRIORITY.EMERGENCY);
    }

    /**
     * Run Standard priority.
     *
     * @param runnable
     */
    public void submit(final @NonNull IExRunnable runnable) {
        submit(runnable, 0);
    }

    /**
     * Run Standard priority. (with sleepTime)
     *
     * @param runnable
     * @param sleepTime
     */
    public void submit(final @NonNull IExRunnable runnable, @NonNull final Integer sleepTime) {
        submit(runnable, sleepTime, PRIORITY.STANDARD);
    }

    /**
     * Run
     *
     * @param runnable
     * @param sleepTime
     * @param priority
     */
    private void submit(final @NonNull IExRunnable runnable, @NonNull final Integer sleepTime, @NonNull final Integer priority) {
        executorService.submit(new PriorityRunnable(runnable, sleepTime, priority));
    }

    /**
     * Run Emergency priority.
     *
     * @param callable
     * @param <T>
     * @return
     */
    public <T> Future<Maybe<T>> submitEmergency(final @NonNull Callable<T> callable) {
        return submitEmergency(callable, 0);
    }

    /**
     * Run Emergency priority. (with sleepTime)
     *
     * @param callable
     * @param sleepTime
     * @param <T>
     * @return
     */
    public <T> Future<Maybe<T>> submitEmergency(final @NonNull Callable<T> callable, @NonNull Integer sleepTime) {
        return submit(callable, sleepTime, PRIORITY.EMERGENCY);
    }

    /**
     * Run Standard priority
     *
     * @param callable
     * @param <T>
     * @return
     */
    public <T> Future<Maybe<T>> submit(final @NonNull Callable<T> callable) {
        return submit(callable, 0);
    }

    /**
     * Run Standard priority. (with sleepTime)
     *
     * @param callable
     * @param sleepTime
     * @param <T>
     * @return
     */
    public <T> Future<Maybe<T>> submit(final @NonNull Callable<T> callable, @NonNull Integer sleepTime) {
        return submit(callable, sleepTime, PRIORITY.STANDARD);
    }

    /**
     * Return Future.
     *
     * @param callable
     * @param sleepTime
     * @param priority
     * @param <T>
     * @return
     */
    private <T> Future<Maybe<T>> submit(final @NonNull Callable<T> callable, @NonNull final Integer sleepTime, @NonNull final Integer priority) {
        return executorService.submit(new PriorityCallable<>(callable, sleepTime, priority));
    }
}
