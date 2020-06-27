easyui：

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">



> 布局easyui-layout - 嵌套布局 - center不可缺失，其他东西南北可缺

    <body class="easyui-layout">
        <div data-options="region:'north',title:'北部'" style="height: 100px">北方</div>
        <div data-options="region:'south',title:'南部'" style="height: 100px">南方</div>
        <div data-options="region:'east',title:'东部'" style="width: 100px">东方</div>
        <div data-options="region:'west',title:'西部'" style="width: 100px">西方</div>
        <div class="easyui-layout" data-options="region:'center',title:'中部'">
            <div data-options="region:'north',title:'北部'" style="height: 50px">北方</div>
            <div data-options="region:'south',title:'南部'" style="height: 50px">南方</div>
            <div data-options="region:'east',title:'东部'" style="width: 50px">东方</div>
            <div data-options="region:'west',title:'西部'" style="width: 50px">西方</div>
            <div data-options="region:'center',title:'中部'">中之中</div>
        </div>
    </body>


> 布局 + 折叠面板easyui-accordion

    <body class="easyui-layout">
        <div data-options="region:'north',title:'北部'" style="height: 100px">北方</div>
        <div data-options="region:'south',title:'南部'" style="height: 100px">南方</div>
        <div class="easyui-accordion" data-options="region:'west',title:'西部'" style="width: 100px">
            <div data-options="title:'基本功能',iconCls:'icon-mini-add'">面板一</div>
            <div data-options="title:'高级功能',iconCls:'icon-mini-add'">面板二</div>
            <div data-options="title:'管理员功能',iconCls:'icon-mini-add'">面板三</div>
        </div>
        <div data-options="region:'east',title:'东部'" style="width: 100px">东方</div>
        <div class="easyui-layout" data-options="region:'center',title:'中部'">
            <div data-options="region:'north',title:'北部'" style="height: 50px">北方</div>
            <div data-options="region:'south',title:'南部'" style="height: 50px">南方</div>
            <div data-options="region:'east',title:'东部'" style="width: 50px">东方</div>
            <div data-options="region:'west',title:'西部'" style="width: 50px">西方</div>
            <div data-options="region:'center',title:'中部'">中之中</div>
        </div>
    </body>


> 布局 + 折叠面板 + 选项卡easyui-tabs

    <body class="easyui-layout">
        <div data-options="region:'north',title:'北部'" style="height: 100px">北方</div>
        <div data-options="region:'south',title:'南部'" style="height: 100px">南方</div>
        <div class="easyui-accordion" data-options="region:'west',title:'西部'" style="width: 100px">
            <div data-options="title:'基本功能',iconCls:'icon-mini-add'">面板一</div>
            <div data-options="title:'高级功能',iconCls:'icon-mini-add'">面板二</div>
            <div data-options="title:'管理员功能',iconCls:'icon-mini-add'">面板三</div>
        </div>
        <div data-options="region:'east',title:'东部'" style="width: 100px">东方</div>
        <div class="easyui-layout" data-options="region:'center',title:'中部'">
            <div data-options="region:'north',title:'北部'" style="height: 50px">北方</div>
            <div data-options="region:'south',title:'南部'" style="height: 50px">南方</div>
            <div data-options="region:'east',title:'东部'" style="width: 50px">东方</div>
            <div data-options="region:'west',title:'西部'" style="width: 50px">西方</div>
            <div class="easyui-tabs" data-options="region:'center',title:'中部'">
                <div data-options="title:'选项卡一'">内容一</div>
                <div data-options="title:'选项卡二'">内容二</div>
                <div data-options="title:'选项卡三'">内容三</div>
            </div>
        </div>
    </body>



ztree


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css"/>

> 
> 设置：

    <script type="text/javascript">
        $(function(){
            var setting = {};
            var zNodes = [
                {"name":"菜单一"}, // 每个{} 就是一个节点
                {"name":"菜单二","children":[
                        {"name":"菜单21"},
                        {"name":"菜单22"}
                    ]}
            ];
            $.fn.zTree.init($("#basicTree"), setting, zNodes);
        });
    </script>
> 
> 哪里显示：

    <ul id="basicTree" class="ztree"></ul>


> 结构

    var zNodes1 = [
                {"name":"菜单一"}, // 每个{} 就是一个节点
                {"name":"菜单二","children":[
                        {"name":"菜单21"},
                        {"name":"菜单22"}
                    ]}
            ];

znodes1用children属性来标志子节点
znodes2用id和pid关系来标志子节点，pid未在出现则说明为根结点
但是有不同的setting要对应上

    var zNodes2 = [
            {"id":"1","pId":"0","name":"菜单一"},
            {"id":"2","pId":"0","name":"菜单二"},
            {"id":"3","pId":"2","name":"菜单21"},
            {"id":"4","pId":"2","name":"菜单22"}
        ];


举例：


    <script type="text/javascript">
        $(function(){
            var setting1 = {};
            var setting2 = {
                data : {
                    simpleData : {
                        enable : true
                    }
                }
            };
            var zNodes1 = [
                {"name":"菜单一"}, // 每个{} 就是一个节点
                {"name":"菜单二","children":[
                        {"name":"菜单21"},
                        {"name":"菜单22"}
                    ]}
            ];
            var zNodes2 = [
                {"id":"1","pId":"0","name":"菜单一"},
                {"id":"2","pId":"0","name":"菜单二"},
                {"id":"3","pId":"2","name":"菜单21"},
                {"id":"4","pId":"2","name":"菜单22"}
            ];
            $.fn.zTree.init($("#basicTree"), setting2, zNodes2);
        });
    </script>
    
    <body class="easyui-layout">
        <div data-options="region:'north',title:'北部'" style="height: 100px">北方</div>
        <div data-options="region:'south',title:'南部'" style="height: 100px">南方</div>
        <div class="easyui-accordion" data-options="region:'west',title:'西部'" style="width: 100px">
            <div data-options="title:'基本功能',iconCls:'icon-mini-add'">
                <ul id="basicTree" class="ztree"></ul>
            </div>
            <div data-options="title:'高级功能',iconCls:'icon-mini-add'">面板二</div>
            <div data-options="title:'管理员功能',iconCls:'icon-mini-add'">面板三</div>
        </div>
        <div data-options="region:'east',title:'东部'" style="width: 100px">东方</div>
        <div class="easyui-layout" data-options="region:'center',title:'中部'">
            <div data-options="region:'north',title:'北部'" style="height: 50px">北方</div>
            <div data-options="region:'south',title:'南部'" style="height: 50px">南方</div>
            <div data-options="region:'east',title:'东部'" style="width: 50px">东方</div>
            <div data-options="region:'west',title:'西部'" style="width: 50px">西方</div>
            <div class="easyui-tabs" data-options="region:'center',title:'中部'">
                <div data-options="title:'选项卡一'">内容一</div>
                <div data-options="title:'选项卡二'">内容二</div>
                <div data-options="title:'选项卡三'">内容三</div>
            </div>
        </div>
    </body>