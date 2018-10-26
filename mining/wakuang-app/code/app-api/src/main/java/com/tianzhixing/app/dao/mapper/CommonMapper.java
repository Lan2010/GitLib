package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.List;

import com.tianzhixing.app.pojo.SuspensionFrame;

public interface CommonMapper {

	/**
	 * 悬浮窗数据获取
	 * @return
	 * @throws SQLException
	 */
	SuspensionFrame getSuspensionFrame() throws SQLException;

}
