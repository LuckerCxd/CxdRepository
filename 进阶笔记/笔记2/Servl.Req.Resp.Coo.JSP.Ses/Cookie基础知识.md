Cookie基础知识:
	
	1.可进行多个cookie(多对数据)的传送
	2.cookie传送信息原理
	3.cookie持久时间	
	4.cookie共享数据
	5.cookie的用处：用于一些轻量级信息设定，重要的放置于数据库

1.可进行多个cookie(多对数据)的传送
	
	步骤：
		0.规定response.setContentType("text/html;charset=utf-8");
		1.创建Cookie对象，如果有特殊字符，使用URLEncoder进行URL指定字符集编码
		2.设置cookie的存活时间
		3.使用response.addCookie()
		4.在进服务器的转送过后使用Request.getCookies()获取Cookie数组
		5.若是经过URL编码，cookie.getValue 经URL指定字符解码,再操作

2.cookie传送信息原理

	response将cookie添加进去后，相当于是添加了键值对，
	将cookie的信息传进了服务器，而在服务器会在下一次页面请求时，
	随后在一次会话中，进行了GET,POST等等方法后,
	并非是addCookie后就能用Request获取到，而是经过了一个服务器的转送
	而服务器会携带所有的Cookie返回到Request请求头信息上

3.cookie持久时间
	
	Cookie.setMaxAge(int expiry)
	expiry :
	an integer specifying the maximum age of the cookie in seconds; 
		if negative, means the cookie is not stored; 
		if zero, deletes the cookie
		
4.cookie共享数据
	
	//同服务器不同工程 uri : "/"
	public void setPath(String uri) {
        path = uri;
    }
	
	
	//不同服务器,使用域名
	public void setDomain(String pattern) {
        domain = pattern.toLowerCase(Locale.ENGLISH); // IE allegedly needs this
    }

5.cookie的用处：用于一些轻量级信息设定(有大小限制和个数限制)，放于客户端中

注意：

	servlet写在html里，换行也应该使用</br>,而不是\n\r..

举例：
	
	@WebServlet("/CookieDemo3")
	public class CookieDemo3 extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			boolean flag = false;
			SimpleDateFormat sDateFormat = null;
			
			Cookie[] cookies = request.getCookies();
			response.setHeader("content-type", "text/html;charset=utf-8");
			if(cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("LastTimeLoggin")) {
						//已经登录过的操作
						String key,value;
						response.getWriter().write("<h1>已经成功登录:"+"</br>");
						for (Cookie c : cookies) {
							key = c.getName();
							if(key.equals("username") || key.equals("LastTimeLoggin")) {
								value = URLDecoder.decode(c.getValue(),"utf-8");
								response.getWriter().write(key +" = "+value+"</br>");
							}
						}
						response.getWriter().write("</h1>");
						
						//response完成过后,进行cookie设置
						cookie = new Cookie("username", URLEncoder.encode("李白","utf-8"));
						sDateFormat = new SimpleDateFormat("yyyy年MM月dd日       HH:mm:ss");
						String logginTime = sDateFormat.format(new Date());
						String encodeLog = URLEncoder.encode(logginTime,"utf-8");
						Cookie cookie1 = new Cookie("LastTimeLoggin", encodeLog);
						
						cookie.setMaxAge(10);
						cookie1.setMaxAge(10);
						response.addCookie(cookie);
						response.addCookie(cookie1);
						flag = true;
						break;
					}
				}
			}
			
			if(!flag) {
				//未登录过，进行cookie设置
				Cookie cookie = new Cookie("username", URLEncoder.encode("李白","utf-8"));
				sDateFormat = new SimpleDateFormat("yyyy年MM月dd日       HH:mm:ss");
				String logginTime = sDateFormat.format(new Date());
				String encodeLog = URLEncoder.encode(logginTime,"utf-8");
				Cookie cookie1 = new Cookie("LastTimeLoggin", encodeLog);
				
				cookie.setMaxAge(10);
				cookie1.setMaxAge(10);
				response.addCookie(cookie);
				response.addCookie(cookie1);
				
				//设置完并非可以立即request获得，而是经过服务器转送才获取到
				cookies = request.getCookies();
				String key,value;
				response.getWriter().write("<h1>第一次登录!"+"</br>");
			}
		}
	}
	

	