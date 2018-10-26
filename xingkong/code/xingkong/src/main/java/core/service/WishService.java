package core.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import core.pojo.Wish;

/**
 * 用户许愿业务处理层
 * @author dev-lan
 * @date 2018年6月16日
 */
public interface WishService {

	String uploadAudio(FileItem audio, String path) throws IOException;

	Integer saveWish(Wish wish)throws SQLException;

	int selectWishCount(Integer userId)throws SQLException;

	List<Wish> selectWishsByPage(Map<String, Object> wishPage)throws SQLException;
	/**
	 * 获取许愿信息
	 * @param userId
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<Wish> getWish(Integer userId,String date)throws SQLException;
}
