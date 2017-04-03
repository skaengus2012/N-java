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
public final class TimeBuilder {

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
        return Create(TimeUtil.GetCalendar());
    }

    /**
     * Deep-copy.
     *
     * @return
     */
    public final TimeBuilder getCopyObject() {
        TimeBuilder newTimeBuilder = new TimeBuilder();
        newTimeBuilder.calendar = calendar;
        newTimeBuilder.locale = locale;

        return newTimeBuilder;
    }

    /**
     * setting locale.
     *
     * @param locale
     * @return
     */
    @NonNull
    public TimeBuilder setLocale(@NonNull Locale locale) {
        TimeBuilder copy = getCopyObject();
        copy.locale = locale;

        return copy;
    }

    /**
     * setting First day of month.
     *
     * @return
     */
    @NonNull
    public TimeBuilder firstDayOfMonth() {
        TimeBuilder copy = getCopyObject();
        copy.calendar = TimeUtil.GetCalendarStartDayOfMonth(TimeUtil.GetYear(calendar), TimeUtil.GetMonthForHuman(calendar));

        return copy;
    }

    /**
     * setting end day of month.
     *
     * @return
     */
    public TimeBuilder endDayOfMonth() {
        TimeBuilder copy = getCopyObject();
        copy.calendar = TimeUtil.GetCalendarEndDayOfMonth(TimeUtil.GetYear(calendar), TimeUtil.GetMonthForHuman(calendar));

        return copy;
    }

    /**
     * Create year +- year value.
     *
     * @param year
     * @return
     */
    @NonNull
    public TimeBuilder addYear(@NonNull int year) {
        TimeBuilder copy = getCopyObject();
        copy.calendar.add(Calendar.YEAR, year);

        return copy;
    }

    /**
     * Add month +- value.
     *
     * @param month
     * @return
     */
    @NonNull
    public TimeBuilder addMonth(@NonNull int month) {
        TimeBuilder copy = getCopyObject();
        copy.calendar.add(Calendar.MONTH, month);

        return copy;
    }

    /**
     * Add day of month +- value.
     *
     * @param day
     * @return
     */
    @NonNull
    public TimeBuilder addDay(@NonNull int day) {
        TimeBuilder copy = getCopyObject();
        copy.calendar.add(Calendar.DAY_OF_MONTH, day);

        return copy;
    }

    /**
     * Add hour of day +- value.
     *
     * @param hour
     * @return
     */
    public TimeBuilder addHour(@NonNull int hour) {
        TimeBuilder copy = getCopyObject();
        copy.calendar.add(Calendar.HOUR_OF_DAY, hour);

        return copy;
    }

    /**
     * Add minute +- value.
     *
     * @param minute
     * @return
     */
    public TimeBuilder addMinute(@NonNull int minute) {
        TimeBuilder copy = getCopyObject();
        copy.calendar.add(Calendar.MINUTE, minute);

        return copy;
    }

    /**
     * Add second +- value.
     *
     * @param second
     * @return
     */
    public TimeBuilder addSecond(@NonNull int second) {
        TimeBuilder copy = getCopyObject();
        copy.calendar.add(Calendar.SECOND, second);

        return copy;
    }

    /**
     * Add millisecond +- value.
     *
     * @param millisecond
     * @return
     */
    public TimeBuilder addMilliSecond(@NonNull int millisecond) {
        TimeBuilder copy = getCopyObject();
        copy.calendar.add(Calendar.MILLISECOND, millisecond);

        return copy;
    }

    /**
     * convert to yyMMdd.
     *
     * @return
     */
    public TimeBuilder to_yyMMdd() {
        TimeBuilder copy = getCopyObject();
        copy.calendar = TimeUtil.To_yyMMdd(calendar);

        return copy;
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
     * Return Year
     *
     * @return
     */
    @NonNull
    public int getYear() {
        return TimeUtil.GetYear(calendar);
    }

    /**
     * Return month
     *
     * @return
     */
    @NonNull
    public int getMonth() {
        return TimeUtil.GetMonth(calendar);
    }

    /**
     * Return human month (1-12)
     *
     * @return
     */
    @NonNull
    public int getMonthForHuman() {
        return TimeUtil.GetMonthForHuman(calendar);
    }

    /**
     * Return day.
     *
     * @return
     */
    @NonNull
    public int getDay() {
        return TimeUtil.GetDay(calendar);
    }

    /**
     * Get hour 24 type
     *
     * @return
     */
    @NonNull
    public int getHour24() {
        return TimeUtil.GetHour24(calendar);
    }

    /**
     * Get hour 12 type
     *
     * @return
     */
    @NonNull
    public int getHour12() {
        return TimeUtil.GetHour12(calendar);
    }

    /**
     * get minute.
     *
     * @return
     */
    @NonNull
    public int getMinute() {
        return TimeUtil.GetMinute(calendar);
    }

    /**
     * get second.
     *
     * @return
     */
    @NonNull
    public int getSecond() {
        return TimeUtil.GetSecond(calendar);
    }

    /**
     * get milli second.
     *
     * @return
     */
    @NonNull
    public int getMilliSecond() {
        return TimeUtil.GetMilliSecond(calendar);
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
