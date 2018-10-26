package com.tianzhixing.oms.web.security.filter;

import com.tianzhixing.oms.commons.CodeConfig;
import com.tianzhixing.oms.web.security.exception.LoginFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jinkai.xie
 */
public class CustomerUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.
     * UsernamePasswordAuthenticationFilter
     * #attemptAuthentication(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String code = request.getParameter("code");
        //验证码校验
        Object SESSION_VALICODE = request.getSession().getAttribute("LOGIN_VAL_CODE");
        if (SESSION_VALICODE == null || code == null || !SESSION_VALICODE.equals(code)) {
            //return _toErrorLogin(request, response);
        }
        return super.attemptAuthentication(request, response);
    }

    private Authentication _toErrorLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            simpleUrlAuthenticationFailureHandler.onAuthenticationFailure(request, response, new LoginFailedException(null, CodeConfig.LOGIN_CODE_ERROR));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        //throw new LoginFailedException(null, CodeConfig.LOGIN_CODE_ERROR);
        return null;
    }

}
