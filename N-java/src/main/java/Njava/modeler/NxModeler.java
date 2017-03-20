package Njava.modeler;

import Njava.function.ISupplier;
import Njava.util.business.ContainerUtil;
import Njava.util.business.StringUtil;
import io.reactivex.annotations.NonNull;

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
public class NxModeler {
    private static final String ERROR_BAD_ASSESS = "[ERROR] BAD DATA ASSESS";

    /**
     * Description : Simple boolean checks
     *
     * @param check
     */
    public final static void Check(boolean check) {
        if (!check) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * Description : Parameter null checks
     *
     * @param obj
     */
    public final static void NullCheck(Object obj) {
        if (obj == null) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * Description : Simple empty check.
     *
     * @param obj
     */
    public final static void EmptyToStringCheck(Object obj) {
        if (StringUtil.IsEmpty(obj)) {
            // toString 체크
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * Description : Empty Collection Check.
     *
     * @param list
     * @param <T>
     */
    public final static <T> void EmptyContainerCheck(Collection<T> list) {
        if (ContainerUtil.IsEmpty(list)) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * Description : Empty Array Check.
     *
     * @param array
     * @param <T>
     */
    public final static <T> void EmptyContainerCheck(T[] array) {
        if (ContainerUtil.IsEmpty(array)) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * Description : Empty map Check.
     *
     * @param dataSet
     * @param <T>
     * @param <R>
     */
    public final static <T, R> void EmptyContainerCheck(Map<T, R> dataSet) {
        if (ContainerUtil.IsEmpty(dataSet)) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
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
