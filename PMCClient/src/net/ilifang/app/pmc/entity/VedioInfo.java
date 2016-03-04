package net.ilifang.app.pmc.entity;

import java.util.Date;

import android.graphics.drawable.Drawable;

public class VedioInfo {

    private int id;
    private Drawable icon;
    private String title;
    private String desc;
    private Date createDate;

    public VedioInfo() {
        super();
    }

    public VedioInfo(int id, Drawable icon, String title, String desc, Date createDate) {
        super();
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.createDate = createDate;
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

}
