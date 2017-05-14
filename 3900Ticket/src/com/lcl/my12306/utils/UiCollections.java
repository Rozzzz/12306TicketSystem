package com.lcl.my12306.utils;

import java.util.Map;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.TableItem;

import com.lcl.my12306.ui.AdminLogin;
import com.lcl.my12306.ui.AdminLogin2;
import com.lcl.my12306.ui.BuyTicket;
import com.lcl.my12306.ui.CompleteInfo;
import com.lcl.my12306.ui.ContactInfo;
import com.lcl.my12306.ui.HomePage;
import com.lcl.my12306.ui.MyInfo;
import com.lcl.my12306.ui.Order;
import com.lcl.my12306.ui.Register;
import com.lcl.my12306.ui.RemainTicket;
import com.lcl.my12306.ui.TicketPrice;
import com.lcl.my12306.ui.UserLogin;

public class UiCollections {
	public static StackLayout stackLayout= new StackLayout();
	public static HomePage homePage;
	public static Register register;
	public static UserLogin userLogin;
	public static AdminLogin adminLogin;
	public static RemainTicket remainTicket;
	public static TicketPrice ticketPrice;
	public static CompleteInfo completeInfo;
	public static ContactInfo contactInfo;
	public static MyInfo myInfo;
	public static Order order;
	public static AdminLogin2 adminLogin2;
	
	public static Map<String,String> currentLoginUser;//当前登录用户
}
