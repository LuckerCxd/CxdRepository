JQuery基础知识:

    本质是JS框架，引入后可以简化JS编写
    
    1.在Html中<head>标签中添加：
        <script src="JS/jquery-3.3.1.min.js"></script>
    
    2.$("#div1");   
        //div1为标签的id
        //类似CSS样式选择器,或是JS中的getElementById();
    
    3.JS和JQuery对象并不通用
        JS的innerHTML  <-----> JQuery的html()
    
        JQuery的html()：
        而不用像getElements的JS对象那样for遍历设置innerHTML
        html(vlaue):
         空参时(value===undefine)返回首元素的innerHTML
         非空参时内部for循环设置所有元素的innerHTML,并返回value
                
    
    
    4.JS和JQuery对象的获取：
        使用$(xx) 获取的是JQuery对象
        使用document.getElementxByxxx() 获取的是JS对象
    
    5.JS和JQuery对象的转换：
        js --> jq : $(JS对象);  
    
        这里的jq对象是object的$("div") //获取所有div标签
        jq --> js : jq.get(index) , jq[index]

    6.事件绑定：
        //相当于事件监听btn,onClick = function(){xxx}
        1. $("xx").click(function(){
            xxx
         })
        
        //相当于window.onload = function(){xxx}
        2.$(function(){  
            xxx
        })



    举例：
    1.
        var div1 = document.getElementById("div1");
        alert("div():"+div1.innerHTML);
        var div2 = $(div1);
        alert("div2:"+div2.html());
    2.
        var divs1 = document.getElementsByTagName("div");
        var divs2 = $(divs1);
        var divs3 = $("div");

        for(var i = 0; i < divs3.length;i++){
        	divs1[i].innerHTML = "js1";
        }
        alert(divs1[0].innerHTML);
        
        
        divs2.html("js2");
        alert($(divs2[0]).html());
        
        divs3.get(0).innerHTML = "js3";
        alert(divs3[0].innerHTML);
