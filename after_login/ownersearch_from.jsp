<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../botan.css">
<title>顧客検索画面</title>
</head>
<body>
<h2>顧客検索</h2>
<%//ログインユーザの名前を表示 %>
<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名:<%=account.getName() %>さん<br>
<form action="./OwnerSearchServlet" method="post">
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
<input type="submit" value="上記の条件で検索">
</form>
<a href="./OwnerMenuServlet">顧客管理メニューに戻る</a>
</body>
</html>