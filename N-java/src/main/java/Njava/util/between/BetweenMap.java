package Njava.util.between;

import Njava.function.IFunction;
import Njava.function.exceptionLambda.IExFunction;
import Njava.function.exceptionLambda.IExPredicate;
import Njava.util.business.CheckUtil;
import Njava.util.business.ContainerUtil;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Manage Range Map.
 *
 * Created by Doohyun on 2017. 7. 3..
 */
public class BetweenMap<T extends Between, R> {

    // For guarantee order.
    private LinkedHashMap<T, R> paramMap;

    public BetweenMap() {
        paramMap = new LinkedHashMap<>();
    }

    /**
     * 범위 Observable 출력.
     *
     * @param comparable
     * @return
     */
    private Observable<T> createRangeObservable(final @NonNull Comparable comparable) {

        CheckUtil.NullCheck(comparable, "[ERROR] NULL EXCEPTION IN RANGE MAP", RuntimeException.class);

        return Observable.fromIterable(paramMap.keySet()).
                    filter(new IExPredicate<T>() {
                        @Override
                        public boolean test(@NonNull T t) throws Exception {
                            return t.contains(comparable);
                        }
                    });
    }

    /**
     * 범위안에 값이 존재하는가?
     *
     * @param comparable
     * @return
     */
    public boolean containsRange(final @NonNull Comparable comparable) {
        return !createRangeObservable(comparable).isEmpty().blockingGet();
    }

    /**
     * Return only value by First.
     *
     * @param comparable
     * @return
     */
    public Maybe<R> getFirst(final Comparable comparable) {
        return get(comparable, new IFunction<Observable<T>, Maybe<R>>() {
                                    @Override
                                    public Maybe<R> apply(Observable<T> tObservable) {

                                        final Maybe<R> resultMaybe;
                                        {
                                            if (tObservable.isEmpty().blockingGet()) {
                                                // EMPTY CASE
                                                resultMaybe = Maybe.empty();
                                            } else {
                                                resultMaybe = ContainerUtil.JustInMap(paramMap, tObservable.blockingFirst());
                                            }
                                        }

                                        return resultMaybe;
                                    }
                                });
    }

    /**
     * Return value list.
     *
     * @param comparable
     * @return
     */
    public List<R> getToList(final Comparable comparable) {
        return get(comparable, new IFunction<Observable<T>, List<R>>() {
                                    @Override
                                    public List<R> apply(Observable<T> tObservable) {
                                        return tObservable.
                                                filter(new IExPredicate<T>() {
                                                    @Override
                                                    public boolean test(@NonNull T t) throws Exception {
                                                        return paramMap.containsKey(t);
                                                    }
                                                }).
                                                map(new IExFunction<T, R>() {
                                                    @Override
                                                    public R apply(@NonNull T t) throws Exception {
                                                        return paramMap.get(t);
                                                    }
                                                }).
                                                toList().blockingGet();
                                    }
                                });
    }

    /**
     * Return template Query!!
     *
     * @param comparable
     * @param templateFunction
     * @param <U>
     * @return
     */
    private <U> U get(final Comparable comparable, IFunction<Observable<T>, U> templateFunction) {
        return templateFunction.apply(createRangeObservable(comparable));
    }

    /**
     * Input Range & value.
     *
     * @param range
     * @param value
     */
    public void put(final T range, R value) {
        Observable<T> equalsRangeObservable = Observable.fromIterable(paramMap.keySet()).
                                                filter(new IExPredicate<T>() {
                                                    @Override
                                                    public boolean test(@NonNull T t) throws Exception {
                                                        return t.equals(range);
                                                    }
                                                });

        if (!equalsRangeObservable.isEmpty().blockingGet()) {
            // IF EQUALS STATE, DELETE DATA.
            paramMap.remove(equalsRangeObservable.blockingFirst());
        }

        paramMap.put(range, value);
    }

    /**
     * Return range keySet.
     *
     * @return
     */
    public Set<T> ketSet() {
        return paramMap.keySet();
    }

    /**
     * Return values.
     *
     * @return
     */
    public Collection<R> values() {
        return paramMap.values();
    }
}
