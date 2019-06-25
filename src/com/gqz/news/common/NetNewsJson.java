package com.gqz.news.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gqz.news.DAO.NetnewsDAO;
import com.gqz.news.factory.NetnewsDAOFactory;
import com.gqz.news.model.NewsPo;
import com.gqz.news.util.DateUtil;
import com.gqz.news.util.HTMLUtils;
import com.gqz.news.util.JsonJsoupUtil;
import com.gqz.news.util.JsonJsoupUtil2;
//import org.apache.hadoop.hbase.client.Put;
 

/**
 * 
* @ClassName: JsonAction
* @Description: TODO(这里用一句话描述这个类的作用)
* @author ganquanzhong
* @date 2019年4月1日 下午11:23:58
 */
public class NetNewsJson{

	/**
	 * 说明：抓取新浪微博 动态(采用js字串截取+JsoupUtil2+json2)
	 * @author 旋葎
	 * 2017 8 24
	 * @throws Exception 
	 */
	public static List<NewsPo> getSinaWeiBo(String souName,String dateTime) throws Exception{
		//微博的数据都存在js中  所以要先解析js  然后在讲标签传入jsoup解析工具类中用规则解析
		String url  = "http://s.weibo.com/weibo/"+souName+"?topnav=1&wvr=6&b=1";
		String content = HTMLUtils.convert(HTMLUtils.getHtml(url, "utf-8"));
		content = content.substring(content.indexOf("direct.js"),content.length()-1);
		content = content.substring(content.indexOf("html")+7,content.indexOf("</script>")-3);
		content = content.replaceAll("\\\\n", "").replaceAll("\\\\t", "").replaceAll("\\\\&quot;", "").replaceAll("\\\\","");
		JsonJsoupUtil2 jsoup2= new JsonJsoupUtil2();
		List<Map<String,String>> listMap =jsoup2.getHTMLListMap(content, "json/weibo2.json");
		List<NewsPo> listAll = new ArrayList<NewsPo>();
		if(null != listMap&& listMap.size()>0){
			for (Map<String, String> map : listMap) {
				try{
					//当前抓取的数据时间 比历史时间要小  代表该数据已经存在于数据库(无需继续抓取)
	        		
					NewsPo po = new NewsPo(map);
					//将时间格式化
					if(po.getDatetime().indexOf("年")==-1&&po.getDatetime().indexOf("日")!=-1&&po.getDatetime().indexOf("月")!=-1){
						po.setDatetime("2017-"+po.getDatetime()+":00");
					}
					po.setDatetime(DateUtil.toHtmlTime(po.getDatetime()));
					if(DateUtil.compareDate(po.getDatetime(), dateTime,"yyyy-MM-dd HH:mm")==2){
						continue;//时间未排序，所以不能直接返回，只能过滤当前这条记录
					}else{
						//设置来源
						po.setMedia("weibo");
						//将内容的前三十个字符作为标题
						if(po.getIntro().length()>30){
							po.setTitle(po.getIntro().substring(0,30));
						}else{
							po.setTitle(po.getIntro());
						}
						//校验数据的完整性
						if(po!=null){
	        				listAll.add(po);
	        				System.out.println("微博  -> " +po.toString());
	        			}
	        		}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("微博数据Map处理异常，跳过该记录！");
				}
			}
		}
		return listAll;
	}
	
