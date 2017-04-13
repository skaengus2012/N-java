package Njava.OOP;

import Njava.function.exceptionLambda.IExConsumer;
import Njava.modeler.NxModeler;
import Njava.util.business.ContainerUtil;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import java.util.Set;

/**
 * Managing Observer with Multi threading.
 *
 * Created by Doohyun on 2017. 4. 13..
 */
public class ObserverManager<T> extends NxModeler {

    private Set<T> observerSet;

    private ObserverManager() {
        observerSet = ContainerUtil.CreateConcurrentSet();
    }

    public static ObserverManager Create() {
        return new ObserverManager<>();
    }

    /**
     * Add observer
     *
     * @param observer
     */
    public void addObserver(@NonNull T observer) {
        NullCheck(observer);
        observerSet.add(observer);
    }

    /**
     * Remove Observer
     *
     * <pre>
     *     using concurrentHashSet.
     *     so that, this function will be thread-safe.
     * </pre>
     *
     * @param observer
     */
    public void removeObserver(@NonNull T observer) {
        NullCheck(observer);
        observerSet.remove(observer);
    }

    /**
     * Observer notify.
     *
     * <pre>
     *     using concurrentHashSet.
     *     so that, this function will be thread-safe. (Never occurred ConcurrentModifyException)
     * </pre>
     *
     * @param consumer
     */
    public void notify(@NonNull IExConsumer<T> consumer) {
        NullCheck(consumer);
        Observable.fromIterable(observerSet).forEach(consumer);
    }

    /**
     * Clear Observer
     */
    public void clear() {
        observerSet.clear();
    }
}
