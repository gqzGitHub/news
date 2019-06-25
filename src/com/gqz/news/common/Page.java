package com.gqz.news.common;

import java.util.List;

/**
 * 
* @ClassName: Page
* @Description: TODO(用于分页的数据显示)
* @author ganquanzhong
* @date 2017-12-5 上午09:44:07
 */
public class Page {
	private int pageNumber;//当前需要显示的页码
	private int pageSize=10;//每页显示的记录数
	private int totalPageNumber;//总页数
	private int totalRecordNumber;//总记录数
	private int startIndex;//每页开始的记录的索引limit startIndex ,pageSize
	private int prePageNumber;//上一页
	private int nextPageNumber;//下一页
	private String url;//查询分页的请求地址
	private List records;//每页显示的记录
	
	public Page(int pageNumber, int totalRecordNumber) {
		super();
		this.pageNumber = pageNumber;
		this.totalRecordNumber = totalRecordNumber;
		//计算总页数		
		this.totalPageNumber=totalRecordNumber%pageSize==0?
				totalRecordNumber/pageSize:totalRecordNumber/pageSize+1;
		//计算每页开始的记录的索引
		this.startIndex=(pageNumber-1)*pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPageNumber() {
		return totalPageNumber;
	}
	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}
	public int getTotalRecordNumber() {
		return totalRecordNumber;
	}
	public void setTotalRecordNumber(int totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public int getPrePageNumber() {
		prePageNumber=pageNumber-1;
		if (prePageNumber<1) {
			prePageNumber=1;
		}
		return prePageNumber;
	}
	
	public void setPrePageNumber(int prePageNumber) {
		this.prePageNumber = prePageNumber;
	}
	
	public int getNextPageNumber() {
		nextPageNumber=pageNumber+1;
		if (nextPageNumber>totalPageNumber) {
			nextPageNumber=totalPageNumber;
		}
		return nextPageNumber;
	}
	
	public void setNextPageNumber(int nextPageNumber) {
		this.nextPageNumber = nextPageNumber;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
}
