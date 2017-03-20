package Njava.function.extension.combineFactory;

import Njava.function.IBinaryOperator;
import Njava.function.exceptionLambda.IExBinaryOperator;
import io.reactivex.annotations.NonNull;

/**
 * Created by Doohyun on 2017. 3. 12..
 */

public class BinaryOperatorFactory<T> extends BiFunctionFactory<T, T, T> {

    public BinaryOperatorFactory(@NonNull IBinaryOperator<T> iBinaryOperator) {
        super(iBinaryOperator);
    }

    /**
     * Return Exception able UnaryOperator.
     *
     * @return
     */
    public IExBinaryOperator<T> getEx() {
        return new IExBinaryOperator<T>() {
            @Override
            public T apply(@NonNull T t1, @NonNull T t2) {
                return get().apply(t1, t2);
            }
        };
    }
}
