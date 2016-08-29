package com.hcq.biz.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.RuntimeErrorException;



import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import com.hcq.biz.MailSend;


@Service
public class MailSendImpl implements MailSend {

	private JavaMailSender mailSender;

	//@Resource(name = "mailSender")
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/* 
	 * 发送简单邮件
	 *  
	 */
	public void sendSimple(final String to, final String[] cc,
			final String[] bcc, final String from, final String subject,
			final String text) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipient(Message.RecipientType.TO,
						new InternetAddress(to));
				if (cc != null && cc.length > 0) {
					setAddresses(cc, mimeMessage);
				}
				if (bcc != null && bcc.length > 0) {
					setAddresses(bcc, mimeMessage);
				}
				// 发送地址
				mimeMessage.setFrom(new InternetAddress(from));
				// 邮件标题
				mimeMessage.setSubject(subject == null ? "untitle" : subject,
						"utf-8");
				mimeMessage.setText("Dear Sir/Madam: " + text);

			}

		};

		try {
			this.mailSender.send(preparator);	//当程序调用send方法时，会自动回调
												//prepare ，完成发送操作
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}

	}
	
	/* 
	 * 发送邮件带附件
	 * 
	 */
	public void sendMailWithAttachment(final String to, final String[] cc, final String[] bcc,
			final String from, final String subject, final String text, final File[] files) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
				
				helper.setTo(to);
				
				if (cc != null && cc.length > 0) {
					helper.setTo(to);
				}
				if (bcc != null && bcc.length > 0) {
					helper.setBcc(bcc);
				}
				// 发送地址
				helper.setFrom(from);
				// 邮件标题
				helper.setSubject(subject == null ? "untitle" : subject);
				helper.setText(text);
				if(files != null && files.length >0){
					for (File f : files) {
						FileSystemResource file = new FileSystemResource(f);
						helper.addAttachment(f.getName(), f);
					}
				}

			}

		};

		try {
			this.mailSender.send(preparator);	//当程序调用send方法时，会自动回调
												//prepare ，完成发送操作
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
		
	}

	private VelocityEngine velocityEngine;
	
	
	//@Resource(name="velocityEngine")
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/* 
	 * 模板邮件，带附件
	 * 
	 */
	public void sendMailWithTemplate(String to, String[] cc, String[] bcc,
			String from, String subject, String vmpath, File[] files,Map model) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			if (cc != null && cc.length > 0) {
				helper.setTo(to);
			}
			if (bcc != null && bcc.length > 0) {
				helper.setBcc(bcc);
			}
			helper.setFrom(from);
			helper.setSubject(subject == null ? "untitle" : subject);
			
			//通过模板来生成
			
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmpath, model);
			helper.setText(text,true);
			if(files != null && files.length >0){
				for (File f : files) {
					FileSystemResource file = new FileSystemResource(f);
					helper.addAttachment(f.getName(), f);
				}
			}
			
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		mailSender.send(message);
		
	}

	private void setAddresses(final String[] cc, MimeMessage mimeMessage)
			throws AddressException, MessagingException {
		if (cc != null && cc.length > 0) {
			InternetAddress[] ccia = new InternetAddress[cc.length];
			for (int i = 0; i < cc.length; i++) {
				ccia[i] = new InternetAddress(cc[i]);
			}
			mimeMessage.setRecipients(Message.RecipientType.CC, ccia);
		}
	}

	

}
