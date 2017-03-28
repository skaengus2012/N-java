package runTest;


import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;

/**
 * Created by Doohyun on 2017. 3. 28..
 */
public class MaybeUtilTest {

    @org.junit.Test
    public void runMaybe(){
        Maybe<Integer> maybe1 = Maybe.empty();
        Maybe<Integer> maybe2 = Maybe.empty();


        System.out.println(maybe1.equals(maybe2));
        System.out.println(MaybeUtil.Equals(maybe1, maybe2));

        System.out.println(MaybeUtil.IsEmpty(maybe2));
    }
}
