BootStrap:

	BootStrap内容使用<div>包裹就可以实现到页面的整体控制，
	而且可以添加左浮动等其他属性<div style = "float: left;">

前置知识：

	0.https://v3.bootcss.com/getting-started/    //下载BootStrap
	1.将bootstrap(css,fonts,js文件夹放入工程)
	2.形成基本框架 

基本框架：
	
	<!DOCTYPE html>
	<html lang="zh-CN">
	  <head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	    <title>Bootstrap 101 Template</title>
	    <link href="../css/bootstrap.min.css" rel="stylesheet">
		<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
	    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
	    <!--[if lt IE 9]>
	      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
	      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
	    <![endif]-->
	  </head>
	  <body>
	    <h1>你好，世界！</h1>
	    </div></div>
	    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	    <script src="../js/bootstrap.min.js"></script>
	  </body>
	</html>

响应式布局：依赖于栅格系统，一行有12个格，元素可以指定占据各自数

	1.定义容器：container(留白) container-fluid:(全占用)
	2.容器内定义行：row
	3.行内定义元素：col  
		指定设备：col-xs手机 col-sm 平板 col-md笔记本 col-lg大屏幕
		
	
CSS样式：按钮，图片，表格，表单
	
	按钮：
		default:按钮的背景色为白.还有danger...
		<a class="btn btn-default" href="#" role="button">Link</a>
		<button class="btn btn-default" type="submit">Button</button>
		<input class="btn btn-default" type="button" value="Input">
		<input class="btn btn-default" type="submit" value="Submit">
	
	图片：
		//圆形..自适应屏幕..边框
		<img src="D:\壁纸\new壁纸\165424.jpg" alt="..." class="img-responsive img-circle img-thumbnail" >
		<img src="..." alt="..." class="img-thumbnail">  
	    <img src="..." alt="..." class="img-rounded"> //方形
		<img src="..." alt="..." class="img-circle">
		
	表格：
		//带边框：table bordered 鼠标悬停变色：table-hover ..
		<table class="table table-bordered  table-hover">
		<tr>
			<th> 编号</th>
			<th> 姓名</th>
			<th> 年份</th>
		</tr>
		
		<tr>
			<td> 1</th>
			<td> 李紫薇</th>
			<td> 1998</th>
		</tr>	
		<tr>
			<td> 2</th>
			<td> 陈夏将</th>
			<td> 1998</th>
		</tr>	
		<tr>
			<td> 3</th>
			<td> 米其林</th>
			<td> 1928</th>
		</tr>	
		
		</table>

	表单：
		//表单项加class="form-control"

	  <form class = "form-horizontal">
	  <div class="form-group">
	    <label for="exampleInputEmail1" class = "col-sm-2 control-label">Email address</label>
	   	<div class = "col-sm-5">
	    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
	  	</div>
	  </div>
	  <div class="form-group">
	    <label for="exampleInputPassword1" class = "col-sm-2 control-label">Password</label>
	     <div class = "col-sm-5">
	    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
	 	</div>
	  </div>
	  
	  <div class="form-group">
	    <label for="exampleInputFile"  class = "col-sm-2 control-label" >File input</label>
	    <div class = "col-sm-5">
	    <input type="file" id="exampleInputFile">
	    <p class="help-block">Example block-level help text here.</p>
	     </div>
	  </div>
	  
	  <div class="checkbox"> 
	  	<div class = "col-sm-2"></div>    <!-- 单选框在中间 -->
	    <label class = "control-label">
	      <input type="checkbox" > Check me out
	    </label>
	  </div>
	  
	  <div class = "col-sm-2"></div>
	  <button type="submit" class="btn btn-default">Submit</button>
	</form>

![]("form.jpg")


组件：导航条，分页条
	
	导航条：

	<nav class="navbar navbar-inverse">
  	<div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    
    <!-- 1.定义可缩小后的按钮形状 -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <!-- 2.定义线条数目3条  ...-->
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    
    
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          	</ul>
       	 </li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>

	分页条：
		 <nav aria-label="Page navigation">
	  <ul class="pagination">
	    <li>
	      <a href="#" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    <li><a href="#">1</a></li>
	    <li><a href="#">2</a></li>
	    <li><a href="#">3</a></li>
	    <li><a href="#">4</a></li>
	    <li><a href="#">5</a></li>
	    <li>
	      <a href="#" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>
	</nav>
			

js插件 ： 轮播图

	   <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	  <!-- Indicators -->
	  <ol class="carousel-indicators">
	    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	  </ol>
	
	  <!-- Wrapper for slides -->
	  <div class="carousel-inner" role="listbox">
	    <div class="item active">
	      <img src="../pictures/test (1).jpg" alt="...">
	      <div class="carousel-caption">
 	<!-- 可填入备注信息在上面，会一起显示 -->
	      </div>
	    </div>
	    <div class="item">
	      <img src="../pictures/test (2).jpg" alt="...">
	      <div class="carousel-caption">
	      </div>
	    </div>
	    
	 <div class="item">
	      <img src="../pictures/test (3).jpg" alt="...">
	      <div class="carousel-caption">
	      		
	      </div>
	    </div>
	     <div class="item">
	      <img src="../pictures/test (4).jpg" alt="...">
	      <div class="carousel-caption">
	      
	      </div>
	    </div>
	     <div class="item">
	      <img src="../pictures/test (5).jpg" alt="...">
	      <div class="carousel-caption">
	      	 
	      </div>
	    </div>
	  </div>
	
	  <!-- Controls -->
	  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>

参考教程：
	
		https://v3.bootcss.com/getting-started/



