package com.tianzhixing.app.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.Page;
import com.tianzhixing.app.pojo.ResultMessage;

public interface StarService {

	ResultMessage ranking()throws SQLException, GrpcException;

	ResultMessage records(Integer userId, Page page)throws SQLException, GrpcException;

	ResultMessage unCollection(Integer userId) throws SQLException, GrpcException;

	ResultMessage collection(Integer userId, String advertId, String recordToken, String taskId) throws GrpcException, SQLException;

	Boolean monitor(Integer userId);

	ResultMessage clickAd(HttpServletRequest request, Integer userId, String advertId, String starPoint, Integer adStarPointType) throws SQLException, GrpcException;

	ResultMessage getLocationStar(String code, String lat, String lng, String m) throws SQLException;

	ResultMessage award(String userToken, String type) throws GrpcException;

}
