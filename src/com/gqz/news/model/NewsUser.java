package com.gqz.news.model;

import java.util.Date;

/**
 * 
* @ClassName: NewsUser
* @Description: TODO(新闻用户)
* @author ganquanzhong
* @date 2017-12-21 上午11:33:04
 */
public class NewsUser {
	private int id;
	private String userName;
	private String password;
	private String sex;
	private String question;
	private String answer;
	private String emailAddr;
	private String qq;
	private Date regTime;
	private int isDel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public NewsUser(String userName, String password, String sex,
			String question, String answer, String emailAddr, String qq,
			Date regTime, int isDel) {
		super();
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.question = question;
		this.answer = answer;
		this.emailAddr = emailAddr;
		this.qq = qq;
		this.regTime = regTime;
		this.isDel = isDel;
	}
	public NewsUser() {
		super();
	}
}
