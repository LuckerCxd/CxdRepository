JS基础知识2：
	BOM,DOM,事件

使用：
	
	用法1:  
	<a href="javascript:refreshCode();">
	<img src = "${pageContext.request.contextPath}/pictureServlet" title = "另一张" id = "anotherPict">
	</a>

	<script>
		function refreshCode(){
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

BOM:(Brower object model)
	
	Window:(如果不指定特定window对象，那么就是当前window对象)
		无需创建,可以使用window,也可以省略
		方法：
			1.alert("xxxx")         //提示弹窗，阻塞，只能确定，返回值undefined
			2.confirm("xxxx")		//提示弹窗，确定：返回true,取消：false
			3.window.close()		//关闭调用者窗口对象的窗口
			4.window.open("url")	//打开窗口，返回出窗口对象
			5.setTimeout(function 对象,millisec)   
				//millisec毫秒后，执行一次，返回id,用于clearTimeout()
			6.setInterval(function 对象,millisec) //间隔millisec毫秒，重复执行
				//millisec毫秒后，重复执行，返回id,用于clearIntercal()
			7.clearTimeout("id")	//取消指定setTimemout()
			7.clearInterval("id")	//取消指定setInterval()
		属性：
			1.history				//访问过的 URL
			2.location				//当前URL的信息
			3.Navigator				//浏览器的信息
			4.Screen				//客户端显示屏幕的信息
			5.document				//window.document.write()
	
	location：
		无需创建,使用location
		方法：
			location.reload()      使用location对象，刷新页面
		属性：
			href:   url			   设置url，跳转页面
		举例：
			var refresh = document.getElementById("refresh");
			refresh.onclick = function(){
				location.reload();
			}


	history:
		无需创建,使用history
		方法：
			go()				go(+n) : forward几个；go(-n) : back几个
			back()				可加载当前窗口的历史列表中的前一个URL(如果存在）
			forward()			可加载当前窗口的历史列表中的下一个URL(如果存在）
		属性：
			length				返回浏览器历史列表中的 URL 数量

DOM:(document object model)
	
	通过document获取element后，
	element对象可以是有通用的方法
	再加上本身标签 HTML的属性设置
	再加上元素的event属性设置,结合function对象，实现事件监听机制
	


	核心DOM:
		结点对象:Node (父节点.xxx)
		元素对象:Element
		文档对象:Document 
		属性对象:Attribute
		文本对象:Text
		注释对象:Comment

0.结点对象:Node (父节点.xxx)
	
	方法：
		createElement("html标签")
		appendChild(element)	为父节点添加element子元素	
		replaceChild(element)	为父节点用新节点替换子元素	
	属性：
		parentNode: 返回节点的父节点
	举例：
							html
		<br>
		<a href="javasrcipt:void(0);" id = "del" value = "删除 "> 删除yes </a>
		<br>
		<a href="javasrcipt:void(0);" id = "add" value = "添加 "> 添加yes </a>
		

							js
		var del = document.getElementById("del");
		del.onclick = function() {
			var big = document.getElementById("id1");
			var small = document.getElementById("id2");
			big.removeChild(small);
		}
	
		var add = document.getElementById("add");
		add.onclick = function() {
			var big = document.getElementById("id1");
			var small = document.createElement("div");
			small.setAttribute("id","id2");
			big.appendChild(small);
		}
		
			
1.文档对象:Document 

	无需创建,使用document
	方法：
		getElementById();返回拥有指定 id 的第一个对象的引用
		getElementsByTagName();  返回指定标签的元素对象们
		getElementsByClassName();返回指定类名的元素对象们
		getElementsByName();	返回指定Name的元素对象们
		createAttribute(name);
		createComment(data);
		createElement(name);
		createTextNode(data);

2.元素对象:Element

	通过document 获取和创建
	方法：
		removeAttribute();   //删除属性
		setAttribute();		 //设置属性

		element.setAttribute(attributename,attributevalue)
		element.removeAttribute(attributename)
	应用：
		1.通过使用innerHTML设置和获取标签体
		2.使用html元素对象的属性
		3.使用元素的style的属性设置，
		或是元素改其className,id等可以使用提前在css中设置好的信息
		都可以控制元素样式

			table = document.getElementById("table");
			table.style.border = "5px solid blue";

举例：
	
1.例子1

					html
	<img alt="null" src="../pictures/off.jpg" id = "light">
	<title id = "title">hello anyBody</title>

					js
	var light = document.getElementById("light");
	var title = document.getElementById("title");
	
	light.onmouseover = function(){
		
		light.src = "../pictures/on.jpg"
		title.text = "on"; 
	}
	
	light.onmouseout = function(){
		title.innerHTML = "off"; 
		light.src = "../pictures/off.jpg"
	}

2.例子2
		
	//<a> 的 href : "javasrcipt:void(0);"  才能停留在当前页面	
		
				html
	<div id = "id1">
	big<div id = "id2">small</div>
	</div>
	<br>
	<a href="javasrcipt:void(0);" id = "del" value = "删除 "> 删除yes </a>
	<br>
	<a href="javasrcipt:void(0);" id = "add" value = "添加 "> 添加yes </a>
	
				js
	var del = document.getElementById("del");
	del.onclick = function() {
		var big = document.getElementById("id1");
		var small = document.getElementById("id2");
		big.removeChild(small);
	}
	
	
	var add = document.getElementById("add");
	add.onclick = function() {
		var big = document.getElementById("id1");
		var small = document.createElement("div");
		small.setAttribute("id","id2");
		big.appendChild(small);
	}

3.例子3

				--css--
	table{
		border : 1px solid blue;
	}
	tr{
		border: 1px solid blue;
		text-align :center;	
	}
	
	td,th{
		width : 100px;
	}
			   --html--

	<table id = "table" align = "center">
	<tr>
		<th> 编号</th>
		<th> 姓名</th>
		<th> 年份</th>
	</tr>
	</table>
				
				--js--
	//添加Element_tr
	var tr = document.createElement("tr");
	
	//添加Element_td和TextNode_text,td添加text子节点
	var td1 = document.createElement("td");
	var text = document.createTextNode("9527");
	td1.appendChild(text);
	
	var td2 = document.createElement("td");
	var text2 = document.createTextNode("李佳");
	td2.appendChild(text2);
	
	var td3 = document.createElement("td");
	var text3 = document.createTextNode("3years");
	td3.appendChild(text3);
	
	//tr添加td子节点
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	
	//table添加tr子节点
	table = document.getElementById("table");
	table.append(tr);			
		
4.例子4	  	
	
				--css--
	table{
		border : 1px solid blue;
	}
	tr{
		border: 1px solid blue;
		text-align :center;	
	}
	
	td,th{
		width : 100px;
	}
			   --html--

	<table id = "table" align = "center">
	<tr>
		<th> 编号</th>
		<th> 姓名</th>
		<th> 年份</th>
	</tr>
	</table>	
			--js--
	table = document.getElementById("table");
	table.innerHTML += "<tr><td>9527</td><td>吴昊</td><td>6years</td> </tr>"


事件：

	（基于元素):onclick单击，ondblclick双击,onblur失去焦点,
	 onmousedown(可接受event形参)鼠标按下，
	 onload加载完成(让window来做,不使js出现空指针异常)
	 onkeydown(可接受event形参)键盘按下，
	 onchange域的内容被改变(select标签)
	 onsubmit提交(表单form)
	
	

举例：//仅对body中的元素监听，而不是全部的空白区域。

例子1：onmousedown()

	<body id = "body">
			...
	</body>


	var body = document.getElementById("body");
	body.onmousedown = function(event){
		var btnNum = event.button;
		if (btnNum==2) {
		  alert("您点击了鼠标右键！")
		  }
		else if(btnNum==0) {
		  alert("您点击了鼠标左键！")
		  }
		else if(btnNum==1) {
		  alert("您点击了鼠标中键！");
		  }
		else{
		  alert("您点击了" + btnNum+ "号键，我不能确定它的名称。");
		  }
	}



例子2：鼠标移动,获取光标，并显示
	
	<div id = "xy"></div>
	<img src="D:\壁纸\new壁纸\165424.jpg" alt ="jpg" id = "jpg">

	window.onload = function(){
		var xy = document.getElementById("xy");
		var text = document.createTextNode("");
		xy.append(text);
		var jpg = document.getElementById("jpg");
		
		jpg.onmousemove = function (event){
		x=event.clientX;
		y=event.clientY;
		text.data = "X 坐标: " + x + ", Y 坐标: " + y;
		}
	}

例子3：onsubmit,阻止提交
		
			--html--
	<form action="#" method="get" id = "form">
	<label for = "username">用户名: </label><input name = "username" id = "username" placeholder="用户名"><br>
	<input  type = "submit" value = "提交表单">
	</form>
			
			--js--
	window.onload = function(){
		var form = document.getElementById("form");
		form.onsubmit = function() {
			return false;
		}
	}


参考资料：
	
		https://www.w3school.com.cn/
