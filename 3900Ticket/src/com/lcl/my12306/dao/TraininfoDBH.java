package com.lcl.my12306.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TraininfoDBH {

	/**
	 * 查询从初始地到目的地的火车号  时间  不同座位的价格
	 * @param t_init 初始地
	 * @param t_dest 目的地
	 * @return
	 */
	public List<Map<String,String>> find(String t_init,String t_dest){
		DBHelper db=new DBHelper();
		String sql="SELECT tt.t_id,SUM(ttt.soft_price),SUM(ttt.hard_price),SUM(ttt.seat_price),tt.start_time,tt.end_time FROM TRAININFO TTT, (SELECT T1.T_ID, T1.T_INIT, T1.INIT_ID, T2.T_DEST, T2.DEST_ID,t1.start_time,t2.end_time FROM TRAININFO T1, TRAININFO T2 WHERE T1.T_ID = T2.T_ID AND T1.T_INIT =? AND T2.T_DEST =? ) TT WHERE TTT.T_ID = TT.T_ID AND TTT.INIT_ID >= TT.INIT_ID AND TTT.DEST_ID <= TT.DEST_ID GROUP BY tt.t_id,tt.start_time,tt.end_time";
		List<Object> params=new ArrayList<Object>();
		params.add(t_init);
		params.add(t_dest);
		return db.finds(sql, params);
	}

	/**
	 * 查询火车票数
	 * @param dates  乘车日期
	 * @param t_ID  车号
	 * @param t_init  初始地
	 * @param t_dest  目的地
	 * @param seatkind  座位种类
	 * @return
	 */
	public double count(String dates,String t_ID,String t_init,String t_dest,String seatkind){
		DBHelper db=new DBHelper();
		String sql="SELECT COUNT(TII.Seat_Id ) FROM (SELECT DISTINCT Ti.Seat_Id FROM TICKETORDER TI  WHERE TI.datet=?  AND TI.T_ID = ? AND  TI.SEAT_KIND = ? AND  TI.CONDITION!='已退票' AND (((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?) >= TI.INIT_ID AND (SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT = ?  AND T.T_ID =?) < TI.DEST_ID) OR  ((SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?) >TI.INIT_ID AND (SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?) <= TI.DEST_ID)OR  ((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?)<=TI.INIT_ID)AND (( SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =? AND T.T_ID =?)>=TI.DEST_ID)OR ((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?)>=TI.INIT_ID)AND (( SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =? AND T.T_ID = ?)<=TI.DEST_ID))) TII";
		List<Object> params=new ArrayList<Object>();
		List<Object> params1=new ArrayList<Object>();
		double t=0;
		params.add(dates);
		params.add(t_ID);
		params.add(seatkind);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);

		params1.add(t_ID);
		if(seatkind=="硬座"){
			String sql1="select t.t_seat from train t where t.t_ID=?";
			t=db.getTotal(sql1, params1);
		}else if(seatkind=="硬卧"){
			String sql1="select t.hard_seat from train t where t.t_ID=?";
			t=db.getTotal(sql1, params1);
		}else if(seatkind=="软卧"){
			String sql1="select t.soft_seat from train t where t.t_ID=?";
			t=db.getTotal(sql1, params1);
		}
//		System.out.println("t="+t);
//		System.out.println("已购票数"+db.getTotal(sql, params));
		return t-db.getTotal(sql, params);
	}

	/**
	 * 分配座位
	 * @param dates 乘车日期
	 * @param t_ID  火车号
	 * @param t_init 初始地
	 * @param t_dest 目的地
	 * @param seatkind 座位类型
	 * @return
	 */
	public int assign_seat(String dates,String t_ID,String t_init,String t_dest,String seatkind){
		DBHelper db=new DBHelper();
//		if(dates.equals("2016-09-22")){
//			System.out.println("时间是对的");
//		}
		String sql=" SELECT DISTINCT Ti.Seat_Id FROM TICKETORDER TI  WHERE TI.datet=?  AND TI.T_ID = ? AND  TI.SEAT_KIND = ? AND  TI.CONDITION!='已退票' AND (((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?) >= TI.INIT_ID AND (SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT = ?  AND T.T_ID =?) < TI.DEST_ID) OR  ((SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?) >TI.INIT_ID AND (SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?) <= TI.DEST_ID)OR  ((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?)<=TI.INIT_ID)AND (( SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =? AND T.T_ID =?)>=TI.DEST_ID)OR ((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?)>=TI.INIT_ID)AND (( SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =? AND T.T_ID = ?)<=TI.DEST_ID))";
		//  String sql="  SELECT DISTINCT Ti.Seat_Id FROM TICKETORDER TI  WHERE TI.datet ='2016-09-22'      AND TI.T_ID = 'G999' AND  TI.SEAT_KIND = '软卧' AND  TI.CONDITION!='已退票' AND (((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =  '北京站'  AND T.T_ID = 'G999') >= TI.INIT_ID AND (SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =  '北京站'  AND T.T_ID = 'G999') < TI.DEST_ID) OR ((SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =  '长沙站'  AND T.T_ID = 'G999') >TI.INIT_ID AND (SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =  '长沙站'  AND T.T_ID = 'G999') <= TI.DEST_ID)OR   ((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =  '北京站'  AND T.T_ID = 'G999')<=TI.INIT_ID)AND (( SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =  '长沙站' AND T.T_ID = 'G999')>=TI.DEST_ID)OR  ((SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =  '北京站'  AND T.T_ID = 'G999')>=TI.INIT_ID)AND (( SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST = '长沙站' AND T.T_ID = 'G999')<=TI.DEST_ID))";
		List<Object> params=new ArrayList<Object>();
		List<Object> params1=new ArrayList<Object>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		double t=0;
//		System.out.println("----------------------");
//		System.out.println(dates);
//		System.out.println(t_ID.trim());
//		System.out.println(seatkind.trim());
//		System.out.println(t_init.trim());
//		System.out.println(t_dest.trim());
//		System.out.println("***************+++++++++++++++++++++");
		params.add(dates);
		params.add(t_ID);
		params.add(seatkind);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		list=db.finds(sql, params);
		System.out.println("___________________________________");
		System.out.println(list);
		System.out.println(list.size());
		//System.out.println(list.get(0).get("seat_id"));
		System.out.println("***************************8");
		params1.add(t_ID);
		if(seatkind.equals("硬座")){
			String sql1="select t.t_seat from train t where t.t_ID=?";
			t=db.getTotal(sql1, params1);
		}else if(seatkind.equals("硬卧")){
			String sql1="select t.hard_seat from train t where t.t_ID=?";
			t=db.getTotal(sql1, params1);
		}else if(seatkind.equals("软卧")){
			String sql1="select t.soft_seat from train t where t.t_ID=?";
			t=db.getTotal(sql1, params1);
		}
		if(list==null||list.size()==0){
			return 1;
		}
		for(int i=1;i<=t;i++){
			int count=0;
			for(int j=1,len=list.size();j<=len;j++){
				System.out.println(list.get(j-1).get("seat_id"));
				if(String.valueOf(i).equals(list.get(j-1).get("seat_id"))){
					System.out.println(list.get(j-1).get("seat_id"+"---"));
					break;
				}
				count++;
				if(count==len){
					return i;
				}
			}
		}
		return 0;
	}

	/**
	 * 买票中的改动traininfo和train中的票数 
	 * @param t_ID 火车号
	 * @param t_init 初始地
	 * @param t_dest 目的地
	 * @return
	 */
	public double buy_tickets(String t_ID,String t_init,String t_dest,String seatkind){
		DBHelper db=new DBHelper();
		String sql="";
		//	String sql1="";
		System.out.println(seatkind);
		if("硬座".equals(seatkind)){
			sql=" UPDATE TRAININFO T SET T_SEAT = T_SEAT - 1 WHERE T.T_ID =?  AND T.INIT_ID >= (SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?) AND T.DEST_ID <= (SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?)";
			// sql1="update train t set t.t_seat=t.t_seat-1 where t.t_ID=?";
		}else if("硬卧".equals(seatkind)){
			sql=" UPDATE TRAININFO T SET hard_seat = hard_seat - 1 WHERE T.T_ID =?  AND T.INIT_ID >= (SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?) AND T.DEST_ID <= (SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?)";
			//	sql1="update train t set t.head_seat=head_seat-1 where t.t_ID=?";
		}else if("软卧".equals(seatkind)){
			sql=" UPDATE TRAININFO T SET soft_seat = soft_seat - 1 WHERE T.T_ID =?  AND T.INIT_ID >= (SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?) AND T.DEST_ID <= (SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?)";
			// sql1="update train t set t.soft_seat=soft_seat-1 where t.t_ID=?";
		}
		List<Object> params=new ArrayList<Object>();
		//	List<Object> params1=new ArrayList<Object>();
		params.add(t_ID);
		params.add(t_init);
		params.add(t_ID);
		params.add(t_dest);
		params.add(t_ID);
		//    params1.add(t_ID);
		return db.update(sql, params);  //+db.update(sql1, params1);
	}

	/**
	 * 返回该站点在指定火车号上的站点号
	 * @param t_ID 火车号
	 * @param station_name 站点名
	 * @return
	 */
	public double find_station(String t_ID,String station_name){
		double a=0;
		double b=0;
		DBHelper db=new DBHelper();
		String sql="SELECT T.INIT_ID FROM TRAININFO T WHERE T.T_INIT =?  AND T.T_ID =?";
		String sql1="SELECT T.DEST_ID FROM TRAININFO T WHERE T.T_DEST =?  AND T.T_ID =?";
		List<Object> params=new ArrayList<Object>();
		List<Object> params1=new ArrayList<Object>();
		params.add(station_name);
		params.add(t_ID);
		params1.add(station_name);
		params1.add(t_ID);
		a=db.getTotal(sql, params);
		b=db.getTotal(sql1, params1);
		if(a==0&&b!=0){
			return b;
		}
		if(a!=0&&b==0){
			return a;
		}
		if(a!=0&&b!=0&&a==b){
			return b;
		}
		return 0;
	}

	/**
	 * 添加火车信息
	 * @param i 初始地车站号
	 * @param t_ID 火车号
	 * @param t_init 初始地
	 * @param t_dest 目的地
	 * @param soft_price 软卧价
	 * @param hard_price 硬卧价
	 * @param seat_price 硬座价 
	 * @param start_time 出发时间
	 * @param end_time 到站时间
	 * @return
	 */
	public int add(Integer i,String t_ID,String t_init,String t_dest,Double soft_price,Double hard_price,Double seat_price,String start_time,String end_time){
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		String sql="INSERT INTO traininfo(t_ID,t_init,init_id,t_dest,dest_id,soft_price,hard_price,seat_price,start_time,end_time)values(?,?,?,?,?,?,?,?,?,?)";
		params.add(t_ID);
		params.add(t_init);
		params.add(i);
		params.add(t_dest);
		params.add(i+1);
		params.add(soft_price);
		params.add(hard_price);
		params.add(seat_price);
		params.add(start_time);
		params.add(end_time);
		return db.update(sql, params);

	}
}
