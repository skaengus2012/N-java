package Njava.util.business;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import Njava.function.ISupplier;
import Njava.function.exceptionLambda.IExConsumer;
import Njava.function.exceptionLambda.IExFunction;
import Njava.function.exceptionLambda.IExRunnable;
import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * Container 클래스의 기능 지원.
 * <p>
 * Created by Doohyun on 2017. 3. 1..
 */

public class ContainerUtil {

    private ContainerUtil(){}

    /**
     * Collection 빈 상태 확인.
     *
     * @param collection
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> boolean IsEmpty(@Nullable Collection<T> collection) {
        return MaybeUtil.JustNullable(collection).
                map(new IExFunction<Collection<T>, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Collection<T> ts) throws Exception {
                        return ts.isEmpty();
                    }
                }).
                blockingGet(true);
    }

    /**
     * 배열의 빈 상태 확인.
     *
     * @param arrayT
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> boolean IsEmpty(@Nullable T... arrayT) {
        return MaybeUtil.JustNullable(arrayT).
                map(new IExFunction<T[], Boolean>() {
                    @Override
                    public Boolean apply(@NonNull T[] ts) throws Exception {
                        return ts.length == 0;
                    }
                }).
                blockingGet(true);
    }

    /**
     * Map 의 빈 상태 확인.
     *
     * @param map
     * @param <T>
     * @param <R>
     * @return
     */
    @NonNull
    public static <T, R> boolean IsEmpty(@Nullable Map<T, R> map) {
        return MaybeUtil.JustNullable(map).
                map(new IExFunction<Map<T, R>, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Map<T, R> trMap) throws Exception {
                        return trMap.isEmpty();
                    }
                }).
                blockingGet(true);
    }

    /**
     * If map have key-value, that function will be return valid maybe.
     *
     * @param targetMap
     * @param key
     * @param <T>
     * @param <R>
     * @return
     */
    @NonNull
    public static <T, R> Maybe<R> JustInMap(
            @NonNull Map<T, R> targetMap
            , @NonNull T key) {
        return MaybeUtil.JustNullable(targetMap.get(key));
    }

    /**
     * If maybe value is valid, exConsumer will be run!!
     *
     * @param targetMap
     * @param key
     * @param exConsumer
     * @param <T>
     * @param <R>
     */
    @NonNull
    public static <T, R> void RunMaybeInMap(
            @NonNull Map<T, R> targetMap
            , @NonNull T key
            , @NonNull IExConsumer<R> exConsumer) {
        JustInMap(targetMap, key).subscribe(exConsumer);
    }

    /**
     * If this map not contains key, put key & value.
     *
     * @param map
     * @param key
     * @param iSupplier
     */
    public static <T, R> void PutEmptyKeyValueInMap(
            @NonNull final Map<T, R> map
            , @NonNull final T key
            , @NonNull final ISupplier<R> iSupplier) {
        MaybeUtil.SubscribeEmpty(JustInMap(map, key), new IExRunnable() {
            @Override
            public void run() {
                map.put(key, iSupplier.accept());
            }
        });
    }

    /**
     * Create ConcurrentHashSet.
     *
     * @param <T>
     * @return
     */
    public static <T> Set<T> CreateConcurrentSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap<T, Boolean>());
    }

    /**
     * Create Sorted Set (TreeSet)
     *
     * @param <T>
     * @return
     */
    public static <T extends Comparable> Set<T> CreateSortedSet() {
        return new TreeSet<>();
    }

    /**
     * Create Sorted Set (TreeSet) with Comparable.
     *
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> Set<T> CreateSortedSet(Comparator<T> comparator) {
        return new TreeSet<>(comparator);
    }

    /**
     * Create linked hash set.
     *
     * @param <T>
     * @return
     */
    public static <T> Set<T> CreateLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    /**
     * Create CopyOnWriteArrayList.
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> CreateConcurrentList() {
        return new CopyOnWriteArrayList<>();
    }

    /**
     * Create list by T[]
     *
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> List<T> AsList(@NonNull T... ts) {
        return Arrays.asList(ts);
    }

    /**
     * input Elements into Collection.
     *
     * @param set
     * @param ts
     * @param <T>
     */
    private static <T> Set<T> Create_UnModifiableSet_By_InputElements(@NonNull Set<T> set, @NonNull T... ts) {
        for (T t : ts) {
            set.add(t);
        }

        return Collections.unmodifiableSet(set);
    }

    /**
     * Create set by T[]
     *
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> Set<T> AsSet(@NonNull T... ts) {
        return Create_UnModifiableSet_By_InputElements(new HashSet<T>(), ts);
    }

    /**
     * Create linked set by T[]
     *
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> Set<T> AsLinkedHashSet(@NonNull T... ts) {
        return Create_UnModifiableSet_By_InputElements(ContainerUtil.<T>CreateLinkedHashSet(), ts);
    }

    /**
     * Get Sorted Set (TreeSet) by T[]
     *
     * @param ts
     * @param <T>
     * @return
     */
    public static <T extends Comparable> Set<T> AsSortedSet(@NonNull T... ts) {
        return Create_UnModifiableSet_By_InputElements(ContainerUtil.<T>CreateSortedSet(), ts);
    }

    /**
     * Get Sorted Set (TreeSet) by T[]
     *
     * @param comparator
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> Set<T> AsSortedSet(@NonNull Comparator<T> comparator, @NonNull T... ts) {
        return Create_UnModifiableSet_By_InputElements(CreateSortedSet(comparator), ts);
    }

    /**
     * Create Sorted Map
     *
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends Comparable, E> Map<T, E> CreateSortedMap() {
        return new TreeMap<>();
    }

    /**
     * Create Sorted Map (with comparator)
     *
     * @param comparator
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E> Map<T, E> CreateSortedMap(Comparator<T> comparator) {
        return new TreeMap<>(comparator);
    }
}
