package com.example.course4_2.utile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class DateUtil {
    public static String getNowTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}
