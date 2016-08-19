<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sub_number=response.toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>因式分解</title>
  	
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="css/main.css" media="all" /> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	function factorize(str)
	{
		var xmlhttp;
		if (str.length==0)
	  	{
	 	 	document.getElementById("myDiv").innerHTML="输入格式错误";
	  		return;
	  	}
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("POST","factorize.action",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("number="+str);
	}
	</script>
  </head>
  
  <body> 
	<div style="margin-top:82px;text-align: center;"><h1>因式分解</h1></div>
	<div style="margin-top:18px;text-align: center;">
		数字:<input type="text" name="number"/>
		<button type="button" onclick="factorize(number.value)">确定</button>
	</div>
	<div id="myDiv" style="margin-top:18px;text-align: center;"></div>
  </body>
</html>
