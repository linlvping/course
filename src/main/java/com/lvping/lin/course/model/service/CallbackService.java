package com.lvping.lin.course.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.CallbackDao;
import com.lvping.lin.course.model.entity.Callback;

/**
 *
 * @author beifeng
 * @Date 2015-1-7 
 *
 */
@Service
public class CallbackService {
    
    @Resource
    private CallbackDao callbackDao;
    
    public void save(Callback callback) {
        int current = CommonUtils.getCurrentTime();
        callback.setCreated(current);
        callback.setLocation(CommonUtils.getLocation());
        callbackDao.save(callback);
    }
    
    public List<Callback> getByStudent(String student) {
        return callbackDao.getByStudent(student);
    }

}
