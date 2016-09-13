package com.hcq.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

public class SiteInfo {
    private String jversion;  //java���а汾
    private String osname ;  //����ϵͳ������
    private String osarch ;  //����ϵͳ�Ĺ���
    private String osversion ;  //����ϵͳ�İ汾
    private String username;  //�û����˻�����
    private String ip;
    private Date os_date;
    
    
	public Date getOs_date() {
		return os_date;
	}
	public String getJversion() {
		return jversion;
	}
	public String getOsname() {
		return osname;
	}
	public String getOsarch() {
		return osarch;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public String getUsername() {
		return username;
	}
	public String getIp() {
		return ip;
	}
	public SiteInfo() throws UnknownHostException {
		super();
		Properties props=System.getProperties();  
		this.jversion = props.getProperty("java.version");
		this.osname = props.getProperty("os.name");
		this.osarch = props.getProperty("os.arch");
		this.osversion = props.getProperty("os.version");
		this.username = props.getProperty("user.name");
		InetAddress address = InetAddress.getLocalHost();  
        String sIP = address.getHostAddress();  
        this.ip=sIP;
        this.os_date=new Date();
	}
}
