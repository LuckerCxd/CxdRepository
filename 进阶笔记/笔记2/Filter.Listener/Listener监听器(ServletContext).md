实现ServletContextListener监听器类：

    1.继承ServletContextListener，重写其方法init，destroy
    2.相当于是监听ServletContext对象(整个服务器)的生命周期
        在服务器启动时自动地就有ServletContext对象：
            init方法，用于请求资源
            destroy方法，用于释放资源

    3.配置：(<context-param>用于加载资源文件)
    4.在Listener的init中加载资源文件

1.web.xml配置：

        在<web-app>标签中使用：<listener>,<context-param>
            <listener>
            <listener-class>包名.xx</listener-class>
            </listener>
        
            <context-param>
             <param-name>ResourceLocation</param-name>
             <param-value>\敏感词汇.txt</param-value>
            </context-param>


2.注解配置 + web.xml配置

        @WebListener
        在<web-app>标签中使用：<context-param>
            <context-param>
             <param-name>ResourceLocation</param-name>
             <param-value>\敏感词汇.txt</param-value>
            </context-param>
    
4.在Listener的init中加载资源文件
    
    1.通过参数ServletContextEvent sce获取ServletContext

    2.将资源加载进入内存：
        使用servletContext.getInitParameter("xxx");
        xxx:<param-name>中定义的资源name
        会返回出<param-value>中的资源路径的字符串形式

        String initParameter = servletContext.getInitParameter("ResourceLocation");
        
    3.通过该字符串形式的<param-value>做参数，
        使用：getRealPath()获取真实路径
    
    4.使用流将资源文件读取出来
        

ListenerDemo1:
-
    package DemoListener;
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.util.ArrayList;
    import java.util.List;
    
    import javax.servlet.ServletContext;
    import javax.servlet.ServletContextEvent;
    import javax.servlet.ServletContextListener;
    import javax.servlet.annotation.WebListener;
    
    
    @WebListener
    public class ListenerDemo1 implements ServletContextListener {
    
        public void contextDestroyed(ServletContextEvent sce)  { 
             System.out.println("destroy");
        }
    
    	
        public void contextInitialized(ServletContextEvent sce)  { 
        	 System.out.println("init");
            //1.通过参数ServletContextEvent sce获取ServletContext
        	 ServletContext servletContext = sce.getServletContext();
            //2.将资源加载进入内存
        	 String initParameter = servletContext.getInitParameter("ResourceLocation");

            //3.使用：getRealPath()获取真实路径
        	 String realPath = servletContext.getRealPath(initParameter);
        	 
        	 List<String> list = new ArrayList<String>();
        	 
            //4.使用流将资源文件读取出来
        	 try(BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));) {
                 String line = null;
                 while((line = bufferedReader.readLine())!= null) {
                     list.add(line);
                 }
                 for (String string : list) {
                     System.out.println("listener:"+string);
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
    
         
        
        }
    	
    }


web-xml:
-
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
      <display-name>WebFilter</display-name>
      <welcome-file-list>
        <welcome-file>index.html</welcome-file>
        <welcome-file>index.htm</welcome-file>
        <welcome-file>index.jsp</welcome-file>
        <welcome-file>default.html</welcome-file>
        <welcome-file>default.htm</welcome-file>
        <welcome-file>default.jsp</welcome-file>
      </welcome-file-list>
      
      <context-param>
      <param-name>ResourceLocation</param-name>
      <param-value>\敏感词汇.txt</param-value>
      </context-param>
    </web-app>