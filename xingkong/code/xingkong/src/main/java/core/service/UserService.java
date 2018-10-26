package core.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import core.pojo.User;

/**
 * 用户管理业务处理层
 * @author dev-teng
 * @date 2018年6月13日
 */
public interface UserService {
	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	Integer addUser(User user)throws SQLException;
	
	/**
	 * 根据主键获取用户信息
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	User getUser(String userId)throws SQLException;
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	Integer updateUser(User user)throws SQLException;
	

	/**
	 * 保存用户手机号码
	 * @param user
	 * @return
	 */
	int saveCellPhone(User user);
	
	/**
	 * 更新用户‘已领取绿卡’状态
	 * @param userId 
	 * @return
	 */
	Integer haveGetGc(String userId);

	
	
}
