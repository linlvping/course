package com.lvping.lin.course.model.entity;


/**
 * 
 * @author beifeng
 * @Date 2015-1-5
 * 
 */
public class Teacher {

    private int id;
    private String name;
    private String phone;
    private String course;
    private int status;
    private int created;
    private int updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }
    
    public String getStatusDisplay() {
        return this.status == 0 ? "正常" : "已失效";
    }
    
    public String getAction() {
        return this.status == 0 ? "<a href='invalid/"+this.id+"'>弃用该老师</a>" : "<a href='valid/"+this.id+"'>重新启用</a>";
    }

}
