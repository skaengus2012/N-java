package Njava.util.function.math;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Observable 확장을 위한 Source 클래스.
 *
 * @param <T>
 * @param <R>
 */
public abstract class ObservableWithSource<T, R> extends Observable<R> {

    protected final ObservableSource<T> source;

    ObservableWithSource(ObservableSource<T> source) {
        this.source = source;
    }
}

