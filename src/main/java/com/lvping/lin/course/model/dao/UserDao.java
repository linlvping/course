package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lvping.lin.course.model.entity.User;

/**
 *
 * @author beifeng
 * @Date 2015-1-6 
 *
 */
public interface UserDao {
    
    @Insert("insert into User(name,password,priority,created,updated) " +
    		"values(#{name},#{password},#{priority},#{created},#{updated})")
    public void save(User user);
    
    @Update("update User set password=#{password} where id=#{id}")
    public void updatePassword(User user);
    
    @Update("update User set status=#{status} where id=#{id}")
    public void updateStatus(User user);
    
    @Select("select * from User where name=#{name}")
    public User getByName(String name);
    
    @Select("select * from User order by status asc, id desc")
    public List<User> getAll();

}
