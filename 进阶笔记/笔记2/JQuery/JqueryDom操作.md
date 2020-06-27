Jquery的Dom操作：(调用方式:jq对象.function())

1.内容操作：
    
    1.html():获取或设置元素标签体的全部内容
        JQuery的html()：
        而不用像getElements的JS对象那样for遍历设置innerHTML
        html(vlaue):
         空参时(value===undefine)返回首元素的innerHTML
         非空参时内部for循环设置所有元素的innerHTML,并返回value
    2.text()：获取或设置元素标签体的文本内容
    3.val()：获取或设置标签中属性value的值

2.属性操作：

    1.通用属性：
        attr适用于自定义属性，prop适用于该标签元素的固有属性
        使用removeAttr可以删除class属性，removeProp不可以
            会删除属性以及其属性值
        1.attr():
            传入属性，获取属性值
            传入键值对，设置属性值
        2.removeAttr():删除属性
        3.prop():
            传入属性，获取属性值
            传入键值对，设置属性值
        4.removeLProp():删除属性

    2.对class属性操作：
        1.addClass("xxx"):添加class属性值
        2.removeClass():删除class属性值
            传入参数：删除指定class属性值
            空参：删除全部class属性值
        3.toggleClass("xxx"):切换class属性
            若class存在该指定属性值则删除，
            若不存在，则将添加class的属性值
            空参：删除全部class属性值，保留class属性
                 或是 添加class属性,但属性值为空
            

3.CRUD操作：增删改查操作
    
    

    1.append();     
        //对象1.append(对象2)
        //对象1作父元素 将 对象2子元素 添加内部并放到末尾
    2.appendTo();  
        //对象2.appendTo(对象1)
        //对象2作子元素 添加到 对象1父元素 内部并放到末尾
    3.prepend(); 
        //对象1.append(对象2)
        //对象1作父元素 将 对象2子元素 添加内部并放到开头   
    4.prependTo();
    
    5.after()；
        //对象1.after(对象2)
        //对象1 后面添加 对象2
    6.before():
        //对象1.before(对象2)
        //对象1 前面添加 对象2
    7.insertAfter():
        //对象2.insertAfter(对象1)
        //对象1 后面添加 对象2
    8.insertBefore();
     
    9.remove():
        //对象.remove():删除对象自身
    10.empty()
        //对象.empty():清空对象的后代元素,但保留当前元素及属性