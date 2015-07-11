package com.lvping.lin.course.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.LocationDao;
import com.lvping.lin.course.model.dao.UserDao;
import com.lvping.lin.course.model.entity.Location;
import com.lvping.lin.course.model.entity.User;

/**
 * @author NAIBA  
 * @date 2015年7月11日
 */
@Service
public class LocationService {
    
    @Resource
    private LocationDao locationDao;
    @Resource
    private UserDao userDao;
    
    public void addLocation(String name, String adminName, String adminPassword) {
        int time = CommonUtils.getCurrentTime();
        Location location = new Location();
        location.setName(name);
        location.setStatus(0);
        location.setCreated(time);
        location.setUpdated(time);
        locationDao.insert(location);
        User user = new User();
        user.setName(adminName);
        user.setPassword(adminPassword);
        user.setPriority(100);
        user.setStatus(0);
        user.setCreated(time);
        user.setUpdated(time);
        user.setLocation(location.getId());
        userDao.save(user);
    }
    
    public List<Location> getAll() {
        return locationDao.getAll();
    }
    
    public void updateStatus(Location location) {
        location.setUpdated(CommonUtils.getCurrentTime());
        locationDao.updateStatus(location);
        userDao.updateStatusByLocation(location.getStatus(), location.getId());
    }
    
    public void updateName(Location location) {
        location.setUpdated(CommonUtils.getCurrentTime());
        locationDao.updateName(location);
    }

}
