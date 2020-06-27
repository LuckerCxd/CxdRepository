struts参数字符串类型转换和JSP的struts标签：

<h3>一.struts参数字符串类型转换:</h3>
    1.xwork中有一系列Converter转换器，用于转换类型，其中仅举例 DateConverter
    2.自定义转换器类步骤
        1.自定义转换器类继承StrutsTypeConverter,并重写方法
        2.在bean同包下配置文件：bean的类名-conversion.properties[局部类型转换器]
            区别也可以使用全局类型转换器，但是局部会覆盖全局转换器
        3.补充说明全局转换器
    3.注意事项

<h3>二.JSP的struts标签:</h3>
    1.在jsp中，导入struts标签包
    2.在jsp中，表单中struts标签的使用与转化分析
        1.<s:head/>：定制了错误显示的红色样式以及其他的样式
        2.<s:fielderror></s:fielderror>：显示错误信息
        3.<s:form action="/p9/hello1" method="GET">:方法默认为POST
        4.表单项
        5.自定义显示错误信息

<h4>一.struts参数字符串类型转换:</h4>

**1.xwork中有一系列Converter转换器，用于转换类型，其中仅举例 DateConverter**

    com.opensymphony.xwork2.conversion.impl.CollectionConverte
    com.opensymphony.xwork2.conversion.impl.ArrayConverter
    com.opensymphony.xwork2.conversion.impl.DateConverter
    com.opensymphony.xwork2.conversion.impl.NumberConverter
    com.opensymphony.xwork2.conversion.impl.StringConverter

    通过观察可找到它和其子类的位置：
        xwork-core-2.3.37\com\opensymphony\xwork2\conversion\impl\DateConverter
        public class DateConverter extends DefaultTypeConverter
        xwork-core-2.3.37\com.opensymphony.xwork2.conversion\TypeConverter
        public abstract class DefaultTypeConverter implements TypeConverter
    但我们将要继承的是struts工具包下的StrutsTypeConverter类：
        struts2-core-2.3.37\org\apache\struts2\util\StrutsTypeConverter
        public abstract class StrutsTypeConverter extends DefaultTypeConverter
    可发现，它也继承了DefaultTypeConverter，这意味着它可以帮我们实现自定义Date转换器
    得出结论：
        继承StrutsTypeConverter抽象子类，可以帮我们实现自定义Date转换器
    
**2.自定义转换器类步骤：**
    
    1.自定义转换器类继承StrutsTypeConverter,并重写方法
    2.在bean同包下配置文件：bean的类名-conversion.properties[局部类型转换器]
        区别也可以使用全局类型转换器，但是局部会覆盖全局转换器

1.自定义转换器类继承StrutsTypeConverter,并重写这两个方法：

    public abstract Object convertFromString(Map context, String[] values, Class toClass);
    public abstract String convertToString(Map context, Object o);

[/StrutsDmeo/src/cn/cxd/convert/DateConvertor.java]()
-
    package cn.cxd.convert;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Map;
    import javax.management.RuntimeErrorException;
    import org.apache.struts2.util.StrutsTypeConverter;

    public class DateConvertor extends StrutsTypeConverter{
    	@Override
    	public Object convertFromString(Map context, String[] values, Class toClass) {
    		if(values != null && values.length > 0 ) {
    			String date = values[0];
    			if("".equals(date)) 
    				return null;
    			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy,MM,dd");
    			try {
    				return simpleDateFormat.parse(date);
    			} catch (ParseException e) {
                    //[在转换器中将异常抛出,用于xml中result的错误回显name_input]()
    				throw new RuntimeException(e);
    			}
    		}
    		return null;
    	}
    
    	@Override
    	public String convertToString(Map context, Object o) {
    		return null;
    	}
    
    }

    
2.在bean同包下配置文件：bean的类名-conversion.properties：**局部类型转换器**

    内容：同包下的指定Bean类的特定私有成员 = 自定义的类转换器的完整包路径
    举例内容：birthday=cn.cxd.convert.DateConvertor
    举例位置：/StrutsDmeo/src/cn/cxd/bean/Student-conversion.properties
    举例：

