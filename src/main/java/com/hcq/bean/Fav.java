package com.hcq.bean;

public class Fav {
	private int Fid;
	private Users users;
	private Message message;
	public int getFid() {
		return Fid;
	}
	public void setFid(int fid) {
		Fid = fid;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Fav(int fid, Users users, Message message) {
		super();
		Fid = fid;
		this.users = users;
		this.message = message;
	}
	public Fav() {
		super();
	}
	
}
