package com.lvping.lin.course.model.entity;

/**
 * 
 * @author beifeng
 * @Date 2015-1-6
 * 
 */
public class Schedule {

    private int id;
    private String teacher;
    private String student;
    private String date;
    private String course;
    private int scope;
    private int shichang;
    private int fact;
    private int status;
    private int created;
    private int updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public int getShichang() {
        return shichang;
    }

    public void setShichang(int shichang) {
        this.shichang = shichang;
    }

    public int getFact() {
        return fact;
    }

    public void setFact(int fact) {
        this.fact = fact;
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
    
    public String getAction() {
        return "<a href='confirm/"+this.id+"'>课时确认</a>|"+
               "<a href='lack/"+this.id+"'>课时不足</a>|"+
               "<a href='off/"+this.id+"'>学员请假</a>|"+
               "<a href='delete/"+this.id+"'>删除</a>";
    }

    public String getTime() {
        String time = "";
        switch (this.scope) {
            case 1 : 
                time = "08:00-10:00"; 
                break;
            case 2 : 
                time = "10:00-12:00"; 
                break;
            case 3 : 
                time = "13:00-15:00";
                break;
            case 4 : 
                time = "15:00-17:00";
                break;
            case 5 : 
                time = "18:00-20:00";
                break;
            case 6 : 
                time = "20:00-22:00";
                break;
            default : 
                time = "";
                break;
        }
        return time;
    }
    
    public String getStatusDisplay() {
        String display = "";
        switch (this.status) {
            case 0 :
                display = "待确认";
                break;
            case 1 :
                display = "<span style='color:#008B00;'>已正常上课</span>";
                break;
            case 2 :
                display = "<span style='color:#EEC900;'>实际上课"+this.fact+"分钟</span>";
                break;
            case 3 :
                display = "<span style='color:#FF4500;'>请假</span>";
                break;
            default :
                display = "";
                break;
        }
        return display;
    }
    
}
