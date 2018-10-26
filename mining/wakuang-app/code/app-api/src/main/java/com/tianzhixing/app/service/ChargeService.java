package com.tianzhixing.app.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.ResultMessage;

public interface ChargeService {

	ResultMessage bind(HttpServletRequest request,Integer userId, String mac, Short mt) throws SQLException, GrpcException ;

	ResultMessage unbindCharge(Integer userId, String mac) throws SQLException;

	ResultMessage chargeList(Integer userId) throws SQLException;

}
