package com.gqz.news.model;

import java.sql.Date;

/**
 * 
* @ClassName: discuss
* @Description: TODO(论坛的一个主题 帖子)
* @author ganquanzhong
* @date 2018年1月2日 上午12:14:07
 */
public class Forum {
	private int id;  //论坛的id
	private String name; //发表论坛使用的名称
	private String username; //用户名
	private String email;//邮箱
	private String subject;  //论坛的主题
	private String content; //论坛的内容
	private String pictures; //论坛的图片
	private Date time;//发表论坛的时间
	private int isDel;//是否删除的标志位
	private String formatTime;//格式化后的时间
	
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
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
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
}
