package Njava.util.time;

import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Manage calendar using Builder.
 *
 * Created by Doohyun on 2017. 3. 26..
 */
public class TimeBuilder {

    private Calendar calendar;
    private Locale locale = Locale.getDefault();

    private TimeBuilder(){}

    /**
     * Create TimeBuilder
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static TimeBuilder Create(@NonNull Calendar calendar){
        TimeBuilder timeBuilder = new TimeBuilder();
        timeBuilder.calendar = calendar;

        return timeBuilder;
    }

    /**
     * Create TimeBuilder using Date.
     *
     * @param date
     * @return
     */
    @NonNull
    public static TimeBuilder Create(@NonNull Date date) {
        return Create(TimeUtil.ToCalendar(date));
    }

    /**
     * Create TimeBuilder using dateString & format.
     *
     * @param dateString
     * @param format
     * @return
     */
    @NonNull
    public static TimeBuilder Create(@NonNull String dateString, @NonNull String format) {
        Maybe<Date> dateMaybe = TimeUtil.ParseDate(dateString, format);

        // If maybe is null, that`s error.
        MaybeUtil.SubscribeEmpty(dateMaybe, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("[ERROR] Date format error");
            }
        });

        return Create(dateMaybe.blockingGet());
    }

    /**
     * Create TimeBuilder by current date.
     *
     * @return
     */
    @NonNull
    public static TimeBuilder Create() {
        return Create(Calendar.getInstance());
    }

    /**
     * setting locale.
     *
     * @param locale
     * @return
     */
    @NonNull
    public TimeBuilder setLocale(@NonNull Locale locale) {
        this.locale = locale;

        return this;
    }

    /**
     * setting First day of month.
     *
     * @return
     */
    public TimeBuilder firstDayOfMonth() {
        calendar = TimeUtil.GetCalendarStartDayOfMonth(TimeUtil.GetYear(calendar), TimeUtil.GetMonth(calendar));

        return this;
    }

    /**
     * setting end day of month.
     *
     * @return
     */
    public TimeBuilder endDayOfMonth() {
        calendar = TimeUtil.GetCalendarEndDayOfMonth(TimeUtil.GetYear(calendar), TimeUtil.GetMonth(calendar) + 1);

        return this;
    }

    /**
     * Create year +- year value.
     *
     * @param year
     * @return
     */
    @NonNull
    public TimeBuilder addYear(@NonNull int year) {
        calendar.add(Calendar.YEAR, year);
        return this;
    }

    /**
     * Add month +- value.
     *
     * @param month
     * @return
     */
    @NonNull
    public TimeBuilder addMonth(@NonNull int month) {
        calendar.add(Calendar.MONTH, month);
        return this;
    }

    /**
     * Add day of month +- value.
     *
     * @param day
     * @return
     */
    @NonNull
    public TimeBuilder addDay(@NonNull int day) {
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return this;
    }

    /**
     * Add hour of day +- value.
     *
     * @param hour
     * @return
     */
    public TimeBuilder addHour(@NonNull int hour) {
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    /**
     * Add minute +- value.
     *
     * @param minute
     * @return
     */
    public TimeBuilder addMinute(@NonNull int minute) {
        calendar.add(Calendar.MINUTE, minute);

        return this;
    }

    /**
     * Add second +- value.
     *
     * @param second
     * @return
     */
    public TimeBuilder addSecond(@NonNull int second) {
        calendar.add(Calendar.SECOND, second);

        return this;
    }

    /**
     * Add millisecond +- value.
     *
     * @param millisecond
     * @return
     */
    public TimeBuilder addMilliSecond(@NonNull int millisecond) {
        calendar.add(Calendar.MILLISECOND, millisecond);

        return this;
    }

    /**
     * convert to yyMMdd.
     *
     * @return
     */
    public TimeBuilder to_yyMMdd() {
        calendar = TimeUtil.To_yyMMdd(calendar);

        return this;
    }

    /**
     * Return calendar.
     *
     * @return
     */
    @NonNull
    public Calendar getCalendar(){
        return calendar;
    }

    /**
     * Return date.
     *
     * @return
     */
    @NonNull
    public Date getDate() {
        return calendar.getTime();
    }

    /**
     * Return time MILLISECOND.
     *
     * @return
     */
    @NonNull
    public long getTime() {
        return getDate().getTime();
    }

    /**
     * Return string format.
     *
     * @param format
     * @return
     */
    @NonNull
    public Maybe<String> getStringFormat(String format) {
        return TimeUtil.ParseString(getDate(), new SimpleDateFormat(format, locale));
    }
}
