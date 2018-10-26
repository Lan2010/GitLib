package core.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import core.pojo.Dest;
import core.pojo.GreenCard;
import core.pojo.PostCard;
import core.pojo.User;


public interface CardService {

	GreenCard getGreenCardById(String userId)throws SQLException;

	int selectDestCount()throws SQLException;

	List<Dest> selectDestsByPage(Map<String, Object> destPage)throws SQLException;

	Dest getDestById(int destId)throws SQLException;

	int selectUserPCCount(Integer userId)throws SQLException;

	List<PostCard> selectPCsByPage(Map<String, Object> pcPage)throws SQLException;

	PostCard getPostcardById(int pcId)throws SQLException;

	int delPostcard(Integer pcId)throws SQLException;

	Integer saveGreenCard(GreenCard gc) throws SQLException;
	
	int selectGCCount()throws SQLException;

	GreenCard getGCByGCId(int gcId)throws SQLException;

	int updatePCTitle(PostCard pc) throws SQLException;
	
	void publish4oms(User user, PostCard pc,Integer operationType);

	void publish4oms(User user, GreenCard gc, Integer operationType);

}
