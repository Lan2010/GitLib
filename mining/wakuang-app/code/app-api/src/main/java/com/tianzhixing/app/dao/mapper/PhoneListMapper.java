package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianzhixing.app.pojo.PhoneList;

public interface PhoneListMapper {
	/**
	 * 获得用户通讯录的注册情况,registerType为1注册过，为2未注册过,为3全部
	 * @throws SQLException
	 */
	List<PhoneList> getPhoneLists(@Param(value = "registerType") Integer registerType,@Param(value = "userId")Integer userId)throws SQLException;
	
	/**
	 * 获取某用户通讯录信息的绑定条数
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	Integer countPhoneList(Integer userId)throws SQLException;

	/**
	 * 批量增加通讯录
	 * @param phonesAdd
	 * @return
	 */
	int addPhoneLists(List<PhoneList> phonesAdd)throws SQLException;

	/**
	 * 批量更新通讯录
	 * @param phonesUpdate
	 * @return
	 */
	int updatePhoneLists(List<PhoneList> phonesUpdate);

	/**
	 * 删除用户通讯录
	 * @param userId
	 * @return
	 */
	int delPhoneLists(Integer userId);
	
}
