package runTest;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doohyun on 2017. 3. 20..
 */
public class Test {

    @org.junit.Test
    public void rxSimple() {
        Observable.range(0, 100).forEach(new Consumer<Integer>() {
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