	/**
	* @Title: getJsonPut
	* @Description: 抓取新闻(采用JsoupUtil1+json1)
	* @author ganquanzhong
	* @date  2019年4月1日 下午6:15:01
	* @param page   需要抓取的页数
	* @param num    每页的数量  (小于或等于50)
	* @param checkTitle  关键字
	* @param checkMedia  来源（baidu/sina）
	* @param dateTime    抓取此时间之后的数据
	* @return  返回结果List<Put>  (如无内容返回null)
	 */
	public static List<NewsPo> getJsonPut(int page,int num,String checkTitle,String checkMedia,String dateTime){
		String homeDatetime = dateTime;  //历史抓取时间
		int nums = 0;     //一次抓取到多少条时，存入数据库
		List<NewsPo> listAll = new ArrayList<NewsPo>();
		for(int pageIndex=1;  pageIndex<=page;  pageIndex++){
			String url="";
			if("baidu".equals(checkMedia)){
				if(pageIndex==1){
					url = "http://news.baidu.com/ns?word="+checkTitle+"&ie=utf-8&bt=0&et=0&tn=news&from=news&cl=2&rn="+String.valueOf(num)+"&ct=0&clk=sortbytime";
				}else{
					url = "http://news.baidu.com/ns?word="+checkTitle+"&pn="+String.valueOf(pageIndex)+"&ie=utf-8&bt=0&et=0&tn=news&from=news&cl=2&rn="+String.valueOf(num)+"&ct=0&clk=sortbytime";
				}
			}else if("sina".equals(checkMedia)){
				if(pageIndex==1){
					url = "http://search.sina.com.cn/?q="+checkTitle+"&c=news&from=channel&ie=utf-8&num="+String.valueOf(num);
				}else{
					url = "http://search.sina.com.cn/?q="+checkTitle+"&c=news&from=channel&ie=utf-8&page="+String.valueOf(pageIndex)+"&num="+String.valueOf(num);
				}
			}else{
				return null;
			}
			List<Map<String,String>> listMap = JsonJsoupUtil.getHTMLListMap(url,"json/"+checkMedia+".json");
			if(listMap!=null &&listMap.size()>0){
				try{
					for (Map<String, String> map : listMap) {
						String medidOrtime = map.get("media").replace(" ", " ");
						int kg = medidOrtime.indexOf(" ");
						if(kg!=-1&&medidOrtime.length()>kg+1){
							//map.put("media", medidOrtime.substring(0,kg));
							map.put("media", checkMedia);
							map.put("datetime", DateUtil.toHtmlTime(medidOrtime.substring(kg+1,medidOrtime.length()).trim().replace("年", "-").replace("月", "-").replace("日", "")));
							//当前抓取的数据时间 比历史时间要小  代表该数据已经存在于数据库(无需继续抓取)
			        		if(DateUtil.compareDate(map.get("datetime"), homeDatetime,"yyyy-MM-dd HH:mm")==2){
								nums=0;
			        			return listAll;
			        		}else{
			        			NewsPo po = new NewsPo(map);
			        			//校验数据的完整性
			        			if(po!=null){
			        				listAll.add(po);
			        				System.out.println(checkMedia+"["+String.valueOf(nums+1)+"] -> "+po.toString());
			        			}
			        			nums++;
			        		}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("当前记录有误，已跳过执行！");
				}
			}
		}
		return listAll;
	}
	
	
	/**
	 * 
	* @Title: main
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2019年4月1日 下午8:01:43
	* @param args
	* @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/******* Hbase数据格式     如需直接返回Hbase数据格式 请导入相关jar包，并取消对应的代码注释即可 ************/
		//新浪新闻   参数1：需要抓取的页数   参数2:每页的数量  (小于或等于50) 参数3：关键字  参数4:来源（baidu/sina）参数5：抓取此时间之后的数据
		//List<Put> sinaPutList = getJsonPut(10,20,"安全","sina",DateUtil.toHtmlTime("1小时前"));
		//百度新闻
		//List<Put> baiduPutList = getJsonPut(10,20,"安全","baidu",DateUtil.toHtmlTime("1小时前"));
		//新浪微博动态
		//List<Put> weiboPutList = getSinaWeiBo("安全",DateUtil.toHtmlTime("1小时前"));
		
		/******** 实体类对象 **************/
		//新浪新闻   参数1：需要抓取的页数   参数2:每页的数量  (小于或等于50) 参数3：关键字  参数4:来源（baidu/sina）参数5：抓取此时间之后的数据
		List<NewsPo> sinaPutList = getJsonPut(1,10,"华为","sina",DateUtil.toHtmlTime("20分钟前"));
		for (NewsPo newsPo : sinaPutList) {
			NetnewsDAO netnewsDAO = NetnewsDAOFactory.getNetnewsDAOInstance();
			netnewsDAO.insert(newsPo);
		}
		//百度新闻
		/*List<NewsPo> baiduPutList = getJsonPut(1,10,"互联网","baidu",DateUtil.toHtmlTime("50分钟前"));
		for (NewsPo newsPo : baiduPutList) {
			NetnewsDAO netnewsDAO = NetnewsDAOFactory.getNetnewsDAOInstance();
			netnewsDAO.insert(newsPo);
		}*/
		//新浪微博动态
		//List<NewsPo> weiboPutList = getSinaWeiBo("互联网",DateUtil.toHtmlTime("1小时前"));
	}
}
