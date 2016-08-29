package com.hcq.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageReply implements Serializable{
	private static final long serialVersionUID = 2046122919404930992L;
	private int mid;
	private int RUid;
	private String Runame;
	private String Ruimage;
	private String Rcontent;
	private String Rdatetime;
	
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getRUid() {
		return RUid;
	}
	public void setRUid(int rUid) {
		RUid = rUid;
	}
	public String getRuname() {
		return Runame;
	}
	public void setRuname(String runame) {
		Runame = runame;
	}
	public String getRuimage() {
		return Ruimage;
	}
	public void setRuimage(String ruimage) {
		Ruimage = ruimage;
	}
	public String getRcontent() {
		return Rcontent;
	}
	public void setRcontent(String rcontent) {
		Rcontent = rcontent;
	}
	public String getRdatetime() {
		Date date=new Date();
		SimpleDateFormat simpleFormatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Rdatetime = simpleFormatter.format(date);
		return Rdatetime;
	}
	public void setRdatetime(String rdatetime) {
		Date date=new Date();
		SimpleDateFormat simpleFormatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Rdatetime =simpleFormatter.format(date);
	}
	public MessageReply(int mid, int rUid, String runame, String ruimage, String rcontent, String rdatetime) {
		super();
		this.mid = mid;
		RUid = rUid;
		Runame = runame;
		Ruimage = ruimage;
		Rcontent = rcontent;
		Rdatetime = rdatetime;
	}
	public MessageReply() {
		super();
	}
	@Override
	public String toString() {
		return "MessageReply [mid=" + mid + ", RUid=" + RUid + ", Runame=" + Runame + ", Ruimage=" + Ruimage
				+ ", Rcontent=" + Rcontent + ", Rdatetime=" + Rdatetime + "]";
	}
}
