package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lvping.lin.course.model.entity.Callback;

/**
 *
 * @author beifeng
 * @Date 2015-1-7 
 *
 */
public interface CallbackDao {
    
    @Insert("insert into Callback(content,student,operator,created,location) values (#{content},#{student},#{operator},#{created},#{location})")
    public void save(Callback callback);
    
    @Select("select * from Callback where student=#{student} order by id desc")
    public List<Callback> getByStudent(String student);

}
