package com.qmdx.ams.interceptor;

import com.qmdx.ams.constant.WebConstant;
import com.qmdx.ams.entity.Role;
import com.qmdx.ams.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthorityInterceptor implements HandlerInterceptor {

    private final String authorityPath = "/auth/"; // permissions validation

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(WebConstant.SESSION_KEY_USER);
        if(user != null) {
            // Determine if it is a request that requires authorization
            String servletUrl = request.getServletPath();
            if(!servletUrl.startsWith(authorityPath)){
                return true;
            }else {
                servletUrl = servletUrl.substring(authorityPath.length());
                String roleType = servletUrl.substring(0, servletUrl.indexOf('/'));
                if (user.getRole().getRole().equals(roleType)) {
                    return true;
                }
            }
        }
        request.setAttribute("authorityErrorMsg","Authorization not granted");
        request.getRequestDispatcher("/login.html").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
