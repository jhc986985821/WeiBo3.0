package com.hcq.biz;

import java.io.File;
import java.util.Map;

public interface MailSend {
	
	/**
	 * @param to	接收方
	 * @param cc	转发
	 * @param bcc	密送
	 * @param from	发件人
	 * @param subject	主题
	 * @param text	内容
	 */
	public void sendSimple(String to, String[] cc,String[] bcc,String from,String subject,String text);
	
	/**
	 * @param to	接收方
	 * @param cc	转发
	 * @param bcc	密送
	 * @param from	发件人
	 * @param subject	主题
	 * @param text	内容
	 * @param files 附件
	 */
	public void sendMailWithAttachment(String to, String[] cc,String[] bcc,String from,String subject,String text,File[] files);
	
	/**
	 * @param to	接收方
	 * @param cc	转发
	 * @param bcc	密送
	 * @param from	发件人
	 * @param subject	主题
	 * @param vmpath	模板文件路径
	 * @param files 附件
	 * @param model 模板内容
	 */
	public void sendMailWithTemplate(String to, String[] cc,String[] bcc,String from,String subject,String vmpath,File[] files,Map model);
	
}
