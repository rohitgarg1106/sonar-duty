package com.example.sonarduty.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    public static boolean isStringEmpty(String value){
        return value == null || value == "";
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime){
        // Format LocalDateTime to String
        if(localDateTime == null){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = localDateTime.format(dateTimeFormatter);
        return formattedDateTime;
    }




}
