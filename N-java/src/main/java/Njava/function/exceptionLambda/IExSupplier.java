package Njava.function.exceptionLambda;

/**
 * Exception able Supplier.
 *
 * Created by Doohyun on 2017. 3. 12..
 */

public interface IExSupplier<T> {
    T accept() throws Exception;
}
