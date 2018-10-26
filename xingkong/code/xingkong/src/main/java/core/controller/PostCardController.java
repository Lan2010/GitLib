package core.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import core.common.StatusCode;
import core.mapper.PostcardMapper;
import core.pojo.Dest;
import core.pojo.PostCard;
import core.pojo.ResultMessage;
import core.pojo.User;
import core.service.CardService;
import core.service.ImageService;
import core.service.UserService;
import core.util.PageUtil;

@Controller
public class PostCardController {

	private static Logger log = LoggerFactory.getLogger(PostCardController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private PostcardMapper postcardMapper;
	@Autowired
	private CardService cardService;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// 获取明信片景点信息列表
	@RequestMapping(value = "/api/postcard/destlist", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String destList(HttpServletRequest request, HttpServletResponse response, int page, int pageSize) {
		// 查找用户照片总记录数
		int count;
		try {
			count = cardService.selectDestCount();
			if (count <= 0) {
				return new ResultMessage(StatusCode.ERROR_REQ_PARAM, "请求超时").toString();
			}
			// 计算总页数
			PageUtil pg = new PageUtil(page, pageSize);
			int start = pg.getStart();
			int totalPage = count / pageSize;
			if ((count % pageSize) > 0)
				totalPage = totalPage + 1;
			// 查询的起始页数
			Map<String, Object> destPage = new HashMap<String, Object>();
			destPage.put("start", start); // 从第几页开始查找
			destPage.put("pageSize", pageSize);// 当前显示的记录数

			List<Dest> destList = cardService.selectDestsByPage(destPage);
			if (destList != null) {
				return new ResultMessage(StatusCode.OK, "请求成功", count, destList).toString();
			} else {
				return new ResultMessage(StatusCode.SYSTEM_IS_BUSY, "请求超时").toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	// 获取某个明信片景点详情
	@RequestMapping(value = "/api/postcard/getDestById", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getDestById(HttpServletRequest request, HttpServletResponse response, int destId) {
		Dest dest;
		try {
			dest = cardService.getDestById(destId);
			if (dest != null) {
				return new ResultMessage(StatusCode.OK, "请求成功", dest).toString();
			} else {
				return new ResultMessage(StatusCode.SYSTEM_IS_BUSY, "请求超时").toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	// 明信片上传
	@RequestMapping(value = "/api/postcard/uploadPostcard", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String uploadPostcard(HttpServletRequest request, HttpServletResponse response) {
		String userId = (String) request.getAttribute("userId");
		try {
			User user = userService.getUser(userId);
			if (user == null) {
				return new ResultMessage(StatusCode.NOT_LOGIN, "未登录~").toString();
			}
			String pcTitle = request.getParameter("pcTitle");
			Integer destId = Integer.valueOf(request.getParameter("destId"));
			String pcText = request.getParameter("pcText");
			PostCard pc = new PostCard(user.getAvatarLocal(), pcTitle, pcText, sdf.format(new Date()), destId,
					Integer.valueOf(userId));
			// 推送"创建/分享明信片"消息到nats
			cardService.publish4oms(user, pc, 1);
			Integer result = postcardMapper.savePostCard(pc);
			if (result <= 0) {
				return new ResultMessage(StatusCode.SQL_EXCEPTION, "请求超时").toString();
			}
			PostCard p = cardService.getPostcardById(pc.getPcId());

			p.setNickName(user.getNickName());
			return new ResultMessage(StatusCode.OK, "请求成功", p).toString();
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

	// 修改明信片标题
	@RequestMapping(value = "/api/postcard/updatePCTitle", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String updatePCTitle(HttpServletRequest request, HttpServletResponse response, int pcId, String pcTitle) {
		String pcTitles = null;
		try {
			pcTitles = new String(pcTitle.getBytes("ISO8859-1"), "utf-8");
			PostCard postcard = new PostCard(pcId, pcTitles);
			int result = cardService.updatePCTitle(postcard);
			if (result <= 0) {
				return new ResultMessage(StatusCode.SQL_EXCEPTION, "请求超时").toString();
			}
			PostCard pc = cardService.getPostcardById(pcId);
			if (pc == null) {
				return new ResultMessage(StatusCode.ERROR_REQ_PARAM, "系统繁忙~", pc).toString();
			}
			User user = userService.getUser(Integer.toString(pc.getUserId()));
			pc.setPcHeadImg(user.getAvatarLocal());
			pc.setNickName(user.getNickName());
			return new ResultMessage(StatusCode.OK, "请求成功", pc).toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	// 获取用户明信片列表
	@RequestMapping(value = "/api/postcard/list", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String userpclist(HttpServletRequest request, HttpServletResponse response, int page, int pageSize) {
		JSONArray ja = new JSONArray();
		String userId = (String) request.getAttribute("userId");
		try {
			User user = userService.getUser(userId);
			if (user == null) {
				return new ResultMessage(StatusCode.NOT_LOGIN, "未登录~").toString();
			}
			// 查找用户照片总记录数
			int count = cardService.selectUserPCCount(Integer.valueOf(userId));
			if (count <= 0) {
				return new ResultMessage(StatusCode.OK, "请求成功", count, ja).toString();
			}
			// 计算总页数
			PageUtil pg = new PageUtil(page, pageSize);
			int start = pg.getStart();
			int totalPage = count / pageSize;
			if ((count % pageSize) > 0)
				totalPage = totalPage + 1;
			// 查询的起始页数
			Map<String, Object> pcPage = new HashMap<String, Object>();
			pcPage.put("start", start); // 从第几页开始查找
			pcPage.put("pageSize", pageSize);// 当前显示的记录数
			pcPage.put("userId", userId);
			List<PostCard> pcList = cardService.selectPCsByPage(pcPage);
			if (pcList == null) {
				return new ResultMessage(StatusCode.ERROR_REQ_PARAM, "请求超时").toString();
			}
			TreeSet<String> dateSet = (TreeSet<String>) new TreeSet<String>().descendingSet();
			for (PostCard postCard : pcList) {
				String pcdate = postCard.getPcDate();
				dateSet.add(pcdate);
			}
			for (String date : dateSet) {
				JSONObject childjObject = new JSONObject();
				JSONArray innerjArray = new JSONArray();
				for (PostCard postCard : pcList) {
					postCard.setPcHeadImg(user.getAvatarLocal());
					postCard.setNickName(user.getNickName());
					if (postCard.getPcDate().equals(date)) {
						innerjArray.add(postCard);
					}
					childjObject.put("date", date);
					childjObject.put("data", innerjArray);
				}
				ja.add(childjObject);
			}
			return new ResultMessage(StatusCode.OK, "请求成功", count, ja).toString();
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

	// 获取某个用户明信片详情
	@RequestMapping(value = "/api/postcard/getPostcardById", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getPostcardById(HttpServletRequest request, HttpServletResponse response, int pcId) {
		try {
			PostCard pc = cardService.getPostcardById(pcId);
			if (pc == null) {
				return new ResultMessage(StatusCode.OK, "请求成功", pc).toString();
			}
			User user = userService.getUser(Integer.toString(pc.getUserId()));
			pc.setPcHeadImg(user.getAvatarLocal());
			pc.setNickName(user.getNickName());
			return new ResultMessage(StatusCode.OK, "请求成功", pc).toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	// 删除用户明信片
	@RequestMapping(value = "/api/postcard/deletepc", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String delPostCard(HttpServletRequest request, HttpServletResponse response, Integer pcId) {
		try {
			String userId = (String) request.getAttribute("userId");
			// 1.先检查该明信片是否属于该用户,然后删除数据库
			PostCard pc = cardService.getPostcardById(pcId);
			if (pc == null) {
				return new ResultMessage(StatusCode.ERROR_REQ_PARAM, "系统繁忙，请稍后再试", pc).toString();
			}
			if (pc.getUserId() != Integer.valueOf(userId)) {
				return new ResultMessage(StatusCode.ERROR_REQ_PARAM, "请求参数错误").toString();
			}
			int result = cardService.delPostcard(pcId);
			if (result <= 0) {
				return new ResultMessage(StatusCode.SYSTEM_IS_BUSY, "系统繁忙，请稍后再试").toString();
			}

			/***
			 * 需求变更 // 2.数据删除成功才去硬盘删除照片， 删除服务器硬盘中的图片文件 // String pcHeadImgurl =
			 * request.getServletContext().getRealPath("/") + pcHeadImg; // File pcfile =
			 * new File(pcHeadImgurl); // if (pcfile.exists()) { // pcfile.delete(); // }
			 ***/
			return new ResultMessage(StatusCode.OK, "请求成功").toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	@RequestMapping(value = "/api/image/deletephoto", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public void deletephoto(HttpServletRequest request, HttpServletResponse response, String fileName) {
		String userId = (String) request.getAttribute("userId");
		// 1.先检查该照片是否属于该用户,然后删除数据库
		boolean boo = false;
		boo = imageService.delImage(fileName, userId);
		// 数据删除成功才去硬盘删除照片
		if (boo) {
			// 2.删除服务器硬盘中的图片文件(原图及缩略图)
			String rawImagePath = request.getServletContext().getRealPath("/") + "/images/rawImages/";
			String thumbImagePath = request.getServletContext().getRealPath("/") + "/images/thumbImages/";
			File rifile = new File(rawImagePath + fileName);
			File tifile = new File(thumbImagePath + fileName);
			if (rifile.exists() || tifile.exists()) {
				rifile.delete();
				tifile.delete();
			}
		}
	}

	// 推送"创建/分享明信片"消息到nats
	@RequestMapping(value = "/api/postcard/share", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public void share(HttpServletRequest request, HttpServletResponse response, Integer pcId) {
		String userId = (String) request.getAttribute("userId");
		try {
			User user = userService.getUser(userId);
			PostCard pc = cardService.getPostcardById(pcId);
			// 推送"创建/分享明信片"消息到nats
			cardService.publish4oms(user, pc, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
