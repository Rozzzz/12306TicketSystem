package com.lcl.my12306.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ticketOrderInfoDBH {

	/**
	 * 查找订单详情
	 * @param accountId 12306账号
	 * @param time 订票日期
	 * @return
	 */
	public List<Map<String,String>> find(String accountId,String time1,String time2 ){
		DBHelper db=new DBHelper();
		String sql="SELECT * FROM ticketOrder WHERE datep BETWEEN ?  AND ? and accountId=?";
        List<Object> params=new ArrayList<Object>();
		params.add(time1);
		params.add(time2);
		params.add(accountId);
		return db.finds(sql, params);
	}
	
	/**
	 * 更改订单状态 （退票 支付）
	 * @param IDs
	 * @param condition
	 * @return
	 */
	public int alter(String IDs,String condition){
		DBHelper db=new DBHelper();
		String sql="UPDATE TICKETORDER SET condition=? WHERE ids=? ";
        List<Object> params=new ArrayList<Object>();
        params.add(condition);
        params.add(IDs);
        return db.update(sql, params);	
	}

	/**
	 * 返回当前时间和规定付款时间
	 * @param time 规定付款时间
	 * @return
	 */
	public Map<String,String> gettime (int time){
		DBHelper db=new DBHelper();
		String sql="SELECT to_char(SYSDATE,'yyyy-mm-dd HH24:Mi:SS')SYSDATE1,to_char(SYSDATE+"+time+"/24/60,'yyyy-mm-dd HH24:Mi:SS')SYSDATE2 FROM dual";
//        List<Object> params=new ArrayList<Object>();
//        params.add(time);
		return db.find(sql, null);
	}
	
	/**
	 * 添加订单
	 * @param Accountid 12306账号
	 * @param c_name 姓名
	 * @param c_ID 身份证
	 * @param datet 乘车日期
	 * @param t_Init 初始地
	 * @param t_Dest 目的地
	 * @param Seat_Kind 座位类型
	 * @param t_Id 火车号
	 * @param time 规定付款时间
	 * @return
	 */
	public int add(String Accountid,String c_name,String c_ID,String datet,String t_Init,String t_Dest,String Seat_Kind,String t_Id,int time,String price){
		TraininfoDBH traininfoDBH=new TraininfoDBH();
		DBHelper db=new DBHelper();
		String sql="INSERT INTO ticketOrder(IDs, datep, past, Accountid, c_name, c_ID, datet, t_Init, Init_Id, t_Dest, Dest_Id, Seat_Kind, Seat_Id, t_Id,price)VALUES(seq_ticketOrder_IDs.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> params=new ArrayList<Object>();
		double a=traininfoDBH.find_station(t_Id, t_Init);
		double b=traininfoDBH.find_station(t_Id, t_Dest);
	//	System.out.println(traininfoDBH.assign_seat(datet, t_Id, t_Init, t_Dest, Seat_Kind));
	//	System.out.println(this.gettime(time));
		params.add(this.gettime(time).get("sysdate1"));
		System.out.println(this.gettime(time).get("sysdate1")+"====");
		params.add(this.gettime(time).get("sysdate2"));
		System.out.println(this.gettime(time).get("sysdate2")+"====");
		params.add(Accountid);
		params.add(c_name);
		params.add(c_ID);
		params.add(datet);
		params.add(t_Init);
		params.add(a);
		params.add(t_Dest);
		params.add(b);
		params.add(Seat_Kind);
		params.add(traininfoDBH.assign_seat(datet, t_Id, t_Init, t_Dest, Seat_Kind));
		params.add(t_Id);
		params.add(price);
		return db.update(sql, params);
	}
	
}
