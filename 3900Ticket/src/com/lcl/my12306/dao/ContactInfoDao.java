package com.lcl.my12306.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ContactInfoDao {
	
	/**
	 * 添加联系人
	 * @param l_id：联系人身份证
	 * @param l_name：联系人姓名
	 * @param isstudent:联系人是否是学生
	 * @param accountid：当前用户
	 * @return
	 */
	public int add(String l_id,String l_name, String isStudent,String accountid,String school,String stu_id){
		DBHelper db=new DBHelper();
		String sql="insert into linkman(l_id,l_name,isStudent,accountid,school,stu_id)values(?,?,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(l_id);
		params.add(l_name);
		params.add(isStudent);
		params.add(accountid);
		params.add(school);
		params.add(stu_id);
		return db.update(sql, params);
	}
	
	/**
	 * 查看所有联系人
	 * @param accountid：当前用户
	 * @return
	 */
	public List<Map<String,String>> find(String accountid){
		DBHelper db=new DBHelper();
		String sql="select l_name,l_id,isStudent,school,stu_id from linkman where accountid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(accountid);
		return db.finds(sql, params);
		
	} 
	
	
	/**
	 * 删除
	 * @param l_name：联系人姓名
	 * @return
	 */
	public int del(String l_name){
		DBHelper db=new DBHelper();
		String sql;
		List<Object> params=new ArrayList<Object>();
		if(l_name!=null && !"".equals(l_name) && l_name.contains(",") && !" or".contains(l_name)){
			sql="delete from linkman where l_name in("+l_name+")";
		}else{
			sql="delete from linkman where l_name =?";
			params.add(l_name);
		}
		return db.update(sql, params);
	}
}
