package com.lvping.lin.course.web.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.common.DateUtils;
import com.lvping.lin.course.common.TableDataUtils;
import com.lvping.lin.course.model.entity.Schedule;
import com.lvping.lin.course.model.entity.Student;
import com.lvping.lin.course.model.entity.Teacher;
import com.lvping.lin.course.model.entity.User;
import com.lvping.lin.course.model.service.PeriodService;
import com.lvping.lin.course.model.service.ScheduleService;
import com.lvping.lin.course.model.service.TeacherService;
import com.lvping.lin.course.web.bean.DateArray;

/**
 *
 * @author beifeng
 * @Date 2015-1-4 
 *
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleAction {
    
    @Resource
    private ScheduleService scheduleService;
    @Resource
    private PeriodService periodService;
    @Resource
    private TeacherService teacherService;
    
    @RequestMapping("/show")
    public String show(Model model, HttpServletRequest request) throws ParseException {
        request.getSession().setAttribute(Constants.SEESION_LINK, "schedule_show");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        if (CommonUtils.isEmpty(beginDate)) {
            beginDate = DateUtils.getMondayOfWeek();
        }
        if (CommonUtils.isEmpty(endDate)) {
            endDate = DateUtils.getCurrentWeekday();
        }
        User user = (User)request.getSession().getAttribute(Constants.SEESION_USER);
        List<DateArray> dateArray = DateUtils.dateArray(beginDate, endDate);
        Map<String, Map<Integer, Map<String, List<Schedule>>>> map = scheduleService.getSchedule(beginDate, endDate, user);
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("dateArray", dateArray);
        model.addAttribute("map", map);
        return "schedule/show";
    }

    @RequestMapping("/add")
    public String add(Model model, HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "schedule_add");
        List<Teacher> teacher = teacherService.getValidTeacher();
        List<Student> student = periodService.getValidStudent();
        model.addAttribute("teacher", teacher);
        model.addAttribute("student", student);
        return "schedule/add";
    }
    
    @RequestMapping("/save")
    public String save(Schedule schedule) {
        scheduleService.addSchedule(schedule);
        return "redirect:list";
    }
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "schedule_list");
        return "schedule/list";
    }

    
    @RequestMapping("/data") 
    @ResponseBody
    public Map<String, Object> data() {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            List<Schedule> list = scheduleService.getNeedActionSchedule();
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }
    
    @RequestMapping("/lack/{id}")
    public String lack(Model model, @PathVariable int id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        return "schedule/lack";
    }
    
    @RequestMapping("/dolack")
    public String doLack(HttpServletRequest request) {
        String id = request.getParameter("id");
        String fact = request.getParameter("fact");
        scheduleService.lackConfirm(Integer.parseInt(id), Integer.parseInt(fact));
        return "redirect:list";
    }
    
    @RequestMapping("/confirm/{id}")
    public String confirm(@PathVariable int id) {
        scheduleService.confirm(id);
        return "redirect:../list";
    }
    
    @RequestMapping("/off/{id}")
    public String off(@PathVariable int id) {
        scheduleService.offCourse(id);
        return "redirect:../list";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        scheduleService.delete(id);
        return "redirect:../list";
    }
    
    @RequestMapping("/report")
    public String report(HttpServletRequest request, Model model) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "schedule_report");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        if (StringUtils.isEmpty(beginDate)) {
            beginDate = DateUtils.getMondayOfWeek();
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = DateUtils.getCurrentWeekday();
        }
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        scheduleService.setModel(model, beginDate, endDate);
        return "schedule/report";
    }
    
    @RequestMapping("/student") 
    @ResponseBody
    public Map<String, Object> student(HttpServletRequest request) {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            String name = CommonUtils.getName(request);
            List<Schedule> list = scheduleService.getScheduleByStudent(name);
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }
}
