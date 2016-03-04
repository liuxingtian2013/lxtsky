package net.ilifang.app.pmc.entity;

import net.ilifang.app.commons.utils.StringUtils;
import android.graphics.drawable.Drawable;

/**
 * 活动业务实体
 * 
 * @author bobby
 *
 */
public class Active {

	private int id;
	private Drawable icon;
	private String title;
	private String[] tags;
	private String tagInfo;
	private String desc;
	private String content;

	public Active() {
	}

	public Active(int id, Drawable icon, String title, String tagInfo, String desc, String content) {
		this.id = id;
		this.icon = icon;
		this.title = title;
		this.tagInfo = tagInfo;
		this.desc = desc;
		this.content = content;
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

	public String[] getTags() {
		if (StringUtils.isEmpty(getTagInfo())) {
			return null;
		}
		return getTagInfo().split(",");
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTagInfo() {
		return tagInfo;
	}

	public void setTagInfo(String tagInfo) {
		this.tagInfo = tagInfo;
	}

}
