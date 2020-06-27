Tomcat服务器的实际行为：

	由于：在浏览器的地址栏输入：localhost:8080/ProjectName/资源路径
	其中的资源路径：
		正是 @WebServlet("/demo3") 中的资源路径
	1.通过该路径可以加载这个路径指向的类，并创建该类对象，
	2.之后再创建Request,Response对象，并传入该类对象的service方法，再执行
	   --	其中Request对象中封装了请求数据,Response对象中封装了响应数据，
	3.可以使用Request获取请求数据并设置Response响应数据，而浏览器通过Response获取响应数据


--

	ServletResquest -> 
		HttpServletResquest -> 
			ResquestFacad(实现了HttpServletRequest接口)
				:内部持有org.apache.catalina.connector.Request对象
				并执行该对象的一系列方法

--
	
	package  package org.apache.coyote 
		:此类不适用于用户代码,由 tomcat 在内部使用:final类

--

	package package org.apache.catalina.connector
			：实现了HttpServletRequest接口
			而这个类内部持有org.apache.coyote.Request类对象
			并执行该对象的一系列方法
			


功能：

	1.获取请求数据:
		1.获取请求行数据	
		2.获取请求头数据 
		3.获取请求体数据
	2.其他:
		1.获取请求参数通用方式
		2.请求转发
		3.共享数据
		4.获取ServletContext
		

1.获取请求行数据	

	获取请求行数据
		请求方式，虚拟目录，Servlet路径，请求参数，
			请求URL，协议和版本，客户机的ip地址

	举例：
		//http://localhost:8080/WebServlet/RequestDemo1?age=12

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Method:"+request.getMethod());
					//	:GET
			System.out.println("虚拟目录:"+request.getContextPath());
					//  :/WebServlet
			System.out.println("Servlet路径:"+request.getServletPath());
					//	:/RequestDemo1
			System.out.println("请求参数:"+request.getQueryString()); 
					//	?连接首个参数,&连接其他参数   //age=12
			System.out.println("URL:"+request.getRequestURL());
					//	:http://localhost:8080/WebServlet/RequestDemo1
			System.out.println("协议及版本:"+request.getProtocol());
					//	:HTTP/1.1
			System.out.println("ip地址:"+request.getLocalAddr());
					//	:ip地址:0:0:0:0:0:0:0:1
		}

		
2.获取请求头数据 
	
	获取请求头名称：request.getHeaderNames()
		host
		connection
		upgrade-insecure-requests
		user-agent
		accept
		dnt
		accept-encoding
		accept-language
		cookie
		referer
	
	获取各自请求头信息：request.getHeader(name)

		String referer = request.getHeader("referer");
		String uagent = request.getHeader("user-agent");
		Cookie[] cookies = request.getCookies();
		System.out.println("referer:"+referer);
		System.out.println("user-agent:"+uagent);
		System.out.print("cookies:");
		for(Cookie c:cookies) {
			System.out.print(c);
			if(cookies[cookies.length-1].equals(c)) {
				break;
			}
			System.out.print(",");
		}

3.获取请求体数据  post方式有效
	
	字节流：getInputStream();
	字符流：getReader();

	username=eeee&password=sdfea

	举例：1
		BufferedReader reader = request.getReader();
		String line = null;
		while((line = reader.readLine())!= null){
			System.out.println(line);
		}	
			
		2
		ServletInputStream inputStream = request.getInputStream();
		StringBuffer sBuffer = new StringBuffer();
		byte[] bs = new byte[4096];
		while(inputStream.read(bs) != -1) {
			sBuffer.append(new String(bs));
		}
		System.out.println(sBuffer.toString());
		



2.其他



1.获取请求参数通用方式
	String   request.getParameter(name);   
	String[]   request.getParameterValues(name); 
				//复选框checkbox
	Map<String, String[]>   request.getParameterMap();
				//键值对
	Enumeration<String>  request.getParameterNames();
				//获取名称，返回值类似迭代器

	举例：
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		for (String string : keySet) {
			System.out.print("key: "+string+" values: ");
			String[] strings = parameterMap.get(string);
			for (String string2 : strings) {
				System.out.print(string2);
				if(string2.equals(strings[strings.length-1])) {
					break;
				}
				System.out.print(",");
			}
			System.out.println();
		}


2.请求转发

	1.当前服务器内部资源才可以被转发的 且 多个资源一次性转发的方法 且 地址不会变化：
	
	在方法内,已拥有HttpServletRequest request, HttpServletResponse response 参数
		request.getRequestDispatcher("/demo3").forward(request, response);

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	其中：
		"/demo3" : 另一个资源路径		
		request：	请求对象
		response:	响应对象
		

3.共享数据 （转发到其他servlet获取数据）
	
	传送的相当于是键值对：
		request.setAttribute("name", "libai");  
		request.getAttribute("name")
		request.removeAttribute(name);

4.获取ServletContext
	
	req.getServletContext()