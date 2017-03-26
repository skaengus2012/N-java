package runTest;

import Njava.function.exceptionLambda.IExConsumer;
import Njava.util.business.TimeUtil;
import io.reactivex.annotations.NonNull;

import java.util.Date;

/**
 * Created by Doohyun on 2017. 3. 26..
 */
public class TimeUtilTest {

    @org.junit.Test
    public void runEndOfDayTest() {
        System.out.println(TimeUtil.GetEndDayOfMonth(2017, 1));
    }

    @org.junit.Test
    public void runParseDate(){
        TimeUtil.ParseDate("2016-12-2", "yyyy-MM-dd").subscribe(new IExConsumer<Date>() {
            @Override
            public void accept(@NonNull Date date) throws Exception {
                TimeUtil.ParseString(date, "yyyy. MM. dd").subscribe(new IExConsumer<String>() {
                    @Override
                    public void accept(@NonNull String string) throws Exception {
                        System.out.println(string);
                    }
                });
            }
        });
    }
}
