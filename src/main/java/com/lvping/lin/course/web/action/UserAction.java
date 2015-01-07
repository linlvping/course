package com.lvping.lin.course.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.common.TableDataUtils;
import com.lvping.lin.course.model.entity.User;
import com.lvping.lin.course.model.service.UserService;

/**
 *
 * @author beifeng
 * @Date 2015-1-7 
 *
 */
@Controller
@RequestMapping("/user")
public class UserAction {
    
    @Resource
    private UserService userService;
    
    @RequestMapping("/add") 
    public String index(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "user_add");
        return "user/add";
    }
    
    @RequestMapping("/save") 
    public String save(User user) {
        userService.save(user);
        return "redirect:list";
    }
    
    @RequestMapping("/list") 
    public String list(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "user_list");
        return "user/list";
    }
    
    @RequestMapping("/data") 
    @ResponseBody
    public Map<String, Object> data() {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            List<User> list = userService.getAllUsers();
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }
    
    @RequestMapping("/valid/{id}")
    public String valid(@PathVariable int id) {
        userService.updateStatus(id, 0);
        return "redirect:../list";
    }

    @RequestMapping("/invalid/{id}")
    public String invalid(@PathVariable int id) {
        userService.updateStatus(id, 1);
        return "redirect:../list";
    }
    
    @RequestMapping("/password") 
    public String password(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "user_password");
        return "user/password";
    }
    
    @RequestMapping("/update") 
    public String update(Model model, HttpServletRequest request) {
        String oldPassword = request.getParameter("old");
        String newPassword = request.getParameter("new");
        User user = (User)request.getSession().getAttribute(Constants.SEESION_USER);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userService.updatePassword(user);
            model.addAttribute("message", "密码更新成功！");
        } else {
            model.addAttribute("message", "旧密码错误！");
        }
        return "user/password";
    }

}
