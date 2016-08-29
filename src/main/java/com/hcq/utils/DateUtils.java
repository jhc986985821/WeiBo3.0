package com.hcq.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final String LONG_PATTERN="yyyy��MM��dd�� HH:mm:ss";
	//private static final String SHORT_PATTERN="yyyy��MM��dd��";
	
	
	/**
	 * ������ת��Ϊ�ַ���
	 * @param d
	 * @param pattern
	 * @return
	 */
	public static String parse(Date d,String pattern){
		DateFormat df=null;
		if(pattern!=null && !"".equals(pattern)){
			df=new SimpleDateFormat(pattern);
		}else{
			df=new SimpleDateFormat(LONG_PATTERN);
		}
		return df.format(d);
	}
	
	public static void main(String[] args) throws java.text.ParseException {
	}
	public static Date parseStringToDate(String str,String pattern) throws java.text.ParseException {
		DateFormat df=null;
		if(pattern!=null && !"".equals(pattern)){
			df=new SimpleDateFormat(pattern);
		}else{
			df=new SimpleDateFormat(LONG_PATTERN);
		}
		Date d=null;
		try{
			d=df.parse(str);
		}catch(Exception e){
			LogUtil.logger.debug(e);
		}
		return d;
	}
}