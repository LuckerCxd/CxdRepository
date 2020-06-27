/**
 * 
 */


/*
var i,j;

document.write("<table align = 'center'>");

for(i = 1;i<10;i++){
	document.write("<tr>");
	for(j = 1;j<=i;j++){
		document.write("<td>");
		document.write(j + "&nbsp;*&nbsp;" +i+"&nbsp;=&nbsp;"+j*i+"&nbsp;&nbsp;&nbsp;");
		document.write("</td>");
	}
	document.write("</tr>");
}
document.write('</table>');

*/


function func() {
	document.write("func() hello everyBody");

	var arr = new Array(1,2,3,4,5);
	document.write(arr[10]);


	document.write("<br>");
	var arr = new Array(5);
	document.write(arr);


	var arr = [2,2,3,5,5,6];
	document.write("<br>");
	document.write(typeof(arr.join("&nbsp;&nbsp;&nbsp;")));

	document.write("<br>");
	var date = new Date();
	document.write(date.getTime());
	document.write("<br>");
	document.write(date.toLocaleString());
	document.write("<br>");
	document.write(date);

	var a = Math.PI;
	document.write("<br>");
	document.write(Math.ceil(a));
	document.write("<br>");
	document.write(Math.floor(a));
	document.write("<br>");
	document.write(Math.round(a));
	document.write("<br>");
	document.write(Math.LOG2E);
}
function func2(a,b,c) {
	document.write("func2() hello everyBody");
	var reg = /^\w+[_0-9a-zA-Z]{5,11}$/;
	document.write(reg.test("eee442ejiee"));
	var str = "现代汉语词典";
	var enStr  = encodeURI(str);
	document.write(enStr)

		
	document.write("<br>");
	var deStr  = decodeURI(str);
	document.write(deStr)
	
	document.write("<br>");
	var str = "123adefe";
	document.write(parseInt(str)+1);
	
	document.write("<br>");
	var str = "adefe123";
	document.write(isNaN(parseInt(str)));
	
	var flag = false;
	
	
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

	var pics = document.getElementById("picture");
	var index = 1;
	function func() {
		index ++;
		if(index >= 12){
			index = 1;
		}
		pics.src =  "../pictures/" + "test ("+ index+").jpg";
	}



	var nowWindow = setInterval(func, 2000);

	var flag = true;
	var cancel = document.getElementById("clear");
	cancel.onclick = function(){
		if(flag){
			clearInterval(nowWindow);
			flag = false;
		}else{
			nowWindow = setInterval(func, 2000);
			flag = true;
		}
		
		var quit = document.getElementById("quit");
		quit.onclick = function(){
			window.close();	
		}

		var refresh = document.getElementById("refresh");
		refresh.onclick = function(){
			location.reload();
		}


		var gobaidu = document.getElementById("gobaidu");
		gobaidu.onclick = function(){
			var win = open( "https://www.baidu.com");
			win.alert("are you ok ?");
			win.location.href = "https://blog.csdn.net";
		}

		var his = document.getElementById("history");
		his.onclick = function(){
			alert(history.length);
			history.go(-1);
		}

	}
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
	var tr = document.createElement("tr");

	var td1 = document.createElement("td");
	var text = document.createTextNode("9527");
	td1.appendChild(text);

	var td2 = document.createElement("td");
	var text2 = document.createTextNode("李佳");
	td2.appendChild(text2);

	var td3 = document.createElement("td");
	var text3 = document.createTextNode("3years");
	td3.appendChild(text3);

	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);

	table = document.getElementById("table");
	table.append(tr);
	table = document.getElementById("table");
	table.innerHTML += "<tr><td>9527</td><td>吴昊</td><td>6years</td> </tr>"
	table.style.border = "5px solid blue";

	
	
	
	
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

}






window.onload = function(){
	var form = document.getElementById("form");
	form.onsubmit = function () {
		return false;
	};
}













