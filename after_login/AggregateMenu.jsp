<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>集計メニュー</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
<%Account account = (Account)session.getAttribute("account");%>
<h2>集計メニュー</h2>
ユーザ名：<%=account.getName()%>さん

<form action="./ServiceAggregateServlet" method="GET">
<input type="submit" class = "btn btn-primary btn-lg" value="サービス別売上一覧"><br>
</form>
<br>
<form action="./PetTypeAggregateServlet" method="GET">
<input type="submit" class = "btn btn-primary btn-lg" value="ペット種類別売上一覧"><br>
</form>
<br>
<a href="MainMenuServlet">メインメニューに戻る</a>

</body>
</html>