package com.lcl.my12306.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StationinfoDBH {

	/**
	 * 添加站点
	 * @param s_ID 站点ID
	 * @param s_name 站点名
	 * @return
	 */
	public int add(String s_name){
		DBHelper db=new DBHelper();
		String sql="insert into station (s_name)values (?)";
		List<Object> params=new ArrayList<Object>();
        params.add(s_name);
        return db.update(sql, params);
	}
	
	/**
	 * 修改站点状态为 “暂停使用”
	 * @param s_ID 站点编号
	 * @return
	 */
	public int delete(String s_ID){
		DBHelper db=new DBHelper();
		String sql="update station  set condition='暂停使用' where s_ID=?";
		List<Object> params=new ArrayList<Object>();
		params.add(s_ID);
		return db.update(sql, params);
	}
	
	/**
	 * 查找车站信息
	 * @return
	 */
	public List<Map<String, String>> find(){
		DBHelper db=new DBHelper();
		String sql="select s_ID,s_name  from station where condition!='暂停使用'";
//		List<Object> params=new ArrayList<Object>();
//		params.add(s_ID);
		return db.finds(sql, null);
	}
	
	/**
	 * 删除站点信息
	 * @param s_ID
	 * @return
	 */
	public int del(String s_ID){
		DBHelper db=new DBHelper();
		String sql;
		List<Object> params=new ArrayList<Object>();
		if(s_ID!=null && "".equals(s_ID) && s_ID.contains(",") && !"or".contains(s_ID)){
			sql="delete from station where s_ID in("+s_ID+")";
		}else{
			sql="delete from station where s_ID=?";
			params.add(s_ID);
		}
		return db.update(sql,params);
	}
	
	/**
	 * 按照给定的站名查询是否有改站点
	 * @param s_name
	 * @return
	 */
	public Map<String,String> finds(String s_name){
		DBHelper db=new DBHelper();
		String sql="select s_ID,s_name  from station where s_name=?";
		List<Object> params=new ArrayList<Object>();
		params.add(s_name);
		return db.find(sql, params);
	}
}
