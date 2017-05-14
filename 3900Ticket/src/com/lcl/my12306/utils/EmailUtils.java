package com.lcl.my12306.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lcl.my12306.dao.RandomCode;


public class EmailUtils {
	public static String str;
	//获取验证码
	public void setMail(String mail) throws MessagingException{
		RandomCode randomCode=new RandomCode();
		String str=randomCode.showRandom();
		Properties props = new Properties(); //Properties 属性文件 
		// 开启debug调试  
		props.setProperty("mail.debug", "true");  
		// 设置邮件服务器主机名 
		props.setProperty("mail.host", "smtp.163.com");  
		// 发送服务器需要身份验证  
		props.setProperty("mail.smtp.auth", "true");  
		// 发送邮件协议名称 
		props.setProperty("mail.transport.protocol", "smtp");  

		// 设置环境信息 
		Session session = Session.getInstance(props);  

		// 创建邮件对象  
		Message msg = new MimeMessage(session);  
		msg.setSubject("网上购票系统"); 
		// 设置邮件内容  
		msg.setText(str);  
		// 设置发件人  
		msg.setFrom(new InternetAddress("qq960089677@163.com"));  
		Transport transport = session.getTransport();  
		// 连接邮件服务器  
		transport.connect("qq960089677@163.com","lcl123456");
		// 发送邮件  

		transport.sendMessage(msg, new Address[] {new InternetAddress(mail)});
		// 关闭连接  
		transport.close(); 
	}

}
