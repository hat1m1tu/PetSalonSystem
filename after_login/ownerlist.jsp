<%@page import="entity.Account"%>
<%@page import="entity.Owner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<html>
<head>
<title>顧客一覧画面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>

<h2>顧客一覧</h2>

<%//値を入手 %>
	<%List<Owner> list = (List<Owner>) request.getAttribute("owner");%>

<%//ログインユーザの名前を表示 %>
<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名:<%=account.getName() %>さん<br>

	<%//データがある場合、すべてのデータを表示 %>
	<%if (!list.isEmpty()) {%>

	<table class ="table table-hover ">
		<tr class="table-dark">
			<th>ID</th>
			<th>名前</th>
			<th>住所</th>
			<th>メールアドレス</th>
			<th>電話番号</th>
			<th>更新</th>
		</tr>

		<%for (Owner owner  : list) {%>

		<tr>
			<td><%=owner.getId()%></td>
			<td><%=owner.getName()%></td>
			<td><%=owner.getAddress()%></td>
			<td><%=owner.getEmail()%></td>
			<td><%=owner.getTel()%></td>
			<td><a href="./OwnerUpdateServlet?id=<%=owner.getId() %>">更新</a></td>
		</tr>
		<%}%>
	</table>
	<br>

	<%//データがない場合、以下を表示%>
	<%} else {%>
	顧客情報が登録されていません。
	<br>
	<%}%>

	<a href="./OwnerMenuServlet">顧客管理メニューに戻る</a>

</body>
</html>