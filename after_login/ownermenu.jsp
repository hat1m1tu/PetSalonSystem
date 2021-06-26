<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客管理メニュー画面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>

<h2>顧客管理メニュー</h2>

<%//ユーザネームの表示%>
<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名:<%=account.getName() %>さん<br>

<%//顧客一覧画面に遷移%>
<form action="./OwnerListServlet" method="get">
<input type="submit" class = "btn btn-primary btn-lg" value="顧客一覧">
</form>
<br>


<%//顧客検索画面に遷移%>
<form action="./OwnerSearchServlet" method="get">
<input type="submit" class = "btn btn-primary btn-lg" value="顧客検索" >
</form>
<br>

<%//顧客新規追加画面に遷移%>
<form action="./OwnerInsertServlet" method="get">
<input type="submit" class = "btn btn-primary btn-lg" value="顧客新規追加">
</form>
<br>

<%//メインメニューに遷移%>
<a href="./main.jsp">メインメニューへ戻る</a>

</body>
</html>