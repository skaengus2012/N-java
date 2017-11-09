package Njava.util.time;

import Njava.function.exceptionLambda.IExRunnable;
import Njava.util.business.ObjectUtil;
import Njava.util.function.MaybeUtil;
import Njava.util.time.duration.Duration;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Manage calendar using Builder.
 * <p>
 * Created by Doohyun on 2017. 3. 26..
 */
public final class TimeBuilder implements Comparable<TimeBuilder> {

    private Calendar calendar;
    private Locale locale = Locale.getDefault();

    private TimeBuilder() {
    }

    public static TimeBuilder Create(@NonNull Integer year, @NonNull Integer humanMonth, @NonNull Integer dayOfMonth) {
        TimeBuilder timeBuilder = new TimeBuilder();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, humanMonth - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        timeBuilder.calendar = calendar;

        return timeBuilder.to_yyMMdd();
    }

    /**
     * Create TimeBuilder
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static TimeBuilder Create(@NonNull Calendar calendar) {
        TimeBuilder timeBuilder = new TimeBuilder();

        Calendar newCal = Calendar.getInstance();
        newCal.setTimeInMillis(calendar.getTimeInMillis());


        timeBuilder.calendar = newCal;

        return timeBuilder;
    }

    /**
     * Create TimeBuilder
     *
     * @param dateTime
     * @return
     */
    @NonNull
    public static TimeBuilder Create(@NonNull Long dateTime) {
        return Create(new Date(dateTime));
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
        MaybeUtil.SubscribeEmpty(dateMaybe, new IExRunnable() {
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
     * setting locale.
     *
     * @param locale
     * @return
     */
    @NonNull
    public TimeBuilder setLocale(@NonNull Locale locale) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar = TimeUtil.GetCalendarStartDayOfMonth(TimeUtil.GetYear(calendar), TimeUtil.GetMonthForHuman(calendar));

        return copy;
    }

    /**
     * setting end day of month.
     *
     * @return
     */
    public TimeBuilder endDayOfMonth() {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar = TimeUtil.GetCalendarEndDayOfMonth(TimeUtil.GetYear(calendar), TimeUtil.GetMonthForHuman(calendar));

        return copy;
    }

    /**
     * Setting Year
     *
     * @param year
     * @return
     */
    public TimeBuilder withYear(@NonNull int year) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.set(Calendar.YEAR, year);

        return copy;
    }

    /**
     * Setting month
     *
     * @param humanMonth
     * @return
     */
    public TimeBuilder withMonth(@NonNull int humanMonth) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.set(Calendar.MONTH, humanMonth - 1);

        return copy;
    }

    /**
     * Setting day of month
     *
     * @param dayOfMonth
     * @return
     */
    public TimeBuilder withDayOfMonth(@NonNull int dayOfMonth) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return copy;
    }

    /**
     * Setting hour of day.
     *
     * @param hour
     * @return
     */
    public TimeBuilder withHour(@NonNull int hour) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.set(Calendar.HOUR_OF_DAY, hour);

        return copy;
    }

    /**
     * Setting minute.
     *
     * @param minute
     * @return
     */
    public TimeBuilder withMinute(@NonNull int minute) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.set(Calendar.MINUTE, minute);

        return copy;
    }

    /**
     * Setting second.
     *
     * @param second
     * @return
     */
    public TimeBuilder withSecond(@NonNull int second) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.set(Calendar.SECOND, second);

        return copy;
    }

    /**
     * Setting milliSecond.
     *
     * @param milliSecond
     * @return
     */
    public TimeBuilder withMilliSecond(@NonNull int milliSecond) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.set(Calendar.MILLISECOND, milliSecond);

        return copy;
    }

    /**
     * Setting time.
     *
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public TimeBuilder withTime(@NonNull int hour, @NonNull int minute, @NonNull int second) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);

        Calendar calendar = copy.calendar;
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);

        return copy;
    }

    /**
     * Setting time.
     *
     * @param hour
     * @param minute
     * @param second
     * @param milliSecond
     * @return
     */
    public TimeBuilder withTime(@NonNull int hour, @NonNull int minute, @NonNull int second, @NonNull int milliSecond) {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);

        Calendar calendar = copy.calendar;
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);

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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
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
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar.add(Calendar.MILLISECOND, millisecond);

        return copy;
    }

    /**
     * convert to yyMMdd.
     *
     * @return
     */
    public TimeBuilder to_yyMMdd() {
        TimeBuilder copy = ObjectUtil.CopyProperties(this);
        copy.calendar = TimeUtil.To_yyMMdd(calendar);

        return copy;
    }

    /**
     * Return calendar.
     *
     * @return
     */
    @NonNull
    public Calendar getCalendar() {
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
     * get is leap year.
     *
     * @return
     */
    public boolean isLeapYear() {
        return new GregorianCalendar(locale).isLeapYear(getYear());
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

    /**
     * Convert duration (with TimeBuilder)
     *
     * @param timeBuilder
     * @return
     */
    @NonNull
    public Duration toDuration(@NonNull TimeBuilder timeBuilder) {
        return Duration.Create(this, timeBuilder);
    }

    /**
     * Convert duration (with Calendar)
     *
     * @param calendar
     * @return
     */
    @NonNull
    public Duration toDuration(@NonNull Calendar calendar) {
        return Duration.Create(this, TimeBuilder.Create(calendar));
    }

    /**
     * Convert duration (with Date)
     *
     * @param date
     * @return
     */
    @NonNull
    public Duration toDuration(@NonNull Date date) {
        return Duration.Create(this, TimeBuilder.Create(date));
    }

    /**
     * Convert duration (with DateFormat)
     *
     * @param dateString
     * @param format
     * @return
     */
    @NonNull
    public Duration toDuration(@NonNull String dateString, @NonNull String format) {
        return Duration.Create(this, TimeBuilder.Create(dateString, format));
    }

    /**
     * Convert duration (with current time)
     *
     * @return
     */
    @NonNull
    public Duration toDuration() {
        return Duration.Create(this, TimeBuilder.Create());
    }

    /**
     * @return
     */
    @NonNull
    public String toString() {
        return calendar.toString();
    }

    /**
     * Compare TimeBuilder
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(TimeBuilder o) {
        return getDate().compareTo(o.getDate());
    }
}
