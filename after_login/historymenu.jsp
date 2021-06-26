<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実施サービス管理メニュー</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
	<h2>実施サービス管理メニュー</h2>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	ユーザ名：<%=account.getName()%>さん
	<br>
	<form action="/Dsystem/after_login/HistoryListServlet" method="GET">
		<input type="submit" class = "btn btn-primary btn-lg" value="実施サービス一覧">
	</form>
	<br>
	<form action="/Dsystem/after_login/HistoryInsertServlet" method="GET">
		<input type="submit" class = "btn btn-primary btn-lg" value="実施サービス新規追加">
	</form>
	<br>
	<a href="./main.jsp"> メインメニューに戻る </a>
</body>
</html>