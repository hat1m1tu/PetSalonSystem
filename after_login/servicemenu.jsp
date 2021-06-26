<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サービス管理</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>

<body>

<%Account account = (Account)session.getAttribute("account"); %>
<h2>サービス管理メニュー</h2>
ユーザ名：<%=account.getName()%>さん

<form action="./ServiceListServlet" method = "get">
	<input type="submit" class = "btn btn-primary btn-lg" value = "サービス一覧">
</form>
<br>
<form action="./ServiceInsertServlet" method = "get">
	<input type="submit" class = "btn btn-primary btn-lg" value = "サービス新規追加"">
</form>
<br>
<a href = "./main.jsp">メインメニューに戻る</a>

</body>
</html>