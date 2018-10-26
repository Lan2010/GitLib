package com.tianzhixing.app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tianzhixing.app.exception.BadRequestException;
import com.tianzhixing.app.exception.DESECBException;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.exception.ServiceException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.BaseResponse;
import com.tianzhixing.app.pojo.ResultMessage;

/**
 * 接口访问转发入口，根据接口的service参数值匹配不同的控制层接口，实现请求转发（兼容原PHP程序）
 * 
 * @author dev-teng
 * @date 2018年7月31日
 */
@Controller
public class MainController {
	@Resource
	private LoginController loginController;
	@Resource
	private StarController starController;
	@Resource
	private TaskController taskController;
	@Resource
	private ChargeController ChargeController;
	@Resource
	private UserController userController;
	@Resource
	private CommonController commonController;
	@Resource
	private AdController adController;
	@Resource
	private RealController realController;
	@Resource
	private SmsController smsController;

	/**
	 * 所有控制层接口的入口:获取service参数，再通过参数转发到对应控制层方法，若参数加密，则先进行解密
	 *
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @param service
	 * @param file
	 * @throws SQLException
	 * @throws GrpcException
	 * @throws IOException
	 * @throws BadRequestException
	 * @throws ServiceException
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/MobileApi", produces = "application/json; charset=utf-8")
	@ResponseBody
	public void mobileApi(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest,
			@RequestParam String service, @RequestParam(name = "file", required = false) CommonsMultipartFile file)
			throws SQLException, GrpcException, IOException, BadRequestException, ServiceException,
			NumberFormatException, ParseException {
		BaseResponse bresponse = null;
		String data = null;//
		if (inspectionSign(baseRequest)) {// 验签通过
			try {
				baseRequest = baseRequest.decryptData();// 解密data，解密后为json格式字符串
				data = dispatch(request, response, baseRequest, service, file);// 异常直接抛出
				bresponse = new BaseResponse(200, data, "", null, null);
				bresponse.output(response);
			} catch (DESECBException e) {
				// bresponse = new BaseResponse(4010, "", "非法请求", null, null);
				throw new BadRequestException(new ResultMessage(4010, "非法请求").toString());
			}
		} else {// 验签不通过
			// bresponse = new BaseResponse(4007, "", "签名不正确", null, null);
			throw new BadRequestException(new ResultMessage(4007, "签名不正确").toString());
		}

	}

	private String dispatch(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest,
			String service, CommonsMultipartFile file) throws SQLException, GrpcException, ServiceException,
			BadRequestException, NumberFormatException, ParseException, IOException {
		String data = null;
		if (("user.login").equals(service.toLowerCase())) {
			data = loginController.login(request, response, baseRequest);// 用户普通方式登录
		} else if ("banner.getstartlist".equals(service.toLowerCase())) {
			data = adController.startAdList(request, response, baseRequest);
		} else if ("user.smslogin".equals(service.toLowerCase())) {
			data = loginController.smsLogin(request, response, baseRequest);
		} else if ("user.loginout".equals(service.toLowerCase())) {
			data = loginController.logout(request, response, baseRequest);
		}

		else if ("account.ranking".equals(service.toLowerCase())) {
			data = starController.ranking(response, baseRequest);
		} else if ("account.records".equals(service.toLowerCase())) {
			data = starController.records(request, response, baseRequest);
		} else if ("account.uncollection".equals(service.toLowerCase())) {
			data = starController.unCollection(request, response, baseRequest);
		} else if ("account.collection".equals(service.toLowerCase())) {
			data = starController.collection(request, response, baseRequest);
		} else if ("account.clickad".equals(service.toLowerCase())) {
			data = starController.clickAd(request, response, baseRequest);
		} else if ("ad.getlocationstar".equals(service.toLowerCase())) {
			data = starController.getLocationStar(request, response, baseRequest);
		}

		else if ("task.getmytask".equals(service.toLowerCase())) {
			data = taskController.getMyTask(request, response, baseRequest);
		} else if ("task.getremindad".equals(service.toLowerCase())) {
			data = taskController.getRemindAd(request, response, baseRequest);
		} else if ("task.gettask".equals(service.toLowerCase())) {
			data = taskController.getTask(request, response, baseRequest);
		} else if ("task.useraccepttask".equals(service.toLowerCase())) {
			data = taskController.userAcceptTask(request, response, baseRequest);
		}

		else if ("charge.bind".equals(service.toLowerCase())) {
			data = ChargeController.bind(request, response, baseRequest);
		} else if ("charge.unbindcharge".equals(service.toLowerCase())) {
			data = ChargeController.unbindCharge(request, response, baseRequest);
		} else if ("charge.chargelist".equals(service.toLowerCase())) {
			data = ChargeController.chargeList(request, response, baseRequest);
		}

		else if ("common.commonurl".equals(service.toLowerCase())) {
			data = commonController.commonUrl(request, response, baseRequest);
		} else if ("common.appstopstar".equals(service.toLowerCase())) {
			data = commonController.appStopStar(request, response, baseRequest);
		} else if ("common.timestamp".equals(service.toLowerCase())) {
			data = commonController.timestamp(request, response, baseRequest);
		} else if ("common.versioninfo".equals(service.toLowerCase())) {
			data = commonController.versionInfo(request, response, baseRequest);
		} else if ("common.suspensionframe".equals(service.toLowerCase())) {
			data = commonController.suspensionFrame(request, response, baseRequest);
		} else if ("common.suspensionframe".equals(service.toLowerCase())) {
			data = commonController.suspensionFrame(request, response, baseRequest);
		} else if ("user.getappinfo".equals(service.toLowerCase())) {
			data = commonController.addAppInfo(request, response, baseRequest);
		}

		else if ("real.cardreal".equals(service.toLowerCase())) {
			data = realController.addIDCard(request, response, baseRequest);
		} else if ("real.getcardinfo".equals(service.toLowerCase())) {
			data = realController.getCardInfo(request, response, baseRequest);
		} else if ("real.addbank".equals(service.toLowerCase())) {
			data = realController.addBank(request, response, baseRequest);
		}

		else if ("sms.loginphonecode".equals(service.toLowerCase())) {
			data = smsController.loginPhoneCode(request, response, baseRequest);
		} else if ("sms.regcode".equals(service.toLowerCase())) {
			data = smsController.regCode(request, response, baseRequest);
		} else if ("sms.findcode".equals(service.toLowerCase())) {
			data = smsController.getBackPwd(request, response, baseRequest);
		}

		else if ("account.info".equals(service.toLowerCase())) {
			data = userController.starInfo(request, response, baseRequest);
		} else if ("user.register".equals(service.toLowerCase())) {
			data = userController.register(request, response, baseRequest);
		} else if ("user.info".equals(service.toLowerCase())) {
			data = userController.detailInfo(request, response, baseRequest);
		} else if ("user.bindmaillist".equals(service.toLowerCase())) {
			data = userController.bindPhoneList(request, response, baseRequest);
		} else if ("user.handldmaillist".equals(service.toLowerCase())) {
			data = userController.synPhoneList(request, response, baseRequest);
		} else if ("user.getmaillistresinfo".equals(service.toLowerCase())) {
			data = userController.getPhoneList(request, response, baseRequest);
		} else if ("user.getinvitation".equals(service.toLowerCase())) {
			data = userController.getInvitation(request, response, baseRequest);
		} else if ("user.changepwd".equals(service.toLowerCase())) {
			data = userController.changePwd(request, response, baseRequest);
		} else if ("user.findpwd".equals(service.toLowerCase())) {
			data = userController.retrievePwd(request, response, baseRequest);
		} else if ("user.addfeback".equals(service.toLowerCase())) {
			data = userController.addFeback(request, response, baseRequest);
		} else if ("user.gene".equals(service.toLowerCase())) {
			data = userController.gene(request, response, baseRequest);
		} else if ("user.getwithdaylist".equals(service.toLowerCase())) {
			data = userController.getWithDayList(request, response, baseRequest);
		} else if ("user.getusercenterstar".equals(service.toLowerCase())) {
			data = userController.getUserCenterStar(request, response, baseRequest);
		} else if ("user.getstarmodulelist".equals(service.toLowerCase())) {
			data = userController.getStarModuleList(request, response, baseRequest);
		} else if ("user.upload".equals(service.toLowerCase())) {
			data = userController.upload(request, response, file, baseRequest);
		}
		return data;
	}

	@RequestMapping(value = "/mobileApi", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void _mobileApi(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest,
			@RequestParam String service, @RequestParam(name = "file", required = false) CommonsMultipartFile file)
			throws DESECBException, SQLException, GrpcException, IOException, BadRequestException, ServiceException,
			NumberFormatException, ParseException {
		mobileApi(request, response, baseRequest, service, file);
	}

	private boolean inspectionSign(BaseRequest baseRequest) {
		// BaseRequest baseRequest = new BaseRequest();
		// baseRequest.setD(json.getString("d"));
		// baseRequest.setMt(json.getShort("mt"));
		// baseRequest.setS(json.getString("s"));//签名
		//
		// baseRequest.setSv(json.getString("sv"));
		// baseRequest.setT(json.getLong("t"));
		// baseRequest.setV(json.getString("v"));
		// String data = json.getString("data");
		// data = EncryptUtil.decrypt3DESECB(data, Constant.API_3DES_KEY);//解密
		// baseRequest.setData(data);
		// 解析json字符串格式类型参数
		// baseRequest = JSON.parseObject(json,BaseRequest.class);

		// TODO 验签
		// String signString = baseRequest.toSignString();
		return true;
	}

}
