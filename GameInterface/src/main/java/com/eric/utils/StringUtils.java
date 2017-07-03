package com.eric.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 字符串操作处理组件
 * @author eric 
 * @version
 */
public class StringUtils {

	/**
	 * 对获取的字符串检查是否为空。
	 * @param p_String 一个String。
	 * @return String 如果为空则返回"",如果不为空则返回p_String本身。
	 */
	public static String checkNullString(String p_String){
		if (p_String == null)
			return "";
		else
			return p_String;
	}

	/**
	 * 返回 Object 参数的字符串表示形式。
	 * @param p_obj 一个Object。
	 * @return String 如果该对象为null,则返回"";否则返回p_obj.toString()的值。
	 */
	public static String checkNullString(Object p_obj){
		if (p_obj == null)
			return "";
		else
			return p_obj.toString();
	}

	/**
	 * 两个字符串内容是否一样。
	 * @param src 源字符串。
	 * @param dest 目标字符串。
	 * @return 如果相同则返回true,否则返回false。
	 */
	public static boolean compareEqualsString(String src, String dest){
		src = checkNullString(src).toLowerCase();
		dest = checkNullString(dest).toLowerCase();
		if (src.equals(dest))
			return true;
		else
			return false;
	}
	
	/**
	 * 按照指定的编码类型对对字符串编码
	 * @param str 需编码的字符串
	 * @param _codeType  编码类型 例如:UTF-8
	 * @return 返回编码后的字符串
	 */
	public static String encoder(String str,String _codeType) {
		if (str == null || str.length() < 1) {
			str = "";
		} else {
			try {
				str = new String(str.getBytes("iso-8859-1"), _codeType);
			} catch (UnsupportedEncodingException e) {
				return str;
			}
		}
		return str;
	}
	
	/**
	 * 转换指定编码的字符串
	 * @param str 源字符串
	 * @return 返回指定编码字符串
	 */
	public static String getEncoderStr(String str, String code) {
		if (str == null || str.length() < 1) {
			str = "";
		} else {
			try {
				str = (new String(str.getBytes(code)));
			} catch (Exception e) {
				e.printStackTrace();
				return str;
			}
		}
		return str;
	}
	/**
	 * 判断指定字符串是否有效
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		boolean flag = false;
		if(str == null || str.equals("")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString().toUpperCase();   
	 }  
}
