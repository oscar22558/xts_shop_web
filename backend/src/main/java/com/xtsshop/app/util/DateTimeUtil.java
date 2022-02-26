package com.xtsshop.app.util;

import java.sql.Date;
import java.util.Calendar;


public class DateTimeUtil {
    public Date now(){
        long now = Calendar.getInstance().getTimeInMillis();
        return new Date(now);
    }
}
