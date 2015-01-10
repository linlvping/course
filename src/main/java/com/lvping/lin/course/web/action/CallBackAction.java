package com.lvping.lin.course.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.common.TableDataUtils;
import com.lvping.lin.course.model.entity.Callback;
import com.lvping.lin.course.model.entity.Student;
import com.lvping.lin.course.model.entity.User;
import com.lvping.lin.course.model.service.CallbackService;
import com.lvping.lin.course.model.service.CourseService;
import com.lvping.lin.course.model.service.PeriodService;

/**
 *
 * @author beifeng
 * @Date 2015-1-5 
 *
 */
@Controller
@RequestMapping("/callback")
public class CallBackAction {
    
    @Resource
    private PeriodService periodService;
    @Resource
    private CallbackService callbackService;
    @Resource
    private CourseService courseService;
    
    @RequestMapping("/index") 
    public String index(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "callback_index");
        return "callback/list";
    }
    
    @RequestMapping("/detail")
    public String detail(Model model, HttpServletRequest request) {
        String name = CommonUtils.getName(request);
        request.setAttribute("list", courseService.getCourse());
        model.addAttribute("student", periodService.getByName(name));
        return "callback/detail";
    }
    
    @RequestMapping("/paike")
    public String paike(Model model, HttpServletRequest request) {
        String name = CommonUtils.getName(request);
        model.addAttribute("student", periodService.getByName(name));
        return "callback/paike";
    }
    
    @RequestMapping("/list") 
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            List<Student> list = periodService.getStudent();
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }

    @RequestMapping("/show")
    public String show(Model model, HttpServletRequest request) {
        String name = CommonUtils.getName(request);
        model.addAttribute("student", name);
        return "callback/callback";
    }
    
    @RequestMapping("/save")
    public String save(Callback callback, Model model, HttpServletRequest request) {
        User operator = (User)request.getSession().getAttribute(Constants.SEESION_USER);
        callback.setOperator(operator.getName());
        callbackService.save(callback);
        model.addAttribute("student", callback.getStudent());
        return "callback/callback";
    }
    
    @RequestMapping("/data") 
    @ResponseBody
    public Map<String, Object> data(HttpServletRequest request) {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            String name = CommonUtils.getName(request);
            List<Callback> list = callbackService.getByStudent(name);
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }
    
}
