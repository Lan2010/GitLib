package core.mapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import core.pojo.Dest;
import core.pojo.PostCard;

/**
 * 明信片管理层
 * @author dev-lan
 *
 */
public interface PostcardMapper {

	//明信片景点总数
	int selectDestCount()throws SQLException;

	//查询明信片景点列表
	List<Dest> selectDestsByPage(Map<String, Object> destPage)throws SQLException;

	//通过ID获取某个景点信息
	Dest getDestById(int dest_id)throws SQLException;

	//上传明信片信息
	Integer savePostCard(PostCard pc)throws SQLException;
	
	//用户明信片总数
	int selectUserPCCount(Integer userId)throws SQLException;

	//分页查询用户明信片列表
	List<PostCard> selectPCsByPage(Map<String, Object> pcPage)throws SQLException;

	//获取用户某个明信片
	PostCard getPostcardById(int pcId)throws SQLException;
	
	//删除明信片
	int delPostcard(Integer pcId)throws SQLException;

	List<Date> getPCDateByUserId(Map<String, Object> pcPage)throws SQLException;

	//修改明信片标题
	int updatePCTitle(PostCard pc)throws SQLException;

}
