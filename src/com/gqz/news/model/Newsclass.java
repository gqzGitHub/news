package com.gqz.news.model;

/**
 * 
* @ClassName: Newsclass
* @Description: TODO(  newsclass表中的字段信息  )
* @author ganquanzhong
* @date 2017-11-29 上午11:19:43
 */
public class Newsclass {
	private int classId;
	private String  content;
	private int isDel;
	
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public Newsclass(int classId, String content, int isDel) {
		super();
		this.classId = classId;
		this.content = content;
		this.isDel = isDel;
	}
	public Newsclass() {
		super();
	}
}
