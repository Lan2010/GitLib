package core.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import core.pojo.Prize;

/**
 * 中奖mapper
 * 
 * @author dev-teng
 * @date 2018年6月23日
 */
public interface PrizeMapper {

	/**
	 * 获取中奖用户数目
	 * @return
	 */
	int selectPrizesCount() throws SQLException;

	/**
	 * 获取中奖用户列表
	 * 
	 * @param map
	 * @return
	 */
	List<Prize> selectPrizesByPage(Map<String, Object> map) throws SQLException;
	
	/**
	 * 获取个人中奖纪录
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	List<Prize> individualPrizes(Integer userId)throws SQLException;

	/**
	 * 获取所有中奖纪录
	 * @return
	 */
	List<Prize> allPrizes();

}
