<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<link rel="stylesheet" href="../botan.css">
		<title>ペット登録画面</title>
	</head>
	<body>

	<h2>ペット登録</h2>

	<%
		Account account = (Account)session.getAttribute("account");
	%>

	<br>



	ユーザ名: <%=account.getName() %>さん<br>

	<%
		String message = (String) request.getAttribute("error");
		String name = "";
		String birthday = "";
		String weight = "";
		String category = "";
		String owner_id = "";

	%>
	<%if(message != null){ %>
		<span style="color: #ff0000"><%=message %></span>
		<% name = (String)request.getAttribute("name");
		if(name.equals("")){
			name = "未入力";
		}
		birthday = (String)request.getAttribute("birthday");
		if(birthday.equals("")){
			birthday = "未入力";
		}
		weight = (String)request.getAttribute("weight");
		if(weight.equals("")){
			weight = "未入力";
		}
		category = (String)request.getAttribute("category");
		if(category.equals("")){
			category = "未入力";
		}
		owner_id = (String)request.getAttribute("owner_id");
		if(owner_id.equals("")){
			owner_id = "未入力";
		}
		%>

		<form action="./PetInsertServlet" method="POST">
	<dl>
	<dt>名前:</dt>
	<dd><input type="text" placeholder= <%=name %> name="name"></dd><br>
	<dt>誕生日:</dt>
	<dd><input type="text" placeholder= <%=birthday %> name="birthday"></dd><br>
	<dt>体重:</dt>
	<dd><input type="text" placeholder= <%=weight %> name="weight"></dd><br>
	<dt>種類:</dt>
	<dd><input type="text" placeholder= <%=category %> name="category"></dd><br>
	<dt>オーナーID:</dt>
	<dd><input type="text" placeholder= <%=owner_id %> name="owner_id"></dd><br>
	</dl>
	<input type="submit" value="上記のペット情報を追加"><br>

</form>

	<%} else {%>




<form action="./PetInsertServlet" method="POST">

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

<input type="submit" value="上記のペット情報を追加"><br>

</form>

<%}%>

<a href="petmenu.jsp">ペット管理メニューに戻る</a>

	</body>
</html>