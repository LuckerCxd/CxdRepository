
CSS基础知识

前置知识：使CSS和html能一起使用：(使用外部关联较好)
	
	1.定义外部CSS文件，在CSS文件中定义选择器和属性
	2.在html文件中的head标签中 使用link标签引入
	举例：
		<link rel = "stylesheet" href="../css/test.css">
	说明：
		rel  定义当前文档与被链接文档之间的关系。
		href 定义被链接文档的位置		

CSS文件内容
-	
	option{           /*选择器*/
		color : red;  /*属性*/
	}

1.选择器
	
	基础选择器：
		1.id选择器：对指定标签定义其id属性，解析时选择出具体id属性值的元素
			#idName{属性...}
			
			举例：
				#tips{
					color : Blue;
					width : 5;
				}
		2.元素选择器：对指定的标签名称进行选择
			labelName{属性...}
				
			举例：
				option{
					color : red;
				}
		3.类选择器：对指定标签定义其class属性，解析时选择出具体class属性值的元素
			.className{属性...}
			
			举例：
				.t1{
					color : green;
					width : 4
				}
		总结：
			id > class > 元素选择器，若存在覆盖情况，则按优先级高低分配
	
	拓展选择器：
		1.选择所有元素：
			*{
				color : red;
			}
		2.并集选择器：
			div,font{     /*并集选择器 作用于 font或div标签  */
				color : blue;
			}
	
		3.子选择器：
			font b{   /*子选择器 作用于 font内部的b标签 */
				color : blue;
			}
		4.父选择器：
			font>b{   /*父选择器 作用于 b外部的font标签*/
				border:5px solid;
			}
		5.元素属性选择器：
			font[size = "10"]{  /*元素属性选择器 作用于具备指定属性值的标签*/
				color : #ff0080;
				border:5px solid;
			}

		6.伪类选择器：选择具有一定状态的元素
			a:link{    /*超链接的初始状态*/
				color : "grey";
			}
			a:hover{	/*悬浮于之上*/
				color : "yellow";
			}
			a:active{	/*被选中*/
				color : "green";
			}
			a:visited{	/*被访问了*/
				color : "red";
			}



属性：

	1.文本属性
		1.color : 		blue;       //文本颜色
		2.font-size :	30px;		//字体大小
		3.text-align :	center;		//对齐方式
		4.line-height:	10;			//行高
		
	2.背景
		background : url("../pictures/base.jpg"); 
										 //在一个声明中设置所有的背景属性
		background-size:200px 200px;	 //定义背景图片大小	
	3.边框	
		border:	5px solid           //边框
	4.尺寸
		height : 200px;				//高度
		width : 200px;				//宽度
	5.盒子模型
		margin,padding,float   td,div..:padding  p,div..:margin

		padding内边距会影响盒子的大小,margin不会
		margin:auto:  左右margin会居中，上下不会调整
		
		举例：
			/*可使两个盒子，内盒子居中，2种方式*/
			div{
				border : 1px solid;
			}
			
			#small{
				width : 100px;
				height : 100px;
				/*margin :50px;    /*1.所有 4 个外边距都是 50px*/
			}
			
			#big{
				width : 200px;
				height : 200px;
				box-sizing:border-box;  
				padding : 50px;   /*2.所有 4 个内边距都是 50px*/
				/*
				固定盒子大小，避免调整内边距影响盒子的大小
				*/
			}

			

			/*可使3个出于同一行的状态*/
			.c1{
				float:left;
			}
			
			.c2{
				float:left;
			}
			.c3{
				float:left;
			}
		
	

参考教程：

	https://www.w3school.com.cn/