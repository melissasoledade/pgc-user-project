package com.user.fixtures.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    public static DateFormat dateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static LocalDate parseDateFromString(String pattern, String textDate) throws ParseException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(textDate, formatter);
    }
}
