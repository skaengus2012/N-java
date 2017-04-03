package Njava.util.time.duration;

import Njava.util.time.TimeBuilder;

/**
 * AbsoluteDuration Define.
 *
 * Created by Doohyun on 2017. 4. 3..
 */
public final class AbsoluteDuration extends Duration {

    protected AbsoluteDuration(){}

    @Override
    protected Duration createCopyObject() {
        return new AbsoluteDuration();
    }

    @Override
    public long toYear() {
        return getMaxTime().getYear() - getMinTime().getYear();
    }

    @Override
    public long toMonth() {
        TimeBuilder max = getMaxTime();
        TimeBuilder min = getMinTime();

        long yearMulti = max.getYear() - min.getYear();

        return (yearMulti * 12) + max.getMonthForHuman() - min.getMonthForHuman();
    }
}
