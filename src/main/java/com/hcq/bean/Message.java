package com.hcq.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Message implements Serializable{
	private static final long serialVersionUID = -895972845813178019L;
	private int Mid;
	private Users user;
	private Integer Cid;
	private String Mcontent;
	private String Mdatetime;
	private List<MessageReply> messageReply;
	private int praiseCnt;  //点赞数
	
	
	public List<MessageReply> getMessageReply() {
		return messageReply;
	}
	public void setMessageReply(List<MessageReply> messageReply) {
		this.messageReply = messageReply;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public int getMid() {
		return Mid;
	}
	public void setMid(int mid) {
		Mid = mid;
	}
	

	public String getMcontent() {
		return Mcontent;
	}
	public void setMcontent(String mcontent) {
		Mcontent = mcontent;
	}
	public String getMdatetime() {
		Date date=new Date();
		SimpleDateFormat simpleFormatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Mdatetime = simpleFormatter.format(date);
		return Mdatetime;
	}
	public void setMdatetime(String mdatetime) {
		Date date=new Date();
		SimpleDateFormat simpleFormatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Mdatetime = simpleFormatter.format(date);
	}
	public Message() {
	}
	public Message(int mid, String mcontent) {
		super();
		Mid = mid;
		Mcontent = mcontent;
	}
	public Integer getCid() {
		return Cid;
	}
	public void setCid(Integer cid) {
		Cid = cid;
	}
	public Message(int mid, Users user, Integer cid, String mcontent, String mdatetime,
			List<MessageReply> messageReply) {
		super();
		Mid = mid;
		this.user = user;
		Cid = cid;
		Mcontent = mcontent;
		Mdatetime = mdatetime;
		this.messageReply = messageReply;
	}
	public int getPraiseCnt() {
		return praiseCnt;
	}
	public void setPraiseCnt(int praiseCnt) {
		this.praiseCnt = praiseCnt;
	}
	@Override
	public String toString() {
		return "Message [Mid=" + Mid + ", user=" + user + ", Cid=" + Cid + ", Mcontent=" + Mcontent + ", Mdatetime="
				+ Mdatetime + ", messageReply=" + messageReply + ", praiseCnt=" + praiseCnt + "]";
	}
	public Message(int mid, Users user, Integer cid, String mcontent, String mdatetime, List<MessageReply> messageReply,
			int praiseCnt) {
		super();
		Mid = mid;
		this.user = user;
		Cid = cid;
		Mcontent = mcontent;
		Mdatetime = mdatetime;
		this.messageReply = messageReply;
		this.praiseCnt = praiseCnt;
	}
}
