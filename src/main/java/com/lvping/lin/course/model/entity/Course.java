package com.lvping.lin.course.model.entity;
/**
 *
 * @author beifeng
 * @Date 2015-1-11 
 *
 */
public class Course {
    
    private String name;
    private int created;
    private int location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
