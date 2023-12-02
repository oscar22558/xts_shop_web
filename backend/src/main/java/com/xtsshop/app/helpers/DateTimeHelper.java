package com.xtsshop.app.helpers;

import java.sql.Date;
import java.util.Calendar;


public class DateTimeHelper {
    public Date now(){
        long now = Calendar.getInstance().getTimeInMillis();
        return new Date(now);
    }
}
