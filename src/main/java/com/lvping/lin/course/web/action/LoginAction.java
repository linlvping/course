package com.lvping.lin.course.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.model.entity.User;
import com.lvping.lin.course.model.service.UserService;

/**
 *
 * @author beifeng
 * @Date 2015-1-4 下午4:42:07
 *
 */
@Controller
public class LoginAction {
    
    @Resource
    private UserService userService;
    
    @RequestMapping("/index")
    public String index() {
        return "home";
    }
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logon")
	public String logon(Model model, HttpServletRequest request) {
		try {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = userService.getByName(name);
			if (user == null) {
			    model.addAttribute("errorMessage", "用户名不存在！");
			    return "login";
			}
			if (user.getPassword().equals(password)) {
			    request.getSession().setAttribute(Constants.SEESION_USER, user);
				return "redirect:/index";
			} else {
			    model.addAttribute("errorMessage", "密码错误！");
				return "login";
			}
		} catch (Exception e) {
		    e.printStackTrace();
			return "login";
		}
	}
	
}
