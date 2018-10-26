package core.mapper;

import java.sql.SQLException;

import core.pojo.GreenCard;

/**
 * 绿卡管理层
 * @author dev-lan
 * @date 2018年6月13日
 *
 */
public interface GreencardMapper {
	
	/**
	 * 数据库保存绿卡信息
	 * @param photo
	 * @return
	 */
	Integer saveGreenCard(GreenCard gc)throws SQLException;

	/**
	 * 获取用户绿卡信息
	 * @param userId
	 * @return
	 */
	GreenCard getGreenCardById(Integer userId)throws SQLException;
	
	/**
	 * 获取最新的绿卡信息
	 * @param userId
	 * @return
	 */
	GreenCard getNewgreencard()throws SQLException;
	
	/**
	 * 绿卡总数
	 * @return
	 */
	int selectGCCount()throws SQLException;

	/**
	 * 根据gcID获取绿卡
	 * @param gcId
	 * @return
	 */
	GreenCard getGCByGCId(int gcId);

}
