package core.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import core.common.Constant;
import core.common.StatusCode;
import core.pojo.ResultMessage;
import core.pojo.User;
import core.pojo.Wish;
import core.service.UserService;
import core.util.EncryptUtil;
import core.util.FileUtil;
import core.util.HttpUtils;
import wechat.token.TokenBuilder;

@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	public static final String WX_ACODE_URL = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=";

	@RequestMapping(value = "/api/user/getUser", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getUser(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		JSONObject data = new JSONObject();
		try {
			User user = userService.getUser(userId);
			if (user == null) {
				data.put("isAuth", "");
				data.put("avatarUrl", "");
				data.put("nickName", "");
				data.put("isGetGC", "");
				return new ResultMessage(StatusCode.OK, "请求成功", data).toString();
			}
			data.put("isAuth", user.getIsAuth());
			data.put("avatarUrl", user.getAvatarUrl());
			data.put("nickName", user.getNickName());
			data.put("isGetGC", user.getIsGetGC());
			return new ResultMessage(StatusCode.OK, "请求成功", data).toString();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~", data).toString();
		}

	}

	@RequestMapping(value = "/api/user/updateUser", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(HttpServletRequest request,User user) {
		String userId = (String) request.getAttribute("userId");
		try {
			String path = request.getServletContext().getRealPath("/") + Constant.AVATAR_LOCAL_PATH ;// 临时测试用路径
			String fileName="";
			if(user.getAvatarUrl()!=null && !user.getAvatarUrl().trim().isEmpty()) {
				fileName = FileUtil.saveToFile(user.getAvatarUrl(), path);
				user.setAvatarLocal(File.separator+Constant.AVATAR_LOCAL_PATH+fileName);//相对路径
			}
			user.setUserId(Integer.valueOf(userId));
			Integer result = userService.updateUser(user);
			if (result > 0) {
				return new ResultMessage(StatusCode.OK, "请求成功").toString();
			} else {
				return new ResultMessage(StatusCode.ERROR, "更新失败").toString();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	@RequestMapping(value = "/api/user/saveCellPhone", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveCellPhone(HttpServletRequest request, HttpServletResponse response, String sessionKey,
			String ivData, String encrypData) throws Exception {
		Integer userId = (Integer) request.getAttribute("userId");
		User user = new User();
		user.setUserId(userId);

		String cellPhone = EncryptUtil.wxDecrypt(sessionKey, ivData, encrypData);
		if (cellPhone != null && !cellPhone.equals("")) {
			// 设置用户手机号码
			user.setCellPhone(cellPhone);
		} else {
			// 未获取到用户手机号码

		}
		int result = userService.saveCellPhone(user);

		return null;

	}

	@RequestMapping(value = "/api/user/isGetGC", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String isGetGC(HttpServletRequest request, HttpServletResponse response) {
		JSONObject data = new JSONObject();
		String userId = (String) request.getAttribute("userId");
		try {
			User user = userService.getUser(userId);
			if (user != null) {
				data.put("isGetGC", user.getIsGetGC());
				return new ResultMessage(StatusCode.OK, "请求成功", data).toString();
			} else {
				logger.error(JSON.toJSONString(data));
				return new ResultMessage(StatusCode.NOT_LOGIN, "请求失败", data).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	@RequestMapping(value = "/api/user/getwxacode", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getwxacode(HttpServletRequest request, HttpServletResponse response, String scene) {
		String ACCESS_TOKEN = TokenBuilder.getInstance().defaultConfig().getToken();
		JSONObject json = new JSONObject();
		json.put("path", "pages/index/index");
		json.put("width", 430);
		String url = WX_ACODE_URL + ACCESS_TOKEN;
		byte[] result = HttpUtils.wxhttpPostJSON(url, json.toJSONString());
		String wxacodePath = "wx" + File.separator + "acode" + File.separator;
		String wxacodeUrl = request.getServletContext().getRealPath("/") + wxacodePath;// 临时测试用路径
		String path = wxacodeUrl+ "tzxwxacode.png";
		File dir = new File(wxacodeUrl);
		if (!dir.exists()) {
			dir.mkdir();
		}
		Boolean results = FileUtil.saveWxacode(path, result);
		if (!results) {
			return new ResultMessage(StatusCode.ERROR, "请求失败").toString();
		}
		JSONObject jsons = new JSONObject();
		jsons.put("wxacodeUrl", path);
		return new ResultMessage(StatusCode.OK, "请求成功", jsons).toString();
	}
	
	public static void main(String[] args) {
		String ACCESS_TOKEN = TokenBuilder.getInstance().defaultConfig().getToken();
		JSONObject json = new JSONObject();
		json.put("path", "pages/index/index");
		json.put("width", 430);
		String url = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=" + ACCESS_TOKEN;
		byte[] result = HttpUtils.wxhttpPostJSON(url, json.toJSONString());
		String path = "E:\\upload\\1.png";
		Boolean results = FileUtil.saveWxacode(path, result);
	}
	

}
