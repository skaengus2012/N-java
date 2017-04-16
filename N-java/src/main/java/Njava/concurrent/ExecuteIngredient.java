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
public class ExecuteIngredient extends NxModeler {
    private Integer corePoolSize = 0;
    private Long keepArriveTime = 60L;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    private ExecuteIngredient() {}

    /**
     * Create Builder
     *
     * @return
     */
    public static ExecuteIngredient Builder() {
        ExecuteIngredient executeIngredient = new ExecuteIngredient();
        return executeIngredient;
    }

    /**
     * Get Default setting.
     *
     * @return
     */
    public static ExecuteIngredient GetDefault() {
        return Builder();
    }

    /**
     * Set Core Pool Size.
     *
     * @param corePoolSize
     * @return
     */
    public ExecuteIngredient setCorePoolSize(@NonNull Integer corePoolSize) {
        ExecuteIngredient copy = CloneUtil.CopyProperties(this);
        return copy;
    }

    /**
     * Set keep Arrive Time.
     *
     * @param keepArriveTime
     * @param timeUnit
     * @return
     */
    public ExecuteIngredient setKeepArriveTime(@NonNull long keepArriveTime, @NonNull TimeUnit timeUnit) {
        ExecuteIngredient copy = CloneUtil.CopyProperties(this);
        copy.keepArriveTime = keepArriveTime;
        copy.timeUnit = timeUnit;

        return copy;
    }


}
