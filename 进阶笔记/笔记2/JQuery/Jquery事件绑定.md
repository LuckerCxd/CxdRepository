Jquery事件绑定：
    
    1.jq对象.事件方法(回调函数)
    2.jq对象.on("事件名称",回调函数) 绑定
      jq对象.off("事件名称",回调函数) 解绑定
           off空参,则解除所有事件绑定
    3.jq对象.toggle(回调1,回调2..) 单击切换
        默认是组件被单击的事件,
        第一次执行回调1，第二次执行回调2
        最后循环继续回调1...回调2
        注意:
            1.在1.9后没有,需要jquery migrate插件
            2.对组件直接toggle，不要再click事件了
举例：
    
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src = "JS/jquery-3.3.1.min.js"></script>
    <script src = "JS/jquery-migrate-1.1.0.js"></script>
    </head>
    <body>
    <input type = "text" value = "输入入口码" id = "InWords">
    <input type = "button" value = "解绑" id = "OFF">
    <input type = "button" value = "单击切换" id = "TOGGLE">
    </body>
    <script type="text/javascript">
    
    //1.jq对象.事件方法(回调函数)
    $("#InWords").mouseover(function () {
    	alert("hello");
    });
    
    
    //2.jq对象.on("事件名称",回调函数) 绑定
    $("#InWords").on("mouseout",function () {
    	alert("byte");
    });
    
    //2.jq对象.off("事件名称",回调函数)解绑定 
    $("#OFF").on("click",function(){
    	$("#InWords").off();
    })
    
    
    $("#TOGGLE").toggle(function(){
    	$("#InWords").off();
    },function(){
    	$("#InWords").mouseover(function () {
    		alert("hello");
    	});
    	$("#InWords").on("mouseout",function () {
    		alert("byte");
    	});
    })
    
    
    </script>
    </html>

