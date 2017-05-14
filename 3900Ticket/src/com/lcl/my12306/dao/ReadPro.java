package com.lcl.my12306.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*
 * 读取配置文件 db.properties
 * 因为读取配置文件只需要在使用的时候读取一次，因此没有必要生成多个对象，即在整个程序的运行过程中，只需要这个类的一个对象即可
 * 所以，我们采用单例模式来确保整个运行期间只会实例化出一个对象
 * 单例模式要点：
 *    1.构造方法私有化
 *    2.提供一个私有的、静态的变量
 *    3.提供一个共有的、静态的方法返回实例化对象
 */
public class ReadPro extends Properties{
	private static ReadPro instance=new ReadPro();
	/*
	 * 构造方法私有化
	 */
	private ReadPro(){
		//需要从db.properties中将内容读取出来，转化为一个Properties对象信息
		InputStream is=this.getClass().getClassLoader().getResourceAsStream("db.properties");
		try {
			this.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ReadPro getInstance(){
		if(instance==null){
			instance=new ReadPro();
		}
		return instance;
	}
	
	
}
