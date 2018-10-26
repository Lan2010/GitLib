package com.tianzhixing.app.service.impl.ex;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.exception.BadRequestException;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.exception.ServiceException;
import com.tianzhixing.app.pojo.BaseResponse;
import com.tianzhixing.app.pojo.ResultMessage;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	private static Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		BaseResponse bresp = new BaseResponse();
		if (ex instanceof GrpcException) {// 服务器异常
			ResultMessage result = new ResultMessage(ReturnCode.FAILED, "Grpc服务器异常");
			bresp.setData(result.toString());
			bresp.setRet(200);
			bresp.setError("服务器运行错误：" + ex.getMessage());
		} else if (ex instanceof SQLException) {
			ResultMessage result = new ResultMessage(ReturnCode.FAILED, "服务器异常");
			bresp.setData(result.toString());
			bresp.setRet(200);
			bresp.setError("服务器运行错误：SQLException.");
		} else if (ex instanceof IOException) {
			ResultMessage result = new ResultMessage(ReturnCode.FAILED, "服务器异常");
			bresp.setData(result.toString());
			bresp.setRet(200);
			bresp.setError("服务器运行错误：IOException.");
		} else if (ex instanceof NumberFormatException) {
			ResultMessage result = new ResultMessage(ReturnCode.FAILED, "服务器异常");
			bresp.setData(result.toString());
			bresp.setRet(200);
			bresp.setError("服务器运行错误：NumberFormatException.");
		} else if (ex instanceof ParseException) {
			ResultMessage result = new ResultMessage(ReturnCode.FAILED, "服务器异常");
			bresp.setData(result.toString());
			bresp.setRet(200);
			bresp.setError("服务器运行错误：ParseException.");
		} else if (ex instanceof ServiceException) {// 业务逻辑异常
			if (ex.getMessage() != null) {
				JSONObject json = JSON.parseObject(ex.getMessage());
				bresp.setRet(200);
				bresp.setError("业务异常：" + json.getString("msg"));
				bresp.setData(json.toJSONString());
			} else {
				bresp.setRet(200);
				bresp.setError("业务异常");
			}
		} else if (ex instanceof BadRequestException) {// 请求发生错误的异常类型
			if (ex.getMessage() != null) {
				JSONObject json = JSON.parseObject(ex.getMessage());
				bresp.setRet(json.getInteger("code"));
				bresp.setError("请求异常:" + json.getString("msg"));
			} else {
				bresp.setRet(400);
				bresp.setError("请求异常");
			}

		} else {
			ResultMessage result = new ResultMessage(ReturnCode.FAILED, "服务器异常");
			bresp.setData(result.toString());
			bresp.setRet(200);
			bresp.setError("未知错误：" + ex.getMessage());
		}
		log.error("Exception.", ex);// 将异常记录到日志
		try {
			bresp.output(response);
		} catch (IOException e) {
			log.error("IOException.", ex);
		}
		return new ModelAndView();

	}

}
