package com.gqz.news.util;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class UtilRegex {

	/**
	 * 说明：正负面判断
	 * @author 旋葎
	 * 2017 8 30
	 */
	/*public static int numCount(String title) throws Exception {
		int num=0;
		Properties prop = new Properties();
		InputStreamReader in = new InputStreamReader(new FileInputStream("keyWord.properties"));
		prop.load(in);
		Iterator<String> it = prop.stringPropertyNames().iterator();
		while (it.hasNext()) {
			String key = it.next();			
			if(title.contains(prop.getProperty(key))) {
				num++;
			}
		}	
		in.close();			
		return num;
	}
	
	//根据标题判断新闻标志，true表示负面新闻，false表示正面新闻
	public static boolean mark(String title) throws Exception {
		int num = numCount(title);			
		boolean flag = false;
		if(num>=2){
			flag=true;
		}
		return flag;
	}
	*/
}
