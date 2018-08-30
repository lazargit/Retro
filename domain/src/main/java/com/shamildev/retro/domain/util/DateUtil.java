package com.shamildev.retro.domain.util;

import com.shamildev.retro.domain.DomainObjectStorable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * Created by Schamil Mischijew on 26.11.2017.
 */

public class DateUtil {

    public static String convertToDateTime(long dateInMilliseconds) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(dateInMilliseconds);
        java.text.DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z", Locale.GERMANY);

        formatter.setCalendar(calendar);

       // System.out.println(formatter.format(calendar.getTime()));


        return formatter.format(calendar.getTime()); //DateFormat.format("dd/MM/yyyy hh:mm:ss", dateInMilliseconds).toString();
    }

    public static Timestamp convertStringUtcToDate(String str) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss UTC",Locale.GERMANY);
            Date parsedDate = dateFormat.parse(str);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            return timestamp;
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }
            return null;
    }


    public static Long convertStringUtcToMilSec(String str) {

        try {
            SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                             outputFmt.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date parsedDate = outputFmt.parse(str);
          //  Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            return parsedDate.getTime();
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption

        }
        return 0L;
    }

//    public static Long convertDateToTimestamp(long dateInMilliseconds) {
//
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTimeInMillis(dateInMilliseconds);
//        java.text.DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z", Locale.GERMANY);
//
//        formatter.setCalendar(calendar);
//
//        // System.out.println(formatter.format(calendar.getTime()));
//
//
//        return formatter.format(calendar.getTime()); //DateFormat.format("dd/MM/yyyy hh:mm:ss", dateInMilliseconds).toString();
//    }



    public static long dateDifInMinutes(long a, long b) {

        return TimeUnit.MILLISECONDS.toMinutes((a-b));

    }

    public static long dateDifFromNowInMinutes(long timeMillis) {

        return TimeUnit.MILLISECONDS.toMinutes((System.currentTimeMillis()-timeMillis));

    }

    public static long NOW() {

        return System.currentTimeMillis();

    }

//    public <T extends DomainObjectStorable> Observable<T> save(T object, Class<T> clazz) {
//        Realm realm = this.realmProvider.get();
//
//        long id;
//
//        try {
//            id = realm.where(clazz).max("id").intValue() + 1;
//        } catch (Exception e) {
//            id = 0L;
//        }
//
//        ((TaskRealModel) object).setId(id);


    public static <T extends DomainObjectStorable> Flowable<Boolean> isCacheTimeExpired(T object,  int cacheExpiredTime) {


        System.out.println(convertToDateTime(object.lastUpdate())+" NOW:"+convertToDateTime(NOW())+" isCacheTimeExpired "+cacheExpiredTime+" DIF :"+dateDifFromNowInMinutes(object.lastUpdate()));

        return Flowable.defer(() -> {

            if(dateDifFromNowInMinutes(object.lastUpdate()) > cacheExpiredTime){
                System.out.println("isCacheTimeExpired"+true);
                return Flowable.just(true);
            }
            System.out.println("isCacheTimeExpired"+false);
            return Flowable.just(false);


        });

    }



}
