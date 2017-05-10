package Njava.util.business;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

import Njava.function.exceptionLambda.IExBiConsumer;
import Njava.function.exceptionLambda.IExFunction;
import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Function;

/**
 * String class 의 유틸 클래스
 * <p>
 * Created by Doohyun on 2017. 3. 19..
 */

public class StringUtil {

    protected StringUtil(){}

    /**
     * String 클래스가 비었는지 확인
     *
     * @param text
     * @return
     */
    @NonNull
    public static boolean IsEmpty(@Nullable Object text) {
        return MaybeUtil.JustNullable(text).
                    map(new IExFunction<Object, Boolean>() {
                        @Override
                        public Boolean apply(@NonNull Object str) throws Exception {
                            final String toStr = str.toString();

                            if (toStr != null) {
                                return toStr.toString().isEmpty();
                            } else {
                                return true;
                            }
                        }
                    }).
                    blockingGet(true);
    }

    /**
     * UUID 를 출력한다.
     * <p>
     * <pre>
     *     36비트 UUID 를 출력한다. (- 포함.)
     * </pre>
     *
     * @return
     */
    @NonNull
    public static String GetUUID36() {
        return UUID.randomUUID().toString();
    }

    /**
     * UUID 를 출력한다.
     * <p>
     * <pre>
     *     32비트 UUID 를 출력한다. (-제외)
     * </pre>
     *
     * @return
     */
    public static String GetUUID32() {
        return GetUUID36().replaceAll("-", "");
    }

    /**
     * String 을 받아 Character 목록을 출력한다.
     *
     * @param text
     * @return
     */
    public static List<Character> GetCharacterList(@NonNull String text) {
        return MaybeUtil.JustNullable(text).map(new Function<String, Single<ArrayList<Character>>>() {
            @Override
            public Single<ArrayList<Character>> apply(@NonNull final String param) throws Exception {
                return Observable.range(0, param.length()).
                        map(new IExFunction<Integer, Character>() {
                            @Override
                            public Character apply(@NonNull Integer index) throws Exception {
                                return param.charAt(index);
                            }
                        }).
                        collect(new Callable<ArrayList<Character>>() {
                            @Override
                            public ArrayList<Character> call() throws Exception {
                                return new ArrayList<>();
                            }
                        }, new IExBiConsumer<ArrayList<Character>, Character>() {
                            @Override
                            public void accept(@NonNull ArrayList<Character> characters, @NonNull Character character) throws Exception {
                                characters.add(character);
                            }
                        });
            }
        }).blockingGet(Single.just(new ArrayList<Character>())).blockingGet();
    }


    /**
     * Parse, boxed object!
     *
     * @param stringValue
     * @param function
     * @param <T>
     * @return
     */
    @NonNull
    private static <T> Maybe<T> ParseBoxedObject(
            @NonNull String stringValue,
            @NonNull IExFunction<String, T> function) {
        try {
            return Maybe.just(function.apply(stringValue));
        } catch (Exception e) {
            return Maybe.empty();
        }
    }

    /**
     * Boolean parse!
     *
     * @param stringValue
     * @return
     */
    @NonNull
    public static Maybe<Boolean> ParseBoolean(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, new IExFunction<String, Boolean>() {
            @Override
            public Boolean apply(@NonNull String s) throws Exception {
                return Boolean.parseBoolean(s);
            }
        });
    }

    /**
     * Integer parse!
     *
     * @param stringValue
     * @return
     */
    @NonNull
    public static Maybe<Integer> ParseInteger(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, new IExFunction<String, Integer>() {
            @Override
            public Integer apply(@NonNull String s) throws Exception {
                return Integer.parseInt(s);
            }
        });
    }

    /**
     * Long parse!
     *
     * @param stringValue
     * @return
     */
    @NonNull
    public static Maybe<Long> ParseLong(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, new IExFunction<String, Long>() {
            @Override
            public Long apply(@NonNull String s) throws Exception {
                return Long.parseLong(s);
            }
        });
    }

    /**
     * Double parse!
     *
     * @param stringValue
     * @return
     */
    @NonNull
    public static Maybe<Double> ParseDouble(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, new IExFunction<String, Double>() {
            @Override
            public Double apply(@NonNull String s) throws Exception {
                return Double.parseDouble(s);
            }
        });
    }

    /**
     * Float parse!
     *
     * @param stringValue
     * @return
     */
    @NonNull
    public static Maybe<Float> ParseFloat(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, new IExFunction<String, Float>() {
            @Override
            public Float apply(@NonNull String s) throws Exception {
                return Float.parseFloat(s);
            }
        });
    }
}
