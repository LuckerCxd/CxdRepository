<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>猜数字小游戏</title>
</head>
<body>
	<form action="http://localhost:8080/JavaJSP/game.jsp" method="get"
		id="gameForm">
		<table align="center" border="1" cellspacing="0" width="230">
			<caption>猜数字（0-9），3次机会</caption>
			<tr>
				<td><label for="inputNum">数字： </label></td>
				<td><input type="text" required="required" placeholder="输入数字"
					name="num" id="inputNum"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="确认" id="check">
				</td>
			</tr>
		</table>
	</form>

	<script>
window.onload = function(){
	<%! int tcount = 1;%>
	var count = 1;
	var checkNum = document.getElementById("check");
	var random = Math.floor(Math.random()*10); 
	checkNum.onclick = function(){ 
		if(count >= 4){
			if (confirm("是否重来?")) {
				window.location.href="http://localhost:8080/JavaJSP/game.jsp";
			  } else {
				window.location.href="http://localhost:8080/JavaJSP/sortedWinner.jsp?count=3";
			  }
		}else{
			var num = document.getElementById("inputNum").value;
			if(parseInt(num)<= 0 && parseInt(num) >= 9){
		        alert("必须输入入0-9之间的数字！");
		    }else{
		    	if(parseInt(num)== parseInt(random)){  
		    		alert("恭喜你答对了！: "+ random);
		    		window.location.href="http://localhost:8080/JavaJSP/sortedWinner.jsp?count="+count;
		    	}
		    	else if(parseInt(num)>parseInt(random)){
		    		alert("猜大了！: "+ random);
		    	}else{
		    		alert("猜小了！: "+ random);
		    	}
		    }
		}
		count += 1;

	}
}
</script>
</body>



</html>