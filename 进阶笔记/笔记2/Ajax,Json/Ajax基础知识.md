ajax基础知识：

		Asynchronous JavaScript and xml
        进行局部更新

实现方式(常用的)：
    
    1.$.ajax();
    2.$.get();
    3.$.post();
    
1.$.ajax()
    
    url:进行数据交换的url
    type:请求方式
    data:提交的参数
    success:成功后回调该方法,可用于接收并处理传回的结果
    error:失败后回调该方法
    dataType:设置接收到的响应体的数据格式

2.$.get(),$.post()

    url,[data],[callback],[type]  String,Map,Function,String 
    url:待载入页面的URL地址
    data:待发送 Key/value 参数。
    callback:载入成功时回调函数。
    type:返回内容格式，xml, html, script, json, text, _default



举例：

servletDemo:
-
    package ServletDemo;
    
    import java.io.IOException;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    
    @WebServlet("/servletDemo")
    public class servletDemo extends HttpServlet {
    	private static final long serialVersionUID = 1L;
    
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		doPost(request, response);
    	}
    
    	
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		try {
    			Thread.currentThread().sleep(5000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		response.getWriter().write(request.getParameter("username")+" is you ?");
    	}
    
    }

xxx.html
-
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="JS/jquery-3.3.1.min.js"></script>
    </head>
    <body>
    
    
    <input type="button" id = "send" value = "发送异步请求">
    
    <input type="text" id = "block" >
    
    </body>
    
    <script type="text/javascript">
    
    /* 
    $("#send").click(function () {
    	$.ajax({
    		url:"http://localhost:8080/TryAjax/servletDemo",
    		type:"POST",
    		data:{"username":"LiMi",
    				"age":23,
    				"location":"GuangDong"},
    		success:function(data){
    			alert(data);
    		},
    		dataType:"text"	
    	});
    })
    
     */
     
    /*  
    $("#send").click(function () {
    	$.get("http://localhost:8080/TryAjax/servletDemo",
    			{"username":"LiMi","age":23,"location":"GuangDong"},
    			function (data) {
    				alert(data);
    			},
    			"text"
    		);
    }) 
    
    
    Request URL:http://localhost:8080/TryAjax/servletDemo?username=LiMi&age=23&location=GuangDong
    Request Method:GET
    Status Code:200 
    Remote Address:[::1]:8080
    Referrer Policy:no-referrer-when-downgrade
    */
     
     
    
    $("#send").click(function () {
    	$.post("http://localhost:8080/TryAjax/servletDemo",
    			{"username":"LiMi","age":23,"location":"GuangDong"},
    			function (data) {
    				alert(data);
    			},
    			"text"
    		);
    }) 
    
    /* 
    Request URL:http://localhost:8080/TryAjax/servletDemo
    Request Method:POST
    Status Code:200 
    Remote Address:[::1]:8080
    Referrer Policy:no-referrer-when-downgrade
    Response Headers
    
     */
    </script>
    
    </html>