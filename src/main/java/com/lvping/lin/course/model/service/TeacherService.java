package com.lvping.lin.course.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.TeacherDao;
import com.lvping.lin.course.model.entity.Teacher;

/**
 *
 * @author beifeng
 * @Date 2015-1-5 
 *
 */
@Service
public class TeacherService {
    
    @Resource
    private TeacherDao teacherDao;
    
    public void add(Teacher teacher) {
        int current = CommonUtils.getCurrentTime();
        teacher.setStatus(0);
        teacher.setCreated(current);
        teacher.setUpdated(current);
        teacher.setLocation(CommonUtils.getLocation());
        teacherDao.save(teacher);
    }
    
    public List<Teacher> getTeacher() {
        return teacherDao.getTeacher(CommonUtils.getLocation());
    }
    
    public List<Teacher> getValidTeacher() {
        return teacherDao.getValidTeacher(CommonUtils.getLocation());
    }
    
    public Teacher getTeacherByName(String name) {
        return teacherDao.getTeacherByName(name, CommonUtils.getLocation());
    }
    
    public void updateTeacher(int id, int status) {
        Teacher t = new Teacher();
        t.setId(id);
        t.setStatus(status);
        t.setUpdated(CommonUtils.getCurrentTime());
        teacherDao.update(t);
    }

}
