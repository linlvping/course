package com.lvping.lin.course.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.ScheduleDao;
import com.lvping.lin.course.model.dao.StudentDao;
import com.lvping.lin.course.model.entity.Student;
import com.lvping.lin.course.model.entity.User;

/**
 *
 * @author beifeng
 * @Date 2015-1-5 
 *
 */
@Service
public class PeriodService {
    
    @Resource
    private StudentDao studentDao;
    @Resource
    private ScheduleDao scheduleDao;
    
    public void addStudent(Student student) {
        int current = CommonUtils.getCurrentTime();
        student.setStatus(0);
        student.setCreated(current);
        student.setUpdated(current);
        student.setGift(CommonUtils.hourToMin(student.getGift()));
        student.setPeriod(CommonUtils.hourToMin(student.getPeriod()));
        student.setRemain((int)(student.getGift() + student.getPeriod()));
        student.setLocation(CommonUtils.getLocation());
        studentDao.save(student);
    }
    
    public void updateStudent(Student student) {
        int current = CommonUtils.getCurrentTime();
        student.setUpdated(current);
        Student old = studentDao.getByName(student.getName());
        student.setGift(CommonUtils.hourToMin(student.getGift()));
        student.setPeriod(CommonUtils.hourToMin(student.getPeriod()));
        int remainChange = (int)(student.getPeriod() + student.getGift() - old.getPeriod() - old.getGift());
        student.setRemain(old.getRemain() + remainChange);
        studentDao.updateStudent(student);
    }
    
    public void upgrade() {
        studentDao.upgrade();
    }
    
    public List<Student> getStudent(User user) {
        if (user.getPriority() >= 100) {
            return studentDao.getStudent(CommonUtils.getLocation());
        } else {
            List<String> list = scheduleDao.getStudentByTeacher(user.getName(), CommonUtils.getLocation());
            List<Student> result = new ArrayList<Student>();
            for (String s : list) {
                Student student = new Student();
                student.setName(s);
                result.add(student);
            }
            return result;
        }
    }
    
    public List<Student> getValidStudent() {
        return studentDao.getValidStudent(CommonUtils.getLocation());
    }
    
    public Student getByName(String name) {
        return studentDao.getByName(name);
    }

}
