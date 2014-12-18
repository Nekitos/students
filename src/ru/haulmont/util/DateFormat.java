package ru.haulmont.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Простой класс, предназначенный для преобразования даты
 * из формата дд.мм.гггг в формат понятный типу java.sql.Date
 * гггг-мм-дд
 */
public class DateFormat {
    /**
     * Метод преобразует формат даты дд.мм.гггг в гггг-мм-дд
     * @param date строка содержащая дд.мм.гггг
     * @param format формат даты, используемый в приложении
     * @return дату понятную для SQL
     * @throws ParseException
     */
    public static Date fromString(String date, SimpleDateFormat format) throws ParseException {
        long time = format.parse(date).getTime();
        return new Date(time);
    }
}
