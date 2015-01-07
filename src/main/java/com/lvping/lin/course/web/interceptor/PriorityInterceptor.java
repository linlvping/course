package com.lvping.lin.course.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.model.entity.User;

/**
 *
 * @author beifeng
 * @Date 2015-1-7 
 *
 */
public class PriorityInterceptor implements HandlerInterceptor {
    
    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.SEESION_USER);
        if (user == null) {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        } 
        if (user.getPriority() < 100) {
            response.sendRedirect(request.getContextPath()+"/index");
            return false; 
        }
        return true;
    }
    
}