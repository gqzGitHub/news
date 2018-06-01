package com.gqz.news.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gqz.news.common.DBCPUtils;
import com.gqz.news.model.NewsAdmin;
import com.gqz.news.model.NewsUser;
/**
 * 
* @ClassName: NewsAdminDAO
* @Description: TODO(NewsAdmin  Data Access Object  newadmin表的数据访问对象)
* @author ganquanzhong
* @date 2017-11-24 上午09:12:42
 */
public class NewsAdminDAO {
	//获取数据库连接资源
	QueryRunner runner=new QueryRunner(DBCPUtils.getDataSource());
	/**
	 * 
	* @Title: isExist
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2017-11-24 上午09:10:27
	* @param username  用户名
	* @param password  密码
	* @return  如果用户名和密码存在adminuser表中的一条记录
	* 返回true  否则返回false
	 */
	public boolean isExist(String username,String password){
		boolean result=false;
		NewsAdmin admin=null;		
		String sql="select * from newsadmin where username=? and password=?" +"and isDel=0";
		BeanHandler<NewsAdmin> bh=new BeanHandler<NewsAdmin>(NewsAdmin.class);			
		try {
			//admin是一个NewsAdmin对象，保存查询结果
			admin=runner.query( sql, bh, username,password);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(admin!=null){
			result=true;
			System.out.println("newsadmin数据库中存在该用户！");
		}
		return result;
	}
	
	/**
	 * 
	* @Title: Insert
	* @Description: TODO(添加管理员)
	* @author ganquanzhong
	* @date  2018年1月3日 下午5:36:06
	* @param username
	* @param password
	* @param type
	* @return
	 */
	public int Insert(String username,String password, String type){
		int result=0;
		String sql="insert into newsadmin(username,password,type,isDel) values(?,?,?,'0')";
		try {
			result=runner.update(sql, username,password,type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 
	* @Title: UpdatePwd
	* @Description: TODO(修改当前用户的密码)
	* @author ganquanzhong
	* @date  2018年1月3日 下午4:51:05
	* @param password
	* @return
	 */
	public int UpdatePwd(String password,String username){
		int result=0;
		String sql="update newsadmin set password=? where username=? and isDel=0";
		try {
			result = runner.update(sql, password,username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: UpdateName
	* @Description: TODO(修改名称)
	* @author ganquanzhong
	* @date  2018年1月3日 下午11:11:18
	* @param password
	* @param username
	* @return
	 */
	public int UpdateName(String newusername, String type,String oldusername){
		int result=0;
		String sql="update newsadmin set username=?,type=?  where username=? and isDel=0";
		try {
			result = runner.update(sql, newusername ,type ,oldusername);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getNewsAdminByPage
	* @Description: TODO(分页显示管理员列表)
	* @author ganquanzhong
	* @date  2018年1月4日 下午1:45:57
	* @param startIndex
	* @param pageSize
	* @return
	 */
	public List<NewsAdmin> getNewsAdminByPage(int startIndex, int pageSize) {
		List<NewsAdmin> list = new ArrayList<NewsAdmin>();
		// SQL语句：查询note表中的所有信息，按照noteId降序排列
		String sql = "select * from newsadmin where isDel=0  order by Id asc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<NewsAdmin> bh = new BeanListHandler<NewsAdmin>(NewsAdmin.class);
		try {
			list = runner.query(sql, bh, startIndex, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	* @Title: getTotalRecordNum
	* @Description: TODO(获取论坛表中的所有记录数)
	* @author ganquanzhong
	* @date  2017-12-29 上午11:05:06
	* @param newsId
	* @return
	 */
	public int getTotalRecordNum() {
		Long num = null;
		String sql = "select count(*) from newsadmin";
		try {
			Object obj = runner.query(sql, new ScalarHandler());
			num = (Long) obj;
			return num.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * 
	* @Title: delete
	* @Description: TODO(删除一条论坛)
	* @author ganquanzhong
	* @date  2017-12-29 上午11:05:36
	* @param noteId
	* @return
	 */
	public int delete(String id) {
		int result = 0;
		String sql = "update newsadmin set isDel=1 where id=?";
		try {
			result = runner.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
