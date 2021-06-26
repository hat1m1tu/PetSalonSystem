<%@page import="entity.Service"%>
<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../botan.css">
<title>Insert title here</title>
</head>
<body>
	<h1>サービス情報更新</h1>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	ユーザ名：<%=account.getName()%>さん
	<br>
	<%
		Service service = (Service) request.getAttribute("service");
	%>

	<%
		String iS = (String) request.getAttribute("iS");
		if (iS != null) {
			if (iS.equals("-1")) {
	%>
	<span>サービス情報の更新に失敗しました。<br> 名前は100文字<br> 値段は、数字<br>
		入力してください<br></span>
	<%
		} else {
	%>
	<h3>該当するサービス情報が存在しません</h3>
	<br>
	<%
		}
		}
	%>
	<%
		if (iS != null) {
	%>
	<%
		if (iS.equals("-1")) {
	%>

	<%
		int id = service.getId();
		String name = service.getName();
		int price = service.getPrice();
		int status = service.getStatus();
	%>

	<h3>更新後の情報を入力してください。</h3>
	<br>
	<dd>
		<form action="./ServiceUpdateServlet" method="post">
			id:<%=id%><br> <input type="hidden" name="id" value=<%=id%>>
			<input type="hidden" name="pname" value=<%=name%>> <input
				type="hidden" name="pprice" value=<%=price%>> <input
				type="hidden" name="pstatus" value=<%=status%>>
			<dt>サービス名：</dt>
			<dl>
				<input type="text" name="name" value=<%=name%>>
				<br>
			</dl>
			<dt>値段：</dt>
			<dl>
				<input type="text" name="price" value=<%=price%>>
				<br>
			</dl>
			<%
				if (status == 0) {
			%>
			<dt>ステータス：</dt>
			<dl>
				<select name="status"><option value="0">サービス休止中</option>
					<option value="1">サービス提供中</option></select>
				<br>
			</dl>
			<%
				} else {
			%>
			<dt>ステータス：</dt>
			<dl>
				<select name="status"><option value="1">サービス提供中</option>
					<option value="0">サービス休止中</option></select>
				<br>
			</dl>
			<%
				}
			%>
			<input type="submit" value="サービス情報を更新する">
		</form>
	</dd>
	<%
		}
	%>
	<%
		} else {
		if (service != null) {
		int id = service.getId();
		String name = service.getName();
		int price = service.getPrice();
		int status = service.getStatus();
	%>

	<h3>更新後の情報を入力してください。</h3>
	<br>
	<dd>
		<form action="./ServiceUpdateServlet" method="post">
			id:<%=id%><br> <input type="hidden" name="id" value=<%=id%>>
			<input type="hidden" name="pname" value=<%=name%>> <input
				type="hidden" name="pprice" value=<%=price%>> <input
				type="hidden" name="pstatus" value=<%=status%>>
			<dt>サービス名：</dt>
			<dl>
				<input type="text" name="name">
				<br>
			</dl>
			<dt>値段：</dt>
			<dl>
				<input type="text" name="price">
				<br>
			</dl>
			<dt>ステータス：</dt>
			<dl>
				<select name="status"><option value="0">サービス休止中</option>
					<option value="1">サービス提供中</option></select>
				<br>
			</dl>
			<input type="submit" value="サービス情報を更新する">
		</form>
	</dd>
	<%
		}
	%>
	<%
		}
	%>
	<br>
	<a href="./servicemenu.jsp">サービス管理メニューに戻る</a>


</body>
</html>