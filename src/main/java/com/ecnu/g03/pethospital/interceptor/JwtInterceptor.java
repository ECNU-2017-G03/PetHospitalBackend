package com.ecnu.g03.pethospital.interceptor;

import com.ecnu.g03.pethospital.util.JwtToken;
import com.ecnu.g03.pethospital.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 1:28
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtToken.class)) {
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);
            if (!jwtToken.required()) {
                return true;
            }
        } else {
            return true;
        }

        // Get authorization from header
        String authHeader = request.getHeader(JwtUtil.AUTH_HEADER_KEY);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // Get token
        String token = authHeader.substring(JwtUtil.TOKEN_PREFIX.length());
        // Validate token
        try {
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
