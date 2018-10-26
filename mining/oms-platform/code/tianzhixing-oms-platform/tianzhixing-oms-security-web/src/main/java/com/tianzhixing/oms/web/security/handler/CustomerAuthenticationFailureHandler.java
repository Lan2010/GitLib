package com.tianzhixing.oms.web.security.handler;

import com.tianzhixing.oms.commons.CodeConfig;
import com.tianzhixing.oms.utils.JSONUtil;
import com.tianzhixing.oms.web.security.entity.ResponseEntity;
import com.tianzhixing.oms.web.security.exception.LoginFailedException;
import com.tianzhixing.oms.web.security.util.RequestUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by routine.k on 2018/4/27.
 */
public class CustomerAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //super.onAuthenticationFailure(request, response, exception);
        if (!RequestUtil.isJsonRequest(request)) {
            super.onAuthenticationFailure(request, response, exception);
        } else {
            //返回json形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSONUtil.beanToJson(new ResponseEntity(CodeConfig.AUTHFAILED.getCode(), CodeConfig.AUTHFAILED.getValue()), false));
            response.getWriter().flush();
        }
    }
}