[/StrutsDmeo/src/cn/cxd/bean/Student-conversion.properties]():
-
    birthday=cn.cxd.convert.DateConvertor


**全局转换器配置文件：**

    全局位置：src包下 xwork-conversion.properties 文件
    内容：指定某一类都是使用该转换器
    举例：
      java.util.Date=cn.cxd.convert.DateConvertor2  ->所有类型都使用这个转换器
    
[/StrutsDmeo/src/xwork-conversion.properties]():
-
    java.util.Date=cn.cxd.convert.DateConvertor2
[/StrutsDmeo/src/cn/cxd/convert/DateConvertor2.java]()
-
    package cn.cxd.convert;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Map;
    import javax.management.RuntimeErrorException;
    import org.apache.struts2.util.StrutsTypeConverter;

    public class DateConvertor extends StrutsTypeConverter{
    	@Override
    	public Object convertFromString(Map context, String[] values, Class toClass) {
    		if(values != null && values.length > 0 ) {
    			String date = values[0];
    			if("".equals(date)) 
    				return null;
    			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    			try {
    				return simpleDateFormat.parse(date);
    			} catch (ParseException e) {
    				throw new RuntimeException(e);
    			}
    		}
    		return null;
    	}
    
    	@Override
    	public String convertToString(Map context, Object o) {
    		return null;
    	}
    
    }

[/StrutsDmeo/src/struts.xml]():
-
    <package name="p9" extends="struts-default" namespace="/p9">
		<action name="hello0" class="cn.cxd.action.HelloAction5" method="showPara"></action>
		<action name="hello1" class="cn.cxd.action.HelloAction6" method="showPara">	
			<!--  DateConvertor2中将错误抛出，才有回显->错误回显,而非自己处理  -->
			<result name="input">/formAction2.jsp</result>
		</action>
		<action name="hello2" class="cn.cxd.action.HelloAction7" method="showPara"></action>
	</package>

**3.注意事项：**

    1.在Bean包下我们设置的配置文件是局部转换器：bean的类名-conversion.properties
        内容是：birthday=cn.cxd.convert.DateConvertor
             内部定义的格式是：SimpleDateFormat("yyyy,MM,dd")
    2.在src包下我们设置得xwork-conversion.properties 文件
        内容是：java.util.Date=cn.cxd.convert.DateConvertor2
             内部定义的格式是：SimpleDateFormat("yyyy/MM/dd")
    3.二者存在覆盖关系：局部覆盖全局，再覆盖框架内的转换器，
        因此在填写表单时，使用全局转换器下的2010/2/2是不可以的，
                        而使用自带转换器下的2010-2-2也是不可以的
        只有局部转换器的2010,2,2可被正确提交
    4.在转换器中将异常抛出，才有错误回显<result name="input">,而非自己处理异常
    




<h4>二.JSP的struts标签:</h4>
**1.在jsp中，导入struts标签包**

    <%@ taglib uri="/struts-tags"  prefix="s" %>
        
**2.在jsp中，表单中struts标签的使用与转化分析**

    1.<s:head/>：定制了错误显示的红色样式以及其他的样式
    2.<s:fielderror></s:fielderror>：显示错误信息
    3.<s:form action="/p9/hello1" method="GET">:方法默认为POST
    4.表单项
    5.自定义显示错误信息

**1.<s:head/>：定制了错误显示的红色样式以及其他的样式**

    网页访问查看源代码得知等于加上了：
        <link rel="stylesheet" href="/StrutsDmeo/struts/xhtml/styles.css" type="text/css"/>
        <script src="/StrutsDmeo/struts/utils.js" type="text/javascript"></script>

    针对这两个文件的内容去寻找：发现这两个文件的位置应该在于：
        1.struts2-core-2.3.37\template\styles.css
        2.struts2-core-2.3.37\org\apache\struts2\static\utils.js
    发现在styles.css有关于错误的显示样式控制

