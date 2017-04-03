package runTest;

import Njava.function.exceptionLambda.IExConsumer;
import Njava.util.time.TimeBuilder;
import Njava.util.time.TimeUtil;
import Njava.util.time.duration.Duration;
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
                addMonth(-2).
                addYear(-1).
                endDayOfMonth().
                to_yyMMdd().
                getStringFormat("yyyy-MM-dd hh:mm:ss a").subscribe(new IExConsumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {

                System.out.println(s);
            }
        });

        System.out.println(TimeUtil.GetEndDayOfMonth(Calendar.getInstance()));
        System.out.println(TimeUtil.GetEndDayOfMonth(2017, 2));

        TimeBuilder.Create(TimeUtil.GetCalendarStartDayOfMonth(2016, 1)).
                getStringFormat("yyyy-MM-dd hh:mm:ss a").subscribe(new IExConsumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    @org.junit.Test
    public void runWith() {
        TimeBuilder.Create(2017, 12, 28).withYear(2000).withMonth(2).getStringFormat("yyyy. MM. dd").subscribe(new IExConsumer<String>() {
            @Override
            public void accept(@NonNull String string) throws Exception {
                System.out.println(string);
            }
        });

       long time =  TimeBuilder.Create().to_yyMMdd().withTime(0, 0, 0).withMilliSecond(0).getTime();

       TimeBuilder.Create(new Date(time)).getStringFormat("yyyy.MM.dd hh.mm.ss aa").subscribe(new IExConsumer<String>() {
           @Override
           public void accept(@NonNull String s) throws Exception {
               System.out.println(s);
           }
       });


       System.out.println(time);
    }

    @org.junit.Test
    public void runDurationTest() {

        Duration duration = TimeBuilder.Create(2017, 12, 28).toDuration();

        System.out.println(duration.toDayOfMonth());
        System.out.println(duration.toHour());
        System.out.println(duration.toMinute());
        System.out.println(duration.toSecond());

        System.out.println(duration.toMonth());
        System.out.println(duration.toAbsolute().toMonth());

        System.out.println(duration.toYear());
        System.out.println(duration.toAbsolute().toYear());
       // System.out.println(duration.toAbsolute().getDifferenceYear());
    }
}
