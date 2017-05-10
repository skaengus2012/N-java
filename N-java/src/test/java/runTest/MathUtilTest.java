package runTest;

import Njava.util.function.MathUtil;
import io.reactivex.Observable;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MathUtil Test
 *
 * Created by Doohyun on 2017. 5. 10..
 */
public class MathUtilTest {

    @Test
    public void runBigDecimalAddTest() {
        List<BigDecimal> bigDecimalList = Collections.emptyList();

        Observable.fromIterable(bigDecimalList).to(MathUtil.SumBigDecimal()).blockingSingle(BigDecimal.ZERO);
    }

    @Test
    public void runBigDecimalAverageTest() {
        List<BigDecimal> bigDecimalList = Arrays.asList(BigDecimal.ZERO, new BigDecimal(5), new BigDecimal(7));

        System.out.println(Observable.fromIterable(bigDecimalList).to(MathUtil.AverageBigDecimal()).blockingSingle());
    }
}
