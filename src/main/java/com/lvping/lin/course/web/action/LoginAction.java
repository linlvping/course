package com.lvping.lin.course.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.model.entity.Student;
import com.lvping.lin.course.model.entity.User;
import com.lvping.lin.course.model.service.PeriodService;
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
    @Resource
    private PeriodService periodService;
    
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "home");
        model.addAttribute("list", periodService.getStudent());
        return "home";
    }
    
    @RequestMapping("/upgrade")
    public String upgrade(Model model, HttpServletRequest request, RedirectAttributes ra) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "home");
        periodService.upgrade();
        ra.addFlashAttribute("message", "年级已升");
        return "redirect:/";
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
			if (user == null || user.getStatus() != 0) {
			    model.addAttribute("errorMessage", "用户名不存在！");
			    return "login";
			}
			if (user.getPassword().equals(password)) {
			    request.getSession().setAttribute(Constants.SEESION_USER, user);
				return "redirect:/";
			} else {
			    model.addAttribute("errorMessage", "密码错误！");
				return "login";
			}
		} catch (Exception e) {
		    e.printStackTrace();
			return "login";
		}
	}
	
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
	    request.getSession().removeAttribute(Constants.SEESION_USER);
        return "login";
    }
	
}
