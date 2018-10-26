package core.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import core.pojo.Sub;

/**
 * 预约Mapper层
 * 
 * @author dev-teng
 * @date 2018年6月16日
 */
public interface SubMapper {

	/**
	 * 保存用户预约信息
	 * 
	 * @param sub
	 * @return
	 * @throws SQLException
	 */
	Integer saveSub(Sub sub) throws SQLException;

	/**
	 * 获取用户预约信息
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	List<Sub> getSubs(Map<String,Object> map) throws SQLException;

}
