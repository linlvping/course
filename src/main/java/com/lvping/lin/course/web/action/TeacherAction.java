package com.lvping.lin.course.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvping.lin.course.common.TableDataUtils;
import com.lvping.lin.course.model.entity.Teacher;
import com.lvping.lin.course.model.service.TeacherService;

/**
 *
 * @author beifeng
 * @Date 2015-1-5 
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherAction {
    
    @Resource
    private TeacherService teacherService;
    
    @RequestMapping("/add")
    public String add() {
        return "teacher/add";
    }
    
    @RequestMapping("/list")
    public String list() {
        return "teacher/list";
    }
    
    @RequestMapping("/save")
    public String save(Teacher teacher) {
        teacherService.add(teacher);
        return "redirect:list";
    }
    
    @RequestMapping("/data") 
    @ResponseBody
    public Map<String, Object> data() {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            List<Teacher> list = teacherService.getTeacher();
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }
    
    @RequestMapping("/valid/{id}")
    public String valid(@PathVariable int id) {
        teacherService.updateTeacher(id, 0);
        return "redirect:../list";
    }

    @RequestMapping("/invalid/{id}")
    public String invalid(@PathVariable int id) {
        teacherService.updateTeacher(id, 1);
        return "redirect:../list";
    }
    
}
