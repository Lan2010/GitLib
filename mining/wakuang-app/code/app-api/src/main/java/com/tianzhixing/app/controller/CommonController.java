package com.tianzhixing.app.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.pojo.AppInfo;
import com.tianzhixing.app.pojo.AppVersions;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.service.CommonService;
import com.tianzhixing.app.util.CommonUtils;

/**
 * 通用接口
 * @author dev-teng
 * @date 2018年8月29日
 */
@Controller
public class CommonController {
	private static Logger log = LoggerFactory.getLogger(CommonController.class);

	@Resource
	private CommonService commonService;

	/**
	 * 获取悬浮窗数据
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 */
	@RequestMapping(value = "/common/suspensionframe", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String suspensionFrame(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		ResultMessage result = new ResultMessage();
		result = commonService.getSuspensionFrame();
		return result.toString();
	}

	/**
	 * “用户协议”、“数字生命”、“基因数据”、“关于我们”等的url
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 */
	@RequestMapping(value = "/common/commonUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String commonUrl(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) {
		ResultMessage result = new ResultMessage(ReturnCode.OK, "操作成功");
		JSONArray info = new JSONArray();
		JSONObject link = null;

		// TODO 需优化，连接放入数据库
		link = new JSONObject();
		link.put("url", request.getScheme() + "://" + request.getServerName() + "/wechat/web/gengc");// 连接URL
		link.put("desc", "基因数据");// 描述
		link.put("state", "GeneUrl");//
		info.add(link);

		link = new JSONObject();
		link.put("url", request.getScheme() + "://" + request.getServerName() + "/wechat/web/getServiceAgreement");// 连接URL
		link.put("desc", "用户服务协议");// 描述
		link.put("state", "RegisterUrl");//
		info.add(link);

		link = new JSONObject();
		link.put("url", "www.baidu.com");// 连接URL
		link.put("desc", "关于我们");// 描述
		link.put("state", "AboutUrl");//
		info.add(link);

		link = new JSONObject();
		link.put("url", request.getScheme() + "://" + request.getServerName() + "/wechat/web/gengc");// 连接URL
		link.put("desc", "数字生命");// 描述
		link.put("state", "DigitalUrl");//
		info.add(link);

		link = new JSONObject();
		link.put("url", "www.baidu.com");// 连接URL
		link.put("desc", "星链攻略");// 描述
		link.put("state", "StrategyUrl");//
		info.add(link);
		result.put("info", info);
		return result.toString();
	}

	/**
	 * 跳转到挖矿攻略页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wechat/web/web", produces = "text/html; charset=utf-8")
	public String strategy(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/view/common/strategy.html";
	}
	
	/**
	 * 跳转到基因数据页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wechat/web/gengc", produces = "text/html; charset=utf-8")
	public String geneData(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/view/gene/index.html";
	}

	/**
	 * 跳转到用户协议页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wechat/web/getServiceAgreement", produces = "text/html; charset=utf-8")
	public String useProtocol(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/view/common/protocol.html";
	}

	/**
	 * APP启动关闭
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/common/appStopStar", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String appStopStar(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) {
		String ip = CommonUtils.getIpAddr(request);
		String phone = baseRequest.getParameter("mobile");
		String operationType = baseRequest.getParameter("type");//1开机 0 关机
		commonService.pushAppOnOff2Nats(ip, baseRequest.getMt(), phone, operationType);//推送消息到nats
		return new ResultMessage(ReturnCode.OK, "").toString();
	}

	/**
	 * 获取当前服务器系统时间
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 */
	@RequestMapping(value = "/common/timestamp", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String timestamp(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) {
		ResultMessage result = new ResultMessage(ReturnCode.OK, "操作成功");
		JSONObject json = new JSONObject();
		json.put("timestamp", System.currentTimeMillis());
		result.put("info", json);
		return result.toString();
	}

	/**
	 * 获取最新APP版本信息，用于APP更新
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 */
	@RequestMapping(value = "/common/versionInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String versionInfo(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) {
		Short terminal = baseRequest.getMt();
		ResultMessage result = new ResultMessage(1, "操作失败");
		if (terminal == 2 || terminal == 3) {
			AppVersions appVersions = commonService.getAppVersions(terminal);
			if (appVersions != null) {
				JSONObject json = new JSONObject();
				json.put("version_name", appVersions.getVersion_name());// 版本名称（IOS版本号/安卓版本名称）
				json.put("version_code", appVersions.getVersion_code());// 版本号
				json.put("update_desc", appVersions.getUpdate_desc());// 更新说明
				json.put("is_forced", appVersions.getIs_forced());// 是否强制更新（0：不强制 1：强制）
				json.put("app_size", appVersions.getApp_size());// APP包大小（单位MB）
				if (appVersions.getTerminal() != null && appVersions.getTerminal() == 2) {
					json.put("update_url", appVersions.getUpdate_url());// 更新地址（用于安卓，IOS不返回该字段）
					json.put("codes", commonService.getCheckCodes());// MD5集合（用于安卓，IOS不返回该字段）
				}
				result = new ResultMessage(ReturnCode.OK, "操作成功");
				result.put("info", json);
			}
		}
		return result.toString();
	}

	/**
	 * 获取用户的App基础信息
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/common/addAppInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addAppInfo(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) throws SQLException {
		 String equipment_id = baseRequest.getD(); //设备ID
	     String versions = baseRequest.getV(); //客户端APP的版本号
	     Short terminal = baseRequest.getMt(); //终端
	     String jph_reg_id = baseRequest.getParameter("jphId");//极光推送ID
	     Short type = (baseRequest.getParameter("type")!=null)?Short.valueOf(baseRequest.getParameter("type")):null; //第一次打开传值为1 其他打开传值为2
	     String phone_info =  baseRequest.getParameter("phoneInfo"); //手机信息 手机的系统 机型信息
	     String latitude = baseRequest.getParameter("latitude"); //纬度
	     String longitude =  baseRequest.getParameter("longitude"); //经度
	     String province =  baseRequest.getParameter("province"); //省份
	     String city =  baseRequest.getParameter("city"); //城市
	     String area = baseRequest.getParameter("area"); //区
	     String place =  baseRequest.getParameter("place"); //地址详情
	     String qdKey =  baseRequest.getParameter("qdKey"); //地址详情
	     ResultMessage resultMessage;
	     String appKey="";
	     String appChannel = "";
	     if(terminal==2) {
	    	 if(Constant.APP_ADDR.containsKey(qdKey)) {
	    		 appKey= qdKey;
	    		 appChannel=Constant.APP_ADDR.get(qdKey);
	    	 }else {
	    		 appKey="defChannel";
	    		 appChannel="未知渠道";
	    	 }
	     }else {
	    	 if(Constant.IOSAPP_ADDR.containsKey(qdKey)) {
	    		 appKey= qdKey;
	    		 appChannel=Constant.IOSAPP_ADDR.get(qdKey);
	    	 }else {
	    		 appKey="appShop";
	    		 appChannel="苹果商店";
	    	 }
	     }
	     
	     AppInfo appInfo = new AppInfo();
	     appInfo.setEquipment_id(equipment_id);
	     appInfo.setVersions(versions);
	     appInfo.setTerminal(terminal);
	     appInfo.setJph_reg_id(jph_reg_id);
	     appInfo.setType(type);
	     appInfo.setPhone_info(phone_info);
	     appInfo.setLatitude(latitude);
	     appInfo.setLongitude(longitude);
	     appInfo.setProvince(province);
	     appInfo.setCity(city);
	     appInfo.setArea(area);
	     appInfo.setPlace(place);
	     appInfo.setChan_key(appKey);
	     appInfo.setChannel(appChannel);
	     appInfo.setAdd_datetime(System.currentTimeMillis()/1000);
	     int result = commonService.addAppInfo(appInfo);
	     if(result>0) {
	    	resultMessage = new ResultMessage(ReturnCode.OK, "成功");
	     }else {
	    	resultMessage = new ResultMessage(ReturnCode.FAILED, "失败");
	     }
	     return resultMessage.toString();
	}
	
}
