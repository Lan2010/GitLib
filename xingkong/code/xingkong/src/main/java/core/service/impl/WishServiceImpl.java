package core.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.mapper.WishMapper;
import core.pojo.Wish;
import core.service.WishService;
import core.util.FileUtil;

@Service
public class WishServiceImpl implements WishService {
	@Autowired
	private WishMapper wishMapper;

	// 上传许愿音频
	public String uploadAudio(FileItem audio, String path) throws IOException {
		return FileUtil.uploadFile(audio, path);
	}

	// 数据库保存许愿信息
	public Integer saveWish(Wish wish) throws SQLException {
		return wishMapper.saveWish(wish);
	}

	// 用户愿望总数
	public int selectWishCount(Integer userId) throws SQLException {
		return wishMapper.selectWishCount(userId);
	}

	// 用户愿望列表
	public List<Wish> selectWishsByPage(Map<String, Object> wishPage) throws SQLException {
		return wishMapper.selectWishsByPage(wishPage);
	}

	public List<Wish> getWish(Integer userId, String date) throws SQLException {
		return wishMapper.getWish(userId, date);
	}

}
