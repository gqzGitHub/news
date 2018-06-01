package com.gqz.news.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.gqz.news.model.Forum;
import com.gqz.news.model.News;

/**
 * 
* @ClassName: WebUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author ganquanzhong
* @date 2017-11-30 上午11:43:44
 */
//返回的对象类型必须可以匹配所有的实体类（News、NewsAsmin、Newsclass）
public class WebUtil {
	/**
	 * 
	* @Title: fillBean
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2017-11-30 上午11:44:05
	* @param <T>
	* @param request  客户端的请求对象，封装了请求的参数名和参数值
	* @param class1  需要设置属性值的对象
	* @return 返回一个实体类的对象
	 */
	public static <T> T fillBean(HttpServletRequest request, Class<T> class1){
		try {
			T bean=class1.newInstance();
			//将一个MAP集合的数据拷贝到一个javabean对象中。
			BeanUtils.populate(bean, request.getParameterMap());
			//使用 HttpServletRequest的getParameterMap()这个方法实现对请求参数的封装
//			System.out.println(request.getParameterMap());
			return bean; //返回一个实体类
		} catch (Exception e) {			
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	* @Title: removeHtml
	* @Description: TODO(将news对象)
	* @author ganquanzhong
	* @date  2017-12-13 上午08:30:12
	* @param news
	 */
	public static void removeHtml(News news){
		//
		String content=news.getContent();
		
		String regex="<[^>]*>";
		
		Pattern pattern=Pattern.compile(regex,Pattern.MULTILINE | Pattern.UNICODE_CASE);
		
		Matcher matcher=pattern.matcher(content);
		
		//将content中的HTML标签换成空字符串
		content=matcher.replaceAll("");
		int length=content.length();
		if(length>150) {
			length=150;
		}
		String newContent=content.substring(0,length);
		news.setContent(newContent);
	}
	
	
	public static void removeForumHtml(Forum forum){
		//
		String content=forum.getContent();
		
		String regex="<[^>]*>";
		
		Pattern pattern=Pattern.compile(regex,Pattern.MULTILINE | Pattern.UNICODE_CASE);
		
		Matcher matcher=pattern.matcher(content);
		
		//将content中的HTML标签换成空字符串
		content=matcher.replaceAll("");
		int length=content.length();
		if(length>150) {
			length=150;
		}
		String newContent=content.substring(0,length);
		forum.setContent(newContent);
	}
	
	/**
	 * 
	* @Title: formateTime
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2017-12-13 上午08:40:46
	* @param date
	* @return
	 */
	public static String formateTime(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return sdf.format(date);
	}
}
