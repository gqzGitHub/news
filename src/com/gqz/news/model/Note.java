package com.gqz.news.model;

import java.util.Date;
/**
 * 
* @ClassName: Note
* @Description: TODO(新闻评论实体对象)
* @author ganquanzhong
* @date 2017-12-20 上午11:50:26
 */
public class Note {
	private int noteId;
	private String username;
	private String content;
	private Date noteTime;
	private String noteTitle;
	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	private int newsId;
	private int isDel;
	
	private String time;//格式化的时间

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNoteTime() {
		return noteTime;
	}

	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
