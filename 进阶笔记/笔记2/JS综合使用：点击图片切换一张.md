JS使用功能：

用法1:
  
	<a href="javascript:checkAnother();">
	<img src = "${pageContext.request.contextPath}/pictureServlet" title = "另一张" id = "anotherPict">
	</a>

	<script>
		function checkAnother(){
			var anotherPict = document.getElementById("anotherPict");
			anotherPict.src = "${pageContext.request.contextPath}/pictureServlet";
	}
	</script>
	

用法2:  

	<img src = "${pageContext.request.contextPath}/pictureServlet" title = "另一张" id = "anotherPict">

	<script>
		window.onload(){
		var anotherPict = document.getElementById("anotherPict");
		anotherPict.onClick = function(){
			anotherPict.src = "${pageContext.request.contextPath}/pictureServlet";
		} 
	}
	</script>


画出图画的servlet:

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
