<%@page import="entity.Owner"%>
<%@page import="dao.OwnerDao"%>
<%@page import="entity.Account"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entity.History"%>
<%@page import="entity.Service"%>
<%@page import="entity.Pet"%>
<%@page import="java.util.List"%>
<%@page import="dao.ServiceDao"%>
<%@page import="dao.PetDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
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
<link rel="stylesheet" href="../botan.css">
<title>サービス情報更新画面</title>
</head>
<body>
	<h1>実施サービス情報更新</h1>
	<hr>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	ユーザ名：<%=account.getName()%>さん
	<br>
	<%
		if (error == 1) {
			History history = (History) request.getAttribute("history");
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
			String service_Date = sdFormat.format(history.getServiceDate());
	%>
	<form action="/Dsystem/after_login/HistoryUpdateServlet" method="POST">
		<dl>
			<dt>ペット名（顧客名）:</dt>
			<dd>
				<select name="pet_Id">
					<%
						for (Pet p : petList) {
					%>
					<%
						for (Owner o : ownerList) {
									if (o.getId() == p.getOwnerId()) {
					%>
					<option value=<%=p.getId()%>><%=p.getName()%>（<%=o.getName()%>）
						<%
						break;
									}
					%>

						<%
							}
						%>
					</option>
					<%
						}
					%>
				</select>
			</dd>
		</dl>
		<br>
		<dl>
			<dt>サービス名:</dt>
			<dd>
				<select name="service_Id">
					<%
						for (Service s : serviceList) {
					%>
					<option value=<%=s.getId()%>><%=s.getName()%></option>
					<%
						}
					%>
				</select>
			</dd>
		</dl>
		<br>
		<dl>
			<dt>サービス日時:</dt>
			<dd>
				<input type="text" name="service_Date" value=<%=service_Date%>><input
					type="hidden" name="id" value=<%=history.getId()%>><br>
			</dd>
		</dl>
		<input type="submit" value="上記の実施サービスを追加する">
	</form>
	<%
		} else if (error == -1) {
			int id = (int) request.getAttribute("id");
			int pet_Id = (int) request.getAttribute("pet_Id");
			int service_Id = (int) request.getAttribute("service_Id");
			String service_Date = (String) request.getAttribute("service_Date");
	%>
	<p style="color: red;">実施サービス情報の追加に失敗しました。フォーマットに従って日時を入力してください。</p>
	<form action="/Dsystem/after_login/HistoryUpdateServlet" method="POST">
		<dl>
			<dt>ペット名（顧客名）:</dt>
			<dd>
				<select name="pet_Id">
					<%
						for (Pet p : petList) {
					%>
					<%
						for (Owner o : ownerList) {
									if (o.getId() == p.getOwnerId()) {
					%>
					<option value=<%=p.getId()%> <%if (pet_Id == p.getId()) {%>
						selected <%}%>><%=p.getName()%>（<%=o.getName()%>）
						<%
						break;
									}
					%>

						<%
							}
						%>
					</option>
					<%
						}
					%>
				</select>
			</dd>
		</dl>
		<br>
		<dl>
			<dt>サービス名:</dt>
			<dd>
				<select name="service_Id">
					<%
						for (Service s : serviceList) {
					%>
					<option value=<%=s.getId()%> <%if (service_Id == s.getId()) {%>
						selected <%}%>>
						<%=s.getName()%></option>
					<%
						}
					%>
				</select>
			</dd>
		</dl>
		<br>
		<dl>
			<dt>サービス日時:</dt>
			<dd>
				<input type="text" name="service_Date" value=<%=service_Date%>><input
					type="hidden" name="id" value=<%=id%>>
			</dd>
		</dl>
		<br> <input type="submit" value="上記の実施サービスを追加する">
	</form>
	<%
		} else {
	%>
	該当の実施サービスが存在しないか、サービス日時の入力値が不正です。
	<br>
	<%
		}
	%>
	<a href="./historymenu.jsp"> 実施サービスメニューに戻る </a>

</body>
</html>