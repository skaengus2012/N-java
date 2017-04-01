package runTest;

import Njava.function.exceptionLambda.IExFunction;
import Njava.util.function.MathUtil;
import hu.akarnokd.rxjava2.math.MathObservable;
import io.reactivex.Observable;

/**
 * Created by Doohyun on 2017. 3. 20..
 */
public class Test {

    @org.junit.Test
    public void rxSimple() {



        Observable.range(0, 100).to(MathUtil.<Integer>ToMax());
    }
}
