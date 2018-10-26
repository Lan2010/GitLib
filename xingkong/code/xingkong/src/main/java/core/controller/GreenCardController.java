package core.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import core.common.StatusCode;
import core.pojo.GreenCard;
import core.pojo.ResultMessage;
import core.pojo.User;
import core.service.CardService;
import core.service.ImageService;
import core.service.UserService;

@Controller
public class GreenCardController {

	private static Logger log = LoggerFactory.getLogger(GreenCardController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private CardService cardService;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// 领取绿卡
	@RequestMapping(value = "/api/greencard/getGreencard", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String uploadGreencard(HttpServletRequest request, HttpServletResponse response, String gcName,
			String gcHeadImg) {
		String gcNames = null;

		String userId = (String) request.getAttribute("userId");
		try {
			gcNames = new String(gcName.getBytes("ISO8859-1"), "utf-8");
			User user = userService.getUser(userId);
			if (user == null) {
				return new ResultMessage(StatusCode.NOT_LOGIN, "未登录~").toString();
			}
			// 1.判断没有授权，报错
			if (user.getIsAuth() != 1) {
				return new ResultMessage(StatusCode.NOT_AUTH, "请求超时").toString();
			}
			// 2.用户已领取，领取失败
			if (user.getIsGetGC() == 1) {
				return new ResultMessage(StatusCode.REGET, "请求超时").toString();
			}

			// 1.数据库存储绿卡信息
			String gcNumber = null;
			int gcCount = cardService.selectGCCount();
			if (gcCount <= 0) {
				gcNumber = "TZX000001000";
			}
			GreenCard gc = new GreenCard(gcNumber, sdf.format(new Date()), gcNames, gcHeadImg, Integer.valueOf(userId));
			Integer result = cardService.saveGreenCard(gc);
			// 推送"创建/分享绿卡"消息到nats
			cardService.publish4oms(user, gc, 1);
			if (result <= 0) {
				return new ResultMessage(StatusCode.SQL_EXCEPTION, "请求超时").toString();
			}
			// 2.领取成功，修改用户“已领取状态”
			Integer result0 = userService.haveGetGc(userId);
			GreenCard greencard = cardService.getGreenCardById(userId);
			if (greencard == null) {
				return new ResultMessage(StatusCode.SYSTEM_IS_BUSY, "系统繁忙,请稍后再试~").toString();
			}
			return new ResultMessage(StatusCode.OK, "请求成功", gc).toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SYSTEM_IS_BUSY, "系统繁忙~").toString();
		}
	}

	// 根据userId获取绿卡信息
	@RequestMapping(value = "/api/greencard/gcDetail", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getGCByUserId(HttpServletRequest request, HttpServletResponse response) {
		String userId = (String) request.getAttribute("userId");
		GreenCard gc;
		try {
			User user = userService.getUser(userId);
			if (user == null) {
				return new ResultMessage(StatusCode.NOT_LOGIN, "未登录~").toString();
			}
			gc = cardService.getGreenCardById(userId);
			if (gc == null) {
				return new ResultMessage(StatusCode.OK, "请求成功", gc).toString();
			}
			gc.setGcHeadImg(user.getAvatarLocal());
			return new ResultMessage(StatusCode.OK, "请求成功", gc).toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	// 根据gc Id获取绿卡信息
	@RequestMapping(value = "/api/greencard/getGCById", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getGCByGCId(HttpServletRequest request, int gcId) {
		GreenCard gc;
		try {
			gc = cardService.getGCByGCId(gcId);
			if (gc == null) {
				return new ResultMessage(StatusCode.OK, "请求成功", gc).toString();
			}
			User user = userService.getUser(Integer.toString(gc.getUserId()));
			gc.setGcHeadImg(user.getAvatarLocal());
			return new ResultMessage(StatusCode.OK, "请求成功", gc).toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	// 推送"创建/分享绿卡"消息到nats
	@RequestMapping(value = "/api/greencard/share", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public void share(HttpServletRequest request, Integer gcId) {
		String userId = (String) request.getAttribute("userId");
		try {
			User user = userService.getUser(userId);
			GreenCard gc = cardService.getGCByGCId(gcId);
			// 推送"创建/分享绿卡"消息到nats
			cardService.publish4oms(user, gc, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
