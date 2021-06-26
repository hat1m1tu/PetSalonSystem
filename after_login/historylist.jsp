<%@page import="entity.Owner"%>
<%@page import="dao.OwnerDao"%>
<%@page import="entity.Service"%>
<%@page import="dao.ServiceDao"%>
<%@page import="entity.Pet"%>
<%@page import="dao.PetDao"%>
<%@page import="entity.Account"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entity.History"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<History> list = (List<History>) request.getAttribute("list");
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");

	PetDao petDao = new PetDao();
	List<Pet> petList = petDao.findAll();
	ServiceDao serviceDao = new ServiceDao();
	List<Service> serviceList = serviceDao.findAll();
	OwnerDao ownerDao = new OwnerDao();
	List<Owner> ownerList = ownerDao.findAll();
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<link rel="stylesheet" href="../table.css">
<meta charset="UTF-8">
<title>実施サービス一覧</title>
</head>
<body>
	<h1>実施サービス管理メニュー</h1>
	<hr>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	ユーザ名：<%=account.getName()%>さん
	<br>
	<%
		if (!list.isEmpty()) {
	%>
	<table class ="table table-hover ">
		<tr class="table-dark">
			<th>ID</th>
			<th>ペット名（顧客名）</th>
			<th>サービス名</th>
			<th>サービス日時</th>
			<th>更新</th>
			<th>削除</th>
		</tr>
		<%
			for (History h : list) {
		%>
		<tr>
			<td><%=h.getId()%></td>
			<td><%
				for (Pet p : petList) {
							if (p.getId() == h.getPetId()) {
			%> <%=p.getName()%> <%
 	}
 %> <%
 	}
 %><%
				for (Owner o : ownerList) {
							if (o.getId() == h.getPetId()) {
			%> （<%=o.getName()%>） <%
 	}
 %> <%
 	}
 %></td>
			<td>
				<%
					for (Service s : serviceList) {
								if (s.getId() == h.getPetId()) {
				%> <%=s.getName()%> <%
 	}
 %> <%
 	}
 %>
			</td>
			<td><%=timeFormat.format(h.getServiceDate())%>
			<td><form action="/Dsystem/after_login/HistoryUpdateServlet"
					method="GET">
					<input type="hidden" name=id value=<%=h.getId()%>> <input
						type="submit" value="更新">
				</form></td>
			<td><form action="/Dsystem/after_login/HistoryDeleteServlet"
					method="GET">
					<input type="hidden" name=id value=<%=h.getId()%>> <input
						type="submit" value="削除">
				</form></td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<%
		} else {
	%>
	実施サービスが登録されていません。
	<br>
	<%
		}
	%>
	<a href="./historymenu.jsp"> 実施サービスメニューに戻る </a>
</body>
</html>