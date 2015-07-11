package com.lvping.lin.course.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lvping.lin.course.model.entity.Location;

/**
 * @author NAIBA  
 * @date 2015年7月11日
 */
public interface LocationDao {
    
    @Insert("insert into Location(name,status,created,updated) values(#{name},#{status},#{created},#{updated})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(Location location);
    
    @Update("update Location set name=#{name},updated=#{updated} where id=#{id}")
    public void updateName(Location location);
    
    @Update("update Location set status=#{status},updated=#{updated} where id=#{id}")
    public void updateStatus(Location location);
    
    @Select("select * from Location")
    public List<Location> getAll();

}