[struts2-core-2.3.37\template\xhtml\styles.css]()：
-
    .wwFormTable {}
    .label {font-style:italic; }
    .errorLabel {font-style:italic; color:red; }
    .errorMessage {font-weight:bold; color:red; }
    .checkboxLabel {}
    .checkboxErrorLabel {color:red; }
    .required {color:red;}
    .tdLabel {text-align:right; vertical-align:top; } 
    
**2.<s:fielderror></s:fielderror>：显示错误信息**

    网页访问查看源代码得知等于加上了：
         <ul                    class="errorMessage"                >
                <li><span>Invalid field value for field &quot;birthday&quot;.</span></li>
        </ul>
    如果输入是错误的，会显示加入标签的位置显示错误信息

**3.<s:form action="/p9/hello1" method="GET">：加入表单**

     网页访问查看源代码得知等于加上了：
        <form id="hello1" name="hello1" action="/StrutsDmeo/p9/hello1" method="GET">
    注意：method 默认为POST
         action 不能添加工程名,会自动加上的

**4.表单项**

    <s:form action="/p9/hello1" method="GET">
    	<s:textfield name="username" label="用户名"  requiredLabel="true" requiredPosition="left"></s:textfield>
    	<s:password name="password" label="密码"  showPassword="true" requiredLabel="true" requiredPosition="left" > </s:password>
    	<s:textfield name="birthday" label="出生日期"></s:textfield>
    	<s:radio list="#{'true':'是' , 'false':'否'} "  name="adult" label="成年"></s:radio>
    	<s:submit value="提交"></s:submit>
	</s:form>
    
    网页访问查看源代码得知等于加上了:
        <table class="wwFormTable">
        <tr>
            <td class="tdLabel">
                <label for="hello1_username" class="label"><span class="required">*</span>用户名:</label>
            </td>
            <td>
                <input type="text" name="username" value="李白" id="hello1_username"/>
            </td>
        </tr>
        
        <!-- 错误输入后的显示信息 -->
        <tr errorFor="hello1_birthday">
         <td align="center" valign="top" colspan="2">
            <span class="errorMessage">Invalid field value for field &quot;birthday&quot;.</span>
         </td>
        </tr>

        <tr>
            <td class="tdLabel">
                <label for="hello1_adult" class="label">成年:</label>
            </td>
            <td>
                <input type="radio" name="adult" id="hello1_adulttrue" checked="checked" value="true"/>
                <label for="hello1_adulttrue">是</label>
                <input type="radio" name="adult" id="hello1_adultfalse" value="false"/>
                <label for="hello1_adultfalse">否</label>
            </td>
        </tr>

    注意：
        刷新时会自动保存textfield数据,用于刷新后显示，不用再重新输入
        而且，一样添加了class用于样式控制，而lable自动完成了输入框的聚焦功能
        发生错误时，不必使用<s:fielderror>,会自动在出错的上一行显示错误
        textfield,password,radio,checkboxlist...
     属性介绍：
             requireLable：不可缺少的项
             requirePosition:*标记的位置
             name:一定要有的属性，用于将内容设置到bean中对应的名称
             label:在输入框前显示的文字信息
             showPassword(password中的属性):刷新时保留密码字段
             list(radio中的属性):
                    使用"#{'':'','':'',..}"设置键值对,
                    key为接收进入的内容，value对应的网页显示的内容
             value(submit中的属性)：显示在submit按钮上的文字信息
        
**5.自定义显示错误信息**

    在上面中如果birthday的输入格式错误,会自动在出错的上一行显示错误信息：
        Invalid field value for field "birthday".
    我们可以自定义显示错误信息：
    步骤：
        1.在bean的同包下，配置 Bean类名.properties 文件
        2.在该文件下添加信息：(输入时会自动变成unicode编码)
            invalid.fieldvalue.出错的标签的name属性=unicode编码的文字信息
        

[/StrutsDmeo/src/cn/cxd/bean/Student.properties]()：
-
    invalid.fieldvalue.birthday=\u8F93\u5165\u6B63\u786E\u683C\u5F0F,\u59822010,2,8
    