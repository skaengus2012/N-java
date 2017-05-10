package Njava.util.function.math;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.internal.observers.DeferredScalarObserver;

import java.math.BigDecimal;

/**
 * BigDecimal Average
 *
 * Created by Doohyun on 2017. 5. 10..
 */
public class ObservableAverageBigDecimal extends ObservableWithSource<BigDecimal, BigDecimal> {

    public ObservableAverageBigDecimal(ObservableSource<BigDecimal> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer<? super BigDecimal> observer) {
        source.subscribe(new ObservableAverageBigDecimal.AverageBigDecimalObserver(observer));
    }

    static final class AverageBigDecimalObserver extends DeferredScalarObserver<BigDecimal, BigDecimal> {

        BigDecimal accumulator = BigDecimal.ZERO;

        int count;

        AverageBigDecimalObserver(Observer<? super BigDecimal> actual) {
            super(actual);
        }

        @Override
        public void onNext(BigDecimal value) {
            count++;
            accumulator = accumulator.add(value);
        }

        @Override
        public void onComplete() {
            int c = count;
            if (c != 0) {
                complete(accumulator.divideToIntegralValue(new BigDecimal(c)));
            } else {
                actual.onComplete();
            }
        }

    }
}
