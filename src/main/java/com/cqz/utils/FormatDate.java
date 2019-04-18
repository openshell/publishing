package com.cqz.utils;

import java.util.Date;

/**
 * @author openshell
 * @date 2019/4/18
 */

public class FormatDate {
    public static String getFormatDate(Date date){
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String  formatDate=format1.format(date);
        return formatDate;
    }


}
