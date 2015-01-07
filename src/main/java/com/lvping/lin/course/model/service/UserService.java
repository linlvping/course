package com.lvping.lin.course.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.UserDao;
import com.lvping.lin.course.model.entity.User;

/**
 *
 * @author beifeng
 * @Date 2015-1-6 
 *
 */
@Service
public class UserService {
    
    @Resource
    private UserDao userDao;
    
    public void save(User user) {
        int current = CommonUtils.getCurrentTime();
        user.setCreated(current);
        user.setUpdated(current);
        user.setPriority(0);
        userDao.save(user);
    }
    
    public void updatePassword(User user) {
        user.setUpdated(CommonUtils.getCurrentTime());
        userDao.updatePassword(user);
    }
    
    public void updateStatus(int id, int status) {
        User user = new User();
        user.setUpdated(CommonUtils.getCurrentTime());
        user.setId(id);
        user.setStatus(status);
        userDao.updateStatus(user);
    }
    
    public User getByName(String name) {
       return userDao.getByName(name);
    }
    
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

}
