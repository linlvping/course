package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lvping.lin.course.model.entity.Schedule;

/**
 *
 * @author beifeng
 * @Date 2015-1-6 
 *
 */
public interface ScheduleDao {
    
    @Insert("insert into Schedule(teacher,date,student,course,scope,shichang,fact,status,created,updated)" +
            " values(#{teacher},#{date},#{student},#{course},#{scope},#{shichang},#{fact},#{status},#{created},#{updated})")
    public void save(Schedule schedule);
    
    @Delete("delete from Schedule where id=#{id}")
    public void delete(int id);
    
    @Update("update Schedule set fact=#{fact},status=#{status},updated=#{updated} where id=#{id}")
    public void updateCourse(Schedule schedule);
    
    @Select("select * from Schedule where date>=#{param1} and date<=#{param2}")
    public List<Schedule> getSchedule(String beginDate, String endDate);
    
    @Select("select * from Schedule where date>=#{param1} and date<=#{param2} and teacher=#{param3}")
    public List<Schedule> getScheduleByTeacher(String beginDate, String endDate, String teacher);
    
    @Select("select * from Schedule where status=#{status} order by id")
    public List<Schedule> getScheduleByStatus(int status);
    
    @Select("select * from Schedule where id=#{id}")
    public Schedule getScheduleById(int id);
    
    @Select("select * from Schedule where student=#{student} order by id desc")
    public List<Schedule> getScheduleByStudent(String student);

}
