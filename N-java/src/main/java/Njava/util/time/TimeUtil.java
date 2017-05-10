package Njava.util.time;

import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * For Time Processing util
 *
 * Created by Doohyun on 2017. 3. 26..
 */
public class TimeUtil {

    protected TimeUtil(){}

    /**
     * Return current calendar.
     *
     * @return
     */
    @NonNull
    public static Calendar GetCalendar() {
        return Calendar.getInstance();
    }

    /**
     * Return current Date.
     *
     * @return
     */
    @NonNull
    public static Date GetDate() {
        return GetCalendar().getTime();
    }

    /**
     * Return current time.
     *
     * @return
     */
    public static long GetTime() {
        return GetDate().getTime();
    }

    /**
     * Return current year.
     *
     * @return
     */
    @NonNull
    public static int GetYear() {
        return GetYear(Calendar.getInstance());
    }

    /**
     * Return param year.
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static int GetYear(@NonNull Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Return param year.
     *
     * @param date
     * @return
     */
    @NonNull
    public static int GetYear(@NonNull Date date) {
        return GetYear(ToCalendar(date));
    }

    /**
     * Return current month.
     *
     * @return
     */
    @NonNull
    public static int GetMonth() {
        return GetMonth(Calendar.getInstance());
    }

    /**
     * Return param month.
     *
     * @return
     */
    @NonNull
    public static int GetMonth(@NonNull Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Return param month.
     *
     * @return
     */
    @NonNull
    public static int GetMonth(@NonNull Date date) {
        return GetMonth(ToCalendar(date));
    }

    /**
     * Return current month. (1-12)
     *
     * @return
     */
    @NonNull
    public static int GetMonthForHuman() {
        return GetMonthForHuman(Calendar.getInstance());
    }

    /**
     * Return param month. (1-12)
     *
     * @return
     */
    @NonNull
    public static int GetMonthForHuman(@NonNull Calendar calendar) {
        return GetMonth(calendar) + 1;
    }

    /**
     * Return param month. (1-12)
     *
     * @return
     */
    @NonNull
    public static int GetMonthForHuman(@NonNull Date date) {
        return GetMonthForHuman(ToCalendar(date));
    }

    /**
     * Return current day.
     *
     * @return
     */
    @NonNull
    public static int GetDay() {
        return GetDay(Calendar.getInstance());
    }

    /**
     * Return param day.
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static int GetDay(@NonNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Return param day.
     *
     * @param date
     * @return
     */
    @NonNull
    public static int GetDay(@NonNull Date date) {
        return GetDay(ToCalendar(date));
    }

    /**
     * Return current 24 Hour
     *
     * @return
     */
    @NonNull
    public static int GetHour24() {
        return GetHour24(Calendar.getInstance());
    }

    /**
     * Return 24 Hour
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static int GetHour24(@NonNull Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Return 24 Hour.
     *
     * @param date
     * @return
     */
    @NonNull
    public static int GetHour24(@NonNull Date date) {
        return GetHour24(ToCalendar(date));
    }

    /**
     * Return current 12 Hour.
     *
     * @return
     */
    @NonNull
    public static int GetHour12() {
        return GetHour12(Calendar.getInstance());
    }

    /**
     * Return 12 Hour.
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static int GetHour12(@NonNull Calendar calendar) {
        return calendar.get(Calendar.HOUR);
    }

    /**
     * Return 12 Hour.
     *
     * @param date
     * @return
     */
    @NonNull
    public static int GetHour12(@NonNull Date date) {
        return GetHour12(ToCalendar(date));
    }

    /**
     * Return current minute.
     *
     * @return
     */
    public static int GetMinute() {
        return GetMinute(Calendar.getInstance());
    }

    /**
     * Return minute
     *
     * @param calendar
     * @return
     */
    public static int GetMinute(@NonNull Calendar calendar) {
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Return minute
     *
     * @param date
     * @return
     */
    public static int GetMinute(@NonNull Date date) {
        return GetMinute(ToCalendar(date));
    }

    /**
     * Return current second.
     *
     * @return
     */
    @NonNull
    public static int GetSecond() {
        return GetSecond(Calendar.getInstance());
    }

    /**
     * Return second.
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static int GetSecond(@NonNull Calendar calendar) {
        return calendar.get(Calendar.SECOND);
    }

    /**
     * Return second.
     *
     * @param date
     * @return
     */
    @NonNull
    public static int GetSecond(@NonNull Date date) {
        return GetSecond(ToCalendar(date));
    }

    /**
     * Return current milli second.
     *
     * @return
     */
    @NonNull
    public static int GetMilliSecond() {
        return GetMilliSecond(Calendar.getInstance());
    }

    /**
     * Return milli second.
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static int GetMilliSecond(@NonNull Calendar calendar) {
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * Return milli second.
     *
     * @param date
     * @return
     */
    @NonNull
    public static int GetMilliSecond(@NonNull Date date) {
        return GetMilliSecond(ToCalendar(date));
    }

    /**
     * Return First Calendar of Month.
     *
     * @param year
     * @param monthForHuman (1-12)
     * @return
     */
    @NonNull
    public static Calendar GetCalendarStartDayOfMonth(@NonNull int year, @NonNull int monthForHuman) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthForHuman - 1, 1);

        return calendar;
    }

