package com.lcl.my12306.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainDBH {
	
	public List<Map<String,String>> find(String dates){
		DBHelper db=new DBHelper();
        String sql ="SELECT t_ID FROM train WHERE ? BETWEEN start_day  AND end_day ";
		List<Object> params=new ArrayList<Object>();
		params.add(dates);
		return db.finds(sql, params);
	}
	
	/**
	 * 添加火车信息进入火车总表
	 * @param t_ID
	 * @param start_day
	 * @param end_day
	 * @return
	 */
	public int add(String t_ID,String start_day,String end_day,int times){
		DBHelper db=new DBHelper();
        String sql ="INSERT INTO train (t_ID,start_day,end_day,times)VALUES(?,?,?,?) ";
		List<Object> params=new ArrayList<Object>();
		params.add(t_ID);
		params.add(start_day);
		params.add(end_day);
		params.add(times);
		return db.update(sql, params);
		
	}
}
