JS_JQ综合使用二“全选以及不可按键”:


注意：
    
    1.jq对象.toggle(回调1,回调2..) 单击切换
        默认是组件被单击的事件,
        第一次执行回调1，第二次执行回调2
        最后循环继续回调1...回调2
        注意:
            1.在1.9后没有,需要jquery migrate插件
            2.对组件直接toggle，不要再click事件了
    2.获取设置属性还是使用attr(),prop()
      而设置样式则是使用css()

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src = "JS/jquery-3.3.1.min.js"></script>
    <script src = "JS/jquery-migrate-1.1.0.js"></script>
    <style type="text/css">
    
    input[type="button"]{
    	width:200px;
    	height:60px;
    
    }
    </style>
    </head>
    <body>
    
    <!-- 1.全选和全不选 -->
    <input type = "checkbox" name = "hobby" id = "allDone">ALL<br/>
    <input type="checkbox" name = "hobby" value = "sing" >唱<br/>
    <input type="checkbox" name = "hobby" value = "jump">跳<br/>
    <input type="checkbox" name = "hobby" value = "rap">Rap<br/>
    <input type="checkbox" name = "hobby" value = "basketball">篮球<br/>
    
    
    <!-- 2.按钮可用和不可用 -->
    
    <input type="button" value = "初始可选" id = "Button">
    <input type="button" value = "设置可不可选" id = "setStates">
    
    
    
    
    </body>
    
    <script type="text/javascript">
    
    $("#allDone").click(function(){
    	var hobbies = $("input[name='hobby']");
    	hobbies.each(function () {
    		$(this).prop("checked",$("#allDone").prop("checked"));
    	})
    });
    
    $("#setStates").toggle(function () {
    	$("#Button").prop("disabled",true);
    	$("#Button").val("不可选");
    	
    },function(){
    	$("#Button").prop("disabled",false);
    	$("#Button").val("可选");
    });
    
    </script>
    </html>