    /**
     * Return End Calendar of Month.
     *
     * @param year
     * @param monthForHuman (1-12)
     * @return
     */
    @NonNull
    public static Calendar GetCalendarEndDayOfMonth(@NonNull int year, @NonNull int monthForHuman) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthForHuman, 0);

        return calendar;
    }

    /**
     * Return end day of month.
     *
     * @param year
     * @param monthForHuman (1-12)
     * @return
     */
    @NonNull
    public static int GetEndDayOfMonth(@NonNull int year, @NonNull int monthForHuman) {
        return GetDay(GetCalendarEndDayOfMonth(year, monthForHuman));
    }

    /**
     * Return end day of month.
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static int GetEndDayOfMonth(@NonNull Calendar calendar) {
        int year = GetYear(calendar);
        int month = GetMonthForHuman(calendar);

        return GetEndDayOfMonth(year, month);
    }

    /**
     * Return end day of month.
     *
     * @param date
     * @return
     */
    @NonNull
    public static int GetEndDayOfMonth(@NonNull Date date) {
        return GetEndDayOfMonth(ToCalendar(date));
    }

    /**
     * Convert Date -> Calendar.
     *
     * @param date
     * @return
     */
    @NonNull
    public static Calendar ToCalendar(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * Convert yy-MM-dd hh:mm:ss -> yy-MM-dd.
     *
     * @param calendar
     * @return
     */
    @NonNull
    public static Calendar To_yyMMdd(@NonNull Calendar calendar) {
        Calendar result = Calendar.getInstance();
        result.setTime(calendar.getTime());

        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);

        return result;
    }

    /**
     * Convert yy-MM-dd hh:mm:ss -> yy-MM-dd.
     *
     * @param date
     * @return
     */
    @NonNull
    public static Date To_yyMMdd(@NonNull Date date) {
        return To_yyMMdd(ToCalendar(date)).getTime();
    }

    /**
     * Return Date by String.
     *
     * <pre>
     *  The Result managed by Maybe. Because I wanna manage parse with out Null & Exception.
     * </pre>
     *
     * @param dateString
     * @param format
     * @return
     */
    @NonNull
    public static Maybe<Date> ParseDate(@NonNull String dateString, @NonNull String format) {
        return ParseDate(dateString, new SimpleDateFormat(format));
    }

    /**
     * Return Date by String.
     *
     * <pre>
     *  The Result managed by Maybe. Because I wanna manage parse with out Null & Exception.
     * </pre>
     *
     * @param dateString
     * @param simpleDateFormat
     * @return
     */
    @NonNull
    public static Maybe<Date> ParseDate(@NonNull String dateString, @NonNull SimpleDateFormat simpleDateFormat) {
        try {
            return Maybe.just(simpleDateFormat.parse(dateString));
        } catch (Exception e) {
            return Maybe.empty();
        }
    }

    /**
     * Return String by Date.
     *
     * <pre>
     *  The Result managed by Maybe. Because I wanna manage parse with out Null & Exception.
     * </pre>
     *
     * @param date
     * @param format
     * @return
     */
    @NonNull
    public static Maybe<String> ParseString(@NonNull Date date, @NonNull String format) {
        return ParseString(date, new SimpleDateFormat(format));
    }

    /**
     * Return String by Date.
     *
     * <pre>
     *  The Result managed by Maybe. Because I wanna manage parse with out Null & Exception.
     * </pre>
     *
     * @param date
     * @param simpleDateFormat
     * @return
     */
    @NonNull
    public static Maybe<String> ParseString(@NonNull Date date, @NonNull SimpleDateFormat simpleDateFormat) {
        try {
            return Maybe.just(simpleDateFormat.format(date));
        } catch (Exception e) {
            return Maybe.empty();
        }
    }
}
