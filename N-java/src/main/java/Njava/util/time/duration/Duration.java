package Njava.util.time.duration;

import Njava.modeler.NxModeler;
import Njava.util.function.LambdaUtil;
import Njava.util.time.TimeBuilder;
import io.reactivex.annotations.NonNull;

import java.util.Comparator;

/**
 * Between Time state.
 *
 * Created by Doohyun on 2017. 4. 3..
 */
public abstract class Duration {
    private TimeBuilder maxTime, minTime;

    /**
     * For dividing time.
     */
    protected static class DIVIDE_CONST {
        protected static final long SECOND_DIVIDE = 1000L;
        protected static final long MINUTE_DIVIDE = 60L * SECOND_DIVIDE;
        protected static final long HOUR_DIVIDE = 60L * MINUTE_DIVIDE;
        protected static final long DAY_DIVIDE = 24L * HOUR_DIVIDE;
        protected static final long MONTH_DIVIDE = 30L;
        protected static final long YEAR_DIVIDE = 365L;
    }

    protected Duration(){}

    public static Duration Create(@NonNull TimeBuilder time1, @NonNull TimeBuilder time2) {
        // standard : relative!!!
        Duration builder = new RelativeDuration();

        NxModeler.NullCheck(time1);
        NxModeler.NullCheck(time2);

        // simple comparator.
        Comparator<TimeBuilder> timeBuilderComparator = new Comparator<TimeBuilder>() {
            @Override
            public int compare(TimeBuilder o1, TimeBuilder o2) {
                return o1.compareTo(o2);
            }
        };

        builder.maxTime = LambdaUtil.MaxBy(timeBuilderComparator).get().apply(time1, time2);
        builder.minTime = LambdaUtil.MinBy(timeBuilderComparator).get().apply(time1, time2);

        return builder;
    }

    /**
     * Create current instance style Object.
     *
     * @return
     */
    protected abstract Duration createCopyObject();

    /**
     * Difference Year.
     *
     * @return
     */
    public abstract long toYear();

    /**
     * Difference month
     *
     * @return
     */
    public abstract long toMonth();

    /**
     * Return deep copy.
     *
     * @return
     */
    private Duration getCopyObject() {
        Duration copyObject = createCopyObject();
        setDeepCopy(copyObject);

        return copyObject;
    }

    /**
     * Setting deep copy.
     *
     * @param abstractDuration
     */
    private void setDeepCopy(Duration abstractDuration) {
        abstractDuration.maxTime = maxTime;
        abstractDuration.minTime = minTime;
    }

    /**
     * Absolute Method for calculating Time
     *
     * @return
     */
    public final Duration toAbsolute() {
        Duration duration = new AbsoluteDuration();
        setDeepCopy(duration);

        return duration;
    }

    /**
     * Relative Method for calculating Time
     *
     * @return
     */
    public final Duration toRelative() {
        Duration duration = new RelativeDuration();
        setDeepCopy(duration);

        return duration;
    }

    /**
     * Return max time.
     *
     * @return
     */
    @NonNull
    public final TimeBuilder getMaxTime() {
        return maxTime;
    }

    /**
     * Return min time.
     *
     * @return
     */
    @NonNull
    public final TimeBuilder getMinTime() {
        return minTime;
    }

    /**
     * Return Difference time.
     *
     * @return
     */
    @NonNull
    public final long toTime() {
        return maxTime.getTime() - minTime.getTime();
    }

    /**
     * Return Difference day
     *
     * @return
     */
    public final long toDayOfMonth() {
        return toTime() / DIVIDE_CONST.DAY_DIVIDE;
    }

    /**
     * Return Difference hour
     *
     * @return
     */
    public final long toHour() {
        return toTime() / DIVIDE_CONST.HOUR_DIVIDE;
    }

    /**
     * Return Difference minute
     *
     * @return
     */
    public final long toMinute() {
        return toTime() / DIVIDE_CONST.MINUTE_DIVIDE;
    }

    /**
     * Return Difference second
     *
     * @return
     */
    public final long toSecond() {
        return toTime() / DIVIDE_CONST.SECOND_DIVIDE;
    }
}
