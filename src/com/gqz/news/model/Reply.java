package com.gqz.news.model;

import java.sql.Date;

/**
 * 
* @ClassName: Reply
* @Description: TODO(论坛回复)
* @author ganquanzhong
* @date 2018年1月2日 上午12:24:09
 */
public class Reply {
	private int id; //回复论坛的id
	private int discussId; //对那一条论坛的回复
	private String name; //用户名
	private String content; //回复的内容
	private String picturess;
	private Date time;
	private int isDel;
	
	private String fromatTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiscussId() {
		return discussId;
	}

	public void setDiscussId(int discussId) {
		this.discussId = discussId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicturess() {
		return picturess;
	}

	public void setPicturess(String picturess) {
		this.picturess = picturess;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getFromatTime() {
		return fromatTime;
	}

	public void setFromatTime(String fromatTime) {
		this.fromatTime = fromatTime;
	}

}
