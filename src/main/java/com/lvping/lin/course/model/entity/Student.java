package com.lvping.lin.course.model.entity;

import com.lvping.lin.course.common.CommonUtils;

/**
 * 
 * @author beifeng
 * @Date 2015-1-5
 * 
 */
public class Student {

    private String id;
    private String name;
    private String pact;
    private int grade;
    private String banji;
    private String address;
    private String tel;
    private String phone;
    private String course;
    private String model;
    private int period;
    private int price;
    private int gift;
    private String tiaojian;
    private int amount;
    private String description;
    private String date;
    private int status;
    private int remain;
    private String operator;
    private int created;
    private int updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPact() {
        return pact;
    }

    public void setPact(String pact) {
        this.pact = pact;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public String getTiaojian() {
        return tiaojian;
    }

    public void setTiaojian(String tiaojian) {
        this.tiaojian = tiaojian;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
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

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }
    
    public String getAll() {
        return CommonUtils.minToHour(this.period + this.gift);
    }
    
    public String getRemainDisplay() {
        String hour = CommonUtils.minToHour(this.remain);
        return this.remain > 1200 ? hour : "<font style='color:red;'>"+hour+"</font>";
    }
    
    public String getCost() {
        return CommonUtils.minToHour(this.period + this.gift - this.remain);
    }
    
    public String[] getCourseList() {
        return CommonUtils.isEmpty(this.course) ? null : this.course.split(",");
    }
    
    public String getStatusDisplay() {
        return this.status == 0 ? "正常" : "失效";
    }
    
    public int getPeriodDisplay() {
        return this.period / 60;
    }
    
    public int getGiftDisplay() {
        return this.gift / 60;
    }
    
    public String getDetail() {
        return "<a href='detail?name="+this.name+"'>详细信息</a>";
    }
    
    public String getPaike() {
        return "<a href='paike?name="+this.name+"'>排课信息</a>";
    }
    
    public String getCallback() {
        return "<a href='show?name="+this.name+"'>回访信息</a>";
    }
    
    public String getGradeDisplay() {
        String result = "";
        switch (this.grade) {
            case 1 : 
                result = "小学一年级"; 
                break;
            case 2 : 
                result = "小学二年级"; 
                break;
            case 3 : 
                result = "小学三年级"; 
                break;
            case 4 : 
                result = "小学四年级"; 
                break;
            case 5 : 
                result = "小学五年级"; 
                break;
            case 6 : 
                result = "小学六年级"; 
                break;
            case 7 : 
                result = "初一"; 
                break;
            case 8 : 
                result = "初二"; 
                break;
            case 9 : 
                result = "初三"; 
                break;
            case 10 : 
                result = "高一"; 
                break;
            case 11 : 
                result = "高二"; 
                break;
            case 12 : 
                result = "高三"; 
                break;
            default : 
                result = "未知"; 
                break;
        }
        return result;
    }

}
