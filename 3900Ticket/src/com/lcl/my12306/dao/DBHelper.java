package com.lcl.my12306.dao;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	static{
		try {
			Class.forName(ReadPro.getInstance().getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	private Connection getConnection(){
		Connection con=null;
	    try {
			con=DriverManager.getConnection(ReadPro.getInstance().getProperty("url"),
					    ReadPro.getInstance().getProperty("user"),
					    ReadPro.getInstance().getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return con;
	}
	
	private void closeAll(Connection con,PreparedStatement pstmt){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs){
		this.closeAll(con,pstmt);
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setValues(PreparedStatement pstmt,List<Object>params){
		if(params!=null&&params.size()>0){
			for(int i=0,len=params.size();i<len;i++){
				Object obj=params.get(i);
				String type;
				try {
					if(obj!=null){
						type=obj.getClass().getSimpleName();
						if("byte[]".equals(type)){
							pstmt.setBytes(i+1,(byte[])obj);
						}else{
							pstmt.setString(i+1,String.valueOf(obj));
						}
					}else{
					    pstmt.setString(i+1, (String)obj);
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int update(String sql,List<Object> params){
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValues(pstmt,params);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con,pstmt);
		}
		return result;
	}
	
	public int update1(List<String> sqls, List<List<Object>> params) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=this.getConnection();
			
			//把自动提交事务设为false
			con.setAutoCommit(false);
			if(sqls!=null&&sqls.size()>0){
				for(int i=0,len=sqls.size();i<len;i++){
			       pstmt=con.prepareStatement(sqls.get(i));
                   //给预编译sql语句中的占位符赋值
			       this.setValues(pstmt,params.get(i));
		           result=pstmt.executeUpdate();
				}
			}		con.commit();
		} catch (SQLException e) {
            try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeAll(con,pstmt);
		}
		return result;
	}
/*
 * 
 * 
 */
	public double getTotal(String sql,List<Object> params){
		double result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValues(pstmt,params);
			rs=pstmt.executeQuery();
			while(rs.next()){
				result=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con,pstmt,rs);
		}
		return result;
	}
	
	/*
	 * 可将列名先取出放入数组
	 * （如下）
	 */
	public String[] getColumnNames(ResultSet rs){
		String[] colNames =null;
		try{
			ResultSetMetaData rsmd=rs.getMetaData();
			int len=rsmd.getColumnCount();
			colNames=new String[len];
			for(int i=0;i<len;i++){
				colNames[i]=rsmd.getColumnName(i+1).toLowerCase();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return colNames;
	}

	
	public Map<String,String> find(String sql,List<Object>params){
		Map<String,String> map=new HashMap<String,String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValues(pstmt,params);
			rs=pstmt.executeQuery();
			String[]colNames=this.getColumnNames(rs);
			if(rs.next()){
			   for(int i=0;i<colNames.length;i++){
			    	map.put(colNames[i], ""+rs.getString(i+1));
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public List<Map<String, Object>> findsPic(String sql,List<Object>params){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);

			this.setValues(pstmt,params);
			rs=pstmt.executeQuery();
			String[]colNames=this.getColumnNames(rs);
			Map<String,Object> map=null;
			String type;
			Object obj;
			while(rs.next()){
				map=new HashMap<String,Object>();
				for(int i=0,len=colNames.length;i<len;i++){
					obj=rs.getObject(colNames[i]);
					if(obj==null){
						map.put(colNames[i],null);
					}else{
						type=obj.getClass().getSimpleName();
					    if("BigDecimal".equals(type)){
					    	map.put(colNames[i],Double.parseDouble(String.valueOf(obj)));
					    }else if("BLOB".equals(type)){
					    	BufferedInputStream bis=null;
					    	byte[] bt=null;
					    	Blob blob=rs.getBlob(colNames[i]);
					    	bis=new BufferedInputStream(blob.getBinaryStream());
					    	try{
					    	   bt=new byte[(int)blob.length()];
					    	   bis.read(bt);
					    	}catch(IOException e){
					    		e.printStackTrace();
					    	}finally{
					    		if(bis==null){
					    			try{
						    			bis.close();
								    }catch(IOException e){
								    	e.printStackTrace();
								    }
					    		}
					    	}
					    	map.put(colNames[i], bt);
					    }else{
							map.put(colNames[i],(String)obj);
					    }
				    }
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,String>> finds(String sql,List<Object>params){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValues(pstmt,params);
			rs=pstmt.executeQuery();
			String[]colNames=this.getColumnNames(rs);
			System.out.println(colNames[0]+"----");
			Map<String,String> map=null;
			while(rs.next()){
				map=new HashMap<String,String>();
				for(int i=0,len=colNames.length;i<len;i++){
					map.put(colNames[i],rs.getString(colNames[i]));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
