package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tianzhixing.app.pojo.FileInfo;
import com.tianzhixing.app.pojo.User;

/**
 * 用户管理相关Mapper接口
 * 
 * @author dev-teng
 * @date 2018年7月30日
 */
public interface UserMapper {

	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param password
	 * @param user_status
	 * @return
	 * @throws SQLException
	 */
	User userLogin(User user) throws SQLException;

	/**
	 * 根据token获取用户信息
	 * 
	 * @param accountToken
	 * @return
	 */
	User getUserByToken(String accountToken) throws SQLException;

	/**
	 * 根据userId获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	User getUserById(Integer userId) throws SQLException;

	/**
	 * 通过手机号获取
	 * 
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	User getByPhone(String phone) throws SQLException;

	/**
	 * 添加新用户
	 * 
	 * @param user
	 * @return
	 */
	int addUser(User user) throws SQLException;

	/**
	 * 查询邀请码信息
	 * 
	 * @param inviteCode
	 * @return
	 */
	User getUserByInviter(String inviteCode);

	/**
	 * 添加邀请信息
	 * 
	 * @param inviteUserId 邀请人id
	 * @param userId       被邀请人id
	 * @param addDatetime  创建时间
	 */
	void addInviter(@Param(value = "inviterUserId") Integer inviterUserId, @Param(value = "userId") Integer userId,
			@Param(value = "addDatetime") Long addDatetime);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 */
	void delUser(Integer userId);

	/**
	 * 更新用户user_token
	 * 
	 * @param userId
	 * @param accountToken
	 */
	//void editUser(@Param(value = "userId") Integer userId, @Param(value = "accountToken") String accountToken);

	/**
	 * 添加用户的认证标识(绑定矿机)
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Integer addModule(Map<String, Object> map) throws SQLException;

	/**
	 * 更新用户的认证标识(解绑矿机)
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Integer updateModule(Map<String, Object> map) throws SQLException;

	/**
	 * 更新用户信息
	 * @param user
	 */
	Integer updateUser(User user)throws SQLException;

	//查询用户是否上传过
	Integer checkUserUpload(Map<String, Object> map)throws SQLException;

	Integer saveFile(FileInfo fileInfo)throws SQLException;

}
