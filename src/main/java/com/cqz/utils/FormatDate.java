package com.cqz.utils;

import com.cqz.model.Task;
import com.cqz.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author openshell
 * @date 2019/4/18
 */

public class FormatDate {
    /**
     * 将Date格式化为yyyy-MM-dd hh:mm:ss形式的字符串
     * @author openshell
     * @date 2019/4/18
     * @param [date]
     * @return java.lang.String
     */
    public static String getFormatDateWithTime(java.util.Date date){
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String  formatDate=format1.format(date);
        return formatDate;
    }
    /**
     * 将Date格式化为yyyy-MM-dd形式的字符串
     * @author openshell
     * @date 2019/4/18
     * @param
     * @return
     */
    public static String getformatDate(java.util.Date date){
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String  formatDate=format1.format(date);
        return formatDate;
    }

    public static List<Task> formatTask(List<Task> tasks){
        for (Task task:tasks ) {
            if (task.getTaskEndTime()!=null){
                task.setFormatTaskEndTime(FormatDate.getformatDate(task.getTaskEndTime()));
            }
            task.setFormatTaskStartTime(FormatDate.getformatDate(task.getTaskStartTime()));
            System.out.println(task.toString());
        }
        return tasks;
    }




}
