package com.user.fixtures.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static DateFormat dateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static Date parseDateFromString(String pattern, String textDate) throws ParseException {
        return dateFormat(pattern).parse(textDate);
    }
}
