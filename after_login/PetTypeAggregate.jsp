<%@page import="entity.Aggregate"%>
<%@page import="java.util.List"%>
<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペット種類別売上集計</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
<%Account account = (Account)session.getAttribute("account"); %>

<h2>ペット種類別売上集計</h2>
ユーザ名：<%=account.getName()%>さん
<%List<Aggregate> list = (List<Aggregate>)request.getAttribute("list"); %>
<%if (!list.isEmpty()) {%>
	<table class ="table table-hover ">
		<tr class="table-dark">
			<th>ペットの種類</th>
			<th>売上合計</th>
		</tr>

<%for(Aggregate agg: list){%>
<tr>
	<td><%=agg.getName() %></td>
	<td><%=agg.getAggregate() %></td>

</tr>

<%}%>
</table>
<%} else {%>
	顧客情報が見つかりませんでした。
	<br>
	<%}%>
<br>
<a href="AggregateMenu.jsp">集計メニューに戻る</a>

</body>
</html>