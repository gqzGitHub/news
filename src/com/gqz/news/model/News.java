package com.gqz.news.model;

import java.util.Date;

public class News {
	private int newsId;
	private int classId;
	private String headTitle;
	private String content;
	private String author;
	private String editor;
	private String newsFrom;
	private int top;
	private Date newsTime;
	private int hits;
	private String state;
	private String pictures;
	private int isDel;
	
	//保存新闻的评论数
	private int noteCount;
	public int getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}
	/**
	 在NewsDAO编写DAO方法，根据新闻分类的classId获取该分类的新闻列表
	为了得到新闻对应的新闻分类的名称，所以News.java类增加一个属性className
	 */
	private String time;//存储格式化后的时间
	private String className;//存储news表关联查询newsclass表时，新增的新闻分类名
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getHeadTitle() {
		return headTitle;
	}
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getNewsFrom() {
		return newsFrom;
	}
	public void setNewsFrom(String newsFrom) {
		this.newsFrom = newsFrom;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public Date getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(int newsId, int classId, String headTitle, String content,
			String author, String editor, String newsFrom, int top, int hits,
			String state, String pictures, int isDel) {
		super();
		this.newsId = newsId;
		this.classId = classId;
		this.headTitle = headTitle;
		this.content = content;
		this.author = author;
		this.editor = editor;
		this.newsFrom = newsFrom;
		this.top = top;
		this.hits = hits;
		this.state = state;
		this.pictures = pictures;
		this.isDel = isDel;
	}
	
}
