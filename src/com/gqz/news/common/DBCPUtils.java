package com.gqz.news.common;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;




/**
 * 
* @ClassName: DBCPUtils
* @Description: TODO(这里用一句话描述这个类的作用)
* @author ganquanzhong
* @date 2017-12-1 上午09:24:41
* 得到一个DataSource
* 
 */
public class DBCPUtils {
	private static DataSource dataSource;
	/*
	 1.类加载的时候，读取 dbcpconfig.proerties文件
	 */
	/**
	 * 
	 */
	static{
		//读取 dbcpconfig.proerties文件.得到一个输入流的对象
		InputStream is = DBCPUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
		Properties props=new Properties();
		try {
			props.load(is);
			dataSource = BasicDataSourceFactory.createDataSource(props);
			System.out.println("数据库连接池创建成功！！！！！！！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getDataSource
	* @Description: TODO(获取数据库连接池)
	* @author ganquanzhong
	* @date  2017-12-1 上午09:40:34
	* @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	/**
	 * 
	* @Title: getConnection
	* @Description: TODO(通过数据库池获得连接)
	* @author ganquanzhong
	* @date  2017-12-1 上午09:40:12
	 */
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
