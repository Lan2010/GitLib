package com.tianzhixing.app.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.Page;
import com.tianzhixing.app.pojo.ResultMessage;

public interface TaskService {

	/**
	 *  添加任务的星数
	 * @param taskId
	 * @param userId
	 * @throws SQLException 
	 */
	void addUserTaskPoint(String taskId, Integer userId) throws SQLException;

	/**
	 * 获取到我的已完成任务或者我的进行中的任务
	 * @param userId
	 * @param page
	 * @param parseInt
	 * @return
	 * @throws SQLException 
	 */
	ResultMessage getMyTask(Integer userId, Page page, Integer type) throws SQLException;

	/**
	 *  获取需要提醒任务列表
	 * @desc 检查服务器是否有最新的推送到数据库
	 * @param userId
	 * @param parseFloat
	 * @param parseFloat2
	 * @return
	 * @throws SQLException 
	 */
	ResultMessage getRemindAd(Integer userId, String lat, String lng) throws SQLException;

	/**
	 * 获取到当前的任务列表
	 * @desc  获取到已经推送到数据库中的任务
	 * @param userId
	 * @param page
	 * @param lat
	 * @param lng
	 * @param cityCode
	 * @return
	 * @throws SQLException 
	 */
	ResultMessage getTask(Integer userId, Page page, String lat, String lng, String cityCode) throws SQLException;

	/**
	 * 用户领取任务接口
	 * @param userId 
	 * @param orderNO
	 * @param tl_id
	 * @param mt 
	 * @return
	 * @throws SQLException 
	 * @throws GrpcException 
	 */
	ResultMessage userAcceptTask(HttpServletRequest request,Integer userId, String orderNO, String tl_id, Short mt) throws SQLException, GrpcException;

}
