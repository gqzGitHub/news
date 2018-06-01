package com.gqz.news.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gqz.news.common.DBCPUtils;
import com.gqz.news.model.News;


/**
 * 
 * @ClassName: NewsDAO
 * @Description: TODO(news表的数据访问对象 Data Access Object)
 * @author ganquanzhong
 * @date 2017-11-27 上午10:54:15
 */
public class NewsDAO {
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
	public int insert(News news) {
		int result = 0;
		/*
		 * public int update(Connection conn,String sql,Object[] params) throws
		 * SQLException; 用来执行一个更新（插入、更新或删除）操作
		 */
		String sql = "insert into news(classId,headTitle,content,author,"
				+ "editor,newsFrom,top,newsTime,hits,state,isDel,pictures)"
				+ "values(?,?,?,?,?,?,?,now(),?,?,?,?)";
		try {
			result = runner.update(sql, news.getClassId(), news.getHeadTitle(),
					news.getContent(), news.getAuthor(), news.getEditor(),
					news.getNewsFrom(), news.getTop(), 0, "未审核", 0,
					news.getPictures());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: getList
	 * @Description: TODO(获取新闻列表)
	 * @author ganquanzhong
	 * @date 2017-12-4 上午10:20:47
	 * @return
	 */
	public List<News> getList() {
		List<News> list = new ArrayList<News>();

		// SQL语句：查询newsclass表中的所有信息，按照classId升序排列
		String sql = "select * from news where isDel=0 "
				+ "order by classId asc";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<News> bh = new BeanListHandler<News>(News.class);
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
	public List<News> getListByPage(int startIndex, int pageSize) {
		List<News> list = new ArrayList<News>();
		// SQL语句：查询newsclass表中的所有信息，按照classId升序排列
		String sql = "select * from news where isDel=0 "
				+ "order by classId desc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<News> bh = new BeanListHandler<News>(News.class);
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
	 * @Description: TODO(得到 已审核新闻 一个startIndex开始的pageSize长的记录到list集合中 limit
	 *               startIndex,pageSize)
	 * @author ganquanzhong
	 * @date 2017-12-15 上午10:48:54
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<News> getVerifiedListByPage(int startIndex, int pageSize) {
		List<News> list = new ArrayList<News>();
		// SQL语句：查询newsclass表中的所有信息，按照classId升序排列
		String sql = "select * from news where isDel=0 and state='已审核' order by classId desc limit ?,?";
		// 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）
		BeanListHandler<News> bh = new BeanListHandler<News>(News.class);
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
		String sql = "select count(*) from news where isDel=0 ";
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
	 * @Title: getVerifiedTotalRecordNum
	 * @Description: TODO(从数据库查询已审核的news表的记录数)
	 * @author ganquanzhong
	 * @date 2017-12-15 上午10:50:24
	 * @return
	 */
	public int getVerifiedTotalRecordNum() {
		Long num = null;
		String sql = "select count(*) from news where isDel=0 and state='已审核' ";
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
	 * @Title: getNewsById
	 * @Description: TODO(从news表中取出对应newId的新闻信息)
	 * @author ganquanzhong
	 * @date 2017-12-6 上午11:33:49
	 * @param newsId
	 * @return 返回一条根据newsId查询到的新闻信息
	 */
	public News getNewsById(String newsId) {
		News news = new News();
		String sql = "select *  from news where isDel=0 and newsId=?";
		try {
			news = runner.query(sql, new BeanHandler<News>(News.class), newsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}

	/**
	 * 
	 * @Title: update
	 * @Description: TODO(新闻update )
	 * @author ganquanzhong
	 * @date 2017-12-7 上午09:14:51
	 * @param news
	 * @return
	 */
	public int update(News news) {
		int result = 0;
		String sql = "update news set classId=?,headTitle=? , content=?, author=? , "
				+ "editor=? , newsFrom=? , top=? ,newsTime=?,pictures=? where newsId=?";
		try {
			result = runner.update(sql, news.getClassId(), news.getHeadTitle(),
					news.getContent(), news.getAuthor(), news.getEditor(),
					news.getNewsFrom(), news.getTop(), new Date(),
					news.getPictures(), news.getNewsId());
			System.out.println("-----news新闻表完成一次更新操作！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(删除新闻 delete)
	 * @author ganquanzhong
	 * @date 2017-12-7 上午09:27:42
	 * @param news
	 * @return
	 */
	public int delete(String newsId) {
		int result = 0;
		String sql = "update news set isDel=1 where newsId=?";
		try {
			result = runner.update(sql, newsId);
//			System.out.println("--删除操作--" + newsId + "号新闻已删除！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: getTopNews
	 * @Description: TODO(获取新闻表中已审核的头条新闻)
	 * @author ganquanzhong
	 * @date 2017-12-13 上午08:25:35
	 * @return
	 */
	public News getTopNews() {
		News news = new News();
		String sql = "select * from news where isDel=0 and top=1 and state='已审核' order by newsId desc";
		try {
			news = runner.query(sql, new BeanListHandler<News>(News.class))
					.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}

	/**
	 * 
	 * @Title: getRecentNews
	 * @Description: TODO(查询news表中的number条最新新闻)
	 * @author ganquanzhong
	 * @date 2017-12-13 上午11:33:06
	 * @param number
	 * @return
	 */
	public List<News> getRecentNews(int number) {
		List<News> newsList = new ArrayList<News>();
		String sql = "select * from news where isDel=0 and state='已审核' order by newsId desc limit ?";
		try {
			newsList = runner.query(sql, new BeanListHandler<News>(News.class),
					number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsList;
	}

	/**
	 * 进行news表的查询的时候，与newsclass表进行左外连接，将news新闻对应classId的新闻分类名称取出来
	 * 
	 * @Title: getNewsByClassId
	 * @Description: TODO(根据classId分类查询指定的number条新闻)
	 * @author ganquanzhong
	 * @date 2017-12-14 上午11:04:42
	 * @param classId
	 * @param number
	 * @return
	 */
	public List<News> getNewsByClassId(int classId, int number) {
		List<News> newsList = new ArrayList<News>();
		String sql = "select n.*,c.content as className from news n left join newsclass c on n.classId=c.classId "
				+ "where n.isDel=0 and state='已审核' and n.classId=? order by newsId desc limit ? ";
		try {
			newsList = runner.query(sql, new BeanListHandler<News>(News.class),
					classId, number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsList;
	}

	/**
	 * 
	 * @Title: getNewsByClassId
	 * @Description: TODO(分页 ，根据classId分类查询news表中已审核的新闻)
	 * @author ganquanzhong
	 * @date 2017-12-18 上午10:08:38
	 * @param classId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<News> getNewsByClassId(String classId, int startIndex,
			int pageSize) {
		List<News> newsList = new ArrayList<News>();
		String sql = "select n.*,c.content as className from news n left join newsclass c on n.classId=c.classId "
				+ "where n.isDel=0 and state='已审核' and n.classId=? order by newsId desc limit ? ,?";
		try {
			newsList = runner.query(sql, new BeanListHandler<News>(News.class),
					classId, startIndex, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsList;
	}

	/**
	 * 
	 * @Title: getTotalRecordNumByClassId
	 * @Description: TODO(news表中对应的classId分类的已审核的新闻记录数)
	 * @author ganquanzhong
	 * @date 2017-12-18 上午10:24:11
	 * @param classId
	 * @return
	 */
	public int getTotalRecordNumByClassId(String classId) {
		Long num = null;
		String sql = "select count(*) from news where isDel=0 and state='已审核' and classId=? ";
		try {
			Object obj = runner.query(sql, new ScalarHandler(), classId);
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
	 * @Title: getTotalRecordNumByTitle
	 * @Description: TODO(根据title的值在news表中模糊查询，返回总共的记录数目)
	 * @author ganquanzhong
	 * @date 2017-12-19 上午09:08:19
	 * @param title
	 * @return
	 */
	public int getTotalRecordNumByTitle(String title) {
		Long num = null;
		String sql = "select count(*) from news where isDel=0 and state='已审核' and headTitle like ? ";
		try {
			Object obj = runner.query(sql, new ScalarHandler(), "%" + title
					+ "%");
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
	 * @Title: getNewsByTitle
	 * @Description: TODO(根据title的值在news表中模糊查询，返回所有的查询结果)
	 * @author ganquanzhong
	 * @date 2017-12-19 上午09:10:42
	 * @param title
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<News> getNewsByTitle(String title, int startIndex, int pageSize) {
		List<News> newsList = new ArrayList<News>();
		String sql = "select n.*,c.content as className from news n left join newsclass c on n.classId=c.classId "
				+ "where n.isDel=0 and state='已审核' and n.headTitle like ? order by newsId desc limit ? ,?";
		try {
			newsList = runner.query(sql, new BeanListHandler<News>(News.class),
					"%" + title + "%", startIndex, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsList;
	}

	/**
	 * 
	 * @Title: getNewsDetailById
	 * @Description: TODO(根据newsId查询news表中的一条具体的新闻，
	 *               并且使用左连接查询将newsclass表中的content查询出来重命名为className)
	 * @author ganquanzhong
	 * @date 2017-12-20 上午08:15:17
	 * @param newsId
	 * @return
	 */
	public News getNewsDetailById(String newsId) {
		News news = new News();
		String sql = "select n.*,c.content as className from news n left join newsclass c on n.classId=c.classId "
				+ "where n.isDel=0 and state='已审核' and n.newsId=?";
		try {
			news = runner.query(sql, new BeanHandler<News>(News.class), newsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}

	/**
	 * 
	* @Title: updateHits
	* @Description: TODO(更新新闻的点击率)
	* @author ganquanzhong
	* @date  2017-12-20 上午08:21:56
	* @param news
	* @return
	 */
	public int updateHits(News news) {
		int result = 0;
		String sql = "update news set hits=? where newsId=?";
		try {
			result = runner.update(sql, news.getHits(), news.getNewsId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: verifyNews
	* @Description: TODO(审核新闻)
	* @author ganquanzhong
	* @date  2018年1月3日 下午11:54:23
	* @param newsId
	* @return
	 */
	public int verifyNews(String newsId){
		int result = 0;
		String sql = "update news set state='已审核' where newsId=?";
		try {
			result = runner.update(sql, newsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
