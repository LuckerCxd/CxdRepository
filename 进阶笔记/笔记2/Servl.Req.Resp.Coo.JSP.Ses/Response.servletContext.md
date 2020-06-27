Response&&servletContext

Response:响应
	
	1.重定向
	2.输出数据
	
servletContext:代表整个web应用，可以与整个服务器通信
	
	0.获取servletContext对象：
		ServletContext servletContext = request.getServletContext();
		ServletContext servletContext = this.getServletContext();
	1. 获取MIME类型：					//用于获取后设置响应信息类型
		getMimeType(filenameString)

	2. 共享整个服务器的数据
		setAttribute(key,value);
		removeAttribute(key);
		getAttribute(key);

	3.	获取服务器中文件的真实路径		//用于服务器资源的加载和写入
		realpath : 服务器位置:将整个工程内容都加载到WebContent下,
			并将此时的WebContent内容放入工程名目录下，
		servlet目录为：
			D:\Java\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\WebServlet目录
		src的内容在WebServlet(工程名)下的WEB-INF下的classes目录下
		其他内容的位置在WebContent基本不变
		// a 在  src 下 -> WebServlet/WEB-INF/classes/a.txt

		servletContext.getRealPath("/classes/a.txt");
			

1.重定向
	
1.实现

	设置以下信息：
		状态码：302
		响应头：Location - URL
	
	实现：
	response.setStatus(302);
	response.setHeader("Location", "http://localhost:8080/WebServlet/ResponseDemo2");
	
	简单实现：
	response.sendRedirect("http://localhost:8080/WebServlet/ResponseDemo2");
	
2.区别于请求转发

	请求转发：
		1.仅能转发到服务器内部的
		2.地址栏不发生变化
		3.仅为一次请求
	request.getRequestDispatcher("/demo3").forward(request, response);

	重定向:
		1.能转发到服务器内部或是外部服务器的
		2.地址栏发生变化
		3.为多次请求
	response.sendRedirect("https://www.baidu.com");
	
2.响应体输出数据
	
2.1输出字符串

		2.1.0.设置response请求头的Content-Type属性，
						设为text/html;charset=utf-8	

		2.1.1获取字符流writer输出
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write("你好猪头");
		
		2.1.2或是获取字节流outputStream输出
		response.setContentType("text/html;charset=utf-8");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write("你好，猪头".getBytes("utf-8"));
			
2.2输出自定义(借助于BufferedImage图片对象，Graphics画笔，ImageIO展示)
	
	@WebServlet("/ResponseDemo1")
	public class ResponseDemo1 extends HttpServlet {
		private static final long serialVersionUID = 1L;
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//		response.getWriter().append("Served at: ").append(request.getContextPath());
			
			int width = 120;
			int height = 100;
			int x = 0;
			int y = 0;
			BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = im.getGraphics();
			graphics.setColor(Color.pink);
			graphics.fillRect(x, y, width, height);
			graphics.setColor(Color.green);
			graphics.drawRect(x, y, width-1,height-1);
			String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			graphics.setColor(Color.BLACK);
			int w_padding = width/6;
			int h_padding = height/2;
			for (int i = 1; i < 6; i++) {
				Random random = new Random();
				int index = random.nextInt(string.length());
				String str = string.charAt(index)+"";
				graphics.drawString(str,w_padding*i,h_padding);
			}
			ServletOutputStream outputStream = response.getOutputStream();
			ImageIO.write(im, "jpg", outputStream);
		}
	}
	
			