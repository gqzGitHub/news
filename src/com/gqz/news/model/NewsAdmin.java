package com.gqz.news.model;

import java.sql.Date;

/**
 * 
* @ClassName: NewsAdmin
* @Description: TODO(与表NewsAdmin的操作)
* @author ganquanzhong
* @date 2017-11-27 上午10:18:09
 */
public class NewsAdmin {
	private int id;
	private String username;
	private String password;
	private Date lastLogin;
	private String lastLoginIp;
	private String type;
	private int isDel;
	
	private String formatTime;//格式化时间
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public NewsAdmin() {
		super();
	}
	public NewsAdmin(String username, String password, Date lastLogin,
			String lastLoginIp, String type, int isDel) {
		super();
		this.username = username;
		this.password = password;
		this.lastLogin = lastLogin;
		this.lastLoginIp = lastLoginIp;
		this.type = type;
		this.isDel = isDel;
	}
	
	
}
