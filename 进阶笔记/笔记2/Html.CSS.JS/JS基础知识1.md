JS基础知识1：
	数据类型，对象


前置知识：使JavaScript和html能一起使用：
	
	1.外部关联
		1.定义外部js文件，js文件中可以使用html标签和自身的语法
		2.在html文件中的任意位置中 使用script标签引入
		举例：
			<script type="text/javascript" src="../JavaScript/help.js"></script>
	2.内部关联
		1.在html文件中的任意位置中 使用script标签，并在标签中写入js代码


数据类型：
	
	1.number  (包括整数和小数,NaN)   "123" ++ ;   --->  number
									"123vd" ++;   ----> NaN

	2.string  （可以是空串）		"123" + 1 ;  ---> 1231 string

	3.null	   (占位符)			var a = null;   ---> a:object类型
	
	4.boolean					var a = true;

	5.undefined (未定义)		var a = undefined; var b ;  ---> a,b都是undefined


对象：
	
	1.function 	方法对象
		创建方法对象：
			function testFunc(形参s) {
				document.write("func() hello everyBody");
			}
		属性：
			length: 形参的个数，而不是真正接收的参数的个数
		特点：
			1.不需要形参类型，返回值类型
			2.仅使用方法对象名(更像是变量)并传递参数来调用
			3.内置有arguments数组对象,来处理实际真正接收的参数
	
	2.Array		数组对象
		创建数组对象：
			var arr = new Array(元素s);
			var arr = new Array(size);
			var arr = [元素s];
		属性：
			length: 数组的长度
		方法：
			join("xx") 使用xx来拼接整个数组，返回字符串
			push(xx)   添加xx元素,返回新的长度
		特点：
			数组里可以放各种数据类型，长度也是可变的。
	3.Date		日期对象
		创建日期对象：
			var date = new Date();
		方法：
			getTime()		返回毫秒数（190.1.1-）
			toLocaleString()返回本地时间字符串

	4.Math		数学对象	
		不必创建，相当于内部都是static方法
		方法：
			random()	返回[0,1)
			ceil(x)		向上舍入
			floor(x)	向下舍入
			round(x)	舍四进五
		属性：
			PI，LOG2E // 返回以 2 为底的 e 的对数（约等于 1.414）
	
	5.Reg      正则对象
		创建：
			var 正则对象名称 = /  正则表达式  /;
		方法：
			test("目标串")		返回匹配结果

		document.write(正则对象名称.test("xxx"));		

	6.Global	全局对象
		不必创建，全局直接使用
		方法：
			parseInt("nnnnxxx");  
				举例： 
				 	var str = "123adefe";
					document.write(parseInt(str)+1);

				//从字符串开始解析成数字，非数字则停止，无法解析成数字，返回NaN
			encodeURI("xx")			//对字符串进行编码
			decodeURI("xx");		//对已编码字符串进行解码
			isNaN(xxx);				//判断xxx是否为NaN,NaN不可以用==






参考资料：
	
		https://www.w3school.com.cn/

	


