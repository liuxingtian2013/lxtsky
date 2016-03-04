package net.ilifang.app.pmc.entity;

import java.util.Date;

import android.graphics.drawable.Drawable;

public class MsgInfo {

    private int id;
    private Drawable icon;
    private String title;
    private String desc;
    private Date createDate;
    private String formatedTime;

    public MsgInfo() {
        super();
    }

    public MsgInfo(int id, Drawable icon, String title, String desc, Date createDate) {
        super();
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.createDate = createDate;
        this.formatedTime = "2小时前";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFormatedTime() {
        return formatedTime;
    }

    public void setFormatedTime(String formatedTime) {
        this.formatedTime = formatedTime;
    }

}
