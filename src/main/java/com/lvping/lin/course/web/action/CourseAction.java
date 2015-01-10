package com.lvping.lin.course.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.model.entity.Course;
import com.lvping.lin.course.model.service.CourseService;

/**
 *
 * @author beifeng
 * @Date 2015-1-11 
 *
 */
@Controller
@RequestMapping("/course")
public class CourseAction {
    
    @Resource
    private CourseService courseService;

    @RequestMapping("/add") 
    public String add(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "course_add");
        return "course/add";
    }
    
    @RequestMapping("/save") 
    public String save(Course course, HttpServletRequest request) {
        courseService.saveCourse(course);
        request.setAttribute("message", "已添加课程："+course.getName());
        request.getSession().setAttribute(Constants.SEESION_LINK, "course_add");
        return "course/add";
    }
    
}
