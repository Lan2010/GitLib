package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianzhixing.app.pojo.AwardStarType;

public interface AwardStarTypeMapper {
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<AwardStarType> getAwardStarType(@Param("is_system")Integer is_system) throws SQLException;

	/**
	 * 
	 * @param type_code
	 * @return
	 */
	AwardStarType getOneModuleByCode(String type_code) throws SQLException;

	/**
	 * 所有分值
	 * 
	 * @return
	 * @throws SQLException
	 */
	Integer querySum() throws SQLException;

	Integer queryUserSum(Integer userId) throws SQLException;
}
