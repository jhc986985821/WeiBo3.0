package com.hcq.bean;

import java.io.Serializable;

public class Dianzan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2888729876962793224L;
	private int Mid;
	private int praiseCnt;  //点赞数
	
	private int Uid;
	public int getMid() {
		return Mid;
	}
	public void setMid(int mid) {
		Mid = mid;
	}
	public int getPraiseCnt() {
		return praiseCnt;
	}
	public void setPraiseCnt(int praiseCnt) {
		this.praiseCnt = praiseCnt;
	}
	
	public int getUid() {
		return Uid;
	}
	public void setUid(int uid) {
		Uid = uid;
	}
	
	@Override
	public String toString() {
		return "Dianzan [Mid=" + Mid + ", praiseCnt=" + praiseCnt + ", Uid="
				+ Uid + "]";
	}
	
	public Dianzan(int mid, int praiseCnt, int uid) {
		super();
		Mid = mid;
		this.praiseCnt = praiseCnt;
		Uid = uid;
	}
	public Dianzan() {
		super();
	}
	
	
	
	
}
