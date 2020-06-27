struts验证:

    1.action类中重写validate方法(全局),编写专用的validate方法(局部)
    2.在action包下,使用指定的validation.xml编写
        步骤：
        2.1.1 先找到xwork-core-2.3.37包下的dtd约束,复制其中一段注释进入validate.xml文件中
        2.2 使用局部或是全局的validate.xml配置
            2.2.1 action类名-validation.xml(全局)
            2.2.2 Action类名-别名(对应唯一method)-validation.xml(局部)
        2.3 使用标签，完成validate.xml文件设置验证
            2.3.1 参考官方提供的validator验证器(在xwork的default.xml中定义),
                    选需要的验证器类型
            2.3.2 查看对应验证器类的源码,并查其父类,找到需要填写的私有变量,将来用于参数设置
            2.3.3 使用指定格式填写<validate>,<param>,<message>标签


<h3>一.action类中重写validate方法(全局),编写专用的validate方法(局部)</h3>

<h4>1.1 action类中重写validate方法(全局)</h4>

    action类中重写validate方法(全局),编写专用的validate方法(局部)
    在ActionSupport中,validate方法是空实现的：public void validate() {}
    因此，action类在继承ActionSupport后，可以重写validate方法
    而该方法是全局的,因此会在本action类的其他方法被调用之前调用，
    如果该方法验证时，一旦添加了错误信息，那么其他方法就不会被执行

    @Override
	public void validate() {
		if(StringUtils.isEmpty(user.getUsername())) {
			addFieldError("username", "用户名不能为空");
		}
		
	}

    提示：
        isEmpty: null 或是  “”
        前提是使用struts页面标签
        其他方法如果不需要该验证,可以添加注解@SkipValidation来忽略该验证

<h4>1.2 编写专用的validate方法(局部)</h4>

    public void validateRegister(){
		if(StringUtils.isEmpty(user.getUsername())) {
			addFieldError("username", "用户名不能为空");
		}
	}
    提示：它是局部的,非重写于父类方法,
          命名规范validateFunction,仅对function方法进行验证
          即仅在function方法被调用前执行该validateFunction方法





<h3>二. 在action包下,使用指定的validation.xml编写</h3>

<h4>前置：</h4>
    
    validate.xml编写所需的库参考文件:
        xwork-core-2.3.37\com\opensymphony\xwork2\validator\validators\default.xml
    validate.xml编写所需的dtd文件：
        xwork-core-2.3.37\xwork-validator-1.0.3.dtd

<h4>步骤:</h4>

    1.先找到xwork-core-2.3.37包下的dtd约束,复制其中一段注释进入validate.xml文件中：
      <!DOCTYPE validators PUBLIC
      		"-//Apache Struts//XWork Validator 1.0.3//EN"
      		"http://struts.apache.org/dtds/xwork-validator-1.0.3.dtd">
    2.1 在action包下写Action类名-validation.xml --全局配置
        <validators>
            <validator type="requiredstring">
            	<param name="fieldName">username</param>
            	<message>用户名不能为空，xml</message>
            </validator>
        </validators>
    2.2 在action包下写Action类名-别名(对应唯一method)-validation.xml --局部配置
        位置其名称规范举例：
        /StrutsDemo/src/cn/cxd/actions/ActionDemo-actiondemo-validation.xml
        提示：
            其中，别名对应的是struts.xml中action标签的name属性，
            每一个action会有不同的method方法,我们为action设置name别名属性，
            在意图访问特定方法时，实际上只是在访问不同的action方法
            而访问不同的action使用的就是其不同的别名,因此验证的命名为：
            Action类名-别名(对应唯一method)-validation.xml
    3.使用标签，完成validate.xml文件设置验证
        1.参考官方提供的validator验证器(在xwork的default.xml中定义),选择需要的验证器类型
        2.查看对应验证器类的源码,并查其父类,找到需要填写的私有变量,将来用于参数设置
        3.使用指定格式填写<validate>,<param>,<message>标签


