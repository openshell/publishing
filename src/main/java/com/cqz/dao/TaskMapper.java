package com.cqz.dao;

import com.cqz.model.Task;
/**
 * @Author: openshell
 * @Description:
 */
public interface TaskMapper {
    /**
     *  通过主键删除任务
     * @author openshell
     * @date 2019/4/16
     * @param taskId
     * @return int
     */
    int deleteByPrimaryKey(Integer taskId);

    /**
     * 插入任务记录
     * @author openshell
     * @date 2019/4/16
     * @param record
     * @return int
     */
    int insert(Task record);

    /**
     * 只会插入数据不为null的字段值
     * @author openshell
     * @date 2019/4/16
     * @param record
     * @return int
     */
    int insertSelective(Task record);
    
    /**
     * 根据主键查询
     * @author openshell
     * @date 2019/4/16
     * @param taskId
     * @return com.cqz.model.Task
     */
    Task selectByPrimaryKey(Integer taskId);

    /**
     * 根据主键更新，只会更新数据不为null的字段值
     * @author openshell
     * @date 2019/4/17
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Task record);
    /**
     * 根据主键更新
     * @author openshell
     * @date 2019/4/17
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Task record);
}