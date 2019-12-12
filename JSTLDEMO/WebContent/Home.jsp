<%@page import="java.util.TreeSet"%>
<%@page import="control.Item"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="LoginController" method="post">
			Username<input type="text" name="un"> Password<input
				type="password" name="pwd"> <input type="submit"
				value="Login">
		</form>
	</div>
	<form action="Fruits" method="post">
		<select name="cat">
			<c:forEach var="x" items="${cat}">
				<option>${x}</option>
			</c:forEach>
		</select> <input type="submit" value="search">
		<table border="1">
			<tr>
				<th>Item</th>
				<th>Image</th>
				<th>Category</th>
			</tr>
			<c:forEach var="i" items="${values}">
				<tr>
					<td>${i.getName()}</td>
					<td><img src='image/${i.getImage()}.jpg' height=100 width=100></td>
					<td>${i.getCategory()}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
