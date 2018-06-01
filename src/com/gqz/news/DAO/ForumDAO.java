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

/**
 * 
* @ClassName: ForumDAO
* @Description: TODO(对forum表的操作层   数据库访问层DAO)
* @author ganquanzhong
* @date 2018年1月2日 上午12:29:59
 */
public class ForumDAO {
	//获取数据库连接资源
	QueryRunner runner=new QueryRunner(DBCPUtils.getDataSource());
		
	/**
	 * 
	* @Title: getForum
	* @Description: TODO(获取论坛列表)
	* @author ganquanzhong
	* @date  2018年1月2日 上午12:50:32
	* @return
	 */
	public List<Forum> getForum() {
		List<Forum> list = new ArrayList<Forum>();
		String sql = "select * from forum where isDel=0 ";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<Forum> bh = new BeanListHandler<Forum>(Forum.class);
		try {
			list = runner.query(sql, bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	
	
	/**
	 * 
	* @Title: getForumById
	* @Description: TODO(通过制定的id获取论坛的详细信息)
	* @author ganquanzhong
	* @date  2018年1月2日 上午12:32:58
	* @param ForumId
	* @return
	 */
	public Forum getForumById(String forumId) {
		Forum forum=new Forum();
		// SQL语句：查询forum表中的论坛
		String sql = "select * from forum where isDel=0 and id=? ";
		BeanHandler<Forum> bh = new BeanHandler<Forum>(Forum.class);
		//用BeanHandler（代表返回结果为单个对象）
		try {
			forum = runner.query(sql, bh,forumId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forum;
	}
	
	
	/**
	 * 
	* @Title: insert
	* @Description: TODO(插入一条论坛)
	* @author ganquanzhong
	* @date  2018年1月2日 上午12:37:56
	* @param forum
	* @return
	 */
	public int insert(Forum forum){
		int result=0;
		String sql="insert into forum(name,username,email,subject,content,pictures,time,isDel) " +
				" values(?,'mike zhong','1367895458@163.com',?,?,?,now(),0)";
		try {
			//admin是一个NewsAdmin对象，保存查询结果
			result=runner.update(sql,forum.getName(),forum.getSubject(), 
					forum.getContent(),forum.getPictures());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getNewsClassByPage
	* @Description: TODO(分页显示论坛)
	* @author ganquanzhong
	* @date  2018年1月3日 下午6:15:18
	* @param startIndex
	* @param pageSize
	* @return
	 */
	public List<Forum> getForumByPage(int startIndex, int pageSize) {
		List<Forum> list = new ArrayList<Forum>();
		// SQL语句：查询note表中的所有信息，按照noteId降序排列
		String sql = "select * from Forum where isDel=0  order by Id asc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<Forum> bh = new BeanListHandler<Forum>(Forum.class);
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
		String sql = "select count(*) from forum";
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
	public int delete(String forumId) {
		int result = 0;
		String sql = "update forum set isDel=1 where id=?";
		try {
			result = runner.update(sql, forumId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
