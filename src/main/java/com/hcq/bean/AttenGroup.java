package com.hcq.bean;

import java.io.Serializable;

public class AttenGroup implements Serializable{
	private static final long serialVersionUID = -7598682118081575569L;
	private int NGid;
	private int uid;
	private int Uider;
	private Users user;
	private String NGDatetime; 
	private String Ngname="未分组";
	private Integer count;
	
	public AttenGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public int getNGid() {
		return NGid;
	}
	public void setNGid(int nGid) {
		NGid = nGid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getNGDatetime() {
		return NGDatetime;
	}
	public void setNGDatetime(String nGDatetime) {
		NGDatetime = nGDatetime;
	}
	public String getNgname() {
		return Ngname;
	}
	public void setNgname(String ngname) {
		Ngname = ngname;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	public int getUider() {
		return Uider;
	}
	public void setUider(int uider) {
		this.Uider = uider;
	}
	@Override
	public String toString() {
		return "AttenGroup [NGid=" + NGid + ", uid=" + uid + ", Uider=" + Uider
				+ ", user=" + user + ", NGDatetime=" + NGDatetime + ", Ngname="
				+ Ngname + ", count=" + count + "]";
	}
	public AttenGroup(int nGid, int uid, int Uider, Users user,
			String nGDatetime, String ngname, Integer count) {
		super();
		NGid = nGid;
		this.uid = uid;
		Uider = Uider;
		this.user = user;
		NGDatetime = nGDatetime;
		Ngname = ngname;
		this.count = count;
	}
/*	public AttenGroup(int nGid, int uid, Users user, String nGDatetime, String ngname, Integer count) {
		super();
		NGid = nGid;
		this.uid = uid;
		this.user = user;
		NGDatetime = nGDatetime;
		Ngname = ngname;
		this.count = count;
	}
	*/
}
