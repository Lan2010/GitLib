package com.tianzhixing.app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.AwardStarModule;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.dao.mapper.PhoneListMapper;
import com.tianzhixing.app.exception.BadRequestException;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.exception.ServiceException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.FeedBack;
import com.tianzhixing.app.pojo.Gene;
import com.tianzhixing.app.pojo.PhoneList;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.app.service.CommonService;
import com.tianzhixing.app.service.GeneService;
import com.tianzhixing.app.service.UserService;
import com.tianzhixing.app.service.impl.grpc.AccountStarPoint;
import com.tianzhixing.app.service.impl.grpc.AccountStarPointOperation;
import com.tianzhixing.app.util.CommonUtils;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult;

import io.swagger.annotations.ApiOperation;

/**
 * 我的模块相关接口
 * 
 * @author dev-teng
 * @date 2018年7月31日
 */
@Controller
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private CommonService commonService;
	@Resource
	private GeneService geneService;
	@Resource
	private AccountStarPoint accountStarPoint;
	@Resource
	private AccountStarPointOperation accountStarPointOperation;
	@Resource
	private PhoneListMapper phoneListMapper;

	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@ApiOperation(value="用户注册",notes = "用户注册")
	@RequestMapping(value = "/user/register", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		ResultMessage rm;
		User user = new User();
		String phone = baseRequest.getParameter("phone");// 手机号
		String agreement = baseRequest.getParameter("agreement");// 是否点击同意用户服务协议
		String password = baseRequest.getParameter("password");// 密码
		String phoneCode = baseRequest.getParameter("phoneCode");// 短信验证码
		String inviteCode = baseRequest.getParameter("inviteCode");// 邀请码
		String ip = "0.0.0.0";
		Short reg_terminal = baseRequest.getMt();// 终端类型
		user.setPhone(phone != null ? phone.trim() : "");
		user.setSmsCode(phoneCode);
		user.setPassword(password);
		user.setInvitationCode(inviteCode);
		user.setRegTerminal(reg_terminal);
		ip = CommonUtils.getIpAddr(request);
		user.setRegIp(ip);
		try {
//			rm = userService.smsValidate(phoneCode, phone, "reg");
//			if (!ReturnCode.OK.equals(rm.getCode())) {
//				return rm.toString();
//			}
			if (agreement == null || agreement.isEmpty() || Boolean.valueOf(agreement) != true) {
				rm = new ResultMessage(1, "您必须同意条款！");
				return rm.toString();
			}

			rm = userService.register(user, baseRequest);
			return rm.toString();
		} finally {
			userService.pushRegister2Nats(ip, reg_terminal, phone);
		}
	}

	/**
	 * 获取用户星星数基本信息
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/user/starInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String starInfo(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage resultMessage;
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getUserById(userId);
		if (user != null && user.getUserToken() != null) {
			com.tianzhixing.kernel.grpc.proto.account.starpoint.Result result = accountStarPoint
					.grpcGetUserInfo(user.getUserToken());
			ResponseEntity resp = result.getResponseEntity();
			if (resp != null && resp.getCode() == 200) {
				resultMessage = new ResultMessage(ReturnCode.OK, resp.getMessage());
				map.put("availableStarPoint", result.getAvailableStarPoint());
				map.put("frozenStarPoint", result.getFrozenStarPoint());
				resultMessage.put("info", map);
			} else {
				resultMessage = new ResultMessage(1, resp.getMessage());
			}
		} else {
			resultMessage = new ResultMessage(1, "系统繁忙！");
		}
		log.info("--返回数据:" + resultMessage.toString());
		return resultMessage.toString();
	}

	/**
	 * 获取用户详细信息
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/user/detailInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String detailInfo(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage resultMessage;
		JSONObject info = new JSONObject();
		User user = userService.getUserById(userId);
		if (user != null) {
			JSONObject userInfo = new JSONObject();
			userInfo.put("user_id", user.getUserId() + "");
			userInfo.put("user_nickname", user.getUserNickname());
			userInfo.put("head_url", user.getHeadUrl());
			userInfo.put("real_name", user.getRealName());
			userInfo.put("phone", user.getPhone());
			userInfo.put("open_id", user.getOpenId());
			userInfo.put("user_token", user.getUserToken());

			JSONObject all_status = userService.getStarStatus(userId);

			if (user.getUserToken() != null) {
				com.tianzhixing.kernel.grpc.proto.account.starpoint.Result result = accountStarPoint
						.grpcGetUserInfo(user.getUserToken());
				ResponseEntity resp = result.getResponseEntity();
				if (resp != null && resp.getCode() == 200) {
					userInfo.put("available", result.getAvailableStarPoint());
					userInfo.put("unavailable", result.getFrozenStarPoint());
				} else {
					userInfo.put("available", 0);
					userInfo.put("unavailable", 0);
				}
			}
			resultMessage = new ResultMessage(ReturnCode.OK, "");
			info.put("all_status", all_status);
			info.put("user_info", userInfo);
			resultMessage.put("info", info);
		} else {
			// 返回错误提示
			resultMessage = new ResultMessage(1, "未找到相应数据");
		}
		return resultMessage.toString();

	}

	/**
	 * 绑定通讯录，获得星星奖励
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/user/bindPhoneList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String bindPhoneList(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage resultMessage = null;
		Map<String, Object> map = new HashMap<String, Object>();
		short isSuccess = 0;
		try {
			Integer status = userService.getOneModule(AwardStarModule.Maillist, userId);
			if (status != null && status == 1) {// 已经绑定过通讯录
				// 提示信息
				resultMessage = new ResultMessage(1, "已认证");
			} else {
				User user = userService.getUserById(userId);
				Integer count = phoneListMapper.countPhoneList(userId);
				if (count == 0) {
					// 返回错误提示
					resultMessage = new ResultMessage(1, "认证信息为零");
				} else {
					AwardResult awardResult = accountStarPointOperation.grpcBindAddr(user.getUserToken(), count);
					ResponseEntity resp = awardResult.getResponseEntity();
					if (awardResult == null || resp == null) {
						resultMessage = new ResultMessage(1, "grpc错误");
					} else {
						if (resp.getCode() == 200) {
							map.put("starpoint", awardResult.getStarPoint());
							map.put("availableStarPoint", awardResult.getAvailableStarPoint());
							map.put("frozenStarPoint", awardResult.getFrozenStarPoint());
							resultMessage = new ResultMessage(ReturnCode.OK, "");
							resultMessage.put("info", map);
							// 添加绑定通讯录信息
							userService.addModule(userId, AwardStarModule.Maillist);
							userService.award(userId, "ADDRESS", baseRequest.getMt());
						} else {
							resultMessage = new ResultMessage(1, resp.getMessage());
						}
					}
				}
			}
			log.info("--返回数据:" + resultMessage.toString());
			if (resultMessage.getCode() == 0) {
				isSuccess = 1;

			}
			return resultMessage.toString();
		} finally {
			// nats 推送
			userService.pushUserAuth2Nats(baseRequest.getMt(), "BINDADDRESSLIST", isSuccess);
		}
	}

	/**
	 * 同步通讯录信息到数据库
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws ServiceException
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/user/synPhoneList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String synPhoneList(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, ServiceException, BadRequestException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage resultMessage = null;
		String jsonPhoneStr = baseRequest.getParameter("phoneStr");
		PhoneList phoneList = null;
		List<PhoneList> phonesAdd = new ArrayList<PhoneList>();
		JSONObject json = null;
		jsonPhoneStr = jsonPhoneStr.replace("\\", "");
		if (jsonPhoneStr == null || jsonPhoneStr.isEmpty()) {
			throw new BadRequestException(new ResultMessage(4000, "phoneStr字段为空").toString());
		}
		JSONArray jsonArr = (JSONArray) JSON.parse(jsonPhoneStr);
		String name = null;
		String phone = null;
		// List<PhoneList> queryResult = userService.getPhoneLists(userId);// 已经同步的通讯录
		Iterator<Object> iterator = jsonArr.iterator();
		for (; iterator.hasNext();) {
			json = (JSONObject) iterator.next();
			name = json.getString("name");
			phone = json.getString("phone");
			phoneList = new PhoneList();
			phoneList.setName(name);
			phoneList.setPhone(phone);
			phoneList.setUserId(userId);
			phoneList.setAddDatetime(System.currentTimeMillis() / 1000);
			phonesAdd.add(phoneList);
		}

		// TODO 此解决方案会浪费自增ID空间，需要优化
		if (phonesAdd.size() > 0) {
			int delCount = userService.delPhoneLists(userId);// 先删除所有
			int addCount = userService.addPhoneLists(phonesAdd);
			if (addCount != phonesAdd.size()) {
				resultMessage = new ResultMessage(1, "操作失败");
				throw new ServiceException(resultMessage.toString());
			} else {
				resultMessage = new ResultMessage(ReturnCode.OK, "操作成功");
			}
		}else {
			resultMessage = new ResultMessage(1, "通讯录是空的");
		}
		return resultMessage.toString();
	}

	/**
	 * 获取到用户同步后的通讯录
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 */
	@RequestMapping(value = "/user/getPhoneList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPhoneList(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		String type = baseRequest.getParameter("type");
		// TODO 分页处理
		if (type == null || type.isEmpty()) {
			type = "3";
		}
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage resultMessage = null;
		List<PhoneList> queryResult = userService.getPhoneLists(Integer.valueOf(type), userId);
		if (queryResult.size() > 0) {
			resultMessage = new ResultMessage(ReturnCode.OK, "");
			resultMessage.put("info", queryResult);
		} else {
			resultMessage = new ResultMessage(1, "数据为空");
		}
		log.info("--返回数据:" + resultMessage.toString());
		return resultMessage.toString();
	}

	/**
	 * 获取用户邀请码
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 */
	@RequestMapping(value = "/user/getInvitation", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getInvitation(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage resultMessage = null;
		User user = userService.getUserById(userId);
		Integer inviteCount = userService.getInviteCount(userId);
		Map<String, Object> info = new HashMap<String, Object>();
		if (user.getInvitationCode() == null || user.getInvitationCode().isEmpty()) {// 数据库用户表里的用户没有邀请码，则生成邀请码
			String inviteCode = CommonUtils.generateInviteCode(userId);
			info.put("code", inviteCode);
			userService.updateInviteCode(userId, inviteCode);
		} else {// 数据库用户表里的用户有邀请码，取表里面的邀请码
			info.put("code", user.getInvitationCode());
		}

		if (inviteCount == null) {
			inviteCount = 0;
		}
		resultMessage = new ResultMessage(ReturnCode.OK, "提交成功");
		info.put("count", inviteCount);
		resultMessage.put("info", info);
		return resultMessage.toString();
	}

	/**
	 * 星星图表
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/user.getWithDayList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getWithDayList(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws NumberFormatException, ParseException, SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String days = baseRequest.getParameter("days");// 第几天
		String endTime = baseRequest.getParameter("endTime");// 结束时间，意思就是 从结束时间开始 返回之前几天的数据
		if (StringUtils.isEmpty(days) || StringUtils.isEmpty(endTime)) {
			return new ResultMessage(ReturnCode.FAILED, "参数缺失").toString();
		}
		ResultMessage result = userService.getWithDayList(userId, Integer.parseInt(days), endTime);
		return result.toString();
	}

	/**
	 * 账户中心显示项目，用户实名认证、绑定银行卡等信息的列表
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/user.getUserCenterStar", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getUserCenterStar(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage result = userService.getUserCenterStar(userId);
		return result.toString();
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 */
	@RequestMapping(value = "/user/changePwd", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String changePwd(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		ResultMessage resultMessage = null;
		String oldPwd = baseRequest.getParameter("usedPwd");// 旧密码
		String newPwd = baseRequest.getParameter("newPwd");// 新密码
		String chkNewPwd = baseRequest.getParameter("chkNewPwd");// 确认密码
		Integer userId = (Integer) request.getAttribute("user_id");
		if (newPwd != null && !newPwd.equals(chkNewPwd)) {
			resultMessage = new ResultMessage(1, "确认密码不一致");
		} else if (oldPwd != null && oldPwd.equals(newPwd)) {
			resultMessage = new ResultMessage(1, "输入的新旧密码相同");
		} else {
			boolean b = userService.verifyLoginPassword(userId, oldPwd);
			if (b) {// 密码校验通过
				String msg = userService.editLoginPassword(userId, oldPwd, newPwd);
				if (msg == null) {
					resultMessage = new ResultMessage(ReturnCode.OK, "修改密码成功");
				} else {
					resultMessage = new ResultMessage(1, msg); 
				}
			} else {
				resultMessage = new ResultMessage(1, "原密码错误");
			}
		}
		return resultMessage.toString();
	}
	
	/**
	 * 取到当前用户实力奖励列表
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/user.getStarModuleList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getStarModuleList(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage result = userService.getStarModuleList(userId);
		return result.toString();
	}

	/**
	 * 找回密码
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/user/retrievePwd", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String retrievePwd(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "找回密码成功"); ;
		String phone = baseRequest.getParameter("phone");// 手机号
		String code = baseRequest.getParameter("code");// 手机短信验证码
		String newPwd = baseRequest.getParameter("newPwd");// 新密码
		String chkNewPwd = baseRequest.getParameter("chkNewPwd");// 确认密码
		Integer userId = (Integer) request.getAttribute("user_id");
		if (newPwd != null && !newPwd.equals(chkNewPwd)) {
			resultMessage = new ResultMessage(1, "确认密码不一致");
			return resultMessage.toString();
		}
		resultMessage = userService.smsValidate(code, phone, "find");
		if (!ReturnCode.OK.equals(resultMessage.getCode())) {// 验证码校验失败
			return resultMessage.toString();
		}
		User user = userService.getByPhone(phone);
		if (user == null) {
			resultMessage = new ResultMessage(1, "手机号不存在");
			return resultMessage.toString();
		}
		String result = userService.restLoginPassword(userId, newPwd, phone);
		if (result != null) {// 有错误
			resultMessage = new ResultMessage(1, result);
		} else {
			resultMessage = new ResultMessage(ReturnCode.OK, "找回密码成功");
		}
		return resultMessage.toString();
	}

	/**
	 * 上传人脸识别的图片
	 * @param request
	 * @param response
	 * @param file
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/user.upload", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file, BaseRequest baseRequest) throws SQLException, IOException {
		ResultMessage result = new ResultMessage();
		Integer userId = (Integer) request.getAttribute("user_id");
		String type = baseRequest.getParameter("type");// 上传的类型 人脸为 Face 声音为 Voice
		Short mt = baseRequest.getMt();
		if (StringUtils.isEmpty(type)) {
			result = new ResultMessage(ReturnCode.FAILED, "参数缺失");
		} else {
			result = userService.upload(request, userId, type, file, mt);
		}
		return result.toString();
	}

	/**
	 * 添加用户反馈信息
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 */
	@RequestMapping(value = "/user/addFeback", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addFeback(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		ResultMessage result = null;
		String deviceID = baseRequest.getD();
		Short terminal = baseRequest.getMt();
		String versions = baseRequest.getV();
		String febackInfo = baseRequest.getParameter("febackInfo");
		String phone = baseRequest.getParameter("phone");
		Integer userId = (Integer) request.getAttribute("user_id");
		long now = System.currentTimeMillis() / 1000;
		long dayStart = getTimesmorning().getTime() / 1000;
		long dayEnd = dayStart + 24 * 60 * 60;
		Integer times = commonService.getFebackTimes(deviceID, dayStart, dayEnd);
		if (times != null && times >= 3) {
			result = new ResultMessage(1, "每天只能反馈3次");
			return result.toString();
		}
		if (phone == null || phone.isEmpty()) {
			phone = (String) request.getAttribute("defaultPhone");
		}
		if (!CommonUtils.validatePhone(phone)) {
			result = new ResultMessage(1, "请输入正确的手机号码！");
			return result.toString();
		}

		FeedBack feedBack = new FeedBack();
		feedBack.setFeback_type(0);
		feedBack.setUser_id(userId);
		feedBack.setVersions(versions);
		feedBack.setTerminal(terminal);
		feedBack.setFeback_info(febackInfo);
		feedBack.setEquipment_id(deviceID);
		feedBack.setAdd_datetime(now);
		feedBack.setPhone(phone == null ? "" : phone);
		Integer count = commonService.addFeedBack(feedBack);
		if (count > 0) {
			result = new ResultMessage(ReturnCode.OK, "提交成功");
		} else {
			result = new ResultMessage(1, "反馈信息失败");
		}

		return result.toString();
	}

	/**
	 * 获得当天0点时间
	 * 
	 * @return
	 */
	private Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();

	}

	/**
	 * 填写用户基因数据
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/user/gene", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String gene(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		Gene gene = geneService.findGene(userId);
		ResultMessage result = null;
		if (gene != null) {
			result = new ResultMessage(1, "你已经填写过了");
		} else {
			gene = new Gene();
			gene.setName(baseRequest.getParameter("name"));
			if(baseRequest.getParameter("brithDate")!=null) {
				gene.setBirth_date(Integer.valueOf(baseRequest.getParameter("brithDate")));
			}
			gene.setNative_place(baseRequest.getParameter("nativePlace"));
			if(baseRequest.getParameter("exerciseTime")!=null) {
				gene.setExercise_time(Integer.valueOf(baseRequest.getParameter("exerciseTime")));
			}
			if(baseRequest.getParameter("sex")!=null) {
				gene.setSex(Integer.valueOf(baseRequest.getParameter("sex")));
			}
			gene.setUser_id(userId);
			gene.setAdd_datetime(System.currentTimeMillis() / 1000);
			gene.setIll(baseRequest.getParameter("ill"));
			if(baseRequest.getParameter("height")!=null) {
				gene.setHeight(Double.valueOf(baseRequest.getParameter("height")));
			}
			if(baseRequest.getParameter("weight")!=null) {
				gene.setWeight(Double.valueOf(baseRequest.getParameter("weight")));
			}

			Integer count = geneService.addGene(gene);
			Integer moduleCount = userService.addModule(userId, AwardStarModule.Gene);
			if (count > 0 && moduleCount > 0) {
				result = userService.award(userId, "GENE", baseRequest.getMt());
			} else {
				result = new ResultMessage(1, "填写失败");
			}
		}
		return result.toString();
	}

}
