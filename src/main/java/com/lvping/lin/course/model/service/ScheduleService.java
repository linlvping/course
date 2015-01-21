package com.lvping.lin.course.model.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lvping.lin.course.common.CommonUtils;
import com.lvping.lin.course.model.dao.ScheduleDao;
import com.lvping.lin.course.model.dao.StudentDao;
import com.lvping.lin.course.model.entity.Schedule;
import com.lvping.lin.course.model.entity.Teacher;
import com.lvping.lin.course.model.entity.User;

/**
 *
 * @author beifeng
 * @Date 2015-1-6 
 *
 */
@Service
public class ScheduleService {
    
    @Resource
    private ScheduleDao scheduleDao;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentDao studentDao;
    
    public void addSchedule(Schedule schedule) {
        int current = CommonUtils.getCurrentTime();
        schedule.setCreated(current);
        schedule.setUpdated(current);
        schedule.setStatus(0);
        schedule.setFact(0);
        schedule.setShichang(120);
        scheduleDao.save(schedule);
    }
    
    public Map<String, Map<Integer, Map<String, List<Schedule>>>> getSchedule(String beginDate, String endDate, User user) {
        List<Schedule> list = null;
        if (user.getPriority() == 100) {
            list = scheduleDao.getSchedule(beginDate, endDate);
        } else {
            list = scheduleDao.getScheduleByTeacher(beginDate, endDate, user.getName());
        }
        //老师=>时间段=>日期=>学生列表
        Map<String, Map<Integer, Map<String, List<Schedule>>>> map = Maps.newHashMap();
        for (Schedule schedule : list) {
            String teacher = schedule.getTeacher();
            Map<Integer, Map<String, List<Schedule>>> tMap;
            if (map.containsKey(teacher)) {
                tMap = map.get(teacher); 
            } else {
                tMap = Maps.newTreeMap();
                for (int i=1; i<=5; i++) {
                    Map<String, List<Schedule>> lMap = Maps.newHashMap();
                    tMap.put(i, lMap);
                }
                map.put(teacher, tMap);
            }
            Map<String, List<Schedule>> lMap = tMap.get(schedule.getScope());
            String date = schedule.getDate();
            List<Schedule> sl;
            if (lMap.containsKey(date)) {
                sl = lMap.get(date);
            } else {
                sl = Lists.newArrayList();
                lMap.put(date, sl);
            }
            sl.add(schedule);
        }
        Map<String, Map<Integer, Map<String, List<Schedule>>>> returnMap = Maps.newHashMap();
        for (String teacherName : map.keySet()) {
            Teacher teacher = teacherService.getTeacherByName(teacherName);
            String teacherDisplay = teacherName + " (" + teacher.getCourse() + ") " + teacher.getPhone();
            returnMap.put(teacherDisplay, map.get(teacherName));
        }
        return returnMap;
    }
    
    public List<Schedule> getNeedActionSchedule() {
        return scheduleDao.getScheduleByStatus(0);
    }
    
    public List<Schedule> getScheduleByStudent(String student) {
        return scheduleDao.getScheduleByStudent(student);
    }
    
    public Schedule getScheduleById(int id) {
        return scheduleDao.getScheduleById(id);
    }
    
    public void offCourse(int id) {
        int current = CommonUtils.getCurrentTime();
        Schedule s = new Schedule();
        s.setUpdated(current);
        s.setStatus(3);
        s.setFact(0);
        s.setId(id);
        scheduleDao.updateCourse(s);
    }
    
    public void delete(int id) {
        scheduleDao.delete(id);
    }
    
    @Transactional
    public void confirm(int id) {
        int current = CommonUtils.getCurrentTime();
        Schedule s = scheduleDao.getScheduleById(id);
        s.setFact(s.getShichang());
        s.setStatus(1);
        s.setUpdated(current);
        scheduleDao.updateCourse(s);
        studentDao.update(s.getShichang(), current, s.getStudent());
    }
    
    @Transactional
    public void lackConfirm(int id, int fact) {
        int current = CommonUtils.getCurrentTime();
        Schedule s = scheduleDao.getScheduleById(id);
        s.setFact(fact);
        s.setStatus(2);
        s.setUpdated(current);
        scheduleDao.updateCourse(s);
        studentDao.update(fact, current, s.getStudent());
    }

}
