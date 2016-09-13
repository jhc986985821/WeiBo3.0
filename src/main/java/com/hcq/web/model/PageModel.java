package com.hcq.web.model;

import java.io.Serializable;
import java.util.List;

import com.hcq.bean.Message;

public class PageModel implements Serializable {
	private static final long serialVersionUID = 5743428084353814158L;
	private Integer total=1;   //总页数
	private Integer currPage;   //当前第几页
	private Integer sizePage=3; //每页多少条
	private List<Message>messages;//记录的集合
	private Integer totalRecord;
	
	private Message message=new Message();
	
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public void setList(List<Message> messages) {
		this.messages = messages;
	}
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
		if(currPage == null || currPage <= 0){
			currPage =1;
		}
		
		/*if(currPage > total){
			currPage = total;
		}*/
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getSizePage() {
		if(sizePage == null || sizePage ==0){
			sizePage = 5;
		}
		return sizePage;
	}
	public void setSizePage(Integer sizePage) {
		this.sizePage = sizePage;
	}
	public PageModel(Integer total, Integer currPage, Integer sizePage,
			List<Message> messages, Integer totalRecord, Message message) {
		super();
		this.total = total;
		this.currPage = currPage;
		this.sizePage = sizePage;
		this.messages = messages;
		this.totalRecord = totalRecord;
		this.message = message;
	}
	public PageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PageModel [total=" + total + ", currPage=" + currPage
				+ ", sizePage=" + sizePage + ", list=" + messages
				+ ", totalRecord=" + totalRecord + ", message=" + message + "]";
	}
	
	
	
}
