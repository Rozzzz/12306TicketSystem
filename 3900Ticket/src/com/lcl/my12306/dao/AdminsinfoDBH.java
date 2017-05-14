package com.lcl.my12306.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminsinfoDBH {

	/**
	 * 管理员登录
	 * @param a_ID 管理员ID
	 * @param a_pwd 管理员密码
	 * @return
	 */
	public boolean landing(String a_ID,String a_pwd){
		DBHelper db=new DBHelper();
		String sql="select * from admins a where a.a_ID=? and a.a_pwd=?";
		List<Object> params=new ArrayList<Object>();
		Map<String,String> map=new HashMap<String,String>();
        params.add(a_ID);
        params.add(a_pwd);
        map=db.find(sql, params);
        if(map!=null && map.size()>0){
        	return true;
        }
        return false;
	}
}
