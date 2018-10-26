package core.mapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import core.pojo.Wish;

public interface WishMapper {

	/**
	 * 保存许愿信息
	 * 
	 * @param wish
	 * @return
	 */
	Integer saveWish(Wish wish) throws SQLException;

	/**
	 * 用户愿望总数
	 * 
	 * @param userId
	 * @return
	 */
	int selectWishCount(Integer userId) throws SQLException;

	/**
	 * 用户愿望列表
	 * 
	 * @param wishPage
	 * @return
	 */
	List<Wish> selectWishsByPage(Map<String, Object> wishPage) throws SQLException;

	/**
	 * 获取许愿信息
	 * 
	 * @param userId
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<Wish> getWish(@Param("userId") Integer userId, @Param("date") String date) throws SQLException;

}
