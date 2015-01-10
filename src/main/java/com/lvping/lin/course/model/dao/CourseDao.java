package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lvping.lin.course.model.entity.Course;

/**
 *
 * @author beifeng
 * @Date 2015-1-11 
 *
 */
public interface CourseDao {
    
    @Insert("insert into Course(name,created) values(#{name},#{created})")
    public void save(Course course);
    
    @Select("select * from Course order by created")
    public List<Course> get();

}
