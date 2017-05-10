package Njava.util.business;

import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * Check State Exception Util.
 *
 * Created by Doohyun on 2017. 5. 10..
 */
public class CheckUtil {

    private CheckUtil(){}

    private static final String ERROR_BAD_ASSESS = "[ERROR] BAD DATA ASSESS";

    /**
     * Check with CustomException.
     *
     * @param check
     * @param message
     * @param exceptionClass
     * @param <T>
     * @throws T
     */
    public final static <T extends Exception> void Check(
            @NonNull Boolean check
            , @NonNull String message
            , @NonNull Class<T> exceptionClass) throws T{
        if (!check) {
            final T exception;

            try {
                exception = exceptionClass.getConstructor(String.class).newInstance(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            throw exception;
        }
    }

    /**
     * Check.
     *
     * @param check
     */
    public final static void Check(@NonNull Boolean check) {
        Check(check, ERROR_BAD_ASSESS, RuntimeException.class);
    }

    /**
     * Null Check with CustomException.
     *
     * @param object
     * @param message
     * @param exceptionClass
     * @param <T>
     * @throws T
     */
    public final static <T extends Exception> void NullCheck(
            @Nullable Object object
            , @NonNull String message
            , @NonNull Class<T> exceptionClass) throws T {
        Check(object != null, message, exceptionClass);
    }

    /**
     * Null Check
     *
     * @param object
     */
    public final static void NullCheck(@Nullable Object object) {
        NullCheck(object, ERROR_BAD_ASSESS, RuntimeException.class);
    }

    /**
     * Maybe empty check with CustomException.
     *
     * @param maybe
     * @param message
     * @param exceptionClass
     * @param <T>
     * @throws T
     */
    public final static <T extends Exception> void EmptyMaybeCheck(
            @Nullable Maybe<? extends Object> maybe
            , @NonNull String message
            , @NonNull Class<T> exceptionClass) throws T {
        Check(!MaybeUtil.IsEmpty(maybe), message, exceptionClass);
    }

    /**
     * Maybe empty check.
     *
     * @param maybe
     */
    public final static void EmptyMaybeCheck(@Nullable Maybe<? extends Object> maybe) {
        EmptyMaybeCheck(maybe, ERROR_BAD_ASSESS, RuntimeException.class);
    }

    /**
     * Description : Simple String empty check with CustomException.
     *
     * @param obj
     * @param message
     * @param exceptionClass
     * @param <T>
     * @throws T
     */
    public final static <T extends Exception> void EmptyToStringCheck(
            @Nullable Object obj
            , @NonNull String message
            , @NonNull Class<T> exceptionClass) throws T {
        Check(!StringUtil.IsEmpty(obj), message, exceptionClass);
    }

    /**
     * Description : Simple String empty check
     *
     * @param obj
     */
    public final static void EmptyToStringCheck(@Nullable Object obj) {
        EmptyToStringCheck(obj, ERROR_BAD_ASSESS, RuntimeException.class);
    }

    /**
     * Description : Empty Collection Check with CustomException.
     *
     * @param list
     * @param message
     * @param exceptionClass
     * @param <T>
     * @param <R>
     * @throws T
     */
    public final static <T extends Exception, R> void EmptyContainerCheck(
            @Nullable Collection<R> list
            , @NonNull String message
            , @NonNull Class<T> exceptionClass) throws T {
        Check(!ContainerUtil.IsEmpty(list), message, exceptionClass);
    }

    /**
     * Description : Empty Collection Check
     *
     * @param list
     * @param <R>
     */
    public final static <R> void EmptyContainerCheck(@Nullable Collection<R> list){
        EmptyContainerCheck(list, ERROR_BAD_ASSESS, RuntimeException.class);
    }

    /**
     * Description : Empty Array Check with CustomException.
     *
     * @param array
     * @param message
     * @param exceptionClass
     * @param <T>
     * @param <R>
     * @throws T
     */
    public final static <T extends Exception, R> void EmptyContainerCheck(
            @Nullable R[] array
            , @NonNull String message
            , @NonNull Class<T> exceptionClass) throws T {
        Check(!ContainerUtil.IsEmpty(array), message, exceptionClass);
    }

    /**
     * Description : Empty Array Check.
     *
     * @param array
     * @param <R>
     */
    public final static <R> void EmptyContainerCheck(@Nullable R[] array) {
        EmptyContainerCheck(array, ERROR_BAD_ASSESS, RuntimeException.class);
    }


    /**
     * Description : Empty map Check with CustomException.
     *
     * @param dataSet
     * @param message
     * @param exceptionClass
     * @param <T>
     * @param <R>
     * @param <U>
     * @throws T
     */
    public final static <T extends Exception, R, U> void EmptyContainerCheck(
            @Nullable Map<R, U> dataSet
            , @NonNull String message
            , @NonNull Class<T> exceptionClass) throws T {
        Check(!ContainerUtil.IsEmpty(dataSet), message, exceptionClass);
    }

    /**
     * Description : Empty map Check.
     *
     * @param dataSet
     * @param <R>
     * @param <U>
     */
    public final static <R, U> void EmptyContainerCheck(@Nullable Map<R, U> dataSet) {
        Check(!ContainerUtil.IsEmpty(dataSet), ERROR_BAD_ASSESS, RuntimeException.class);
    }
}
