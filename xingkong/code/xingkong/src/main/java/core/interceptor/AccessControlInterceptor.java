package core.interceptor;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import core.common.Constant;
import core.common.StatusCode;
import core.pojo.ResultMessage;
import core.util.FileUtil;
import core.util.JWTUtil;
import core.util.RedisUtil;

public class AccessControlInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(AccessControlInterceptor.class);

	/**
	 * 在request请求之前做拦截处理
	 * 
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String servletPath = request.getServletPath();
			log.info("servletPath:{}", servletPath);
			return isAuthorize(request, response, handler);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return false;
	}

	private boolean isAuthorize(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String contentType = request.getContentType();
		String token = request.getParameter(Constant.TOKEN);
		System.out.println("contentType:" + contentType);
		response.setContentType("application/json; charset=utf-8");
		if (ServletFileUpload.isMultipartContent(request)==true) {
			token = getToken(request, contentType);
			if (null == token) {
				response.getWriter().print(new ResultMessage(StatusCode.NOT_LOGIN, "未登录"));
				return false;
			} 
		}  
		return handleToken(request, response, handler, token);
	}

	private String getToken(HttpServletRequest request, String contentType) throws FileUploadException {
		String fieldName = null;
		String token = null;
		   // 创建工厂（这里用的是工厂模式）
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取汽车零件清单与组装说明书（从ServletContext中得到上传来的数据）
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //工厂把将要组装的汽车的参数录入工厂自己的系统，因为要根据这些参数开设一条生产线（上传来的文件的各种属性）
        factory.setRepository(repository);
        //此时工厂中已经有了汽车的组装工艺、颜色等属性参数（上传来的文件的大小、文件名等）
        //执行下面的这一行代码意味着根据组装工艺等开设了一条组装生产线
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
		for (FileItem item : items) {
			// 获取表单的属性名字
			fieldName = item.getFieldName();
			// 如果获取的表单信息是普通的文本 信息
			if (item.isFormField() && "token".equals(fieldName)) {
				// 获取上传表单数据
				Map<String, Object> datas = FileUtil.getData(items);
				request.setAttribute("fileItems", datas);
				token = item.getString();
				break;
			}
		}
		return token;
	}

	private boolean handleToken(HttpServletRequest request, HttpServletResponse response, Object handler, String token)
			throws Exception {
		String userId = null;
		String session_key = RedisUtil.getString(token);// 从redis中获取session_key
		System.out.println("session_key:" + session_key);
		if (session_key == null) {
			response.getWriter().print(new ResultMessage(StatusCode.NOT_LOGIN, "未登录"));
			return false;
		}
		userId = (String) JWTUtil.getJti(token, session_key);
		log.info("userId:{} sign in", userId);
		if (userId != null && token.equals(RedisUtil.getString("token." + userId))) {// 在redis中可查询到用户的token
			// 已授权，放行
			request.setAttribute("userId", userId);// 必须设置，多个接口调用时，需要获取该参数
			RedisUtil.updateExpire("token." + userId, RedisUtil.EXPIRE);// 更新用户token信息，重置该key的redis生命周期
			return super.preHandle(request, response, handler);
		} else {
			response.getWriter().print(new ResultMessage(StatusCode.NOT_LOGIN, "未登录"));
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
