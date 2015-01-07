package com.lvping.lin.course.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.StudentDao;
import com.lvping.lin.course.model.entity.Student;

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
    
    public void addStudent(Student student) {
        int current = CommonUtils.getCurrentTime();
        student.setStatus(0);
        student.setCreated(current);
        student.setUpdated(current);
        student.setGift(CommonUtils.hourToMin(student.getGift()));
        student.setPeriod(CommonUtils.hourToMin(student.getPeriod()));
        student.setRemain(student.getGift() + student.getPeriod());
        studentDao.save(student);
    }
    
    public List<Student> getStudent() {
        return studentDao.getStudent();
    }
    
    public List<Student> getValidStudent() {
        return studentDao.getValidStudent();
    }
    
    public Student getByName(String name) {
        return studentDao.getByName(name);
    }

}
