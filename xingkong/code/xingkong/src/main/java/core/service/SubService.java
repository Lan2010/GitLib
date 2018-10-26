package core.service;

import java.sql.SQLException;
import java.util.List;

import core.pojo.Sub;

public interface SubService {
	/**
	 * 保存用户预约信息
	 * 
	 * @param sub
	 * @return
	 * @throws SQLException
	 */
	int saveSub(Sub sub) throws SQLException;

	/**
	 * 获取用户预约信息
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	List<Sub> getSubs(Integer userId,String date) throws SQLException;
	
	/**
	 * 抽奖决定预约成功还是失败,成功返回true,否则false
	 * @return
	 */
	boolean prizeSub();
}
