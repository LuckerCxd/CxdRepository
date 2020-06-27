Jquery:

    1.基本选择器
    2.层次选择器
    3.类选择器
    4.属性选择器
    5.过滤选择器


1.基本选择器

    标签选择器：
        $("html标签名");  //选中所有匹配标签名称的元素
        $("div").css("backgroundColor","pink");
    id选择器：
        $("#属性id的值")；  //选中首个指定id的元素
        $("#div1").css("backgroundColor","pink");
    类选择器：
        $(".class的属性值")； //选中所有匹配的class属性的元素
        $(".eff").css("backgroundColor","pink");
举例：
    
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="JS/jquery-3.3.1.min.js"></script>
    </head>
    <body>
    <!-- class属性值既包含了mini，又包含了eef，二者皆是其属性-->
    <div id = "div1" class = "mini eef">div1</div>
    <div id = "div1" class = "mini eef">div2</div>
    
    <script type="text/javascript">
    $(".eef").css("backgroundColor","pink");
    </script>
    </body>
    </html>

2.层次选择器：（A,B元素可以是被基本选择器选中的元素）

    1.后代选择器：
        $("A B");  
        //A元素的满足条件的所有子元素 B元素 及 
                B元素下所有同样满足条件的后代元素都被选中
    2.子选择器：
        $("A > B");
        //A元素的满足条件的所有子元素 B元素 被选中

举例：
    
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="JS/jquery-3.3.1.min.js"></script>
    <style type="text/css">
    
    #small{
    	width : 100px;
    	height : 100px;
    	margin :50px;    /*1.所有 4 个外边距都是 50px*/
    }
    
    #big{
    	width : 200px;
    	height : 200px;
    	/*box-sizing:border-box;  
    	padding : 50px;   /*2.所有 4 个内边距都是 50px*/
    	/*
    	固定盒子大小，避免调整内边距影响盒子的大小
    	*/
    	margin : 100px;
    }
    
    #Big{
    	width : 400px;
    	height : 400px;
    	
    }
    
    
    </style>
    </head>
    <body>
    
    <div id = "Big" class = "mini eff">
    	<div  id = "big" class = "mini eff">
    		<input type="button" id = "small" class = "mini eff" value ="登录">
    	</div>
    </div>
    
    </body>
    <script type="text/javascript" >
    $(function () {
    	
    	$("div").css("border","1px solid red");
    	$("span").css("border","1px solid red");
    })
    
    $(function () {
    	
    	//后代选择器
    	//$("#big #small").css("backgroundColor","black");
    	//$("#Big .eff").css("backgroundColor","black");
    
    	//子选择器
    	$("#Big > div").css("backgroundColor","black");
    })
    
    
    </script>
    </html>

3.属性选择器：（A,B元素可以是被基本选择器选中的元素）

    1.属性名称选择器
        $("A[属性名]");  //选中包含该属性的元素class id...
    2.属性选择器
        $("A[属性名='xx']"); //选中属性名与属性值完全匹配的元素
    3.复合属性选择器
        $("A[属性名='xx'][属性名='xx']") //选中各个属性都能匹配上的元素

举例：

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="JS/jquery-3.3.1.min.js"></script>
    <style type="text/css">
    
    #small{
    	width : 100px;
    	height : 100px;
    	margin :50px;    /*1.所有 4 个外边距都是 50px*/
    }
    
    #big{
    	width : 200px;
    	height : 200px;
    	/*box-sizing:border-box;  
    	padding : 50px;   /*2.所有 4 个内边距都是 50px*/
    	/*
    	固定盒子大小，避免调整内边距影响盒子的大小
    	*/
    	margin : 100px;
    }
    
    #Big{
    	width : 400px;
    	height : 400px;
    	
    }
    
    
    </style>
    </head>
    <body>
    
    <div id = "Big" class = "mini eff">
    	<div  id = "big" class = "mini eff">
    		<input type="button" id = "small" class = "mini eff" value ="登录">
    	</div>
    </div>
    
    </body>
    <script type="text/javascript" >
    $(function () {
    	
    	$("div").css("border","1px solid red");
    	$("span").css("border","1px solid red");
    })
    
    $(function () {
    
    	//属性选择器,完全匹配上class='mini eff',不能是eff或mini
    	//$(".mini[id='small']").css("backgroundColor","black");
    	
    	//复合属性选择器
    	$(".mini[class='mini eff'][id='small']").css("backgroundColor","black");
    })
    
    
    </script>
    </html>

