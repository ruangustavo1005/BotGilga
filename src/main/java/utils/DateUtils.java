package utils;

import java.util.Date;

public class DateUtils {

    public static long dateToLong(Date data) {
        return data.getTime() / 1000;
    }
    
    public static Date now() {
        return new Date(System.currentTimeMillis());
    }
    
}