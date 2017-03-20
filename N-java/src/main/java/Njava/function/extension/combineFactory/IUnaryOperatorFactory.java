package Njava.function.extension.combineFactory;

import Njava.function.IUnaryOperator;
import Njava.function.exceptionLambda.IExUnaryOperator;
import io.reactivex.annotations.NonNull;

/**
 * UnaryOperator Builder
 *
 * Created by Doohyun on 2017. 3. 12..
 */

public class IUnaryOperatorFactory<T> extends FunctionFactory<T, T>{

    public IUnaryOperatorFactory(@NonNull IUnaryOperator<T> iUnaryOperator) {
        super(iUnaryOperator);
    }

    /**
     * Return Exception able UnaryOperator.
     *
     * @return
     */
    public IExUnaryOperator<T> getEx() {
        return new IExUnaryOperator<T>() {
            @Override
            public T apply(@NonNull T t) throws Exception {
                return get().apply(t);
            }
        };
    }
}
