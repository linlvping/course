package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lvping.lin.course.model.entity.Schedule;
import com.lvping.lin.course.web.bean.Report;

/**
 *
 * @author beifeng
 * @Date 2015-1-6 
 *
 */
public interface ScheduleDao {
    
    @Insert("insert into Schedule(teacher,date,student,course,scope,shichang,fact,status,created,updated,location,money)" +
            " values(#{teacher},#{date},#{student},#{course},#{scope},#{shichang},#{fact},#{status},#{created},#{updated},#{location},#{money})")
    public void save(Schedule schedule);
    
    @Delete("delete from Schedule where id=#{id}")
    public void delete(int id);
    
    @Update("update Schedule set fact=#{fact},status=#{status},updated=#{updated},money=#{money} where id=#{id}")
    public void updateCourse(Schedule schedule);
    
    @Select("select * from Schedule where date>=#{param1} and date<=#{param2} and location=#{param3}")
    public List<Schedule> getSchedule(String beginDate, String endDate, int location);
    
    @Select("select * from Schedule where date>=#{param1} and date<=#{param2} and teacher=#{param3} and location=#{param4}")
    public List<Schedule> getScheduleByTeacher(String beginDate, String endDate, String teacher, int location);
    
    @Select("select * from Schedule where status=#{param1} and location=#{param2} order by id")
    public List<Schedule> getScheduleByStatus(int status, int location);
    
    @Select("select * from Schedule where id=#{id}")
    public Schedule getScheduleById(int id);
    
    @Select("select * from Schedule where student=#{student} order by id desc")
    public List<Schedule> getScheduleByStudent(String student);
    
    @Select("select distinct student from Schedule where teacher=#{param1} and location=#{param2}")
    public List<String> getStudentByTeacher(String teacher, int location);
    
    @Select("select student,coalesce(sum(fact),0) as keshi,coalesce(sum(money),0) as money from Schedule where date>=#{param1} and date<=#{param2} and location=#{param3} group by student")
    public List<Report> getReport(String beginDate, String endDate, int location);

}
