package Njava.util.business;

import com.rits.cloning.Cloner;
import io.reactivex.annotations.NonNull;


/**
 * Object Relation Util.
 *
 * Created by Doohyun on 2017. 4. 16..
 */
public class ObjectUtil {

    private ObjectUtil(){}

    /**
     * 객체 생성
     *
     * @return
     */
    private static Cloner GetInstance() {
        return new Cloner();
    }

    /**
     * deep Copy
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T CopyProperties(@NonNull T t) {
        return GetInstance().deepClone(t);
    }

    /**
     * deep copy
     *
     * @param src
     * @param dest
     * @param <T>
     * @param <E>
     */
    public static <T, E extends T> void CopyProperties(@NonNull T src, @NonNull E dest) {
        GetInstance().copyPropertiesOfInheritedClass(src, dest);
    }

    /**
     * Create Clone <instance/> or new Instance <T.getCloss>
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T NewInstance(@NonNull final Class<T> c) {
        return GetInstance().fastCloneOrNewInstance(c);
    }
}
