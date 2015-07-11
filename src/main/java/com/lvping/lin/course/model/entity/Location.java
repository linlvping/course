package com.lvping.lin.course.model.entity;

import java.io.Serializable;

import com.lvping.lin.course.common.CommonUtils;

/**
 * @author NAIBA  
 * @date 2015年7月11日
 */
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
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
    
    public String getCreatedDisplay() {
        return CommonUtils.formatTime(created);
    }
    
    public String getUpdatedDisplay() {
        return CommonUtils.formatTime(updated);
    }
    
    public String getStatusDisplay() {
        return this.status == 0 ? "正常" : "已失效";
    }
    
    public String getAction() {
        String action = this.status == 0 ? "<a href='invalid/"+this.id+"'>弃用该校区</a>" : "<a href='valid/"+this.id+"'>重新启用该校区</a>";
        return "<a href='javascript:modifyName("+this.id+")'>修改校区名称</a>|" + action;
    }

}
