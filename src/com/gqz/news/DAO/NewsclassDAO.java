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
import com.gqz.news.model.News;
import com.gqz.news.model.Newsclass;
import com.gqz.news.model.Note;
/**
 * 
* @ClassName: NewsclassDAO
* @Description: TODO(这里用一句话描述这个类的作用)
* @author ganquanzhong
* @date 2017-11-29 上午10:39:35
* @return   newsclass 表的所有新闻分类信息
 */
public class NewsclassDAO {
	QueryRunner runner=new QueryRunner(DBCPUtils.getDataSource());
	/**
	 * 
	* @Title: getList
	* @Description: TODO(获取新闻分类列表)
	* @author ganquanzhong
	* @date  2018年1月3日 下午6:16:20
	* @return
	 */
	public List<Newsclass> getList(){
		List<Newsclass> list=new ArrayList<Newsclass>();
		
		//SQL语句：查询newsclass表中的所有信息，按照classId升序排列
		String sql="select * from newsclass where isDel=0 "+"order by classId asc";
		//因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<Newsclass> bh = new BeanListHandler<Newsclass>(Newsclass.class);
		try {
			list=runner.query( sql, bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 
	* @Title: update
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2018年1月3日 下午7:23:55
	* @param classId
	* @return
	 */
	public int update(String classId,String content) {
		int result = 0;
		String sql = "update newsclass set content=? where classId=?";
		try {
			result = runner.update(sql, content,classId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: Insert
	* @Description: TODO(增加新闻分类)
	* @author ganquanzhong
	* @date  2017-12-8 上午11:16:32
	* @param content
	* @return
	 */
	public int Insert(String content){
		int result=0;
		String sql="insert newsclass(content,isDel)  values(?,?)";
		try {
			result=runner.update(sql,content,0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: delete
	* @Description: TODO(删除一条新闻分类)
	* @author ganquanzhong
	* @date  2018年1月3日 下午6:17:20
	* @param noteId
	* @return
	 */
	public int delete(String classId) {
		int result = 0;
		String sql = "update newsclass set isDel=1 where classId=?";
		try {
			result = runner.update(sql, classId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getNewsClassByPage
	* @Description: TODO(分页显示新闻分类)
	* @author ganquanzhong
	* @date  2018年1月3日 下午6:15:18
	* @param startIndex
	* @param pageSize
	* @return
	 */
	public List<Newsclass> getNewsClassByPage(int startIndex, int pageSize) {
		List<Newsclass> list = new ArrayList<Newsclass>();
		// SQL语句：查询note表中的所有信息，按照noteId降序排列
		String sql = "select * from newsclass where isDel=0  order by classId asc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<Newsclass> bh = new BeanListHandler<Newsclass>(Newsclass.class);
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
	* @Description: TODO(获取新闻分类表中的所有记录数)
	* @author ganquanzhong
	* @date  2017-12-29 上午11:05:06
	* @param newsId
	* @return
	 */
	public int getTotalRecordNum() {
		Long num = null;
		String sql = "select count(*) from newsclass";
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
	* @Title: getNewsclassById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2018年1月3日 下午7:12:39
	* @param classId
	* @return
	 */
	public Newsclass getNewsclassById(String classId) {
		Newsclass newsclass = new Newsclass();
		String sql = "select *  from newsclass where isDel=0 and classId=?";
		try {
			newsclass = runner.query(sql, new BeanHandler<Newsclass>(Newsclass.class), classId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsclass;
	}
}
