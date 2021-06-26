<%@page import="java.util.List"%>
<%@page import="entity.Account"%>
<%@page import="entity.Pet"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ペット検索結果</title>
</head>
<body>

	<%-- requestオブジェクトからデータを取得 --%>
	<%
		List<Pet> plist = (List<Pet>) request.getAttribute("list");
		Account account = (Account)session.getAttribute("account");
	%>

	<h2>
		ペット検索結果
	</h2>

	ユーザ名: <%=account.getName() %>さん<br>

	<!-- Goodsのデータをテーブルで表示 -->
	<%
		if (plist.isEmpty()) {
	%>
		ペット情報が見つかりませんでした。<br><br>
	<%
		} else {
	%>
	<form action="./PetListServlet" method="post">
	<table border="4">
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>誕生日</th>
			<th>体重</th>
			<th>種類</th>
			<th>オーナーID</th>
			<th>更新</th>
		</tr>

		<%
			for (Pet p : plist) {
		%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getBirthday()%></td>
			<td><%=p.getWeight()%></td>
			<td><%=p.getCategory()%></td>
			<td><%=p.getOwnerId()%></td>
			<td><a href="./PetUpdateServlet?id=<%= p.getId()%>">更新</a></td>

		</tr>
		<%}%>

	</table>
	<br>

	</form>

	<%}%>


	<a href="petmenu.jsp">ペット管理メニューに戻る</a>
</body>
</html>