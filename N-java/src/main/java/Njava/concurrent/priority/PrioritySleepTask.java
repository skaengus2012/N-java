package Njava.concurrent.priority;

import io.reactivex.annotations.NonNull;

/**
 * Priority & Sleep able Task
 *
 * Created by Doohyun on 2017. 4. 16..
 */
public abstract class PrioritySleepTask implements IPriorityAble {

    private Integer priority;
    private Integer sleepMilliSecond;

    /**
     * Construct
     *
     * @param sleepMilliSecond
     * @param priority
     */
    protected PrioritySleepTask(@NonNull Integer sleepMilliSecond, @NonNull Integer priority) {
        this.priority = priority;
        this.sleepMilliSecond = sleepMilliSecond;
    }

    /**
     * Sleep Task.
     *
     * @throws InterruptedException
     */
    protected final void sleep() throws InterruptedException{
        if (sleepMilliSecond.intValue() != 0) {
            Thread.sleep(sleepMilliSecond);
        }
    }

    @Override
    public final Integer getPriority() {
        return priority;
    }
}
