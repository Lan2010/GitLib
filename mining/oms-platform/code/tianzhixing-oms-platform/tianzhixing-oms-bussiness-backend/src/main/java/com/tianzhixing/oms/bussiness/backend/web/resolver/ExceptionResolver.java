package com.tianzhixing.oms.bussiness.backend.web.resolver;

import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.utils.JSONUtil;
import com.tianzhixing.oms.web.security.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller层异常捕捉
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {

    @Autowired
    protected AbstractMessageSource messageSource;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mv = null;
        if (!RequestUtil.isJsonRequest(request)) {
            mv = super.doResolveException(request, response, handler, ex);
        } else {
            try {
                // json 请求返回
                PrintWriter writer = response.getWriter();
                if (ex != null) {
                    int code = 500;
                    String msg = ex.getMessage();
                    writer.write(JSONUtil.beanToJsonWithJackson(new ResponseEntity(code, msg)));
                } else {
                    writer.write(JSONUtil.beanToJsonWithJackson(new ResponseEntity(500, "system.error")));
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        doLog((HandlerMethod) handler, ex);
        return mv;
    }

    /**
     * 记录异常日志
     *
     * @param handler
     * @param excetpion
     */
    private void doLog(HandlerMethod handler, Exception excetpion) {
        excetpion.printStackTrace();
        //SystemLogger.error(excetpion.getMessage(), ExceptionResolver.class);
    }

}
