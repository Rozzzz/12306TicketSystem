package com.lcl.my12306.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


/**
 * 向注册表中添加数据
 * @author Lcl
 *
 */
public class RegisterUtils {
	public static void add(Map<String,String> map){
		Preferences pf=Preferences.systemNodeForPackage(RegisterUtils.class);
		if(map!=null || map.size()>0){
			Set<String> keys=map.keySet();
			for(String key:keys){
				pf.put(key, map.get(key));
			}
		}
//		try {
//			pf.flush();
//		} catch (BackingStoreException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 获取所有注册表中的数据
	 * @return
	 */
	public static Map<String,String> getAll(){
		//先将注册表的路径设定为当前类下
		Preferences pf=Preferences.userNodeForPackage(RegisterUtils.class);
		Map<String,String> map=new HashMap<String,String>();
		try {
			String[] keys=pf.keys();
			if(keys!=null && keys.length>0){
				for(String key:keys){
					map.put(key, pf.get(key, ""));
				}
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据指定的键，返回对应的值
	 * @param key
	 */
	
	public static String get(String key){
		Preferences pf =Preferences.userNodeForPackage(RegisterUtils.class);
		return pf.get(key, null);
	}
	
	/**
	 * 移除
	 * @param key
	 * @return
	 */
	public static void remove(String key){
		Preferences pf=Preferences.userNodeForPackage(RegisterUtils.class);
		pf.remove(key);
	}
}
