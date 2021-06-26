<%@page import="java.util.List"%>
<%@page import="entity.Owner"%>
<%@page import="dao.OwnerDao"%>
<%@page import="entity.Service"%>
<%@page import="dao.ServiceDao"%>
<%@page import="entity.Pet"%>
<%@page import="dao.PetDao"%>
<%@page import="entity.History"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	History history = (History) request.getAttribute("history");
	SimpleDateFormat timeFormat = new SimpleDateFormat("YYYY/MM/dd/HH:mm");

	PetDao petDao = new PetDao();
	List<Pet> petList = petDao.findAll();
	ServiceDao serviceDao = new ServiceDao();
	List<Service> serviceList = serviceDao.findAll();
	OwnerDao ownerDao = new OwnerDao();
	List<Owner> ownerList = ownerDao.findAll();
%>
<%
	int error = 1;
		if (request.getAttribute("error") != null) {
			error = (int) request.getAttribute("error");
		}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<h1>実施サービス管理メニュー</h1>
	<hr>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	ユーザ名：<%=account.getName()%>さん
	<br>
	<%
		if (error == 1) {
	%>
	<%
		if (history != null) {
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>ペット名（顧客名）</th>
			<th>サービス名</th>
			<th>サービス日時</th>
		</tr>
		<tr>
			<td><%=history.getId()%></td>
			<td><%
				for (Pet p : petList) {
							if (p.getId() == history.getPetId()) {
			%> <%=p.getName()%> <%
 	}
 %> <%
 	}
 %><%
				for (Owner o : ownerList) {
							if (o.getId() == history.getPetId()) {
			%> （<%=o.getName()%>） <%
 	}
 %> <%
 	}
 %></td>
			<td><%
 	for (Service s : serviceList) {
 				if (s.getId() == history.getPetId()) {
 %> <%=s.getName()%> <%
 	}
 %> <%
 	}
 %></td>
			<td><%=timeFormat.format(history.getServiceDate())%></td>
		</tr>
	</table>
	<form action="/Dsystem/after_login/HistoryDeleteServlet" method="POST">
	<input type="hidden" name="id" value=<%=history.getId() %>>
	<input type="submit" class = "btn btn-primary btn-lg" value="実施サービス情報を削除する">
	</form>
	<%
		} else {
	%>
	実施サービスが登録されていません。<br>
	<br>
	<%
		}
	%>
	<%} else {%>
	該当の実施サービスが存在しません。<br>
	<%} %>
	<a href="./historymenu.jsp"> 実施サービスメニューに戻る </a>

</body>
</html>