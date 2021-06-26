<%@page import="entity.Owner"%>
<%@page import="entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客検索結果画面</title>
<link rel="stylesheet" href="../table.css">
</head>
<body>

<h2>顧客検索結果</h2>

<%//値を入手 %>
<%List<Owner> list = (List<Owner>) request.getAttribute("owner");%>

<%//ログインユーザの名前を表示 %>
<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名:<%=account.getName() %>さん<br>

	<%//データがある場合、すべてのデータを表示 %>
	<%if (!list.isEmpty()) {%>

	<table border="4">
		<tr>
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
	顧客情報が見つかりませんでした。
	<br>
	<%}%>

	<a href="./OwnerMenuServlet">顧客管理メニューに戻る</a>


</body>
</html>