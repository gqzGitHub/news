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
import com.gqz.news.model.Note;
import com.gqz.news.model.Reply;

/**
 * 
* @ClassName: ReplyDAO
* @Description: TODO(获取帖子回复)
* @author ganquanzhong
* @date 2018年1月2日 下午7:30:51
 */
public class ReplyDAO {
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
	public List<Reply> getReply(String discussId) {
		List<Reply> list = new ArrayList<Reply>();
		// SQL语句：查询reply表中的所有信息，按照noteId升序排列
		String sql = "select * from reply where isDel=0 and discussId=? order by id desc";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<Reply> bh = new BeanListHandler<Reply>(Reply.class);
		try {
			list = runner.query(sql, bh, discussId);
			System.out.println("数据查询成功，并且成功将指定discussId论坛表的回复以list集合的方式存入！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
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
	public int insert(Reply reply,String discussId){
		int result=0;
		String sql="insert into reply(discussId,name,content,pictures,time,isDel) " +
				" values(?,?,?,'2018280303280982.jpg',now(),0)";
		try {
			//admin是一个NewsAdmin对象，保存查询结果
			result=runner.update(sql,discussId,reply.getName(),reply.getContent());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: delete
	* @Description: TODO(删除一条回复)
	* @author ganquanzhong
	* @date  2017-12-29 上午11:05:36
	* @param noteId
	* @return
	 */
	public int delete(String id) {
		int result = 0;
		String sql = "update reply set isDel=1 where id=?";
		try {
			result = runner.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
