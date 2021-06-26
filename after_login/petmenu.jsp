<%@page import="entity.Account"%>
<%@page import="entity.Pet"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペット管理メニュー画面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
	<%Account account = (Account)session.getAttribute("account");%>
<h2>ペット管理メニュー</h2>
ユーザ名: <%=account.getName() %>さん

	<form action="./PetListServlet" method="get">
		<input type="submit" class = "btn btn-primary btn-lg" value="ペット一覧">
	</form>
	<br>
	<form action="./pet_search_form.jsp" method="get">
		<input type="submit" class = "btn btn-primary btn-lg" value="ペット検索">
	</form>
	<br>
	<form action="./pet_insert_form.jsp" method="post">
		<input type="submit" class = "btn btn-primary btn-lg" value="ペット新規追加">
	</form>
	<br>
	<a href="./main.jsp">メインメニューに戻る</a>


</body>
</html>