<%@page import="entity.Owner"%>
<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報更新画面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>

<h2>顧客情報更新</h2>

<%//更新失敗時の記入情報を取得%>
<%String message = (String)request.getAttribute("message"); %>
<%//エラーメッセージ表示%>
<% if(message != null){%>
<div>
<p style="color: red;"><%=message %><br>
</div>
<%} %>

<%//ログインユーザの名前を表示 %>
<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名:<%=account.getName() %>さん<br>
更新後の顧客情報を入力してください。<br>

<%//更新する顧客情報を取得 %>
<%Owner owner = (Owner)request.getAttribute("owner"); %>
<form action="./OwnerUpdateServlet" method="post">
<dl>
ID:<%= owner.getId()%><br>
<input type="hidden" name="id"  value=<%=owner.getId() %>>
<dt>名前:</dt>
<dd><input type="text" name="name" value=<%= owner.getName().replaceAll(" ", "&nbsp")%>></dd><br>
<dt>住所:</dt>
<dd><input type="text" name="address" value=<%= owner.getAddress().replaceAll(" ", "&nbsp")%>></dd><br>
<dt>電話番号:</dt>
<dd><input type="text" name="tel" value=<%= owner.getTel()%>></dd><br>
<dt>メールアドレス:</dt>
<dd><input type="text" name="email"  value=<%= owner.getEmail()%>></dd><br>
</dl>
<input type="submit" value="顧客情報を更新する">
</form>
</body>
</html>