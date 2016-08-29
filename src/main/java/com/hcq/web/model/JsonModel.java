package com.hcq.web.model;

import java.util.List;

import com.hcq.bean.MessageReply;
import com.hcq.bean.Users;

public class JsonModel {
	private Integer code;
	private String msg;
	private Object obj;
	private String url;
	private Users users;
	private List<Users> userslist;
	private List<MessageReply>messageReplies;
	
	
	public List<Users> getUserslist() {
		return userslist;
	}
	public void setUserslist(List<Users> userslist) {
		this.userslist = userslist;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public JsonModel() {
		super();
	}
	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", msg=" + msg + ", obj=" + obj + ", url=" + url + ", users=" + users
				+ ", userslist=" + userslist + ", messageReplies=" + messageReplies + "]";
	}
	public List<MessageReply> getMessageReplies() {
		return messageReplies;
	}
	public void setMessageReplies(List<MessageReply> messageReplies) {
		this.messageReplies = messageReplies;
	}
	
	
	
}
