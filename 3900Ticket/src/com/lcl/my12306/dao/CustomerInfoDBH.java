package com.lcl.my12306.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerInfoDBH {

    /**
     * 查找客户信息
     * @param accountId 12306账号
     * @return
     */
	public Map<String,String> find(String accountId ){
		DBHelper db=new DBHelper();
		String sql="SELECT * FROM customers c WHERE c.accountid=?";
		List<Object> params=new ArrayList<Object>();
        params.add(accountId);
        return db.find(sql, params);
	}
	
	/**
	 * 修改是否是学生 并将学校名学号存入数据库 or 修改密保问题和答案
	 * @param isstudent
	 */
	public int alter_stu(String accountId,String school,String stu_ID){
		DBHelper db=new DBHelper();
		String sql="update customers c set c.isStudent='是', c.school=?,c.stu_ID=? where c.accountid=? ";
		List<Object> params=new ArrayList<Object>();
        params.add(school);
        params.add(stu_ID);
        params.add(accountId);
        return db.update(sql, params);
	}
	/**
	 * 修改密保问题和答案
	 * @param question 新问题
	 * @param answer 新答案
	 */
	public int alter_ques(String accountId,String question,String answer ){
		DBHelper db=new DBHelper();
		String sql="update customers c set c.question=?,c.answer=? where c.accountid=? ";
		List<Object> params=new ArrayList<Object>();
        params.add(question);
        params.add(answer);
        params.add(accountId);
        return db.update(sql, params);
	}
	
	/**
	 * 修改客户邮箱
	 * @param accountId 账号
	 * @param mail 新邮箱
	 */
	public int alter_mail(String accountId,String mail){
		DBHelper db=new DBHelper();
		String sql="update customers c set c.mailbox=? where c.accountid=? ";
		List<Object> params=new ArrayList<Object>();
        params.add(mail);
        params.add(accountId);
        return db.update(sql, params);
	}
	
	/**
	 * 修改登录密码
	 * @param accountId 账号
	 * @param epwd 新密码
	 */
	public int alter_epwd(String accountId,String epwd){
		DBHelper db=new DBHelper();
		String sql="update customers c set c.e_pwd=? where c.accountid=? ";
		List<Object> params=new ArrayList<Object>();
        params.add(epwd);
        params.add(accountId);
        return db.update(sql, params);
	}
	
	/**
	 * 修改登录密码
	 * @param accountId 账号
	 * @param ppwd 新密码
	 */
	public int alter_ppwd(String accountId,String ppwd){
		DBHelper db=new DBHelper();
		String sql="update customers c set c.p_pwd=? where c.accountid=? ";
		List<Object> params=new ArrayList<Object>();
        params.add(ppwd);
        params.add(accountId);
        return db.update(sql, params);
	}
	/**
	 * 第一次注册添加
	 * @param accountId 账号
	 * @param e_pwd 登录密码
	 * @param mailbox 邮箱
	 * @param question 密保问题
	 * @param answer 密保答案
	 * @return
	 */
	public int add1(String accountId,String e_pwd,String mailbox,String question,String answer ){
		DBHelper db=new DBHelper();
        String sql="insert into customers(accountId, e_pwd, email, question, answer) values(?,?,?,?,?) ";
		List<Object> params=new ArrayList<Object>();
        params.add(accountId);
        params.add(e_pwd);
        params.add(mailbox);
        params.add(question);
        params.add(answer);
        return db.update(sql, params);
	}
	
	/**
	 * 第二次注册添加 完善个人信息
	 * @param cname 姓名
	 * @param p_pwd 支付密码
	 * @param c_ID 身份证号
	 * @param sex 性别
	 * @param age 年龄
	 * @return
	 */
	public int add2(String accountId,String cname,String c_ID, String sex,String p_pwd){
		DBHelper db=new DBHelper();
       // String sql="insert into customers1(cname, c_ID, sex, p_pwd) values(?,?,?,?) ";
		String sql="update customers set cname=?, c_ID=?, sex=?, p_pwd=? where accountId=?";
		List<Object> params=new ArrayList<Object>();
        params.add(cname);
        params.add(c_ID);
        params.add(sex);
        params.add(p_pwd);
        params.add(accountId);
        return db.update(sql, params);
	} 
	
	/**
	 * 显示所有密保问题
	 * @return
	 */
	public List<Map<String,String>> findQuestion(){
		DBHelper db=new DBHelper();
		String sql="select question from customers";
		return db.finds(sql, null);
	}
}
