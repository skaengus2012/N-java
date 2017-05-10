package Njava.util.function;

import Njava.function.exceptionLambda.IExFunction;
import Njava.util.function.math.ObservableAverageBigDecimal;
import Njava.util.function.math.ObservableSumBigDecimal;
import hu.akarnokd.rxjava2.math.MathObservable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.plugins.RxJavaPlugins;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Math function for "To" in Observable Util.
 *
 * Created by Doohyun on 2017. 4. 2..
 */
public class MathUtil {

    protected MathUtil(){}

    /**
     * Return Max Function Observable.
     *
     * @param <T>
     * @return
     */
    @NonNull
    public static <T extends Comparable> IExFunction<Observable<T>, Observable<T>> Max(){

        return new IExFunction<Observable<T>, Observable<T>>(){

            @Override
            public Observable<T> apply(Observable<T> t) throws Exception {
                return MathObservable.max(t);
            }

        };
    }

    /**
     * Return Max Function Observable. (with Comparator)
     *
     * @param comparator
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> IExFunction<Observable<T>, Observable<T>> Max(final Comparator<T> comparator){

        return new IExFunction<Observable<T>, Observable<T>>(){

            @Override
            public Observable<T> apply(Observable<T> t) throws Exception {
                return MathObservable.max(t, comparator);
            }

        };
    }

    /**
     * Return Min Function Observable.
     *
     * @param <T>
     * @return
     */
    @NonNull
    public static <T extends Comparable> IExFunction<Observable<T>, Observable<T>> Min(){

        return new IExFunction<Observable<T>, Observable<T>>(){

            @Override
            public Observable<T> apply(Observable<T> t) throws Exception {
                return MathObservable.min(t);
            }

        };
    }

    /**
     * Return Min Function Observable. (with Comparator)
     *
     * @param comparator
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> IExFunction<Observable<T>, Observable<T>> Min(final Comparator<T> comparator){

        return new IExFunction<Observable<T>, Observable<T>>(){

            @Override
            public Observable<T> apply(Observable<T> t) throws Exception {
                return MathObservable.min(t, comparator);
            }

        };
    }

    /**
     * Return Sum int Function Observable
     *
     * @return
     */
    @NonNull
    public static IExFunction<Observable<Integer>, Observable<Integer>> SumInt(){

        return new IExFunction<Observable<Integer>, Observable<Integer>>(){

            @Override
            public Observable<Integer> apply(Observable<Integer> t) throws Exception {
                return MathObservable.sumInt(t);
            }

        };
    }

    /**
     * Return Long int Function Observable
     *
     * @return
     */
    @NonNull
    public static IExFunction<Observable<Long>, Observable<Long>> SumLong(){

        return new IExFunction<Observable<Long>, Observable<Long>>(){

            @Override
            public Observable<Long> apply(Observable<Long> t) throws Exception {
                return MathObservable.sumLong(t);
            }

        };
    }

    /**
     * Return Sum Float Function Observable
     *
     * @return
     */
    @NonNull
    public static IExFunction<Observable<Float>, Observable<Float>> SumFloat(){

        return new IExFunction<Observable<Float>, Observable<Float>>(){

            @Override
            public Observable<Float> apply(Observable<Float> t) throws Exception {
                return MathObservable.sumFloat(t);
            }

        };
    }

    /**
     * Return Sum Double Function Observable
     *
     * @return
     */
    @NonNull
    public static IExFunction<Observable<Double>, Observable<Double>> SumDouble(){

        return new IExFunction<Observable<Double>, Observable<Double>>(){

            @Override
            public Observable<Double> apply(Observable<Double> t) throws Exception {
                return MathObservable.sumDouble(t);
            }

        };
    }

    /**
     * Return Sum BigDecimal Function Observable
     *
     * @return
     */
    public static IExFunction<Observable<BigDecimal>, Observable<BigDecimal>> SumBigDecimal() {
        return new IExFunction<Observable<BigDecimal>, Observable<BigDecimal>>(){

            @Override
            public Observable<BigDecimal> apply(Observable<BigDecimal> t) throws Exception {
                return RxJavaPlugins.onAssembly(new ObservableSumBigDecimal(t));
            }

        };
    }

    /**
     * Return Average Float Function Observable
     *
     * @return
     */
    public static IExFunction<Observable<? extends Number>, Observable<Float>> AverageFloat(){

        return new IExFunction<Observable<? extends Number>, Observable<Float>>(){

            @Override
            public Observable<Float> apply(Observable<? extends Number> t) throws Exception {
                return MathObservable.averageFloat(t);
            }

        };
    }

    /**
     * Return Average Double Function Observable
     *
     * @return
     */
    public static IExFunction<Observable<? extends Number>, Observable<Double>> AverageDouble(){

        return new IExFunction<Observable<? extends Number>, Observable<Double>>(){

            @Override
            public Observable<Double> apply(Observable<? extends Number> t) throws Exception {
                return MathObservable.averageDouble(t);
            }

        };
    }

    /**
     * Return Average BigDecimal Function Observable
     *
     * @return
     */
    public static IExFunction<Observable<BigDecimal>, Observable<BigDecimal>> AverageBigDecimal(){

        return new IExFunction<Observable<BigDecimal>, Observable<BigDecimal>>(){

            @Override
            public Observable<BigDecimal> apply(Observable<BigDecimal> t) throws Exception {
                return RxJavaPlugins.onAssembly(new ObservableAverageBigDecimal(t));
            }

        };
    }
}
