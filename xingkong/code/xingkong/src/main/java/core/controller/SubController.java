package core.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import core.common.StatusCode;
import core.pojo.ResultMessage;
import core.pojo.Sub;
import core.pojo.Wish;
import core.service.SubService;
import core.service.WishService;

/**
 * 预约控制层
 * 
 * @author dev-teng
 * @date 2018年6月16日
 */
@Controller
public class SubController {
	@Autowired
	private SubService subService;

	@Autowired
	private WishService wishService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static Logger logger = LoggerFactory.getLogger(SubController.class);

	@RequestMapping(value = "/api/sub/addSub", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String addSub(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		//String userId = "8";
		Date now =new Date();
		Sub param = new Sub((short) 0, now, Integer.valueOf(userId));
		Sub sub = null;
		try {
			List<Sub> subs = subService.getSubs(Integer.valueOf(userId), sdf.format(now));// 查找当前用户当天是否已经预约过
			if (subs != null && subs.size() > 0) {// 存在预约记录
				return new ResultMessage(StatusCode.ERROR, "请明天再试~", new Sub()).toString();
			}
			int result = subService.saveSub(param);
			if (result > 0 ) {
				subs = subService.getSubs(Integer.valueOf(userId), sdf.format(now));
				if (subs != null && subs.size() > 0) {
					sub=subs.get(0);
					return new ResultMessage(StatusCode.OK, "请求成功", sub).toString();
				}
			} 
			return new ResultMessage(StatusCode.ERROR, "请明天再试~", new Sub()).toString();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	@RequestMapping(value = "/api/sub/getSub", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getSub(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		JSONObject data = new JSONObject();
		try {
			List<Sub> subs = subService.getSubs(Integer.valueOf(userId), sdf.format(new Date()));
			if (subs != null && subs.size() > 0) {// 存在预约记录,判断预约是否成功，成功才能继续
				if (subs.size() > 1) {
					return new ResultMessage(StatusCode.ERROR, "请明天再试~", data).toString();
				}
				data.put("subStatus", 0);// 不可预约
				//判断是否预约成功
				if(subs.get(0).getSubSuccess()!=1) {
					data.put("wishStatus", 0);//预约失败，不可许愿
					return new ResultMessage(StatusCode.OK, "请求成功", data).toString();
				}
				//查询是否许愿过
				List<Wish> wish = wishService.getWish(Integer.valueOf(userId), sdf.format(new Date()));
				if (wish!=null && wish.size()>0) {//许愿过
					data.put("wishStatus", 0);// 不可许愿
				} else {// 未许愿过
					data.put("wishStatus", 1);// 可许愿
				}
				return new ResultMessage(StatusCode.OK, "请求成功", data).toString();
			} else {// 不存在预约记录
				data.put("subStatus", 1);//可预约
				return new ResultMessage(StatusCode.OK, "请求成功", data).toString();
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResultMessage(StatusCode.ERROR, "系统繁忙~").toString();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

}
