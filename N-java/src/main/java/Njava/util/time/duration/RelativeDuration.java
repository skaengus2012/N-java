package Njava.util.time.duration;

/**
 * Relative Duration define.
 *
 * Created by Doohyun on 2017. 4. 3..
 */
public final class RelativeDuration extends Duration {

    protected RelativeDuration(){}

    @Override
    protected Duration createCopyObject() {
        return new RelativeDuration();
    }

    @Override
    public long toYear() {
        return toDayOfMonth() / DIVIDE_CONST.YEAR_DIVIDE;
    }

    @Override
    public long toMonth() {
        return toDayOfMonth() / DIVIDE_CONST.MONTH_DIVIDE;
    }
}
