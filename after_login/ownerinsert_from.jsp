<%@page import="entity.Owner"%>
<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../botan.css">
<title>顧客新規追加画面</title>
</head>
<body>
<%//更新失敗時の記入情報を取得%>
<%String message = (String)request.getAttribute("message"); %>
<%Owner owner = (Owner)request.getAttribute("owner"); %>

<h2>顧客登録</h2>

<%//エラーメッセージ表示%>
<% if(message != null){%>
<div>
<p style="color: red;"><%=message %><br>
</div>
<%} %>

<%//ユーザネームの表示%>
<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名:<%=account.getName() %>さん<br>
追加する顧客情報を入力してください。

<%//顧客情報が存在する場合(2回目～)%>
<% if(owner != null){%>
<form action="./OwnerInsertServlet" method="post">
<dl>
<dt>名前:</dt>
<dd><input type="text" name="name" value=<%= owner.getName()%>></dd><br>
<dt>住所:</dt>
<dd><input type="text" name="address" value=<%= owner.getAddress()%>></dd><br>
<dt>電話番号:</dt>
<dd><input type="text" name="tel" value=<%= owner.getTel()%>></dd><br>
<dt>メールアドレス:</dt>
<dd><input type="text" name="email" value=<%= owner.getEmail()%>></dd><br>
</dl>
<input type="submit" value="上記の顧客情報を追加する">
</form>

<%} else{%>
<%//顧客情報が存在しない場合（初回）%>
<form action="./OwnerInsertServlet" method="post"><br>
<dl>
<dt>名前:</dt>
<dd><input type="text" name="name"></dd><br>
<dt>住所:</dt>
<dd><input type="text" name="address"></dd><br>
<dt>電話番号:</dt>
<dd><input type="text" name="tel"></dd><br>
<dt>メールアドレス:</dt>
<dd><input type="text" name="email"></dd><br>
</dl>
<input type="submit" value="上記の顧客情報を追加する">
</form>
<%} %>

<a href="./OwnerMenuServlet">顧客管理メニューに戻る</a>

</body>
</html>