4.过滤选择器(A,B元素可以是被基本选择器选中的元素,下标从0开始)

    1.首元素选择器
        $("A:first"); //选中A元素(复数s)的第一个元素
    2.尾元素选择器
        $("A:last"); //选中A元素(复数s)的最后元素
    3.非元素选择器
        $("A:not(其他选择器[而不是元素])")
            //选中A元素(复数s)不具备或不是的其他元素
    4.偶数选择器
        $("A:even");//选中A元素(复数s)的偶数下标元素
    4.奇数选择器
        $("A:odd");//选中A元素(复数s)的奇数下标元素
    6.等于索引选择器
        $("A:gt(index)");//选中A元素(复数s)的下标等于x元素
    6.大于索引选择器
        $("A:gt(index)");//选中A元素(复数s)的下标大于x元素
    6.小于索引选择器
        $("A:gt(index)");//选中A元素(复数s)的下标小于x元素
    7.标题选择器：
        $(":header");   //选中header h1-h6的标题
举例：
    
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="JS/jquery-3.3.1.min.js"></script>
    <style type="text/css">
    
    #small{
    	width : 100px;
    	height : 100px;
    	margin :50px;    /*1.所有 4 个外边距都是 50px*/
    }
    
    #big{
    	width : 200px;
    	height : 200px;
    	/*box-sizing:border-box;  
    	padding : 50px;   /*2.所有 4 个内边距都是 50px*/
    	/*
    	固定盒子大小，避免调整内边距影响盒子的大小
    	*/
    	margin : 100px;
    }
    
    #Big{
    	width : 400px;
    	height : 400px;
    	
    }
    
    
    </style>
    </head>
    <body>
    
    <h1>Don't Mention it</h1>
    
    
    <div id = "Big" class = "mini eff">
    	<div  id = "big" class = "mini eff">
    		<input type="button" id = "small" class = "mini eff" value ="登录">
    	</div>
    </div>
    
    </body>
    <script type="text/javascript" >
    $(function () {
    	
    	$("div").css("border","1px solid red");
    	$("span").css("border","1px solid red");
    })
    
    $(function () {
    	
    	//首元素选择器
    	//$("#Big:first").css("backgroundColor","black");
    	
    	
    	//尾元素选择器
    	//$("div:last").css("backgroundColor","black");
    	
    	//非元素选择器1,2
    	//$("div:not(div:last)").css("backgroundColor","black");
    	//$("div:not(.en)").css("backgroundColor","black");
    	
    	//偶数选择器
    	//$("#small:even").css("backgroundColor","black");
    	
    	//奇数选择器
    	//$("div:odd").css("backgroundColor","black");
    	
    	//等于索引选择器
    	//$("input:eq(0)").css("backgroundColor","black");
    	
    	//大于索引选择器,gt(0)->1
    	//$("div:gt(0)").css("backgroundColor","black");
    	
    	//小于索引选择器,lt(1) ->0
    	//$("input:lt(1)").css("backgroundColor","black");
    	
    	//标题选择器
    	$(":header").css("backgroundColor","pink");
    })
    
    </script>
    </html>

5.表单过滤选择器：

    1.可用元素选择器(input)
        $("input[type='text']:enabled");
    2.不可用元素选择器（input）
        $("input[type='text']:disabled");
    3.选中选择器（复选框 checkbox）
        $("input[type='checkbox']:checked");
    4.选中选择器(单选框select有multiple选项)
        $("select option:selected");

举例：
    
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="JS/jquery-3.3.1.min.js"></script>
    <style type="text/css">
    
    input{
    	width : 100px;
    	height : 40px;
    }
    
    </style>
    </head>
    <body>
    
    <input type="text" disabled="disabled" value="不可用1">
    <input type="text" disabled="disabled" value="不可用2">
    <input type="text"  value="可用1">
    <input type="text"  value="可用2">
    
    <input type="checkbox"  name = "cxk">唱
    <input type="checkbox" name = "cxk">跳
    <input type="checkbox"   name = "cxk">Rap
    <input type="checkbox"  name = "cxk">篮球
    
    <select multiple="multiple">
    <option>唱</option>
    <option>跳</option>
    <option>Rap</option>
    
    
    </select>
    
    
    <input type="button" id = "length" value="长度">
    </body>
    <script type="text/javascript" >
    
    
    $(function () {
    	//可用元素选择器
    	//$("input[type='text']:enabled").val("aaa");
    	//不可用元素选择器
    	//$("input[type='text']:disabled").val("aaa");
    	
    	//选中选择器
    	//$("#length").click(function () {
    		//alert($("input[type='checkbox']:checked").length);
    	//})
    	
    	//选中选择器
    	$("#length").click(function () {
    		alert($("select option:selected").length);
    	})
    })
    
    
    </script>
    </html>
