<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
<%Account account = (Account)session.getAttribute("account"); %>
<h2>メインメニュー</h2>
ユーザ名：<%=account.getName()%>さん<br>

<form action="/Dsystem/after_login/OwnerMenuServlet" method="GET" >
<input type="submit" class = "btn btn-primary btn-lg" value="顧客管理"style="width:150px;height:50px" ><br>
</form>
<br>
<form action="/Dsystem/after_login/PetMenuServlet" method="GET">
<input type="submit" class = "btn btn-primary btn-lg" value="ペット管理"style="width:150px;height:50px"><br>
</form>
<br>
<form action="/Dsystem/after_login/ServiceMenuServlet" method="GET">
<input type="submit" class = "btn btn-primary btn-lg" value="サービス管理"style="width:150px;height:50px"><br>
</form>
<br>
<form action="/Dsystem/after_login/HistoryMenuServlet" method="GET">
<input type="submit" class = "btn btn-primary btn-lg" value="実施サービス"style="width:150px;height:50px"><br>
</form>
<br>
<form action="/Dsystem/after_login/AggregateMemuServlet" method="GET">
<input type="submit" class = "btn btn-primary btn-lg" value="集計"style="width:150px;height:50px"><br>
</form>
<br>

<br>
<a href="/Dsystem/LogoutServlet">ログアウト</a>
</body>

</html>