package com.gqz.news.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gqz.news.common.DBCPUtils;
import com.gqz.news.model.Forum;
import com.gqz.news.model.NewsAdmin;
import com.gqz.news.model.NewsUser;

/**
 * 
* @ClassName: NewsUserDAO
* @Description: TODO(newsuser表的访问层)
* @author ganquanzhong
* @date 2017-12-21 上午11:34:25
 */
public class NewsUserDAO {
	QueryRunner runner=new QueryRunner(DBCPUtils.getDataSource());
	/**
	 * 
	* @Title: isExist
	* @Description: TODO(判断newsuser表中是否存在该用户)
	* @author ganquanzhong
	* @date  2017年12月20日 下午9:57:23
	* @param username
	* @param password
	* @return
	 */
	public boolean isExist(String username,String password){
		//获取数据库连接资源
		boolean result=false;
		NewsUser user=null;
		String sql="select * from newsuser where username=? and password=?" +"and isDel=0";
		BeanHandler<NewsUser> bh=new BeanHandler<NewsUser>(NewsUser.class);	
		try {
			//admin是一个NewsAdmin对象，保存查询结果
			user=runner.query( sql, bh, username,password);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(user!=null){
			result=true;
		}
		return result;
	}
	
	/**
	 * 
	* @Title: existNewsUser
	* @Description: TODO(判断newsuser表中是否存在该用户 )
	* @author ganquanzhong
	* @date  2017年12月25日 下午5:23:01
	* @param username
	* @return
	 */
	public boolean existNewsUser(String username){
		boolean result=false;
		NewsUser user=null;		
		String sql="select * from newsuser where username=? and isDel=0";
		BeanHandler<NewsUser> bh=new BeanHandler<NewsUser>(NewsUser.class);	
		try {
			//admin是一个NewsUser对象，保存查询结果
			user=runner.query( sql, bh, username);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(user!=null){
			result=true;
		}
		return result;
	}
	
	/**
	 * 
	* @Title: insert
	* @Description: TODO(用户注册时，向newsuser表中插入一条记录)
	* @author ganquanzhong
	* @date  2017年12月25日 下午5:57:36
	* @param newsUser
	* @return
	 */
	public int insert(NewsUser user){
		int result=0;
		String sql="insert into newsuser(username,password,sex,question,answer,emailAddr,qq,regTime,isDel) "
				+ "values(?,?,?,?,?,?,?,now(),0)" ;
		try {			
			result=runner.update( sql, user.getUserName(),user.getPassword(),user.getSex(),
								user.getQuestion(),user.getAnswer(),
								user.getEmailAddr(),user.getQq());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	/**
	 * 
	* @Title: getNewsUserByPage
	* @Description: TODO(分页显示用户)
	* @author ganquanzhong
	* @date  2018年1月3日 下午6:15:18
	* @param startIndex
	* @param pageSize
	* @return
	 */
	public List<NewsUser> getNewsUserByPage(int startIndex, int pageSize) {
		List<NewsUser> list = new ArrayList<NewsUser>();
		// SQL语句：查询note表中的所有信息，按照noteId降序排列
		String sql = "select * from newsuser where isDel=0  order by Id asc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<NewsUser> bh = new BeanListHandler<NewsUser>(NewsUser.class);
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
		String sql = "select count(*) from newsuser";
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
		String sql = "update newsuser set isDel=1 where id=?";
		try {
			result = runner.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
