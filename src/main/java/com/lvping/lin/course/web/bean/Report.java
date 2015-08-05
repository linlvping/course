package com.lvping.lin.course.web.bean;

/**
 * @author NAIBA
 * @date 2015年8月5日
 */
public class Report {

    private String student;
    private int keshi;
    private int money;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getKeshi() {
        return keshi;
    }

    public void setKeshi(int keshi) {
        this.keshi = keshi;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    public float getKeshiDisplay() {
        return (float) keshi / 60;
    }

}
