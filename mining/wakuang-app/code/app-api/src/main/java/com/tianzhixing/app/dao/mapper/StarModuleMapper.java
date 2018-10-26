package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianzhixing.app.pojo.StarModule;

public interface StarModuleMapper {
	/**
	 * 获取某用户的星星奖励情况
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<StarModule> getStarModules(Integer userId) throws SQLException;

	/**
	 * 获取用户指定星星奖励模块的状态
	 * @param moduleCode
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	Integer getOneModule(@Param(value = "moduleCode") String moduleCode, @Param(value = "userId") Integer userId)
			throws SQLException;

	/**
	 * 添加星星奖励模块信息
	 * @param userId
	 * @param moduleCode
	 */
	Integer addModule(@Param(value = "userId") Integer userId, @Param(value = "moduleCode") String moduleCode)throws SQLException;

}
