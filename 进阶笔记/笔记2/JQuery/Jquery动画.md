Jquery动画(调用方式:jq对象.function())
    
    
    1.折叠隐藏显示(纵轴)
    2.滑动隐藏显示(纵轴)
    3.淡入淡出隐藏显示
        ：其实就是使用不同的方式调整标签的属性style_display：none
        隐藏后html上不再显示，也就无法点击了，位置也让出了
        但是实际上该标签代码还是存在的


通用参数：

    speed:slow,normal,fast,毫秒值
    easing:默认swing,可用匀速 linear
    fn:(finish)方法调用后执行什么方法

注意：

    toggle就是toggle,
    不是 显示时调用自定义的显示函数，
         隐藏时调用自定义的隐藏函数

1.折叠隐藏显示(纵轴)

    show(speed,[easing],[fn])
    hide(speed,[easing],[fn])
    toggle(speed,[easing],[fn])

举例：后两种同理使用
    
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
    <input type = "button" value = "2秒隐藏3秒出现" id = "Trick">
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
    
    $("#Trick").click(function(){
    	setTimeout(() => {
    		$("#InWords").hide("slow");
    	}, 2000);
    	setTimeout(() => {
    		$("#InWords").show("slow");
    	}, 5000);
    })
    
    </script>
    </html>
    
2.滑动隐藏显示(纵轴)

    slideDown(speed,[easing],[fn])
    slideUp(speed,[easing],[fn])
    slideToggle(speed,[easing],[fn])

3.淡入淡出隐藏显示

    fadeIn(speed,[easing],[fn])
    fadeOut(speed,[easing],[fn])
    fadeToggle(speed,[easing],[fn])
