package com.lcl.my12306.utils;

import java.security.MessageDigest;

public class MD5AndKillAction {
	// MD5加码 32位 
	public  String MD5(String inStr) { 
		MessageDigest md5 = null; 
		try { 
			md5 = MessageDigest.getInstance("MD5"); //返回指定算法对象
		} catch (Exception e) { 
			System.out.println(e.toString()); 
			e.printStackTrace(); 
			return ""; 
		} 
		char[] charArray = inStr.toCharArray(); //变为字符数组
		byte[] byteArray = new byte[charArray.length]; 

		for (int i = 0; i < charArray.length; i++) 
			byteArray[i] = (byte) charArray[i]; //把字符数组变为字节数组
		
		//使用指定的字节数组对摘要进行最后更新，然后完成摘要计算。
		byte[] md5Bytes = md5.digest(byteArray); 

		StringBuffer hexValue = new StringBuffer(); 

		for (int i = 0; i < md5Bytes.length; i++) { 
			int val = ((int) md5Bytes[i]) & 0xff; 
			if (val < 16) 
				hexValue.append("0"); 
			hexValue.append(Integer.toHexString(val)); 
		} 

		return hexValue.toString(); 
	} 



	// 加密算法 (可逆)
	public  String KL(String inStr) { 
		char[] a = inStr.toCharArray(); 
		for (int i = 0; i < a.length; i++) { 
			a[i] = (char) (a[i] ^ 't'); 
		} 
		String s = new String(a); 
		return s; 
	} 
	public static void main(String[] args) {
		MD5AndKillAction md=new MD5AndKillAction();
		md.MD5("s");
		
		System.out.println(md.MD5("s"));
		System.out.println(md.KL(md.MD5("s")));
		System.out.println(md.KL(md.KL(md.MD5("s"))));
		
	
	}
}
