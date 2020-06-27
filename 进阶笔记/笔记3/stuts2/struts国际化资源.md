struts的国际化资源：
    
    一.国际化资源包的放置位置和命名规范
    二.作用范围
        2.1 注意
    三.使用：
        3.1 java代码使用
            3.1.1 说明
            3.1.2 范例
            3.1.3 源码
            3.1.4 参数解释

        3.2 jsp原标签库使用
            3.2.1 范例

        3.3 jsp中struts标签库使用
            3.3.1 默认的国际化资源包的文件位置和配置信息
            3.3.2 步骤
                3.3.2.1 配置struts.xml
                3.3.2.2 jsp中使用struts标签库
                    3.3.2.2.1 说明
                    3.3.2.2.2 范例

<h2>一.国际化资源包的放置位置和命名规范：</h2> 

    src:
        cn.cxd.web
            actions
                I18NActionDemo_en_US.properties
                I18NActionDemo_zh_CN.properties
                I18NActionDemo.java
            package_en_US.properties
            package_zh_CN.properties
        resources：
            message_zh_CN.properties
            message_en_US.properties

<h2>二.作用范围：</h2>

    全局：src下的自定义包中，自定义名称_语言代码_地区代码.properties,
    包范围下：包裹着actions包的父类包中，package_语言代码_地区代码.properties
    局部：actions包中，action类名_语言代码_地区代码.properties

<h3>2.1注意：</h3>

    1.properties文件存放的相当于key=value这样格式的键值对,不用加""
    2.在“_语言代码_地区代码.properties”之前的名称字段可以认为是 前缀名称，
      在访问资源包时需要使用这个前缀名称

<h2>三.使用</h2>

<h3>3.1 java使用：</h3>

<h4>3.1.1 说明：</h4>

    通过使用ResourceBundle类的 静态getBundle方法,
        传入 properties文件的包位置和前缀名称以及Locale地区 参数
        返回 ResourceBundle对象,
        进而使用该ResourceBundle对象的getString(),
        输入对应的key作参数 即可得到对应的value

<h4>3.1.2 范例：</h4>

    @Test
	public void test() {
		ResourceBundle bundle = ResourceBundle.getBundle("resources.message",Locale.CANADA);
		System.out.println(bundle.getString("login.username"));
		Locale.setDefault(Locale.US);
		bundle = ResourceBundle.getBundle("resources.message");
		System.out.println(bundle.getString("login.username"));
	}

<h4>3.1.3 源码：</h4>
    @CallerSensitive
    public static final ResourceBundle getBundle(String baseName,
                                                 Locale locale)
    {
        return getBundleImpl(baseName, locale,
                             getLoader(Reflection.getCallerClass()),
                             getDefaultControl(baseName));
    }

<h4>3.1.4 参数解释：</h4>

    1.baseName：
            包名-前缀名称 
      文档说明：
        baseName：the base name of the resource bundle, a fully qualified class name
    
    2.locale: 地区  
        如果是设置了Locale，而在对应的baseName没有发现所设置的Locale后缀，
            那就使用同baseName下，默认Locale地区的properties文件
        其中,默认Locale地区是Java虚拟机启动时根据运行环境确定的,很多方法是使用默认地区的。
        而默认地区也可以修改：
            Locale.setDefault(Locale.US);
        文档说明：
          locale ：the locale for which a resource bundle is desired
          The Java Virtual Machine sets the default locale during startup
          based on the host environment. It is used by many locale-sensitive
          methods if no locale is explicitly specified.
          It can be changed using the
              {@link #setDefault(java.util.Locale) setDefault} method.
       

<h3>3.2 jsp中的原标签使用:(仍是使用java代码,但位置不同)</h3>

<h4>3.2.1 范例：</h4>

    <%
    	ResourceBundle bundle = ResourceBundle.getBundle("resources.message",Locale.US);
    %>
    <form action="${pageContext.request.contextPath}/p1/i18n" method="get">
    <%=bundle.getString("login.username") %> <input type="text" name="username" >
    <br/>
    <%=bundle.getString("login.password") %> <input type="text" name="password" >
    <br/>
    <input type="submit" value=<%=bundle.getString("login.submit") %> >
    </form>


<h3>3.3 jsp中struts标签库使用:(使用的是标签获取)</h3>


<h4>3.3.1 默认的国际化资源包的文件位置和配置信息</h4>

    文件位置 :struts2-core-2.3.37\org\apache\struts2\default.properties
    配置设置 ：# struts.custom.i18n.resources=testmessages,testmessages2


<h4>3.3.2 步骤：</h4>

    3.3.3.1 配置struts.xml
    3.3.3.2 在jsp中使用struts标签库

<h5>3.3.2.1 配置struts.xml</h5>

    <constant name="struts.custom.i18n.resources" value="cn.cxd.web.package"/>
    <constant name="struts.custom.i18n.resources" value="resources.message"/>
    <constant name="struts.custom.i18n.resources" value="cn.cxd.web.actions.I18NActionDemo"/>

<h5>3.3.2.2 在jsp中使用struts标签库</h5>

<h6>3.3.2.2.1 说明：</h6>

    将struts标签库的代码包裹在<s:i18n name="baseName"> 标签中
    baseName:包名-前缀名称：
    <s:i18n name="cn.cxd.web.actions.I18NActionDemo">
    <s:i18n name="resources.message">

<h6>3.3.2.2.2 范例：</h6>

    <s:i18n name="cn.cxd.web.package">
    <form action="${pageContext.request.contextPath}/p1/i18n" method="get">
    <s:text name="login.username"/> <input type="text" name="username" >
    <br/>
    <s:text name="login.password"/> <input type="text" name="password" >
    <br/>
    <input type="submit" value= <s:text name="login.submit"/>>
    </form>
    </s:i18n>



