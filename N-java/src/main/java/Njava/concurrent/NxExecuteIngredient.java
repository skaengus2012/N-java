package Njava.concurrent;

import Njava.modeler.NxModeler;
import Njava.util.business.CloneUtil;
import io.reactivex.annotations.NonNull;

import java.util.concurrent.TimeUnit;

/**
 * Set ExecuteManager Setting Ingredient.
 *
 * Created by Doohyun on 2017. 4. 16..
 */
public class NxExecuteIngredient extends NxModeler {

    Integer corePoolSize = 0;
    Long keepArriveTime = 60L;
    TimeUnit timeUnit = TimeUnit.SECONDS;
    Integer maxPoolSize = Integer.MAX_VALUE;

    Integer queueSize = 100;

    private NxExecuteIngredient() {}

    /**
     * Create Builder
     *
     * @return
     */
    public static NxExecuteIngredient Builder() {
        NxExecuteIngredient executeIngredient = new NxExecuteIngredient();
        return executeIngredient;
    }

    /**
     * Get Default setting.
     *
     * @return
     */
    public static NxExecuteIngredient GetDefault() {
        return Builder();
    }

    /**
     * Set Core Pool Size.
     *
     * @param corePoolSize
     * @return
     */
    public NxExecuteIngredient setCorePoolSize(@NonNull Integer corePoolSize) {
        NxExecuteIngredient copy = CloneUtil.CopyProperties(this);
        copy.corePoolSize = corePoolSize;
        return copy;
    }

    /**
     * Set Max Pool Size.
     *
     * @param maxPoolSize
     * @return
     */
    public NxExecuteIngredient setMaxPoolSize(@NonNull Integer maxPoolSize) {
        NxExecuteIngredient copy = CloneUtil.CopyProperties(this);
        copy.maxPoolSize = maxPoolSize;

        return copy;
    }

    /**
     * Set Queue size.
     *
     * @param queueSize
     * @return
     */
    public NxExecuteIngredient setQueueSize(@NonNull Integer queueSize) {
        NxExecuteIngredient copy = CloneUtil.CopyProperties(this);
        copy.queueSize = queueSize;

        return copy;
    }

    /**
     * Set keep Arrive Time.
     *
     * @param keepArriveTime
     * @param timeUnit
     * @return
     */
    public NxExecuteIngredient setKeepArriveTime(@NonNull long keepArriveTime, @NonNull TimeUnit timeUnit) {
        NxExecuteIngredient copy = CloneUtil.CopyProperties(this);
        copy.keepArriveTime = keepArriveTime;
        copy.timeUnit = timeUnit;

        return copy;
    }
}
