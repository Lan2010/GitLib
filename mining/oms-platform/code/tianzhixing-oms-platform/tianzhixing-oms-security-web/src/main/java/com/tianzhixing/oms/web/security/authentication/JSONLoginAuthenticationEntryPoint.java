package com.tianzhixing.oms.web.security.authentication;

import com.tianzhixing.oms.commons.CodeConfig;
import com.tianzhixing.oms.utils.JSONUtil;
import com.tianzhixing.oms.web.security.entity.ResponseEntity;
import com.tianzhixing.oms.web.security.util.RequestUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义JSON格式返回
 * Created by routine.k on 2018/4/27.
 */
public class JSONLoginAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public JSONLoginAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if (!RequestUtil.isJsonRequest(request)) {
            super.commence(request, response, authException);
        }else{
            //返回json形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSONUtil.beanToJson(new ResponseEntity(CodeConfig.NEEDLOGIN.getCode(), CodeConfig.NEEDLOGIN.getValue()), false));
            response.getWriter().flush();
        }
    }
}
