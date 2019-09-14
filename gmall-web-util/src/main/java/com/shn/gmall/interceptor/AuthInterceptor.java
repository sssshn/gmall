package com.shn.gmall.interceptor;

import com.shn.gmall.annotation.LoginRequire;
import com.shn.gmall.util.CookieUtil;
import com.shn.gmall.util.HttpClientUtil;
import com.shn.gmall.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author sss
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequire loginRequire = handlerMethod.getMethodAnnotation(LoginRequire.class);

        //不需要登录
        if (loginRequire == null) {
            return true;
        }

        String oldToken = CookieUtil.getCookieValue(request, "userToken", true);
        String newToken = request.getParameter("newToken");
        String token = "";

        //已经登录过
        if (StringUtils.isNotBlank(oldToken) && StringUtils.isBlank(newToken)) {
            token = oldToken;
        }

        //第一次登录
        if (StringUtils.isBlank(oldToken) && StringUtils.isNotBlank(newToken)) {
            token = newToken;
        }

        //登录过期
        if (StringUtils.isNotBlank(oldToken) && StringUtils.isNotBlank(newToken)) {
            token = newToken;
        }

        //需要登录，且没有token,跳转登录页面，并把之前请求路径传递过去
        if (loginRequire.isNeedSuccess() && StringUtils.isBlank(token)) {
            StringBuffer requestURL = request.getRequestURL();
            response.sendRedirect("http://passport.gmall.com:8085/index?returnURL=" + requestURL);
            return false;
        }

        //需要登录验证，且已经有token
        if (StringUtils.isNotBlank(token)) {
            //获取ip地址
            String ipAddress = request.getRemoteAddr();
            if (StringUtils.isBlank(ipAddress)) {
                ipAddress = request.getHeader("x-forwarded-for");
            }
            //验证token
            String result = HttpClientUtil.doGet("http://passport.gmall.com:8085/verify?token=" + token + "&salt=" + ipAddress);
            if ("success".equals(result)) {
                CookieUtil.setCookie(request, response, "userToken", token, 60*60*2, true);
                Map map = JwtUtil.decode("gmall", token, ipAddress);
                String userId = (String) map.get("userId");
                request.setAttribute("userId", userId);
                return true;

            }
            if (!"success".equals(result) && loginRequire.isNeedSuccess()) {
                response.sendRedirect("http://passport.gmall.com:8085/index");
                return false;
            }

            if (!"success".equals(result) && !loginRequire.isNeedSuccess()) {
                return true;
            }
        }

        return true;
    }
}
