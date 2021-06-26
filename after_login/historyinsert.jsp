<%@page import="entity.History"%>
<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	History history = (History) request.getAttribute("history");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サービス新規追加画面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
	<h1>実施サービス管理メニュー</h1>
	<hr>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	ユーザ名：<%=account.getName()%>さん
	<br>
	<%
		if (request.getAttribute("error") == null) {
	%>
	<form action="./HistoryInsertServlet" method="post">
		ペットID:<input type="text" name="pet_Id"> <br>サービスID:<input
			type="text" name="service_Id"><br> 希望日時:<input
			type="text" name="year">年<input type="text" name="month">月<input
			type="text" name="day">日<input type="text" name="hour">時<input
			type="text" name="minitue">分 <br> <input type="submit"
			value="上記の実施サービスを追加する">
	</form>
	<%
		} else {
	%>
	<%=request.getAttribute("error")%>
	<form action="./HistoryInsertServlet" method="post">
		ペットID:<input type="text" name="pet_Id"
			value=<%=request.getAttribute("pet_Id")%>> <br>サービスID:<input
			type="text" name="service_Id"
			value=<%=request.getAttribute("service_Id")%>><br>希望日時:
		<input type="text" name="year" value=<%=request.getAttribute("year")%>>年<input
			type="text" name="month" value=<%=request.getAttribute("month")%>>月<input
			type="text" name="day" value=<%=request.getAttribute("day")%>>日<input
			type="text" name="hour" value=<%=request.getAttribute("hour")%>>時<input
			type="text" name="minitue" value=<%=request.getAttribute("minitue")%>>分<br>
		<input type="submit" class = "btn btn-primary btn-lg" value="上記の実施サービスを追加する">
	</form>
	<%
		}
	%>
	<a href="main.jsp"> メインメニューに戻る </a>
</body>
</html>