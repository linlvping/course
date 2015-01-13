package com.lvping.lin.course.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.common.TableDataUtils;
import com.lvping.lin.course.model.entity.Student;
import com.lvping.lin.course.model.entity.User;
import com.lvping.lin.course.model.service.CourseService;
import com.lvping.lin.course.model.service.PeriodService;

/**
 *
 * @author beifeng
 * @Date 2015-1-4 
 *
 */
@Controller
@RequestMapping("/period")
public class PeriodAction {
    
    @Resource
    private PeriodService periodService;
    @Resource
    private CourseService courseService;
    
    @RequestMapping("/add")
    public String login(HttpServletRequest request) {
        request.setAttribute("list", courseService.getCourse());
        request.getSession().setAttribute(Constants.SEESION_LINK, "period_add");
        return "period/add";
    }
    
    @RequestMapping("/save")
    public String save(Student student) {
        periodService.addStudent(student);
        return "redirect:list";
    }
    
    @RequestMapping("/update")
    public String update(Student student) {
        periodService.updateStudent(student);
        return "redirect:../callback/index";
    }
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "period_list");
        return "period/list";
    }

    
    @RequestMapping("/data") 
    @ResponseBody
    public Map<String, Object> data(HttpServletRequest request) {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            User user = (User)request.getSession().getAttribute(Constants.SEESION_USER);
            List<Student> list = periodService.getStudent(user);
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }
    
}
