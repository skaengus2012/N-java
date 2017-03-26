package runTest;

import Njava.function.exceptionLambda.IExConsumer;
import Njava.util.time.TimeBuilder;
import Njava.util.time.TimeUtil;
import io.reactivex.annotations.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Doohyun on 2017. 3. 26..
 */
public class TimeUtilTest {

    @org.junit.Test
    public void runEndOfDayTest() {
        Calendar calendar = TimeUtil.GetCalendarEndDayOfMonth(2016, 2);
        
        System.out.println(TimeUtil.GetMonth(calendar) + " " + TimeUtil.GetMonthForHuman(calendar));
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

    @org.junit.Test
    public void runTimeBuilderTest() {
        TimeBuilder.Create("2016.12.12", "yyyy.MM.dd").
                endDayOfMonth().to_yyMMdd().addDay(1).
                getStringFormat("yyyy-MM-dd hh:mm:ss a").subscribe(new IExConsumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {

                System.out.println(s);
            }
        });

    }
}