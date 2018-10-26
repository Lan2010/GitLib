package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tianzhixing.app.pojo.Task;

public interface TaskMapper {

	/**
	 * 根据order_no获取task_id
	 * 
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	Integer getTaskId(String order) throws SQLException;

	/**
	 * getStarPoint
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	String getStarPoint(Map<String, Object> map) throws SQLException;

	/**
	 * 添加任务的星数
	 * 
	 * @param map01
	 * @return
	 */
	Integer saveStarPoint(Map<String, Object> map) throws SQLException;

	/**
	 * 获取我的任务列表
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	List<Task> getMyTaskList(Map<String, Object> map) throws SQLException;

	/**
	 * 我的任务总数
	 * 
	 * @param map
	 * @return
	 */
	Integer getMyTaskCount(Map<String, Object> map) throws SQLException;

	/**
	 * 获取到提醒的任务
	 * 
	 * @param task
	 * @param task
	 * @return
	 */
	List<Task> getRemind(Task task) throws SQLException;

	/**
	 * 获取到当前的任务列表总数
	 * 
	 * @param map
	 * @return
	 */
	Integer getTaskCount(Map<String, Object> map) throws SQLException;

	/**
	 * 获取到当前的任务列表
	 * 
	 * @param map
	 * @return
	 */
	List<Task> getTaskList(Map<String, Object> map) throws SQLException;

	/**
	 * 根据orderNO获取任务
	 * 
	 * @param orderNO
	 * @return
	 * @throws SQLException
	 */
	Task getTask(String orderNO) throws SQLException;

	/**
	 * 获取到用户的领取列表
	 * 
	 * @param userId
	 * @param task_id
	 * @return
	 */
	List<Task> getUserTaskList(Task task) throws SQLException;

	/**
	 * 添加用户领取任务表
	 * @param map
	 * @return
	 */
	int addUserTask(Map<String, Object> map)throws SQLException;


}
