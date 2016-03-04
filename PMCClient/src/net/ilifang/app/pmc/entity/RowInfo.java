package net.ilifang.app.pmc.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RowInfo  implements Serializable{
	private String Name;
	private String classTypeName;
	private String CheckTime;
	private String Title;
	private String PublishTime;
	private String Summary;
	private String Color;
	private int id;
	public RowInfo() {
		super();
	}
	public RowInfo(String title, String publishTime, String summary, String color, int id) {
		super();
		Title = title;
		PublishTime = publishTime;
		Summary = summary;
		Color = color;
		this.id = id;
	}
	public RowInfo (String checktime,int id){
		super();
		CheckTime=checktime;
		this.id = id;
	}
	public RowInfo(String name,String ClassTypeName,int id){
		super();
		Name = name;
		classTypeName = ClassTypeName;
		this.id = id;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}

	public String getCheckTime() {
		return CheckTime;
	}
	public void setCheckTime(String checkTime) {
		CheckTime = checkTime;
	}
	//
	//    private String cName;
	//
	//    private String emps;
	//
	//    private String classTypeName;
	public String getPublishTime() {
		return this.PublishTime;
	}

	public void setPublishTime(String publishTime) {
		this.PublishTime = publishTime;
	}

	public String getSummary() {
		return this.Summary;
	}

	public void setSummary(String summary) {
		this.Summary = summary;
	}

	public String getColor() {
		return this.Color;
	}

	public void setColor(String color) {
		this.Color = color;
	}
	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	//    public void setCName(String cName) {
	//        this.cName = cName;
	//    }
	//
	//    public String getCName() {
	//        return this.cName;
	//    }
	//
	//    public void setEmps(String emps) {
	//        this.emps = emps;
	//    }
	//
	//    public String getEmps() {
	//        return this.emps;
	//    }
	//
	//    public void setClassTypeName(String classTypeName) {
	//        this.classTypeName = classTypeName;
	//    }
	//
	//    public String getClassTypeName() {
	//        return this.classTypeName;
	//    }

}
