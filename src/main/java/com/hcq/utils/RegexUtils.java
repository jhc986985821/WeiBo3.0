package com.hcq.utils;

/**
 * @author HCQ
 *һЩ����������֤
 */
public class RegexUtils {

	public static final String reg1="[1-9]\\d*";
	public static final String reg2="-[1-9]\\d*";
	public static final String reg3="-?[1-9]\\d*|0";
	public static final String reg4="[1-9]\\d*|0";
	public static final String reg5="-[1-9]\\d*|0";
	public static final String reg6="[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*";//��������
	public static final String reg7="-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)";
	public static final String reg8="-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)";
	public static final String reg9="[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0";
	public static final String reg10="(-([1-9]\\d*\\.\\d*|0\\0\\.\\d*[1-9]\\d*))";
	
	//��yyyy-mm-dd�� ��ʽ������У�飬�ѿ���ƽ���ꡣ
	public static final String reg11="^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?";
	
	//У��18λ���֤����
	public static final String reg12="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
	
	//У������
	public static final String reg13="[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w]";
	
	//У��15λ���֤����
	public static final String reg14="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";

	//�����ǹ��� 13��15��18��ͷ���ֻ���������ʽ��
	public static final String reg15="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
	
	//���У�飬��ȷ����λС��
	public static final String reg16="^[0-9]+(.[0-9]{2})?$";
	
	//IE�汾���ı��ʽ
	public static final String reg17="^.*MSIE [5-8](?:\\.[0-9]+)?(?!.*Trident\\/[5-9]\\.0).*$";
	
	//У��IP-v4��ַ
	public static final String reg18="\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\";
	
	//�����������ʽ����ɸѡ��һ���ı��е�URL��
	public static final String reg19="^(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?";
	
	//��ȡע��
	public static final String reg20="<!--(.*?)-->";
	
	//�ַ������������ġ�
	public static final String reg21="^[\\u4e00-\\u9fa5]{0,}$";
	
	//�����ǿ�ȱ����ǰ�����Сд��ĸ�����ֵ���ϣ�����ʹ�������ַ���������8-10֮�䡣
	public static final String reg22="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
			
}