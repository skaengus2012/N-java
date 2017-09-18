package Njava.util.between;

import Njava.function.exceptionLambda.IExFunction;
import Njava.util.business.CheckUtil;
import Njava.util.function.MaybeUtil;
import io.reactivex.annotations.NonNull;

/**
 * 범위를 표현하는 객체 처리.
 *
 * Created by Doohyun on 2017. 7. 3..
 */
public final class Between<T extends Comparable<T>> {

    private T minValue, maxValue;

    private Between(){}

    /**
     * 범위 생산.
     *
     * @param minValue
     * @param maxValue
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>>Between<T> Just(@NonNull T minValue, @NonNull T maxValue) {
        Between<T> range = new Between<>();

        // NULL CHECK!!!!
        CheckUtil.NullCheck(minValue, "[ERROR] minValue is Null!", RuntimeException.class);
        CheckUtil.NullCheck(maxValue, "[ERROR] maxValue is Null!", RuntimeException.class);

        range.maxValue = maxValue;
        range.minValue = minValue;

        return range;
    }

    /**
     * Only min & max value check!
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Between) {
            Between target = (Between)object;

            // 최소 & 최대값 비교.
            return target.minValue.equals(minValue) && target.maxValue.equals(maxValue);
        } else {
            return false;
        }
    }

    /**
     * 해당 값이 범위에 포함되어 있는가?
     *
     * @param value
     * @return
     */
    public Boolean contains(@NonNull T value) {
        Boolean result = MaybeUtil.JustNullable(value).
                            map(new IExFunction<T, Boolean>() {
                                @Override
                                public Boolean apply(@NonNull T v) throws Exception {

                                    // Comparable Check!

                                    Boolean minValueCheckYn = minValue.compareTo(v) <= 0;
                                    Boolean maxValueCheckYn = maxValue.compareTo(v) >= 0;

                                    return minValueCheckYn && maxValueCheckYn;
                                }
                            }).
                            blockingGet(false);

        return result;
    }
}
