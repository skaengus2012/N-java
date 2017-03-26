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
    public static int GetHumanMonth() {
        return GetHumanMonth(Calendar.getInstance());
    }

    /**
     * Return param month. (1-12)
     *
     * @return
     */
    @NonNull
    public static int GetHumanMonth(@NonNull Calendar calendar) {
        return GetMonth(calendar) + 1;
    }

    /**
     * Return param month. (1-12)
     *
     * @return
     */
    @NonNull
    public static int GetHumanMonth(@NonNull Date date) {
        return GetHumanMonth(ToCalendar(date));
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
     * Return First Calendar of Month.
     *
     * @param year
     * @param month
     * @return
     */
    @NonNull
    public static Calendar GetCalendarStartDayOfMonth(@NonNull int year, @NonNull int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        return calendar;
    }

    /**
     * Return End Calendar of Month.
     *
     * @param year
     * @param month
     * @return
     */
    @NonNull
    public static Calendar GetCalendarEndDayOfMonth(@NonNull int year, @NonNull int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 0);

        return calendar;
    }

    /**
     * Return end day of month.
     *
     * @param year
     * @param month
     * @return
     */
    @NonNull
    public static int GetEndDayOfMonth(@NonNull int year, @NonNull int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 0);

        return GetDay(GetCalendarEndDayOfMonth(year, month));
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
        int month = GetHumanMonth(calendar);

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

        result.set(Calendar.HOUR, 0);
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
