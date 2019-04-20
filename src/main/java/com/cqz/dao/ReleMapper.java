package com.cqz.dao;

import com.cqz.model.Rele;
import org.apache.ibatis.annotations.Param;

public interface ReleMapper {
    int deleteByPrimaryKey(Integer releId);

    int insert(Rele record);

    int insertSelective(Rele record);

    Rele selectByPrimaryKey(Integer releId);

    int updateByPrimaryKeySelective(Rele record);

    int updateByPrimaryKey(Rele record);




    /**
     * 根据userId 和taskId,更新一条记录
     * @author openshell
     * @date 2019/4/19
     * @param [userId, taskId, releTypeBeClaim]
     * @return int
     */
    int updateReleType(@Param("userId")int userId, @Param("taskId")int taskId, @Param("releTypeBeClaim")String releTypeBeClaim);


    /**
     * 判断自己是否已认领该任务，一般用于协同制，返回0则代表没有认领
     * @author openshell
     * @date 2019/4/20
     * @param userId, taskId, releTypeSelfSelect
     * @return int
     */
    int selectIsBeSelectBySelf(@Param("userId")int userId, @Param("taskId")int taskId, @Param("releTypeSelfSelect")String releTypeSelfSelect);

    /**
     * 通过taskId 和userId唯一确定一条关联记录，并查询是否被选中(被选中返回1，否则返回0)
     * @author openshell
     * @date 2019/4/20
     * @param taskId, userId, releTypeBeSelect
     * @return int
     */
    int selectIsBeSelect(@Param("taskId") int taskId, @Param("userId") int userId);


    /**
     * 判断用户对应的任务是否已经认领，只有返回0表示没有认领
     * @author openshell
     * @date 2019/4/20
     * @param taskId, userId, releTypeBeClaim
     * @return int
     */
    int selectIsBeClaim(@Param("taskId")int taskId, @Param("userId")int userId,@Param("releTypeBeClaim") String releTypeBeClaim);
}