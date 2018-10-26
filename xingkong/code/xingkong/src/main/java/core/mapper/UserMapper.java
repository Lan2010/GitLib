package core.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import core.pojo.User;

/**
 * 用户管理Mapper层
 * @author dev-teng
 * @date 2018年6月12日
 */
public interface UserMapper {

	/**
	 * 根据openid查询用户信息
	 * @param openid
	 * @return
	 */
	User selectOne(String openid)throws SQLException;;

	/**
	 * 新增用户信息
	 * @param user
	 */
	void insert(User user)throws SQLException;;
	
	/**
	 * 根据主键查询用户信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	User selectOneByID(Integer userId)throws SQLException;



	/**
	 * 更新用户审核状态
	 * @param isAuth
	 */
	Integer updateUser(User user);
	
	/**
	 * 保存用户手机号码
	 * @param user
	 * @return
	 */
	Integer saveCellPhone(User user);

	/**
	 * 获取用户照片总数
	 * @param user
	 * @return
	 */
	Integer selectPhotoCount(User user);
	
	/**
	 * 更新用户已领取绿卡状态
	 * @param userId
	 * @return
	 */
	Integer updateIsGetGC(Integer userId);

	
}
