package runTest;

import Njava.function.exceptionLambda.IExConsumer;
import Njava.util.between.Between;
import Njava.util.between.BetweenMap;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * For Between Test
 *
 * Created by Doohyun on 2017. 7. 3..
 */
public class BetweenTest {

    @Test
    public void test() {

        // Between Instance Create!!!!
        {
            Between<Integer> between3to7 = Between.Just(3, 7);

            System.out.println("Between 3 to 7 -> contains [4] Test! -> " + between3to7.contains(4) + "\n");
        }

        // Between Map
        {
            System.out.println("Between Map Test");

            BetweenMap<Between<BigDecimal>, String> betweenStringBetweenMap = new BetweenMap<>();

            betweenStringBetweenMap.put(Between.Just(BigDecimal.valueOf(0.0), BigDecimal.valueOf(5.0)), "A");
            betweenStringBetweenMap.put(Between.Just(BigDecimal.valueOf(5.1), BigDecimal.valueOf(10.0)), "S");
            betweenStringBetweenMap.put(Between.Just(BigDecimal.valueOf(6.1), BigDecimal.valueOf(13.0)), "S++");

            // BigDecimal map getter.
            // Return type -> Rx.Maybe
            betweenStringBetweenMap.getFirst(BigDecimal.valueOf(3.87)).subscribe(new IExConsumer<String>() {
                @Override
                public void accept(@NonNull String s) throws Exception {
                    System.out.println("Maybe [3.87] Returns" + s);
                }
            });

            // List Type Return.
            System.out.println("List Type [8.7] Returns : " + betweenStringBetweenMap.getToList(BigDecimal.valueOf(8.7)));
        }

    }
}
