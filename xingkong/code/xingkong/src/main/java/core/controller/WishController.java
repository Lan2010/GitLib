package core.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import core.common.Constant;
import core.common.StatusCode;
import core.pojo.ResultMessage;
import core.pojo.User;
import core.pojo.Wish;
import core.service.UserService;
import core.service.WishService;
import core.service.nats.NatsComponent;
import core.util.CommonUtils;
import core.util.PageUtil;

@Controller
public class WishController {
	private static Logger log = LoggerFactory.getLogger(WishController.class);

	@Autowired
	private WishService wishService;
	@Autowired
	private NatsComponent natsComponent;
	@Autowired
	private UserService userService;


	@RequestMapping(value = "/api/wish/addAudio", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String addAudio(HttpServletRequest request, HttpServletResponse response) {
		try {
			//String basePath = request.getScheme() + "://" + request.getServerName();// 存入数据库中的路径
			Date date = new Date();
			Map<String,Object> map = (Map<String,Object>) request.getAttribute("fileItems");
			String basePath = Constant.BASE_PATH;// 存入数据库中的路径
			// 获取文件需要上传到的路径
			String wishPath = "wish" + File.separator + "audio" + File.separator;
			// String path = "/data001/data/images/"+wishPath;//linux环境路径
			String path = request.getServletContext().getRealPath("/") + wishPath;// 临时测试用路径
			FileItem audio = (FileItem) map.get("file");
			Integer audio_time_len = Integer.valueOf((String)map.get("audioTimeLen"));
			String userId = (String) request.getAttribute("userId");
			// 1.文件上传
			String fileName = wishService.uploadAudio(audio, path);
			Wish wish = new Wish(Integer.valueOf(userId), Constant.SDF.format(date), audio_time_len,
					basePath + File.separator + wishPath + fileName);
			Integer result = wishService.saveWish(wish);
			//推送添加录音消息到nats
			if(result>0) {
				publish4oms(request,date);
			}
			return new ResultMessage(StatusCode.OK, "请求成功").toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.IO_EXCEPTION, "系统繁忙~").toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SYSTEM_IS_BUSY, "系统繁忙，请稍后再试").toString();

		}
	}
	
	/**
	 * 推送"添加录音"消息到nats
	 * @param request
	 * @param date
	 */
	private void publish4oms(HttpServletRequest request,Date date) {
		String userId = (String) request.getAttribute("userId");
		try {
			User user = userService.getUser(userId);
			JSONObject json = new JSONObject();
			json.put("id", CommonUtils.randomID(8));
			json.put("createTime", date.getTime());
			json.put("operationTime", date.getTime());
			json.put("nickName", user.getNickName());
			json.put("wxID", user.getOpenid());
			json.put("recordingTime", date.getTime());
			natsComponent.publish4oms("oms.subject.user.recording", json.toJSONString());
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException.",e);
		}
		
//		主题：oms.subject.user.recording
//		{
//		    "id": null,//必填
//		    "createTime": 1531453134842,//必填
//		    "platformFrom": "WKWXAPP",//必填
//		    "clientPlatformType": "WKWXAPP",
//		    "mobile": "1388888888",//选填
//		    "voiceId": "0",//选填
//		    "voiceLink": "link",//选填
//		    "nickName": "nickname",//选填
//		    "wxID": "wx_id",//选填
//		    "recordingTime": 1531453134842//必填
//		}
	}

	// 获取用户愿望列表
	@RequestMapping(value = "/api/wish/wishList", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String destList(HttpServletRequest request, HttpServletResponse response, Integer page, Integer pageSize) {
		String userId = (String) request.getAttribute("userId");
		List<Wish> wishList = new ArrayList<Wish>();
		try {
			// 查找用户愿望总数
			int count = wishService.selectWishCount(Integer.valueOf(userId));
			if (count <= 0) {
				return new ResultMessage(StatusCode.OK,"请求成功", 0, wishList).toString();
			}
			// 计算总页数
			PageUtil pg = new PageUtil(page, pageSize);
			// int start = pg.getStart();
			// int totalPage = count / pageSize;
			// if ((count % pageSize) > 0)
			// totalPage = totalPage + 1;
			// 查询的起始页数
			Map<String, Object> wishPage = new HashMap<String, Object>();
			wishPage.put("start", pg.getStart()); // 从第几页开始查找
			wishPage.put("pageSize", pg.getPageSize());// 当前显示的记录数
			wishPage.put("userId", userId);// 用户Id
			wishList = wishService.selectWishsByPage(wishPage);
			if (wishList != null) {
				return new ResultMessage(StatusCode.OK, "请求成功", count, wishList).toString();
			} else {
				return new ResultMessage(StatusCode.OK,"请求成功", 0, wishList).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}

	}

}
