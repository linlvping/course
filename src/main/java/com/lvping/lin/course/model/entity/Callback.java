package com.lvping.lin.course.model.entity;

import com.lvping.lin.course.common.CommonUtils;

/**
 *
 * @author beifeng
 * @Date 2015-1-7 
 *
 */
public class Callback {
    
    private int id;
    private String content;
    private String student;
    private String operator;
    private int created;
    private int location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }
    
    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getTime() {
        return CommonUtils.formatTime(this.created);
    }

}
