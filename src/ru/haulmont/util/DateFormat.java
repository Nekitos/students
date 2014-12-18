package ru.haulmont.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by nikita on 12/18/14.
 */
public class DateFormat {
    public static Date fromString(String date, SimpleDateFormat format) throws ParseException {
        long time = format.parse(date).getTime();
        return  new Date(time);
    }
}
