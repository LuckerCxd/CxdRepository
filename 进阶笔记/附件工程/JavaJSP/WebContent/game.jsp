<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>������С��Ϸ</title>
</head>
<body>
	<form action="http://localhost:8080/JavaJSP/game.jsp" method="get"
		id="gameForm">
		<table align="center" border="1" cellspacing="0" width="230">
			<caption>�����֣�0-9����3�λ���</caption>
			<tr>
				<td><label for="inputNum">���֣� </label></td>
				<td><input type="text" required="required" placeholder="��������"
					name="num" id="inputNum"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="ȷ��" id="check">
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
			if (confirm("�Ƿ�����?")) {
				window.location.href="http://localhost:8080/JavaJSP/game.jsp";
			  } else {
				window.location.href="http://localhost:8080/JavaJSP/sortedWinner.jsp?count=3";
			  }
		}else{
			var num = document.getElementById("inputNum").value;
			if(parseInt(num)<= 0 && parseInt(num) >= 9){
		        alert("����������0-9֮������֣�");
		    }else{
		    	if(parseInt(num)== parseInt(random)){  
		    		alert("��ϲ�����ˣ�: "+ random);
		    		window.location.href="http://localhost:8080/JavaJSP/sortedWinner.jsp?count="+count;
		    	}
		    	else if(parseInt(num)>parseInt(random)){
		    		alert("�´��ˣ�: "+ random);
		    	}else{
		    		alert("��С�ˣ�: "+ random);
		    	}
		    }
		}
		count += 1;

	}
}
</script>
</body>



</html>