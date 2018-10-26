package core.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import core.pojo.Prize;

/**
 * 用户中奖业务处理层
 * @author dev-teng
 * @date 2018年6月23日
 */
public interface PrizeService {
	
	/**
	 * 获取中奖用户数目
	 * 
	 * @param valueOf
	 * @return
	 */
	int selectPrizesCount() throws SQLException;

	/**
	 * 获取中奖用户列表
	 * 
	 * @param wishPage
	 * @return
	 */
	JSONArray selectPrizesByPage(Map<String, Object> map) throws SQLException;
	
	/**
	 * 获取个人中奖纪录
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	JSONArray individualPrizes(Integer userId)throws SQLException;

	JSONArray allPrizes()throws SQLException;;

	
}
