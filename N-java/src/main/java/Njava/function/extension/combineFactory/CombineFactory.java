package Njava.function.extension.combineFactory;


import Njava.common.NxComponent;
import io.reactivex.annotations.NonNull;

/**
 * Lambda combine Factory define
 *
 * Created by Doohyun on 2017. 3. 8..
 */
public abstract class CombineFactory<T, V> extends NxComponent {
    private T lambda;

    public CombineFactory(@NonNull T lambda) {
        NullCheck(lambda);
        this.lambda = lambda;
    }

    /**
     * Return Rx style Lambda
     *
     * @return
     */
    public abstract V getRx();

    /**
     * Return simple Lambda
     *
     * @return
     */
    public T get() {
        return lambda;
    }
}
