package com.cqz.dao;

import com.cqz.model.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 只会插入数据不为null的字段值，并返回新增的主键的值
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

    /**
     * 查询所有任务
     * @author openshell
     * @date 2019/4/18
     * @param
     * @return java.util.List<com.cqz.model.Task>
     */
    List<Task> selectAllTask();

    /**
     * 根据任务名查询任务
     * @author openshell
     * @date 2019/4/18
     * @param searchKey
     * @return java.util.List<com.cqz.model.Task>
     */
    List<Task> selectTaskByName(String searchKey);

    /**
     * 根据用户Id查询任务
     * @author openshell
     * @date 2019/4/18
     * @param [userId]
     * @return java.util.List<com.cqz.model.Task>
     */
    List<Task> selectBackLog(@Param("userId")int userId, @Param("releTypeBeClaim") String releTypeBeClaim);


}