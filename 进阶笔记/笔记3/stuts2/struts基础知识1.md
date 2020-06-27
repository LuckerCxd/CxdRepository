struts基础知识：

    1.使用步骤
    2.加载配置文件的顺序
    3.执行顺序
    4.举例修改配置信息(后缀名为例)

1.使用步骤：

    0.创建dynastic web工程

    1.导入jar包：
        struts-2.3.37-all\struts-2.3.37\apps\struts2-blank.war下的lib包

    2.将struts.xml放入src下
       struts2-blank.war包下(解压)\WEB-INF\src\java\struts.xml

    3.在修改struts.xml需要一些约束条件,需要联网，因此可以改成本地的
        <!DOCTYPE struts PUBLIC
    	"-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
    	"http://struts.apache.org/dtds/struts-2.3.dtd">
        1.将dtd:复制到工程中的dtd文件夹中
            struts-2.3.37\lib\struts2-core-2.3.37\struts-2.3.dtd
        2.preferences->xml catalog -> workspace -> 选中改dtd文件 
            -> key（URI）-> struts.xml中复制下来的地址
           "http://struts.apache.org/dtds/struts-2.3.dtd"

    3.创建WebContent\WEB-INF\web.xml
        在其中添加filter,filter-mapping标签

        <filter>
          <filter-name>struts2</filter-name>
          <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
        </filter>

        <filter-mapping>
          <filter-name>struts2</filter-name>
          <url-pattern>/*</url-pattern>
        </filter-mapping>

    4.在struts.xml中修改其内容：
        <package name="p1" extends="struts-default">
    		<action name="hello" class="cn.cxd.action.HelloAction" method="doHello">
    			<result name = "success" type="dispatcher" >/success.html</result>
    		</action>
	   </package>

    5.创建action标签中定义的包和类名和实例方法，注意字符串返回值于result对应
        package cn.cxd.action;
        public class HelloAction {
        	public String doHello() {
        		System.out.println("doHello被执行了");
        		int i = 5/0;
        		return "success";
        	}
        }

        action :（相当于servlet）
        name : 相当于是@WebServlet映射路径,用name来访问
        class: 用于反射出实例，调用method
        method: 访问时执行的方法
                要求：public，
                返回值：String
                参数：空参
        result: 执行完method后的return字符串结果与result_name属性对比,
                如果内容对的上,就是返回 result标签体内容(路径URL)
                默认是： dispatcher:转发方式

    6.创建aciton中result标签体路径内容
        D:\Java\StrutsDmeo\WebContent\success.html
        <!DOCTYPE html>
        <html>
            <head>
                <meta charset="UTF-8">
                <title>Insert title here</title>
            </head>
            <body>
                <h1>successHtml</h1>
            </body>
        </html>


2.加载配置文件的顺序：

    参考我们在web.xml中配置的filter信息：创建了Dispatcher对象
    org.apache.struts2.dispatcher.ng.filter.StrutsExecuteFilter
    protected void lazyInit() {
        if (this.execute == null) {
          InitOperations init = new InitOperations();
          Dispatcher dispatcher = init.findDispatcherOnThread();
          init.initStaticContentLoader(new FilterHostConfig(this.filterConfig), dispatcher);
          this.prepare = new PrepareOperations(dispatcher);
          this.execute = new ExecuteOperations(dispatcher);
        } 
      }
    
    在Dispatcher对象的init方法中,可以看见加载顺序：
    org.apache.struts2.dispatcher.Dispatcher.init() : void

        init_FileManager();
        init_DefaultProperties();
        init_TraditionalXmlConfigurations();
        init_LegacyStrutsProperties();
        init_CustomConfigurationProviders();
        init_FilterInitParameters();
        init_AliasStandardObjects();
    
    1.init_DefaultProperties():
        org.apache.struts2.default.properties

    2.init_TraditionalXmlConfigurations():
        struts-default.xml 
        struts-plugin.xml
        struts.xml(放置于src下）

    3.init_LegacyStrutsProperties()：
        struts.properties（自定义,但名称不可更改,放置于src下）

    4.init_CustomConfigurationProviders()：
        
    5.init_FilterInitParameters():
        web.xml

    6.init_AliasStandardObjects():
        各个bean...

    注意：
        由于加载的顺序问题，因此配置信息可以被覆盖，
        不可更改的有：
            1.org.apache.struts2.default.properties
            2.struts-default.xml
            3.struts-plugin.xml
3.执行顺序：
    
    1.tomcat -> web.xml -> filter -> struts.xml 
      -> package -> action -> class -> method -> result

        <package name="p1" extends="struts-default">
    		<action name="hello" class="cn.cxd.action.HelloAction" method="doHello">
    			<result name = "success" type="dispatcher" >/success.html</result>
    		</action>
	   </package>
        
4.举例修改配置信息(后缀名为例)：

    default.properties后缀配置：
        default.properties：
            struts.action.extension=action,,
        
    修改方法：
        1.struts.xml
            <constant name="struts.action.extension" value="com"></constant>
            会覆盖default.properties

        2.struts.properties（自定义,但名称不可更改,放置于src下）
            struts.action.extension=java
            会覆盖struts.xml 以及 default.properties

        3.web.xml
            <filter>
              <filter-name>struts2</filter-name>
              <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>

               <!-- 参数配置 -->
              <init-param>
              	<param-name>struts.action.extension</param-name>
              	<param-value>cc</param-value>
              </init-param>

            </filter>
    
            <filter-mapping>
              <filter-name>struts2</filter-name>
              <url-pattern>/*</url-pattern>
            </filter-mapping>
    
            会覆盖struts.xml 以及 default.properties 以及 struts.properties
    注意：
        会有缓存，因此要稍等一下才能看出结论

    总结：
        default.properties中的配置信息，
        可以使用struts.xml,struts.properties,web.xml来修改
