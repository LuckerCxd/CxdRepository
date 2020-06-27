session：借助于Cookie实现

获取：
	
	request.getSession();


1.共享数据(不需要请求转发) 
	
	session.addAttribute();
	session.getAttribute();
	session.removeAttribute();

	区别：		
		1. request.setAttribute()请求转发 才进行共享
		2. servletContext 整个服务器的信息共享

2.服务器关闭,客户端不关:不是同一个session

	在服务器重启时，会将work文件夹清空，
	session数据就丢失了，随后再生成新的work文件夹
	
3.客户端关闭，服务器不关：不是同一个session

	用Cookie来保存起来可以保持不变：

		HttpSession session = request.getSession();
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);
		response.getWriter().write(session.getId());

4.session 销毁和失效：(存入信息的移除应该使用session.removeAttribute())
	
	1.服务器关闭
	2.session对象调用invalidate()
	3.session在tomcat/conf/web.xml的session-config 默认失效时间为30

注意：

	1.任意类型，任意大小 2.一次会话的多次请求数据 (例如重定向),放于服务器中
	3.cookie(有大小限制和个数限制)，放于客户端中,session 借助于Cookie实现
	4.session 借助于Cookie实现，但存储信息不在request,response响应和请求头