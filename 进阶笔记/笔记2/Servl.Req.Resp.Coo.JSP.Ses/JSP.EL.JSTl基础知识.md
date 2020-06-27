0.JSP：Java Server Pages 可写html又可写Java,本质是Servlet
	
1.原理：.jsp -> .java -> .class -> 由class字节码文件提供访问 本质是Servlet
	
	.jsp可写入代码查：
		<%= request.getServletContext().getRealPath(".") %>
	得：
		D:\Java\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\WebServlet
	而.jsp在：
		D:\Java\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\work
			\Catalina\localhost\WebServlet\org\apache\jsp


2.代码位置：
	
	1.<% 将被包含于servlet接口中的实现的service方法,页面请求,无论是GET或是POSTMethod %>

		包含于 public void _jspService(){...}
		
	2.<%! 成为该类的成员变量 %>
		
	3.<%= 打算输出的内容,也将被包含于servlet接口中的实现的service方法中,使用out.write() %>


3.包导入
	
	格式：
	
		<%@page import="java.net.URLDecoder"%>
		<%@page import="java.net.URLEncoder"%>
	
	简单方法：在缺失包的类上, alt+/ ,可完成包的导入



4.内置对象
	
	4个域对象：共享数据 Attribute（get,set,remove）
	域范围从小到大：
	javax.servlet.jsp.PageContext 	pageContext 当前页面,可获得其余对象
	javax.servlet.http.HttpServletRequest 	request	一次请求多个资源(转发)
    javax.servlet.http.HttpSession 	session	    一次会话多个请求(重定向)
    javax.servlet.ServletContext 	application 所有用户间共享数据
	


    javax.servlet.ServletConfig 			config  servlet的配置对象
    javax.servlet.jsp.JspWriter 			out 	输出对象
    java.lang.Object		 				page  	 当前servlet
	javax.servlet.http.HttpServletResponse 	response 响应对象
	java.lang.Throwable 					excepiton 异常对象
	
    


5.指令：配置jsp,导入资源文件

	分类：
		page:	ContentType,
				pageEncoding 当前页面编码,
				language,
				buffer 缓冲区大小,
				import 导入包..一行一个
				errorPage 发生错误去其他页面，
				isError	标识是否为错误配置，true可以使用excepiton内置对象
		include:
				<%@include file="...."%> 导入文件:并显示

		
		taglib:导入标签库
				<%@taglib prefix="xx" uri="标签库position"%>


6.注释：

	<!-- -->  	html特有的，浏览器接收，不显示
	<%-- -->	jsp特有的,浏览器不接收，不显示


7.EL,JSTL  用于替换java脚本代码<%.%!.%=.%>

	EL Expression Language 表达式语言
 	JSTL Java Server Pages Tag Library JSP标准标签库

8.EL：
	
	1.使用
	2.忽略EL
	3.用途
    4.隐含对象pageContext.request.


1.使用：
	
	${表达式}:结果会输出,若为空则，显示空字符串

2.忽略EL：

	在当前表达式前\$：会忽略当前EL 
	在page指令中： isELIgnored="true" :忽略所有EL

3.用途：
	
	1.运算
		运算符：+-*/% == <= && || ! empty:(Str,List,Map)null或是长度为0

	2.获取值(只能从域对象中获取“属性”值)
		1.${域名称.键名}：从指定域名称的域对象获取指定键名的值
		2.${键名}：从域范围最小的开始找出值		
		3.${域名称.键名(对应value:JavaBean实体类对象).JavaBean实体类对象的属性} 
				：自动调用get方法,并将get的结果返回出来

		4个域对象：共享数据 Attribute（get,set,remove）
		域范围从小到大：
		javax.servlet.jsp.PageContext 	pageContext 当前页面,可获得其余对象
		javax.servlet.http.HttpServletRequest 	request	一次请求多个资源(转发)
	    javax.servlet.http.HttpSession 	session	    一次会话多个请求(重定向)
	    javax.servlet.ServletContext 	application 所有用户间共享数据
		
			
		对应的域名称：
			1.pageScope
			2.requestScope
			3.responseScope
			4.applicationScope
		
		方式：
			list[index]
			map["key"]
			map.keyName


举例：
	
ELDemo.jsp
-	

	<% 
		User user = new User();
		user.setName("李白");
		user.setPassword("123456");
		user.setLoginTime(new Date());
		session.setAttribute("us", user);
	%>	
	
	<%
	Map map = new HashMap();
	map.put("name", "李太白");
	map.put("age", 19);
	session.setAttribute("mip",map);
	%>
	
	what time is?<br/>
	${sessionScope.us.logTimeStr} 
	<br/>
	${sessionScope.ls[0]}
	<br/>
	${sessionScope.mip["name"]}
	<br/>
	${sessionScope.mip.name}
	
User.java
-
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogTimeStr() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		return simpleDateFormat.format(loginTime);
	}
	...

9.JSTL：
	
	1.使用步骤
	2.常用标签if choose foreach
	
1.使用步骤：
	
	1.导入jstl相关jar包:
		jstl-impl-1.2.jar
		javax.servlet.jsp.jstl-api-1.2.2.jar
	2.引入标签库taglib
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	3.使用标签

2.常用标签if,choose,foreach 标签的属性都使用""包裹
	
	1.if  test布尔表达式,可以是EL表达式,
		若test结果为true，输出标签体内容，否则不显示

	<c:if test="${1>=1}"><h1>he1o</h1> </c:if>
	<c:if test="true">he13o</c:if>
	<c:if test="${not empty sessionScope.ls}">hwj1o</c:if>
	<c:if test="${3 mod 2 !=1}"><h1>h1eo</h1> </c:if>
	
	2.choose choose标签内嵌套when,otherwise标签 
					形成 switch-case-default结构
	
	<c:choose>
	<c:when test="${sessionScope.number == 4}"> it is 4</c:when>
	<c:when test="${sessionScope.number == 3}"> it is 3</c:when>
	<c:when test="${sessionScope.number == 6}"> it is 6</c:when>
	<c:when test="${sessionScope.number == 7}"> it is 7</c:when>
	<c:otherwise> it is sb</c:otherwise>
	</c:choose>
	
	3.foreach 迭代容器或是重复执行
		begin: 起始下标
		end: 终止下标(包含该下标)
		step:步长
		items:引用容器 使用EL获取值"${}"
		var：变量名称
		varStatus:->index,count
					获取循环体的当前下标,循环次数	
	

ELDemo.jsp:
-
	<%  List list = new  ArrayList();
		list.add("abc");
		list.add("aec");
		list.add("avc");
		list.add("aec");
		session.setAttribute("ls", list);
	%>
	
1.例子1

	<c:forEach var="i" step="1" begin="0" end="10" varStatus="s">
	${i}
	${s.index}
	${s.count}
	<br/>
	</c:forEach>

2.例子2

	<c:forEach items="${sessionScope.ls}" var ="ls" varStatus="les" begin="1" end="2" step="1">
	${ls}
	</c:forEach>

3.例子3

	<table align = "center" border="1px solid black">
	<tr>
	<th>编号</th>
	<th>名称</th>
	</tr>
	
	<c:forEach items="${sessionScope.list}" varStatus="ls">
	<c:if test="${ls.count % 2 == 1}">
	<tr bgcolor = "pink">
	<td>${ls.count}</td>
	<td>${list[ls.index]}</td>
	</tr>
	</c:if>
	
	<c:if test="${ls.count % 2 != 1}">
	<tr bgcolor="blue">
	<td>${ls.count}</td>
	<td>${list[ls.index]}</td>
	</tr>
	</c:if>
	
	</c:forEach>
	
	</table>