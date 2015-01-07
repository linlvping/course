package com.lvping.lin.course.model.dao;

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
    
    @Insert("insert into User(name,password,priority,created,update) " +
    		"values(#{name},#{password},#{priority},#{created},#{update})")
    public void save(User user);
    
    @Update("update User set password=#{password} where id=#{id}")
    public void updatePassword(User user);
    
    @Update("update User set status=#{status} where id=#{id}")
    public void updateStatus(User user);
    
    @Select("select * from User where name=#{name}")
    public User getByName(String name);

}
