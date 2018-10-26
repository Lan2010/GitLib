package com.tianzhixing.app.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianzhixing.app.common.AwardStarModule;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.UserBank;
import com.tianzhixing.app.pojo.UserRealName;
import com.tianzhixing.app.service.RealService;
import com.tianzhixing.app.service.UserService;
import com.tianzhixing.app.service.impl.grpc.IDCardAuth;
import com.tianzhixing.app.util.CommonUtils;
import com.tianzhixing.auth.grpc.proto.idcard.AuthResult;

/**
 * 用户实名、绑定银行卡相关接口
 * 
 * @author dev-teng
 * @date 2018年7月31日
 */
@Controller
public class RealController {
	private static Logger log = LoggerFactory.getLogger(RealController.class);

	@Resource
	private RealService realService;
	@Resource
	private UserService userService;
	@Resource
	private IDCardAuth iDCardAuth;

	/**
	 * 实名（身份证）认证
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/real/addIDCard", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addIDCard(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		String cardID = baseRequest.getParameter("cardID");
		String name = baseRequest.getParameter("name");
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage result = new ResultMessage();

		UserRealName userRealName = realService.getRealByCardID(cardID);
		if (userRealName != null) {
			result = new ResultMessage(1, "存在同样的实名信息");
		} else {
			AuthResult authResult = iDCardAuth.authIDCard(name, cardID);
			if (authResult != null && authResult.getCode() != 201) {
				result = new ResultMessage(1, authResult.getMessage() == null ? "身份证校验失败！" : authResult.getMessage());
			} else {
				userRealName = new UserRealName();
				userRealName.setTerminal(baseRequest.getMt());
				userRealName.setCard_id(cardID);
				userRealName.setUser_id(userId);
				userRealName.setReal_name(name);
				userRealName.setOperate_ip(CommonUtils.getIpAddr(request));
				int addResult = realService.addRealName(userRealName);
				userService.addModule(userId, AwardStarModule.Auth);
				userService.award(userId, "AUTHIDCARD", baseRequest.getMt());
				if (addResult == 1) {
					result = new ResultMessage(ReturnCode.OK, "验证成功");
				} else {
					result = new ResultMessage(ReturnCode.FAILED, "实名验证失败");
				}
			}
		}
		return result.toString();
	}

	/**
	 * 获取用户实名信息
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 */
	@RequestMapping(value = "/real/getCardInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCardInfo(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		UserRealName userRealName;
		userRealName = realService.getRealByUserID(userId);
		ResultMessage result = null;
		if (userRealName != null) {
			result = new ResultMessage(ReturnCode.OK, "");
			result.put("info", userRealName);
		} else {
			result = new ResultMessage(1, "不存在实名的信息或者审核尚未通过");
		}
		return result.toString();
	}

	/**
	 * 绑定银行卡
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException 
	 * @throws GrpcException 
	 */
	@RequestMapping(value = "/real/addBank", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addBank(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) throws SQLException, GrpcException {
		String bank_card_no = baseRequest.getParameter("bankCardNo");
		Integer userId = (Integer) request.getAttribute("user_id");
		UserBank userBank = realService.getBankByCode(bank_card_no);
		ResultMessage result =  new ResultMessage();
		if (userBank != null) {
			result = new ResultMessage(1, "存在银行卡信息");
		} else {
			userBank = new UserBank();
			userBank.setUser_id(userId);
			userBank.setTerminal(baseRequest.getMt());
			userBank.setBank_card_no(bank_card_no);
			Integer count = realService.addUserBank(userBank);
			if (count > 0) {
				userService.addModule(userId, AwardStarModule.BindBank);
				result = userService.award(userId, "BINDBANK", baseRequest.getMt());
			} else {
				result = new ResultMessage(ReturnCode.FAILED, "绑定失败");
			}
		}
		return result.toString();
	}

}
