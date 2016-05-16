package es.uji.daal.easyrent.utils;

import java.sql.Date;

/**
 * Created by Alberto on 17/05/2016.
 */
public class DateUtils {
    public static int daysBetweenDates(Date date1, Date date2) {
        return (int) (date1.getTime()-date2.getTime()) / (1000*60*60*24);
    }
}
