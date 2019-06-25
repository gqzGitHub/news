package com.gqz.news.DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gqz.news.common.DBCPUtils;
import com.gqz.news.model.NewsPo;


/**
 * 
* @ClassName: NetnewsDAO
* @Description: 网络来源新闻
* @author ganquanzhong
* @date 2019年4月1日 下午8:19:10
 */
public class NetnewsDAO {
	QueryRunner runner = new QueryRunner(DBCPUtils.getDataSource());
	/**
	 * 
	 * @Title: insert
	 * @Description: TODO(对news表进行数据插入)
	 * @author ganquanzhong
	 * @date 2017-11-27 上午09:25:22
	 * @param news
	 *            代表要插入的新闻数据对象
	 * @return 当返回为0时，表示插入不成功；返回不为0时，代表插入成功
	 */
	public int insert(NewsPo netnews) {
		int result = 0;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/*
		 * public int update(Connection conn,String sql,Object[] params) throws
		 * SQLException; 用来执行一个更新（插入、更新或删除）操作
		 */
		String sql = "insert into netnews(title,url,intro,datetime,media,plus_minus,status,isDel)"
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			try {
				result = runner.update(sql,
						netnews.getTitle(),
						netnews.getUrl(),
						netnews.getIntro(),						
						sd.parse(netnews.getDatetime()),
						netnews.getMedia(),
						netnews.getPlus_minus(),
						netnews.getStatus(),
						0					
						);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: getList
	 * @Description: TODO(获取网络新闻列表)
	 * @author ganquanzhong
	 * @date 2017-12-4 上午10:20:47
	 * @return
	 */
	public List<NewsPo> getList() {
		List<NewsPo> list = new ArrayList<NewsPo>();

		// SQL语句：查询newsclass表中的所有信息，按照classId升序排列
		String sql = "select * from netnews where isDel=0 "
				+ "order by id asc";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<NewsPo> bh = new BeanListHandler<NewsPo>(NewsPo.class);
		try {
			list = runner.query(sql, bh);
//			System.out.println("数据查询成功，并且成功将新闻分类表<news>数据以list集合的方式存入！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: getListByPage
	 * @Description: TODO(得到一个startIndex开始的pageSize长的记录到list集合中 limit
	 *               startIndex,pageSize)
	 * @author ganquanzhong
	 * @date 2017-12-5 上午10:23:21
	 * @param startIndex
	 *            查询开始的索引
	 * @param pageSize
	 *            查询的范围
	 * @return
	 */
	public List<NewsPo> getListByPage(int startIndex, int pageSize) {
		List<NewsPo> list = new ArrayList<NewsPo>();
		// SQL语句：查询newsclass表中的所有信息，按照classId升序排列
		String sql = "select * from netnews where isDel=0 "
				+ "order by id desc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<NewsPo> bh = new BeanListHandler<NewsPo>(NewsPo.class);
		try {
			list = runner.query(sql, bh, startIndex, pageSize);
//			System.out.println("数据查询成功，并且成功将新闻分类表<news>数据以list集合的方式存入！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: getVerifiedListByPage
	 * @Description: TODO(得到 已审核新闻 一个startIndex开始的pageSize长的记录到list集合中 limit    startIndex,pageSize)
	 * @author ganquanzhong
	 * @date 2017-12-15 上午10:48:54
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<NewsPo> getVerifiedListByPage(int startIndex, int pageSize) {
		List<NewsPo> list = new ArrayList<NewsPo>();
		// SQL语句：查询newsclass表中的所有信息，按照classId升序排列
		String sql = "select * from netnews where isDel=0  order by id desc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<NewsPo> bh = new BeanListHandler<NewsPo>(NewsPo.class);
		try {
			list = runner.query(sql, bh, startIndex, pageSize);
//			System.out.println("数据查询成功，并且成功将新闻分类表<news>数据以list集合的方式存入！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: getTotalRecordNum
	 * @Description: TODO(从数据库查询news表的记录数)
	 * @author ganquanzhong
	 * @date 2017-12-5 上午10:32:38
	 * @return
	 */
	public int getTotalRecordNum() {
		Long num = null;
		String sql = "select count(*) from netnews where isDel=0 ";
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
}
