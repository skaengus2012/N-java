package Njava.common;

import Njava.function.ISupplier;
import Njava.util.business.CheckUtil;
import Njava.util.business.ContainerUtil;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * 모델을 담당하는 클래스는 이곳에 제작한다.
 * <p>
 * <pre>
 *      스프링의 서비스와 같은 그 것!
 *      모든 모델러는 해당 클래스를 상속받아야함!
 *      참고 : http://doohyun.tistory.com/38
 *
 *      단순 MVP 모델에서 사용하기 위한 모델러 역할 포함, 비지니스 로직만 전문으로 사용하는 클래스에서 사용하자!
 * </pre>
 * <p>
 * Created by Doohyun on 2017. 1. 29..
 */
public class NxComponent {
    /**
     * Description : Simple boolean checks
     *
     * @param check
     */
    public final static void Check(@NonNull Boolean check) {
        CheckUtil.Check(check);
    }

    /**
     * Description : Parameter null checks
     *
     * @param obj
     */
    public final static void NullCheck(@Nullable Object obj) {
        CheckUtil.NullCheck(obj);
    }

    /**
     * Description : maybe empty check.
     *
     * @param maybe
     */
    public final static void EmptyMaybeCheck(@Nullable Maybe<? extends Object> maybe) {
        CheckUtil.EmptyMaybeCheck(maybe);
    }

    /**
     * Description : Simple empty check.
     *
     * @param obj
     */
    public final static void EmptyToStringCheck(Object obj) {
        CheckUtil.EmptyToStringCheck(obj);
    }

    /**
     * Description : Empty Collection Check.
     *
     * @param list
     * @param <T>
     */
    public final static <T> void EmptyContainerCheck(Collection<T> list) {
        CheckUtil.EmptyContainerCheck(list);
    }

    /**
     * Description : Empty Array Check.
     *
     * @param array
     * @param <T>
     */
    public final static <T> void EmptyContainerCheck(T[] array) {
        CheckUtil.EmptyContainerCheck(array);
    }

    /**
     * Description : Empty map Check.
     *
     * @param dataSet
     * @param <T>
     * @param <R>
     */
    public final static <T, R> void EmptyContainerCheck(Map<T, R> dataSet) {
        CheckUtil.EmptyContainerCheck(dataSet);
    }

    /**
     * If this map not contains key, put key & value.
     *
     * @param map
     * @param key
     * @param iSupplier
     */
    public static <T, R> void PutEmptyKeyValueInMap(@NonNull Map<T, R> map, @NonNull T key, @NonNull ISupplier<R> iSupplier) {
        NullCheck(map);
        NullCheck(key);
        NullCheck(iSupplier);

        ContainerUtil.PutEmptyKeyValueInMap(map, key, iSupplier);
    }
}
