package Njava.concurrent.priority;

import Njava.function.exceptionLambda.IExRunnable;
import io.reactivex.annotations.NonNull;

/**
 * Priority runnable.
 *
 * Created by Doohyun on 2017. 4. 16..
 */
public final class PriorityRunnable extends PrioritySleepTask implements Runnable{

    private IExRunnable runnable;

    /**
     * Construct
     *
     * @param runnable
     * @param sleepTime
     * @param priority
     */
    public PriorityRunnable(@NonNull IExRunnable runnable, @NonNull Integer sleepTime, @NonNull Integer priority) {
        super(sleepTime, priority);

        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            sleep();
            runnable.run();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
