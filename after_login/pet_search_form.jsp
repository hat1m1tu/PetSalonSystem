<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<link rel="stylesheet" href="../botan.css">
		<title>ペット検索画面</title>
	</head>
	<body>

	<%
		Account account = (Account)session.getAttribute("account");
	%>

	<h2>ペット検索</h2>

	ユーザ名: <%=account.getName() %>さん

<form action="./PetSearchServlet" method="POST">
<dl>
<dt>名前:</dt>
<dd><input type="text" name="name"></dd><br>
<dt>誕生日:</dt>
<dd><input type="text" name="birthday"></dd><br>
<dt>体重:</dt>
<dd><input type="text" name="weight"></dd><br>
<dt>種類:</dt>
<dd><input type="text" name="category"></dd><br>
<dt>オーナーID:</dt>
<dd><input type="text" name="owner_id"></dd><br>
</dl>
<input type="submit" value="上記の条件で検索"><br>

</form>

<a href="petmenu.jsp">ペット管理メニューに戻る</a>

	</body>
</html>