<h5>3.1 参考官方提供的validator验证器(在xwork的default.xml中定义),选择需要的验证器类型</h5>

    <validators>
        <validator name="required" class="com.opensymphony.xwork2.validator.validators.RequiredFieldValidator"/>
        <validator name="requiredstring" class="com.opensymphony.xwork2.validator.validators.RequiredStringValidator"/>
        <validator name="int" class="com.opensymphony.xwork2.validator.validators.IntRangeFieldValidator"/>
        <validator name="long" class="com.opensymphony.xwork2.validator.validators.LongRangeFieldValidator"/>
        <validator name="short" class="com.opensymphony.xwork2.validator.validators.ShortRangeFieldValidator"/>
        <validator name="double" class="com.opensymphony.xwork2.validator.validators.DoubleRangeFieldValidator"/>
        <validator name="date" class="com.opensymphony.xwork2.validator.validators.DateRangeFieldValidator"/>
        <validator name="expression" class="com.opensymphony.xwork2.validator.validators.ExpressionValidator"/>
        <validator name="fieldexpression" class="com.opensymphony.xwork2.validator.validators.FieldExpressionValidator"/>
        <validator name="email" class="com.opensymphony.xwork2.validator.validators.EmailValidator"/>
        <validator name="url" class="com.opensymphony.xwork2.validator.validators.URLValidator"/>
        <validator name="visitor" class="com.opensymphony.xwork2.validator.validators.VisitorFieldValidator"/>
        <validator name="conversion" class="com.opensymphony.xwork2.validator.validators.ConversionErrorFieldValidator"/>
        <validator name="stringlength" class="com.opensymphony.xwork2.validator.validators.StringLengthFieldValidator"/>
        <validator name="regex" class="com.opensymphony.xwork2.validator.validators.RegexFieldValidator"/>
        <validator name="conditionalvisitor" class="com.opensymphony.xwork2.validator.validators.ConditionalVisitorFieldValidator"/>
    </validators>

</h5>3.2 查看对应验证器类的源码,并查其父类,找到需要填写的私有变量,将来用于参数设置</h5>

    RequiredStringValidator 继承 FieldValidatorSupport父类 
    其中 fieldName 就是我们RequiredStringValidator需要的参数

    public abstract class FieldValidatorSupport extends ValidatorSupport implements FieldValidator {
        private String fieldName;    
        private String type;
        ...
<h5>3.3 使用指定格式填写<validate>,<param>,<message>标签</h5>

    格式1：
        <validator type="requiredstring">
    		<param name="fieldName">username</param>
    		<message>用户名不能为空</message>
    	</validator>
    格式1提示：
        1.type,从官方提供的validator验证器中选择type
        2.param,从对应验证器类的源码中选择其 一个或多个 私有变量
            其中filedName 对应我们 bean类的 私有变量 的名称
            也就是需要验证器去验证其语法的 变量名称
        3.message,错误信息

    格式2：
        <field name="rePassword">
    		<field-validator type="expression">
    			<param name="expression">
    				<![CDATA[password == rePassword]]>
    			</param>
    			<message>两次密码不一致</message>
    		</field-validator>
    	</field>
    格式2提示：
        1.name，对应我们 bean类的 私有变量 的名称
            也就是需要验证器去验证其语法的 变量名称
        2.type,从官方提供的validator验证器中选择type
        3.param,从对应验证器类的源码中选择其 一个或多个 私有变量
            可以使用![CDATA[]]验证表达式,仅是验证表达式,而无需判断条件
            不再需要fieldName这个参数，因为<field>标签中已经填写了name属性
        3.message,错误信息

    注意：
        1.有关expression的错误信息是在actionError中，不在FieldError，
          因此不会自动显示,而是显示在：
              errorMessages	[两次密码不一致]
              actionErrors	[两次密码不一致]
           而不是显示在：
              fieldErrors	{password=[密码长度在6-12之间]}
        2.如果想要显示出来,可以在jsp文件中使用<s:actionerror/>：
              用于在jsp中显示出actionerror信息
        3.对于Expression,regex,url这些type的变量参数可以：
            可以使用![CDATA[]]验证表达式,仅是验证表达式,而无需判断条件




