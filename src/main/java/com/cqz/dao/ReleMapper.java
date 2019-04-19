package com.cqz.dao;

import com.cqz.model.Rele;
import org.apache.ibatis.annotations.Param;

public interface ReleMapper {

    /**
     * 检查是否有userId与taskId重复的字段
     * @author openshell
     * @date 2019/4/19
     * @param [userId, taskId]
     * @return int
     */
    int checkRele(@Param("userId")int userId, @Param("taskId")int taskId);


    int deleteByPrimaryKey(Integer releId);

    int insert(Rele record);

    int insertSelective(Rele record);

    Rele selectByPrimaryKey(Integer releId);

    int updateByPrimaryKeySelective(Rele record);

    int updateByPrimaryKey(Rele record);
}