html基础知识：

	1. 文件标签：构成html最基本的标签
	2. 文本标签：和文本有关的标签
	3. 图片标签：定义图像<img>
	4. 列表标签：定义列表
	5. 链接标签：定义超链接
	6. 表格标签：定义表格<table></table>
	7. 块标签：span div     需CSS
	8. 语义化标签：header footer aside 需CSS
	9. table布局	
	10. 表单标签：定义表单
	11.表单项标签：定义表单项（在表单中使用）	

1. 文件标签：构成html最基本的标签

	    1. <!DOCTYPE html> 	定义文档类型 为 html
    	2. <html> </html>   根标签。
    	3. <head> </html>	头标签,用于指定文档的一些属性或是引入外部资源
    		属性：
    		1. <meta charset="UTF-8">
    		2. <title>新建网站</title>
    	4. <title></title>	标题标签,用于指定文档的标题名称。
    	5. <body> </body>	体标签。
	
2. 文本标签：和文本有关的标签

	    0.<!--...-->...注释
    	举例：<!-- 注释内容 -->
    
    	1.<h1> ~ <h6>   从1到6标题字变小
    	举例：
    		<h1>dddddd</h1>
    			<h2>eeee</h2>
    			<h3>dddd</h3>
    	2.<br/> 			换行
    		举例：
    			<font>dddddddd<br/>kkkkkkk</font>
    	3.<p> </p>			段落
    		举例：
    			<p>dddddddddh和嘿嘿dddddkkkkkkkddddddddkkkkkkkkkkkkkk</p>
    			<p>ddddddd啦啦啦啦啦啦ddddddkkkkkkkkkk</p>
    	4.<hr/>				划线
    		举例:
    			<hr size="10" width="50%" align="left" color="yellow" />
    		说明：
    			属性：size 厚度，width 长度 align 对齐方式 color 颜色
    	5.<b></b>			加粗
    		举例：
    			<font color = "red" size="10"><b>绿色灯光</b></font>
    	6.<i></i>			斜体
    		举例：
    			<b>绿色<i>灯光</i></b>
    	7.<font></font>		字体标签
    		举例：
    			<font color="green" size="10" face="宋体">少林功夫好野</font>
    		说明：
    			属性：color 颜色(颜色拾取器 #xxxxxx , green blue red) 
    				 size 字体大小 
    				 face 文本字体
    	8.<center></center> 文本居中 
    		举例：
    			<center ><font color="green" size="10" face="宋体">少林功夫好野</font></center>
    			
3.图片标签

	<img src = "">        定义图像
	举例：
		<img alt="xxxx" src="..\pictures\select.jpg" height="500" width="200" border="20">
	说明：
		属性: src 路径 height 高度 width 宽度 border 边界厚度

4.列表标签
	
	有序列表:
		ol（order list）li(list item)   有编号
		举例：
			<ol type = "i" start = 5>
			<li>唱</li>
			<li>跳</li>
			<li>rap</li>	
			</ol>
		说明：type:编号类型 start：起点值

	无序列表:
		ul li    					无编号
		<ul type = "square">
		<li>唱</li>
		<li>跳</li>
		<li>rap</li>	
		</ul>
		说明： type 标记类型
			
		

5.链接标签

	<a></a>
	举例：
	<a href = "https://www.baidu.com" target="_blank">click</a>
	说明：   href 资源定位符  url
			target 打开方式 _self _blank _parent _top
			

	
6.表格标签					定义表格

 	<table>
		<caption>		<!-- 居中的表格标题 -->
		<tr>			<!-- 表行 -->
		<th>    	 	<!-- 表头单元格 -->
		<td>			<!-- 表单元格 -->			 
	</table>

	举例：
		<table border="5" width = "50%" align = "center" cellpadding = "0" cellspacing = "0" bgcolor = "red">
		<caption>公民表</caption>
		<tr>
			<th > 姓名</th>
			<th > 语文 </th>
			<th > 数学 </th>
		</tr>
		
		<tr bgcolor="#8DC07C" align = "center">
			<td rowspan="2"> 李志强 </td>
			<td> 女 </td>
			<td> 23 </td>
		</tr>
		
		<tr align = "center">
			<td > 女 </td>
			<td> 52 </td>
		</tr>

	说明：
		table：	
			 border 边框
			 align 表格的位置 
			 cellspacing 单元格之间留白 为0则仅一条线
			 bgcolor 背景颜色
		tr:
			bgcolor 行的背景色
			align	内容对齐方式
		td:
			colspan 列合并单元格(变长)
			rowspan 行合并单元格(变厚)
			


7.块标签  		需CSS

	<span></span>   一行显示，连续2个span也作一行
	<div></div>  	占一行，会换行

	举例：
		<span>hello</span>
		<div>hello</div>

8.语义化标签		需CSS

	header 页眉 footer 页脚 aside ... 仅html没有什么区别

9.table布局
	
	1.一行一个单元格 <tr><td></td></tr>
	2.一行多个单元格(嵌套table)
		<tr><td> <table></table> </td></tr>

10.表单标签
	
	<form action = "接收提交信息的url" method = "xx"></form>
	举例：
		<form action=?" method="get">
		用户名 <input type = "text" name="username">
		<br>
		密码 <input type = "password" name="password">
		<br>
		<input type="submit" value ="登录">
	说明：
		1.input标签要定义name属性，才能类似key-value被提交
		2.<input type="submit" value ="登录"> 
			当点击登录时，信息会被提交到action的位置
		3.get方法其请求参数会在地址栏,参数大小受限制
		  post则不会，较安全,参数大小不受限制

11.表单项标签：定义表单项（在表单中使用）
		
	1.<input>	定义输入
		0.input标签要定义name属性，才能类似key-value被提交
		1.type：text，password,radio单选框,checkbox复选框...	
				button普通按钮 submit提交 image图片提交按钮			
				date,email,number

			text:
				用户名 <input name="username" placeholder="username">
				<label for = "name">用户名</label> <input name="username" id = "name">

				说明：placeholder:灰色的提示词，会自动清空
					使用<lable>,设置for属性 与 <label>id属性 一致时，
						点击label区域会对应到input输入框

			password:
				密码 <input type = "password"  placeholder="password">

			radio:
				性别 <input type = "radio" name = "sex" value = "man"> 男
				<input type = "radio" name = "sex" value = "women"> 女
				
				说明：1个radio 1个框，多个radio的name属性一致时，才有单选功能
					且每一个radio得有各自的value，用于提交上去
			
			checkbox:
				特长<input type = "checkbox" name = "character" value = "read">阅读
				<input type="checkbox" name="character" value = "play" checked>玩
				<input type ="checkbox"name="character" value ="music">音乐
					
				说明：1个checkbox 1个框，多个checkbox的name属性一致时，
					才有多选功能
					且每一个checkbox得有各自的value，用于提交上去
	
			submit:
				<input type="submit" value ="登录">
				说明：value为按键名称,点击就会提交
			
			button:
				<input type="button" value = "Button">

			image:图片提交按钮
				<input type = "image" src="..\pictures\base.jpg">
				说明：点击图片就会提交表单信息
			
			date,datetime-local		
				上次登录时间<input type="datetime-local" name = "lasttime">
				上次登录时间<input type="date" name = "lasttime">
				
			email
				密保邮箱<input type="email" name = "email">

			number
				手机号<input type="number" name = "phone">

	2.<select>	下拉列表
			
		<select name = "province">
		<option value="0"> -- 请选择 -- </option>
		<option value="1"> 1班 </option>
		<option value="2"> 2班 </option>
		<option value="3"> 3班 </option>
		<option value="4"> 4班 </option>
		</select>
		说明：		
			1.有name才能被提交
		    2.<option>选择后提交的是value的值,指定列表项，
				便于使用,而不必找对应字符集编码。


	3.<textarea>文本域
		<textarea rows="4" cols="100" placeholder="请输入自我描述" name = "discribe"></textarea>
		说明：row 行数 cols 列数 placeholder 提示 
		
特殊字符

 	实体名称对大小写敏感！
	显示结果	 描述		    实体名称	     实体编号
		 		空格			&nbsp;			&#160;
		<		小于号			&lt;		   &#60;
		>	    大于号	        &gt;	       &#62;
		&		和号			&amp;		    &#38;
		"		引号			&quot;			&#34;
		'		撇号 		    &apos; 			&#39;
		￠		分			&cent;			&#162;
		£		镑			&pound;			 &#163;
		¥		人民币/日元	  &yen;			  &#165;
		€		欧元			&euro;			&#8364;
		§		小节			&sect;			&#167;
		©		版权			&copy;			&#169;
		®		注册商标		&reg;		   &#174;
		™		商标			&trade;			&#8482;
		×		乘号			&times;			&#215;
		÷		除号			&divide;		&#247;


参考资料：
	
		https://www.w3school.com.cn/