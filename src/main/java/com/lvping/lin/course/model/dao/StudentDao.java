package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lvping.lin.course.model.entity.Student;

/**
 *
 * @author beifeng
 * @Date 2015-1-5 
 *
 */
public interface StudentDao {
    
    @Insert("insert into Student(id,name,pact,grade,banji,address,tel,phone,course,model,period,price,gift,tiaojian,amount,description,date,status,remain,created,updated)" +
            " values(#{id},#{name},#{pact},#{grade},#{banji},#{address},#{tel},#{phone},#{course},#{model},#{period},#{price},#{gift},#{tiaojian},#{amount},#{description},#{date},#{status},#{remain},#{created},#{updated})")
    public void save(Student student);
    
    @Update("update Student set id=#{id},pact=#{pact},banji=#{banji},address=#{address},tel=#{tel},phone=#{phone},course=#{course},model=#{model},period=#{period},price=#{price}," +
    		"gift=#{gift},tiaojian=#{tiaojian},amount=#{amount},description=#{description},date=#{date},remain=#{remain},updated=#{updated} where name=#{name}")
    public void updateStudent(Student student);
    
    @Update("update Student set remain=remain-${param1},updated=#{param2} where name=#{param3}")
    public void update(int cost, int updated, String name);
    
    @Update("update Student set grade=grade+1")
    public void upgrade();
    
    @Select("select * from Student order by remain,status")
    public List<Student> getStudent();
    
    @Select("select * from Student where status=0 and remain>0 order by id")
    public List<Student> getValidStudent();
    
    @Select("select * from Student where name=#{name}")
    public Student getByName(String name);
    
}
