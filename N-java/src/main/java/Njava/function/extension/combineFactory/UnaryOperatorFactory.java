package Njava.function.extension.combineFactory;

import Njava.function.IUnaryOperator;
import Njava.function.exceptionLambda.IExUnaryOperator;
import io.reactivex.annotations.NonNull;

/**
 * UnaryOperator Builder
 *
 * Created by Doohyun on 2017. 3. 12..
 */

public class UnaryOperatorFactory<T> extends FunctionFactory<T, T>{

    public UnaryOperatorFactory(@NonNull IUnaryOperator<T> iUnaryOperator) {
        super(iUnaryOperator);
    }

    /**
     * Return UnaryOperator
     *
     * @return
     */
    @Override
    public IUnaryOperator<T> get() {
        return (IUnaryOperator<T>)super.get();
    }

    /**
     * Return Exception able UnaryOperator.
     *
     * @return
     */
    @Override
    public IExUnaryOperator<T> getRx() {
        return new IExUnaryOperator<T>() {
            @Override
            public T apply(@NonNull T t) throws Exception {
                return get().apply(t);
            }
        };
    }
}
