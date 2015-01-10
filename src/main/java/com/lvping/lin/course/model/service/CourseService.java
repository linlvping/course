package com.lvping.lin.course.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.CourseDao;
import com.lvping.lin.course.model.entity.Course;

/**
 *
 * @author beifeng
 * @Date 2015-1-11 
 *
 */
@Service
public class CourseService {
    
    @Resource
    private CourseDao courseDao;
    
    public void saveCourse(Course course) {
        course.setCreated(CommonUtils.getCurrentTime());
        courseDao.save(course);
    }
    
    public List<Course> getCourse() {
        return courseDao.get();
    }

}
