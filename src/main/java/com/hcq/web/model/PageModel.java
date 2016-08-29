package com.hcq.web.model;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable {
	private static final long serialVersionUID = 5743428084353814158L;
	private Integer total=1;   //总页数
	private Integer currPage;   //当前第几页
	private Integer sizePage=5; //每页多少条
	private List<T>list;//记录的集合
	private Integer totalRecord;
	
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getSizePage() {
		return sizePage;
	}
	public void setSizePage(Integer sizePage) {
		this.sizePage = sizePage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
