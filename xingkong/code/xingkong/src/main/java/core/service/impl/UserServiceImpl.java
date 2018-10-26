package core.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.mapper.UserMapper;
import core.pojo.User;
import core.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	public Integer addUser(User user) throws SQLException {
		User temp = userMapper.selectOne(user.getOpenid());
		if (temp == null) {// 不存在用户时，新增入库
			userMapper.insert(user);
			temp = userMapper.selectOne(user.getOpenid());
		} else {
			//
		}
		return temp.getUserId();
	}

	public User getUser(String userId) throws SQLException {
		if (userId == null || userId.isEmpty()) {
			return null;
		}
		return userMapper.selectOneByID(Integer.valueOf(userId));
	}

    public Integer updateUser(User user) throws SQLException {
		if(user.getUserId()==null)
			return 0;
		if(user.getIsAuth()==null)
			return 0;
		return userMapper.updateUser(user);
	}


	public int saveCellPhone(User user) {
		return userMapper.saveCellPhone(user);
	}

	public int selectPhotoCount(User user) {
		return userMapper.selectPhotoCount(user);
	}

	public Integer haveGetGc(String userId) {
		return userMapper.updateIsGetGC(Integer.valueOf(userId));
	}

	

	
}
