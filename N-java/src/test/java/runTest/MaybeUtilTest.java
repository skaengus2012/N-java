package runTest;


import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;

import java.util.Comparator;

/**
 * Created by Doohyun on 2017. 3. 28..
 */
public class MaybeUtilTest {

    @org.junit.Test
    public void runMaybe(){
        Maybe<Integer> maybe1 = Maybe.just(1);
        Maybe<Integer> maybe2 = Maybe.just(2);


        System.out.println(maybe1.equals(maybe2));
        System.out.println(MaybeUtil.Equals(maybe1, maybe2));

        System.out.println(MaybeUtil.IsEmpty(maybe2));

        System.out.println(MaybeUtil.CompareTo(maybe1, maybe2));

        System.out.println(MaybeUtil.CompareTo(maybe1, maybe2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        }));
    }
}
