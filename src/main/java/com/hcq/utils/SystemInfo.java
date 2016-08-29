package com.hcq.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class SystemInfo {
	 /**ϵͳ��*/  
    private String os_name;  
    /**ϵͳ�ܹ�*/  
    private String os_arch ;  
    /**ϵͳ�汾��*/  
    private String os_version ;  
    /**ϵͳIP*/  
    private String os_ip ;  
    /**ϵͳMAC��ַ*/  
    private String os_mac;  
    /**ϵͳʱ��*/  
    private Date os_date;  
    /**ϵͳCPU����*/  
    private Integer os_cpus ;  
    /**ϵͳ�û���*/  
    private String os_user_name;  
    /**�û��ĵ�ǰ����Ŀ¼*/  
    private String os_user_dir;  
    /**�û�����Ŀ¼*/  
    private String os_user_home;  
      
    /**Java�����л����汾*/  
    private String java_version ;  
    /**javaĬ�ϵ���ʱ�ļ�·��*/  
    private String java_io_tmpdir;  
      
    /**java ƽ̨*/  
    private String sun_desktop ;  
      
    /**�ļ��ָ���  �� unix ϵͳ���ǣ�����*/  
    private String file_separator;  
    /**·���ָ���  �� unix ϵͳ���ǣ�:��*/  
    private String path_separator;  
    /**�зָ���   �� unix ϵͳ���ǣ�/n��*/  
    private String line_separator;  
      
    /**����context**/  
    private String server_context ;  
    /**��������*/  
    private String server_name;  
    /**�������˿�*/  
    private Integer server_port;  
    /**��������ַ*/  
    private String server_addr;  
    /**��ÿͻ��˵��Ե����֣���ʧ�ܣ��򷵻ؿͻ��˵��Ե�ip��ַ*/  
    private String server_host;  
    /**����Э��*/  
    private String server_protocol;  
      
    public static SystemInfo SYSTEMINFO;  
      
    public static SystemInfo getInstance(){  
        if(SYSTEMINFO == null){  
            SYSTEMINFO = new SystemInfo();  
        }  
        return SYSTEMINFO;  
    }  
      
      
    public static SystemInfo getInstance(HttpServletRequest request){  
        if(SYSTEMINFO == null){  
            SYSTEMINFO = new SystemInfo(request);  
        }  
        else {  
            SYSTEMINFO.ServerInfo(request);  
        }  
        return SYSTEMINFO;  
    }  
      
    private SystemInfo() {  
        super();  
        init();  
    }  
      
    private SystemInfo(HttpServletRequest request) {  
        super();  
        init();  
        /** 
         * ������Ϣ 
         */  
        ServerInfo(request);  
    }  
      
    /** 
     * �����Ϣ 
     */  
    public void PrintInfo(){  
        Properties props=System.getProperties();  
        System.out.println("Java�����л����汾��"+props.getProperty("java.version"));   
        System.out.println("Ĭ�ϵ���ʱ�ļ�·����"+props.getProperty("java.io.tmpdir"));   
        System.out.println("����ϵͳ�����ƣ�"+props.getProperty("os.name"));   
        System.out.println("����ϵͳ�Ĺ��ܣ�"+props.getProperty("os.arch"));   
        System.out.println("����ϵͳ�İ汾��"+props.getProperty("os.version"));   
        System.out.println("�ļ��ָ�����"+props.getProperty("file.separator"));   //�� unix ϵͳ���ǣ�����   
        System.out.println("·���ָ�����"+props.getProperty("path.separator"));   //�� unix ϵͳ���ǣ�:��   
        System.out.println("�зָ�����"+props.getProperty("line.separator"));   //�� unix ϵͳ���ǣ�/n��  
        System.out.println("�û����˻����ƣ�"+props.getProperty("user.name"));   
        System.out.println("�û�����Ŀ¼��"+props.getProperty("user.home"));   
        System.out.println("�û��ĵ�ǰ����Ŀ¼��"+props.getProperty("user.dir"));  
    }  
      
    /** 
     * ��ʼ���������� 
     */  
    private void init(){  
        Properties props=System.getProperties();  
          
        this.java_version = props.getProperty("java.version");  
        this.java_io_tmpdir = props.getProperty("java.io.tmpdir");  
        this.os_name = props.getProperty("os.name");  
        this.os_arch = props.getProperty("os.arch");  
        this.os_version = props.getProperty("os.version");  
        this.file_separator = props.getProperty("file.separator");  
        this.path_separator = props.getProperty("path.separator");  
        this.line_separator = props.getProperty("line.separator");  
          
        this.os_user_name = props.getProperty("user.name");  
        this.os_user_home = props.getProperty("user.home");  
        this.os_user_dir = props.getProperty("user.dir");  
          
        this.sun_desktop = props.getProperty("sun.desktop");  
          
        this.os_date = new Date();  
        this.os_cpus = Runtime.getRuntime().availableProcessors();  
          
        try {  
            ipMac();  
        } catch (Exception e) {  
            this.os_ip = "";  
            this.os_mac = "";  
        }  
    }  
      
    /** 
     * ��ȡip��mac��ַ 
     * @throws Exception 
     */  
    @SuppressWarnings("resource")  
    private void ipMac() throws Exception{  
        InetAddress address = InetAddress.getLocalHost();  
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);  
        ni.getInetAddresses().nextElement().getAddress();  
        byte[] mac = ni.getHardwareAddress();  
        String sIP = address.getHostAddress();  
        String sMAC = "";  
        Formatter formatter = new Formatter();  
        for (int i = 0; i < mac.length; i++) {  
            sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],  
                    (i < mac.length - 1) ? "-" : "").toString();  
  
        }  
        SystemInfo.this.os_ip = sIP;  
        SystemInfo.this.os_mac = sMAC;  
    }  
      
    /** 
     * ��ȡ��������Ϣ 
     * @param request 
     */  
    public void ServerInfo(HttpServletRequest request){  
        this.server_name = request.getServerName();  
        this.server_port = request.getServerPort();  
        this.server_addr = request.getRemoteAddr();  
        this.server_host = request.getRemoteHost();  
        this.server_protocol = request.getProtocol();  
        this.server_context = request.getContextPath();  
    }  
  
    public String getOs_name() {  
        return os_name;  
    }  
  
    public String getOs_arch() {  
        return os_arch;  
    }  
  
    public String gOss_version() {  
        return os_version;  
    }  
  
    public String getOs_ip() {  
        return os_ip;  
    }  
  
    public String getOs_mac() {  
        return os_mac;  
    }  
  
    public Date getOs_date() {  
        return os_date;  
    }  
  
    public Integer getOs_cpus() {  
        return os_cpus;  
    }  
  
    public String getOs_user_name() {  
        return os_user_name;  
    }  
  
    public String getOs_user_dir() {  
        return os_user_dir;  
    }  
  
    public String getOs_user_home() {  
        return os_user_home;  
    }  
  
    public String getJava_version() {  
        return java_version;  
    }  
  
    public String getJava_io_tmpdir() {  
        return java_io_tmpdir;  
    }  
  
    public String getSun_desktop() {  
        return sun_desktop;  
    }  
  
    public String getFile_separator() {  
        return file_separator;  
    }  
  
    public String getPath_separator() {  
        return path_separator;  
    }  
  
    public String getLine_separator() {  
        return line_separator;  
    }  
  
    public String getServer_context() {  
        return server_context;  
    }  
  
    public String getServer_name() {  
        return server_name;  
    }  
  
    public Integer getServer_port() {  
        return server_port;  
    }  
  
    public String getServer_addr() {  
        return server_addr;  
    }  
  
    public String getServer_host() {  
        return server_host;  
    }  
  
    public String getServer_protocol() {  
        return server_protocol;  
    }  
      
      
}  
