package com.gqz.news.DAO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gqz.news.common.DBCPUtils;
import com.gqz.news.model.Note;

/**
 * 
* @ClassName: NoteDAO
* @Description: TODO(对note表的操作层   数据库访问层DAO)
* @author ganquanzhong
* @date 2017-12-21 上午08:14:51
 */
public class NoteDAO {
	//获取数据库连接资源
	QueryRunner runner=new QueryRunner(DBCPUtils.getDataSource());
	
	/**
	 * 
	* @Title: getNoteList
	* @Description: TODO(根据newsId号，获取note的评论list集合)
	* @author ganquanzhong
	* @date  2017-12-21 上午08:24:29
	* @param newsId
	* @return
	 */
	public List<Note> getNoteList(String newsId) {
		List<Note> list = new ArrayList<Note>();
		// SQL语句：查询note表中的所有信息，按照noteId升序排列
		String sql = "select * from note where isDel=0 and newsId=? order by noteId desc";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<Note> bh = new BeanListHandler<Note>(Note.class);
		try {
			list = runner.query(sql, bh, newsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	* @Title: insert
	* @Description: TODO(向新闻评论表插入一条comment)
	* @author ganquanzhong
	* @date  2017-12-22 上午10:58:57
	* @param note
	* @return
	 */
	public int insert(Note note){
		int result=0;
		String sql="insert into note(username,content,noteTime,newsId,isDel) " +
				" values(?,?,now(),?,0)";
		try {
			//admin是一个NewsAdmin对象，保存查询结果
			result=runner.update(sql, note.getUsername(), note.getContent(),note.getNewsId());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getNoteByPage
	* @Description: TODO(将指定的新闻的评论分页显示)
	* @author ganquanzhong
	* @date  2017-12-29 上午10:46:06
	* @param newsId
	* @param startIndex
	* @param pageSize
	* @return
	 */
	public List<Note> getNoteByPage(String newsId, int startIndex, int pageSize) {
		List<Note> list = new ArrayList<Note>();
		// SQL语句：查询note表中的所有信息，按照noteId降序排列
		String sql = "select note.*,news.headTitle as noteTitle from note note left join news news on news.newsId=note.newsId " +
				"where note.isDel=0 and note.newsId=? order by noteId desc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<Note> bh = new BeanListHandler<Note>(Note.class);
		try {
			list = runner.query(sql, bh, newsId,startIndex, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	* @Title: getTotalRecordNumById
	* @Description: TODO(获取评论表中的所有记录数)
	* @author ganquanzhong
	* @date  2017-12-29 上午11:05:06
	* @param newsId
	* @return
	 */
	public int getTotalRecordNumById(String newsId) {
		Long num = null;
		String sql = "select count(*) from note where isDel=0 and newsId=? ";
		try {
			Object obj = runner.query(sql, new ScalarHandler(),newsId);
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
	* @Description: TODO(删除一条评论)
	* @author ganquanzhong
	* @date  2017-12-29 上午11:05:36
	* @param noteId
	* @return
	 */
	public int delete(String noteId) {
		int result = 0;
		String sql = "update note set isDel=1 where noteId=?";
		try {
			result = runner.update(sql, noteId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
