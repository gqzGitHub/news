[![wechat-group](https://badgen.net/badge/demo/演示)](http://ganquanzhong.top/mynews)
[![ForFuture](https://badgen.net/badge/ForFuture/gqzdev/cyan)](http://ganquanzhong.top)
[![github](https://badgen.net/badge/github/github?icon)](https://github.com/gqzdev)
[![csdn](https://badgen.net/badge/blog/ganquanzhong/red)](https://blog.csdn.net/ganquanzhong)
[![stars](https://badgen.net/github/stars/gqzdev/news)](https://github.com/gqzdev/news)
[![forks](https://badgen.net/github/forks/gqzdev/news)](https://github.com/gqzdev/news)
[![prs](https://badgen.net/github/prs/gqzdev/news)](https://github.com/gqzdev/news)
[![gitee](https://badgen.net/badge/gitee/zhong96/orange)](https://gitee.com/zhong96)


 # ForFuture News  新闻管理系统         
                     
# 1. 系统功能介绍：
## 1.1前言
&emsp;&emsp;当今社会是一个信息化的社会，新闻作为信息的一部分有着信息量大，类别繁多，形式多样的特点，新闻发布系统的概念就此提出。简单的说，新闻发布系统就是充当一个网络新闻媒介的功能，主要实现对新闻的分类、上传、审核、发布，模拟了一般的新闻媒介的新闻发布过程。

&emsp;&emsp;利用JSP技术开发的新闻发布系统，主要有五大功能模块：前台查看模块，登陆验证模块，管理员管理模块，新闻类型管理模块，新闻信息管理模块，实现了对网站新闻的动态管理。因为可以用来动态发布新闻信息，所以对信息的管理更加及时、高效，提高了工作效率。

## 1.2功能介绍
&emsp;&emsp;本系统采用JSP +Servlet等相关技术来实现一个Web应用程序：新闻网站系统。其主要目的是为用户提供一个方便的、可快速浏览当前最新新闻的界面，并且也可以随时发布最新的信息以达到信息共享的目的。因此要为用户提供个方便易用的使用界面，同时也为具有更高权限的管理用户提供添加、修改和删除新闻等的系统维护功能。

&emsp;&emsp;访问本系统的用户可分为两大类：普通用户和管理员用户。

&emsp;&emsp;普通用户可以直接访问新闻发布系统的前台，通过点击每条新闻的标题来查看该条新闻的详细内容。查看各条新闻的评论。用户在登录之后可以发表自己的评论。用户还可以在发布一个论坛，让广大网友发出自己的看法和声音。

&emsp;&emsp;当用户作为管理员成功登陆后，可以对新闻系统进行管理，包括四大模块：

- 管理员信息管理：增加管理员、删除管理员、修改管理员信息；
- 新闻类型管理：增加新闻类型、查看新闻类型信息；
- 新闻管理：查看新闻、修改新闻、发布新闻、审核新闻，信息；
- 论坛管理：查看论坛、删除论坛。   

# 2．数据库设计：
## 2.1数据库技术
&emsp;&emsp;本系统使用MySQL作为开发中使用的数据库，它具有使用简单，稳定等特性。在与java程序连接时，为提到数据库操作的效率提高系统的性能。使用到Dbutils和DBCP等工具。
&emsp;&emsp;
在使用Dbutils 之前，我们Dao层使用的技术是JDBC，那么分析一下JDBC的弊端。数据库链接对象、sql语句操作对象，封装结果集对象，这三大对象会重复定义封装数据的代码重复，而且操作复杂，代码量大。释放资源的代码重复

- 结果：（1）程序员在开发的时候，有大量的重复劳动。

           （2）开发的周期长，效率低

      &emsp;&emsp;数据库连接是一种关键的有限的昂贵的资源,这一点在多用户的网页应用程序中体现的尤为突出.对数据库连接的管理能显著影响到整个应用程序的伸缩性和健壮性,影响到程序的性能指标.数据库连接池正式针对这个问题提出来的.数据库连接池负责分配,管理和释放数据库连接,它允许应用程序重复使用一个现有的数据库连接,而不是重新建立一个。

&emsp;&emsp;数据库连接池在初始化时将创建一定数量的数据库连接放到连接池中, 这些数据库连接的数量是由最小数据库连接数来设定的.无论这些数据库连接是否被使用,连接池都将一直保证至少拥有这么多的连接数量.连接池的最大数据库连接数量限定了这个连接池能占有的最大连接数,当应用程序向连接池请求的连接数超过最大连接数量时,这些请求将被加入到等待队列中。

&emsp;&emsp;相信使用这些数据库的技术之后，能很好保证新闻管理系统正常运行。

## 2.2数据库详细设计
       &emsp;&emsp;在设计数据库时，需要分析清楚需要有几个数据表，最重要的是各个数据表之间的逻辑关系。使用一款数据库设计工具将会大大方便开发的时间和成本，powerdesigner。但是本次的课程设计是在老师教学的基础之上的二次开发，数据库的大体模式基本已经给出，我们只需要在此基础上添加几个表（forum表、reply表等）。数据库的设计一定要遵循数据库设计的一些原则，如各个完整性的要求，范式，冗余，备份等等方面。由于本次课题新闻管理系统在数据库这方面要求不是很高，故实现起来也不是很难。重点在与JSP的使用，Tomcat服务器上的程序编写。下面简要介绍数据库的逻辑关系、关键表等。


        对数据库的数据表的操作通过DAO层进行，DAO层主要是做数据持久层的工作，负责与数据库进行联络的一些任务都封装在此，DAO层的设计首先是设计DAO的接口，然后就可在模块中调用此接口来进行数据业务的处理，而不用关心此接口的具体实现类是哪个类，显得结构非常清晰，DAO层的数据源配置，以及有关数据库连接的参数进行配置。

# 3. 系统架构和文件作用介绍：
## 3.1 系统的目录结构图
       一个系统，在开发初期就必须有一个清晰的文件结构，进而在文件结构中分解出详细的目录结构图。在Java web项目开发中，有一个项目存放的规范，这样便于管理，同时也便于其他人的再次开发。

        1. 一个 web 应用程序是由一组 Servlet，HTML 页面，类，以及其它的资源组成的运行在 web 服务器上的完整的应用程序，以一种结构化的有层次的目录形式存在；

        2. 组成 web 应用程序的这些文件要部署在相应的目录层次中，根目录代表整个 web 应用程序的“根”；

        3. 通常将 web 应用程序的目录放在 webapps 目录下，在 webapps 目录下的每一个子目录都是一个独立的 web 应用程序，子目录的名字就是 web 应用程序的名字，也就是 web 应用程序的“根”。用户通过 web 应用程序的“根”来访问 web 应用程序中的资源.


## 3.2 jsp文件的作用
### 3.2.1 JSP 组成概要

一个JSP页面主要由注释（Comment）、指令（Directives）、脚本元素（Declaration，Scriptlet，Expression).动作元素(Action)的内容组成。

1. 注释包括HTML注释和JSP隐藏注释。

2．指令包括：    

　　（1）page：用来定义整个JSP页面的属性和相关功能。

　　（2）include：用来指定JSP文件被编译时需要插入的资源,可以是文本、代码、HTML文件或JSP文件。

　　（3）taglib：页面使用者用来自定义标签。

3．脚本元素

　　（1）声明：用来定义在程序中使用的实体,它是一段Java代码，可以声明变量，也可以声明方法。格式：<%! 开始声明 %>  例如:<%! int  i;%>。

　　（2）表达式：格式:<%=表达式%>  最后是没有分号的.例如:<%=1+2+3%>。

　　（3）Scriptlet：格式:<%java代码%>。

4．在JSP中的动作指令包括：Include、 Forward、 UseBean、 GetProperty、 SetProperty、 Plugin。

　　（1）include指令：表示包含一个静态的或者动态的文件。子句能让你传递一个或多个参数给动态文件，也可在一个页面中使用多个指令来传递多个参数给动态文件。

　　（2）forward指令：表示重定向一个静态html/jsp的文件，或者是一个程序段。

　　（3）Plugin指令：用于在浏览器中播放或显示一个对象（典型的就是Applet和Bean），而这种显示需要浏览器的Java插件。一般来说，元素会指定对象是Applet还是Bean,同样也会指定class的名字和位置，另外还会指定将从哪里下载这个Java插件。

　　（4）useBean指令：表示用来在JSP页面中创建一个Bean实例并指定它的名字以及作用范围。

　　（5）setProperty指令：用来为一个Bean的属性赋值。若在jsp:useBean后使用，jsp:setProperty将被执行。若jsp:setProperty出现在jsp:useBean标签内，jsp:setProperty只会在新的对象被实例化时才将被执行。注意name值应当和useBean中的id值相同。

    （6）getProperty指令：表示获取Bean的属性的值并将之转化为一个字符串，然后将其插入到输出的页面中。

### 3.2.2 JSP工作原理

当客户端请求浏览JSP页面时，JSP服务器在把页面传递给客户端之前，先将JSP页面编译成Servlet（纯Java代码），然后由Java编译器生成的服务器小程序编译为Java字节码，最后再转换成纯HTML代码，这样客户端接收到的只是HTML代码。

JSP到Servlet的编译过程一般在第一次页面请求时进行。因此，如果希望第一个用户不会由于JSP页面编译成Servlet而等待太长的时间，希望确保Servlet已经正确地编译并装载，你可以在安装JSP页面之后自己请求一下这个页面。

### 3.2.3 项目中的JSP

在Java Web项目中，书写jsp文件时，一定要注意命名规范，需要做到看jsp文件名就知道jsp文件是完成什么功能的。在新闻管理系统中最多涉及的是news这样一个前缀或者后缀，根据需要的功能书写jsp文件。

## 3.3 系统所需的jar包
       ① commons-beanutils.jar：提供对Java反射和自省API的包装，主要提供了对于JavaBean进行各种操作。

② commons-lang.jar:它扩展了标准java.langAPI，增加了字符串操作方法、基本数值方法、对象反射、创建和串行化以及System属性。它还包含一个可继承的enum类型、对多种嵌套的Exception类型的支持、对java.util.Date的增强以及用于构建方法的实用程序，例如自动生成toString()的结果、自动实现hashCode()和equals()方法、数组操作、枚举、日期和时间的处理等等。

- ArrayUtils–用于对数组的操作，如添加、查找、删除、子数组、倒序、元素类型转换等；
- BitField–用于操作位元，提供了一些方便而安全的方法；
- BooleanUtils–用于操作和转换boolean或者Boolean及相应的数组；
- CharEncoding–包含了Java环境支持的字符编码，提供是否支持某种编码的判断；
- CharRange–用于设定字符范围并做相应检查；
- CharSet–用于设定一组字符作为范围并做相应检查；
- CharSetUtils–用于操作CharSet；
- CharUtils–用于操作char值和Character对象；
- ClassUtils–用于对Java类的操作，不使用反射；
- ObjectUtils–用于操作Java对象，提供null安全的访问和其他一些功能；
- RandomStringUtils–用于生成随机的字符串；
- SerializationUtils–用于处理对象序列化，提供比一般Java序列化更高级的处理能力；
- StringEscapeUtils–用于正确处理转义字符，产生正确的Java、JavaScript、HTML、XML和SQL代码；
- StringUtils–处理String的核心类，提供了相当多的功能；
- SystemUtils–在java.lang.System基础上提供更方便的访问，如用户路径、Java版本、时区、操作系统等判断；
- Validate–提供验证的操作，有点类似assert断言； 
- WordUtils–用于处理单词大小写、换行等。
- commons-codec.jar：包含一些通用的编码解码算法。包括一些语音编码器，Hex,Base64,以及URLencoder。
- commons-dbutil.jar:对传统操作数据库的类进行二次封装，可以把结果集转化成List。

(1) org.apache.commons.dbutils

DbUtils :提供如关闭连接、装载JDBC驱动程序等常规工作的工具类

QueryRunner:该类简单化了SQL查询，它与ResultSetHandler组合在一起使用可以完成大部分的数据库操作，能够大大减少编码量。

QueryLoader:属性文件加载器，主要用于加载属性文件中的SQL到内存中。

(2)org.apache.commons.dbutils.handlers

ArrayHandler：将ResultSet中第一行的数据转化成对象数组ArrayListHandler将ResultSet中所有的数据转化成List，List中存放的是Object[]。

BeanHandler：将ResultSet中第一行的数据转化成类对象。

BeanListHandler：将ResultSet中所有的数据转化成List，List中存放的是类对象。

ColumnListHandler：将ResultSet中某一列的数据存成List，List中存放的是Object对象。

KeyedHandler：将ResultSet中存成映射，key为某一列对应为Map。Map中存放的是数据。

MapHandler：将ResultSet中第一行的数据存成Map映射。

MapListHandler：将ResultSet中所有的数据存成List。List中存放的是Map。

ScalarHandler：将ResultSet中一条记录的其中某一列的数据存成Object。

4. 通用功能的实现：
4.1 model模式介绍
model模型是指模型表示业务规则。在MVC的三个部件中，模型拥有最多的处理任务。被模型返回的数据是中立的，模型与数据格式无关，这样一个模型能为多个视图提供数据，由于应用于模型的代码只需写一次就可以被多个视图重用，所以减少了代码的重复性。

4.2 项目中的model
模型是应用程序的主体部分。模型表示业务数据，或者业务逻辑。model包（NewsAdmin类、Newsclass类、Newsuser类、Note类、News类等）

4.3 项目中的common
    在整个项目中需要用到一些公共的类，如数据库连接池、password加密的MD5、分页显示的Page类、BaseSerlvet（用于Front和Back的继承）、WebUtil的前台页面的操作等。

其中详细说明WebUtil类的一些方法：
```java
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

     * @date  2017-11-30 上午11:44:05

     * @param <T>

     * @param request  客户端的请求对象，封装了请求的参数名和参数值

     * @param class1  需要设置属性值的对象

     * @return 返回一个实体类的对象

      */

     public static <T> T fillBean(HttpServletRequest request, Class<T> class1){

         try {

              T bean=class1.newInstance();

              //将一个MAP集合的数据拷贝到一个javabean对象中。

              BeanUtils.populate(bean, request.getParameterMap());

              //使用 HttpServletRequest的getParameterMap()这个方法实现对请求参数的封装

              System.out.println(request.getParameterMap());

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

     * @date  2017-12-13 上午08:30:12

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

     * @date  2017-12-13 上午08:40:46

     * @param date

     * @return

      */

     public static String formateTime(Date date){

         SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");

         return sdf.format(date);

     }

}
```
&emsp;&emsp;在WebUtil类中完成：fillBean()获取class的参数、remove**()去除HTML标签、formatTime()格式化时间等。

# 5. 数据访问层功能的实现：
#  5.1 工厂factory类代码
&emsp;&emsp;在面向对象的编程中，工厂模式是一种经常被使用到的模式。总的来说，在工厂模式里，如果有一个公共的抽象父类，该父类派生出一组子类。那么在创建不同的子类时，把任务交给一个工厂类，由它选择生成哪个子类实例，即用户只需要传递必要的参数给工厂类就可以了，而不去关心具体的子类是怎样产生的。

根据工厂类的不同，工厂模式分3种：

1. 简单工厂(Simple Factory)模式；

2. 工厂方法(Factory Method)模式，又称多形性工厂(Polymorphic Factory)模式；

3. 抽象工厂(Abstract Factory)模式，又称工具箱(Kit或Toolkit)模式。

在本系统中使用的factory类，主要是生产一个\**DAO的instance，避免使用DAO时多次实例化一个对象，增加系统资源开销。


其中的代码为（其他的类似，主要是生产一个对应的DAO实例）：
```java
package com.gqz.news.factory;

import com.gqz.news.DAO.ForumDAO;

/**

 *

* @ClassName: ForumDAOFactory

* @Description: TODO(ForumDAO的工厂类)

* @author ganquanzhong

* @date 2018年1月4日 下午11:30:50

 */

public class ForumDAOFactory {

    /*设计模式之工厂模式，由工厂产生对象：用户不用操心对象如何产生，只要从工厂获取对象  Spring

     */

    public static ForumDAO getForumDAOInstance(){

       return new ForumDAO();

    }

}
```

## 5.2 DAO设计模式
### 5.2.1 DAO介绍

&emsp;&emsp;DAO全称是（Data Access Object，数据库访问对象），主要功能就是用于进行数据操作的，在程序的标准开发架构中属于数据层的操作。

简要介绍一下企业分层架构：



显示层：主要使用JSP/Servlet进行页面效果的显示。

业务层：（Business Object，数据对象）会将多个原子性的DAO操作进行组合，组合成一个完整的业务逻辑。

数据层：（DAO，Data Access Object，数据库访问对象）提供多个原子性的DAO操作，例如：增、删、改、查，都是原子性操作。

DAO 模式是标准 J2EE 设计模式之一。开发人员用这种模式将底层数据访问操作与高层业务逻辑分离开。一个典型的 DAO 实现有以下组件：

一个 DAO 工厂类
一个 DAO 接口
一个实现了 DAO 接口的具体类
数据传输对象(有时称为值对象)
### 5.2.2 DAO类代码

对数据表的增、删、改、查等操作，都封装到一个对应的DAO类中，在需要用到某数据表的某种操作时，直接调用对应数据库的DAO类的对应操作方法即可。

事务界定

关于 DAO 要记住的重要一点是它们是事务性对象。由 DAO 所执行的每一个操作 -- 如创建、更新或者删除数据 -- 都与一个事务相关联。因此， 事务界定的概念就变得特别重要了。

事务界定是定义事务边界的方式。J2EE 规范描述了两种事务界定的模型：编程式(programmatic)和声明式(declarative)。

两种事务界定的模型

声明式事务界定

编程式事务界定

程序员用 EJB 部署描述符声明事务属性。

程序员负责编写事务逻辑。

运行时环境(EJB 容器)用这些属性自动管理事务。

应用程序通过一个 API 控制事务。

 我们将侧重于编程式事务界定。

&emsp;&emsp;在每一个DAO类中都完成对数据表的操作（根据功能要求），通过最基本的增、删、改、查等实现复杂的业务功能。下面就举一个例子说明在DAO类中的一些方法，其他的DAO类类似。
```java
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

* @Description: TODO(对forum表的操作层   数据库访问层DAO)

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

    * @date  2018年1月2日 上午12:50:32

    * @return

     */

    public List<Forum> getForum() {

        List<Forum> list = new ArrayList<Forum>();

        String sql = "select * from forum where isDel=0 ";

        // 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）

        BeanListHandler<Forum> bh = new BeanListHandler<Forum>(Forum.class);

        try {

            list = runner.query(sql, bh);

            System.out.println("数据查询成功，并且成功将论坛表<forum>数据以list集合的方式存入！！！");

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

    * @date  2018年1月2日 上午12:32:58

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

            System.out.println("数据查询成功，获取一条论坛的信息！！");

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

    * @date  2018年1月2日 上午12:37:56

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

    * @date  2018年1月3日 下午6:15:18

    * @param startIndex

    * @param pageSize

    * @return

     */

    public List<Forum> getForumByPage(int startIndex, int pageSize) {

        List<Forum> list = new ArrayList<Forum>();

        // SQL语句：查询note表中的所有信息，按照noteId降序排列

        String sql = "select * from Forum where isDel=0  order by Id asc limit ?,?";

        // 因为返回结果是List集合，所以使用BeanListHandler，而不是用BeanHandler（代表返回结果为单个对象）

        BeanListHandler<Forum> bh = new BeanListHandler<Forum>(Forum.class);

        try {

            list = runner.query(sql, bh, startIndex, pageSize);

            System.out.println("数据查询成功，并且成功将新闻分类表<newsclass>数据以list集合的方式存入！！！");

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

    * @date  2017-12-29 上午11:05:06

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

    * @date  2017-12-29 上午11:05:36

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
```
    &emsp;&emsp;在ForumDAO类中完成：getForum()获取全部论坛列表、getForumById()根据指定的id获取一条论坛、insert(Forum forum)插入一条论坛、getForumByPage(int startIndex, int pageSize)分页显示论坛列表、getTotalRecordNum()获取论坛的总数、delete(String forumId)根据指定的id删除一条论坛。

# 6.前台普通用户访问新闻网站功能的实现：
## 6.1 前台中的Servlet类（Front）
&emsp;&emsp;Servlet程序是由WEB服务器调用，web服务器收到客户端的Servlet访问请求后：
　>　①Web服务器首先检查是否已经装载并创建了该Servlet的实例对象。如果是，则直接执行第④步，否则，执行第②步。
　　②装载并创建该Servlet的一个实例对象。 
　　③调用Servlet实例对象的init()方法。
　　④创建一个用于封装HTTP请求消息的HttpServletRequest对象和一个代表HTTP响应消息的HttpServletResponse对象，然后调用Servlet的service()方法并将请求和响应对象作为参数传递进去。
　　⑤WEB应用程序被停止或重新启动之前，Servlet引擎将卸载Servlet，并在卸载之前调用Servlet的destroy()方法。


&emsp;&emsp;在使用servlet时，需要说明的是：一定要通web.xml来注册servlet，否则form表单根据action属性中的url地址提交数据时，将找不到对应的地址，对应页面提示The requested resource is not available.（请求资源不可用）。该servlet的作用是接收来自jsp中的form表单（包含用户名和密码），然后调用数据库中的用户信息验证表单传来的用户名和密码是否正确。

在新闻的前台中，将所有的servlet类将封装到Front类（继承BaseServlet类）中，这样避免了代码的冗余和繁杂，提高系统的可靠性。

在Front类中完成前台需要的所有serlvet操作，对后台的业务请求。由于该类的方法实在太多，主要介绍说明有关Forum的方法。

forum方法主要完成进入论坛页面的操作：1.查询新闻分类 2.查询论坛 3.查询论坛的回复。
```java
/**

      *

      * @Title: forum

      * @Description: TODO(进入论坛页面)

      * @author ganquanzhong

      * @date 2018年1月2日 上午12:26:04

      * @param request

      * @param response

      * @return

      * @throws IOException

      * @throws ServletException

      */

     public String forum(HttpServletRequest request, HttpServletResponse response)

              throws IOException, ServletException {

         // 1.显示新闻分类信息

          NewsclassDAO newsclassDAO = NewsclassDAOFactory

                   .getNewsclassDAOInstance();

         List<Newsclass> classList = newsclassDAO.getList();

         request.setAttribute("classList", classList);



         // 2.获取论坛列表

         // 获取需药查询的num号

         String forumId = request.getParameter("num");

         System.out.println("需要显示的num号为" + forumId);

         ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();

         // 获取详细论坛消息

         Forum forumInfo = forumDAO.getForumById(forumId);

         // 格式化时间

         forumInfo.setFormatTime(WebUtil.formateTime(forumInfo.getTime()));

         request.setAttribute("forumInfo", forumInfo);

         System.out.println("论坛消息成功存储到forumInfo中！");



         // 3.获取指定的论坛的回复，评论

         ReplyDAO replyDAO = ReplyDAOFactory.getReplyDAOInstance();

         List<Reply> reply = replyDAO.getReply(forumId);

         // 格式化时间

         for (Reply reply2 : reply) {

              reply2.setFromatTime(WebUtil.formateTime(reply2.getTime()));

         }

         request.setAttribute("replyList", reply);

         return "forum.jsp";

     }
```
&emsp;&emsp;addForum方法主要完成添加进入论坛页面的操作：1.收集前台页面的参数2.上传文件到服务器 3.在数据库forum表中增加一条论坛。
```java
/**

      *

      * @Title: addForum

      * @Description: TODO(添加论坛)

      * @author ganquanzhong

      * @date 2018年1月2日 下午9:40:32

      * @param request

      * @param response

      * @return

      * @throws IOException

      * @throws ServletException

      */

     public String addForum(HttpServletRequest request,

              HttpServletResponse response) throws IOException, ServletException {

         // 1.显示新闻分类信息

         NewsclassDAO newsclassDAO = NewsclassDAOFactory

                   .getNewsclassDAOInstance();

         List<Newsclass> classList = newsclassDAO.getList();

         request.setAttribute("classList", classList);



         // 2.添加论坛

         // 在编码过滤器中已经完成设置

         // 设置请求编码

         request.setCharacterEncoding("utf-8");

         // 设置服务器响应编码

         response.setContentType("text/html;charset=utf-8");

         PrintWriter out = response.getWriter();



         // 1.判断是否支持文件上传，底层判断是否使用了enctype="multipart/form-data"

         boolean multipartContent = ServletFileUpload

                   .isMultipartContent(request);

         if (!multipartContent) {

              throw new RuntimeException("the form is not multipart/form-data");

         }

         // 2.创建工厂--基于硬盘的文件列表工厂FileItemFactory factory = new

         // DiskFileItemFactory();

         FileItemFactory factory = new DiskFileItemFactory();

         ServletFileUpload suf = new ServletFileUpload(factory);

         // 3.解决上传文件的中文乱码问题

         suf.setHeaderEncoding("utf-8");

         // 4.解析request，获得上传所有内容，每一个内容都封装到一个对象（FileItem）中

         List<FileItem> items = new ArrayList<FileItem>();

         try {

              items = suf.parseRequest(request);

         } catch (FileUploadException e) {

              e.printStackTrace();

         }

         // 5.判断是否是普通字段 item.isFormField()

         // 6.如果是普通字段 字段名item.getFormField();字段值 item.igetString(encoding)；

         // 7.如果不是普通字段，则是上传的文件, 获取上传文件名称item.getName()、上传内容item.getInputStream

         // 8.上传文件，将文件写入到服务器的指定位置下

         Forum forum=new Forum();

         //News news = new News();

         for (FileItem item : items) {

              if (item.isFormField()) {

                   processFormFiled(item, forum);

              } else {

                   processUploadFiled(item, forum);

              }

         }

         //NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();

         //int result = newsDAO.insert(news);

         ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();

         int result = forumDAO.insert(forum);

         // 3.存储成功，提示用户存储成功，反之提示添加失败

         if (result == 0) {

              out.print("<script>alert('论坛发布失败!');"

                       + "window.location.href='Front?op=beforeForum'</script>");

         } else {

              out.print("<script>alert('论坛发布成功!');"

                       + "window.location.href='Front?op=main'</script>");

         }

         return "";

     }
```


## 6.2 JSP页面核心代码
       由于在serlvet的操作中基本很多需要跳转到jsp页面。JSP页面在前面也介绍过其组成部分和工作原理等。下面直接说明JSP页面里的重点部分，主要是JSP的一些表达式和标签的使用。下面介绍主页index.jsp的重要部分，其他的jsp页面也将部分说明。
```html
<!---main导航 start---->

            <nav id="main-navigation" class="clearfix">

                <ul>

                    <li><a href="Front?op=main" class="current">主页</a></li>

                    <c:forEach items="${classList }" var="c">

                        <li>

<a href="Front?op=listByClass&classId=${c.classId }">

${c.content }

</a>

</li>

                    </c:forEach>

                </ul>

            </nav>

<!-- End Main-Navigation -->

<!---main导航 end---->
```
&emsp;&emsp;这部分使用<c:forEach></c:forEach>标签，循环输出c.classId（新闻分类）。

还使用到EL表达式：EL 全名为Expression Language。EL 语法很简单，它最大的特点就是使用上很方便。接下来介绍EL主要的语法结构：

${sessionScope.user.sex}

所有EL都是以${为起始、以}为结尾的。上述EL范例的意思是：从Session的范围中，取得用户的性别。假若依照之前JSP Scriptlet的写法如下：

User =(User)session.getAttribute("user");

String sex =user.getSex( );

两者相比较之下，可以发现EL 的语法比传统JSP Scriptlet 更为方便、简洁。

因此在后面的JSP页面中基本都是到上述两种搭配，基本都是<c:forEach></c:forEach>和${ }等语法。

# 8.系统运行效果图：


爬取网络新闻并存入数据库中：

