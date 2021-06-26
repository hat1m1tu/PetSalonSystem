<%@page import="entity.Account"%>
<%@page import="sun.print.resources.serviceui"%>
<%@page import="entity.Service"%>
<%@page import="java.util.List"%>
<%@page import="dao.ServiceDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サービス一覧</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
<h1>サービス一覧</h1>
<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名：<%=account.getName()%>さん<br>

<% List<Service> list = (List<Service>)request.getAttribute("list"); %>

<% if (list.isEmpty() || list == null) { %>
	<h3>サービスが登録されていません</h3>
	<br>
<a href = "./servicemenu.jsp">サービス管理メニューに戻る</a>
<% }else { %>
<table class ="table table-hover ">
 
	<tr class="table-dark">
	<th>ID</th>
	<th>サービス名</t>
	<th>値段</th>
	<th>ステータス</th>
	<th>更新</th>
	</tr>

	<% for(Service service : list){ %>
	<% int id = service.getId(); %>
		<tr>
			<td><%=service.getId() %></td>
			<td><%=service.getName() %></td>
			<td><%=service.getPrice() %></td>
			<td><%if(service.getStatus()==0) {%>
				休止中
			<%}else{%>
				実施中
			<%} %>
			</td>

			<td><a href = "./ServiceUpdateServlet?id=<%= id %>">更新</a></td>
		</tr>
	<% } %>
</table>
<br>
<a href = "./servicemenu.jsp">サービス管理メニューに戻る</a>
<% } %>


</body>
</html>