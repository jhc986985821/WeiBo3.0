package com.hcq.web.model;

import java.util.List;

import com.hcq.bean.AttenGroup;
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
	private List<HotWord>hotWords;
	private List<AttenGroup> attenGroups;
	
	
	public List<AttenGroup> getAttenGroups() {
		return attenGroups;
	}
	public void setAttenGroups(List<AttenGroup> attenGroups) {
		this.attenGroups = attenGroups;
	}
	public List<HotWord> getHotWords() {
		return hotWords;
	}
	public void setHotWords(List<HotWord> hotWords) {
		this.hotWords = hotWords;
	}
	public JsonModel(Integer code, String msg, Object obj, String url, Users users, List<Users> userslist,
			List<MessageReply> messageReplies, List<HotWord> hotWords) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
		this.url = url;
		this.users = users;
		this.userslist = userslist;
		this.messageReplies = messageReplies;
		this.hotWords = hotWords;
	}
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
	
	public List<MessageReply> getMessageReplies() {
		return messageReplies;
	}
	public void setMessageReplies(List<MessageReply> messageReplies) {
		this.messageReplies = messageReplies;
	}
	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", msg=" + msg + ", obj=" + obj + ", url=" + url + ", users=" + users
				+ ", userslist=" + userslist + ", messageReplies=" + messageReplies + ", hotWords=" + hotWords
				+ ", attenGroups=" + attenGroups + "]";
	}
	public JsonModel(Integer code, String msg, Object obj, String url, Users users, List<Users> userslist,
			List<MessageReply> messageReplies, List<HotWord> hotWords, List<AttenGroup> attenGroups) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
		this.url = url;
		this.users = users;
		this.userslist = userslist;
		this.messageReplies = messageReplies;
		this.hotWords = hotWords;
		this.attenGroups = attenGroups;
	}
	
}
