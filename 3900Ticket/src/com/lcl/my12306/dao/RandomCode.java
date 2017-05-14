package com.lcl.my12306.dao;

import java.util.Random;

public class RandomCode {

	public String showRandom() {
				StringBuffer sbf =new StringBuffer();
				int flag=0;
				Random rd =new Random();
				while(sbf.toString().length()<6){
					flag=rd.nextInt(3);    //0,1,2
					if(flag==0){			//数字
						sbf.append(rd.nextInt(10));
					}else if(flag==1){		//大写字母
						sbf.append( (char) (rd.nextInt(26)+65));
					}else{
						sbf.append(  (char) (rd.nextInt(26)+97) );
					}
				}
				return sbf.toString();
	}

}
