package com.hcq.bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Users implements Serializable{
	private static final long serialVersionUID = 4741714432812910924L;
	private Integer Uid; 		//用户编号
	private String Uname; 		//真实姓名
	private String Ualais;		//用户妮称
	private String Utel;			//手机号
	private String Ulogon;		//（登陆名）
	private String Upassword;	//--密码
	private String Region;		//地区
	private Date birth=new Date(new java.util.Date().getTime());			//--生日
	private char Usex;			//性别
	private String Ublog; 		//个人微博	
	private String Uinfo;		//自我介绍
	private Date Udatetime;		//注册时间
	private String Uimage;		//用户头像
	private boolean Uprivacy; 	//隐私
	
	private String password;	//--新密码
	
	
	
	public Users(Integer uid, String ualais, String uimage) {
		super();
		Uid = uid;
		Ualais = ualais;
		Uimage = uimage;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String birthString;	
	
	public String getBirthString() {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		this.birthString=df.format(new java.util.Date(this.birth.getTime()));
		return this.birthString;
	}

	public void setBirthString(String birthString) {
		this.birthString = birthString;
	}

	public Users(int uid) {
		Uid = uid;
	}
	
	public Users() {
		super();
	}
	public Users(Integer uid, String uname, String ualais, String utel,
			String ulogon, String upassword, String region, Date birth,
			char usex, String ublog, String uinfo, Date udatetime,
			String uimage, boolean uprivacy, String password, String birthString) {

		Uid = uid;
		Uname = uname;
		Ualais = ualais;
		Utel = utel;
		Ulogon = ulogon;
		Upassword = upassword;
		Region = region;
		this.birth = birth;
		Usex = usex;
		Ublog = ublog;
		Uinfo = uinfo;
		Udatetime = udatetime;
		Uimage = uimage;
		Uprivacy = uprivacy;
		this.password = password;
		this.birthString = birthString;
	}

	
	public boolean isUprivacy() {
		return Uprivacy;
	}

	public void setUprivacy(boolean uprivacy) {
		Uprivacy = uprivacy;
	}

	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public String getUname() {
		return Uname;
	}
	public void setUname(String uname) {
		Uname = uname;
	}
	public String getUalais() {
		return Ualais;
	}
	public void setUalais(String ualais) {
		Ualais = ualais;
	}
	public String getUtel() {
		return Utel;
	}
	public void setUtel(String utel) {
		Utel = utel;
	}
	public String getUlogon() {
		return Ulogon;
	}
	public void setUlogon(String ulogon) {
		Ulogon = ulogon;
	}
	public String getUpassword() {
		return Upassword;
	}
	public void setUpassword(String upassword) {
		Upassword = upassword;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public Date getBirth() throws ParseException {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		this.birth=new Date(df.parse(this.birthString).getTime());
		return this.birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
		this.getBirthString();
	}
	public char getUsex() {
		return Usex;
	}
	public void setUsex(char usex) {
		Usex = usex;
	}
	public String getUblog() {
		return Ublog;
	}
	public void setUblog(String ublog) {
		Ublog = ublog;
	}
	public String getUinfo() {
		return Uinfo;
	}
	public void setUinfo(String uinfo) {
		Uinfo = uinfo;
	}
	public Date getUdatetime() {
		return Udatetime;
	}
	public void setUdatetime(Date udatetime) {
		Udatetime = udatetime;
	}
	public String getUimage() {
		return Uimage;
	}
	public void setUimage(String uimage) {
		Uimage = uimage;
	}

	@Override
	public String toString() {
		return "User [Uid=" + Uid + ", Uname=" + Uname + ", Ualais=" + Ualais
				+ ", Utel=" + Utel + ", Ulogon=" + Ulogon + ", Upassword="
				+ Upassword + ", Region=" + Region + ", birth=" + birth
				+ ", Usex=" + Usex + ", Ublog=" + Ublog + ", Uinfo=" + Uinfo
				+ ", Udatetime=" + Udatetime + ", Uimage=" + Uimage
				+ ", Uprivacy=" + Uprivacy + ", birthString=" + birthString
				+ "]";
	}
	
	
}
