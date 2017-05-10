package Njava.util.function.math;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.internal.observers.DeferredScalarObserver;

import java.math.BigDecimal;

/**
 * BigDecimal Sum source.
 *
 * Created by Doohyun on 2017. 5. 10..
 */
public class ObservableSumBigDecimal extends ObservableWithSource<BigDecimal, BigDecimal> {

    public ObservableSumBigDecimal(ObservableSource<BigDecimal> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer<? super BigDecimal> observer) {
        source.subscribe(new SumBigDecimalObserver(observer));
    }

    static final class SumBigDecimalObserver extends DeferredScalarObserver<BigDecimal, BigDecimal> {

        BigDecimal accumulator = BigDecimal.ZERO;

        boolean hasValue = false;

        SumBigDecimalObserver(Observer<? super BigDecimal> actual) {
            super(actual);
        }

        @Override
        public void onNext(BigDecimal value) {
            if (!hasValue) {
                hasValue = true;
            }
            accumulator = accumulator.add(value);
        }

        @Override
        public void onComplete() {
            if (hasValue) {
                complete(accumulator);
            } else {
                actual.onComplete();
            }
        }

    }
}
