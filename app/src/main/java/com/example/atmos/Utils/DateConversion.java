package com.example.atmos.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {

    private static Date date;
    private static SimpleDateFormat simpleDateFormat;

    public static String convertToDate(long unixTimestamp, String pattern){
        date = new Date(unixTimestamp * 1000);
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
