package com.hcq.utils;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * @author HCQ
 *
 */
public class Mail {
	private MimeMessage mimeMessage;//�����ʼ���Ҫ�����ʼ�������������Ϣ�ʼ�����
	private Session session;   //�ʼ��Ự����
	private Properties props;    //��ȡ��Ŀ�������ļ�  ϵͳ����
	private String username;   //��������ʵ����
	private String password;    //����
	private Multipart mp;   //�ʼ����Ĳ���
	
	private Mail(String smtp){
		setSmtpHost(smtp);  //ϵͳ����
		createMimeMessage();    //�Ự�ʼ�����
		
	}

	
	/**
	 * @param smtp   ��smtp�����������������뵽ϵͳ������Ϣ��
	 */
	private void setSmtpHost(String hostName) {
		if(props==null){
			props=System.getProperties();   //����ʼ�����������Ϣ
		}
		props.put("mail.smtp.host", hostName);//����smtp����
	}
	
	/**
	 * @return    ��ȡ�Ự���󣬴����ʼ������Ƿ�ɹ�
	 */
	private boolean createMimeMessage() {
		try{
			session=Session.getDefaultInstance(props,null);
		}catch(Exception e){
			return false;
		}
		try{
			mimeMessage=new MimeMessage(session);//����mime�ʼ�����
			mp= new MimeMultipart();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * @param need
	 * ����smtp�Ƿ���Ҫ��֤
	 */
	private void setNeedAuth(boolean need){
		if(props==null){
			props=System.getProperties();
		}if(need){
			props.put("mail.smtp.auth", "true");
		}else{
			props.put("mail.smtp.auth","false");
		}
	}
	
	//�����û�����
	private void setNamePass(String username,String password){
		this.username=username;
		this.password=password;
	}
	
	//�����ʼ�����
	private boolean setSubject(String subject){
		try{
			mimeMessage.setSubject(subject);
			return true;
		}catch(Exception E){
			System.out.println("�����ʼ����ⷢ������");
			return false;
		}
	}
	
	//�����ʼ�����
	private boolean setBody(String content){
		try{
			BodyPart bp=new MimeBodyPart();
			bp.setContent(""+content,"text/html;charset=GBK");
			mp.addBodyPart(bp);
			return true;
		}catch(Exception e){
			System.out.println("�����ʼ�����ʱ��������");
			return false;
		}
	}
	
	//���÷�����
	private boolean setFrom(String from){
		try{
			mimeMessage.setFrom(new InternetAddress(from));  //������
			return true;
		}catch(Exception e){
			return false;
		}
	}
	//����������
	private boolean setTo(String to){
		if(to==null){
			return false;
		}try{
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));;
			return true;
		}catch(Exception E){
			return false;
		}
	}
	
	//�����ʼ�ģ��
	private boolean sendOut(){
		try{
			mimeMessage.setContent(mp);
			mimeMessage.saveChanges();
			Session mailSession=Session.getInstance(props, null);
			Transport transport=mailSession.getTransport("smtp");
			transport.connect((String)props.get("mail.smtp.host"), username,password);
			transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
			transport.close();
			return true;
		}catch(Exception e){
			System.out.println("����ʧ�ܿ���");
			return false;
		}
	}
	
	//ʹ��sendout����
	
	public static boolean sendAndCc(String smtp,String from,String to,String copyto,
			String subject,String content,String username,String password){
		Mail theMail=new Mail(smtp);
		theMail.setNeedAuth(true);
		if(!theMail.setSubject(subject)){
			return false;
		}
		if(!theMail.setBody(content)){
			return false;
		}
		if(!theMail.setTo(to)){
			return false;
		}
		if(!theMail.setFrom(from)){
			return false;
		}
		theMail.setNamePass(username, password);
		if(!theMail.sendOut()){
			return false;
		}
		return true;
	}
}