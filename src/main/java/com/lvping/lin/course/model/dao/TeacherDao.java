package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lvping.lin.course.model.entity.Teacher;

/**
 *
 * @author beifeng
 * @Date 2015-1-5 
 *
 */
public interface TeacherDao {
    
    @Insert("insert into Teacher(name,phone,course,status,created,updated,location)" +
    		" values(#{name},#{phone},#{course},#{status},#{created},#{updated},#{location})")
    public void save(Teacher teacher);
    
    @Update("update Teacher set status=#{status},updated=#{updated} where id=#{id}")
    public void update(Teacher teacher);
    
    @Select("select * from Teacher where location=#{location} order by status asc, id desc")
    public List<Teacher> getTeacher(int location);
    
    @Select("select * from Teacher where status=0 and location=#{location} order by id")
    public List<Teacher> getValidTeacher(int location);
    
    @Select("select * from Teacher where name=#{param1} and location=#{param2}")
    public Teacher getTeacherByName(String name, int location);

}
