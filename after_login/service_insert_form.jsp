<%@page import="entity.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../botan.css">
<title>サービス登録</title>
</head>
<body>

<%Account account = (Account)session.getAttribute("account"); %>
ユーザ名：<%=account.getName()%>さん
<br>
<%try{ %>
 <% int isSuccess = (int)request.getAttribute("isSuccess"); %>
<p style="color: red;"><span>サービス情報の更新に失敗しました。<br>
 名前は100文字<br>
 値段は、数字<br></span>
<br>

<%}catch(NullPointerException e) { %>
	<% e.printStackTrace(); %>
<%} %>

<h3>追加する顧客情報を入力してください。</h3><br>
<dd>
<form action="./ServiceInsertServlet" method = "post"><br>
<dt>サービス名:</dt><dl><input type = "text" name = "name"><br></dl>
<dt>値段:</dt><dl><input type = "text" name = "price"><br></dl>
<dt>ステータス:</dt><dl><select name = "status"><option value = "0">サービス休止中</option><option value = "1">サービス提供中</option></select><br></dl>
<input type = "submit" value = "サービス情報を追加する">
</form>
</dd>
<br>
<a href = "./servicemenu.jsp">サービス管理メニューに戻る</a>
</body>
</html>