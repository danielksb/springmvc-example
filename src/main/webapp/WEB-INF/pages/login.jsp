<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Example</title>
</head>
<body>
	<h1>Login</h1>
	<form method="post" action=<c:url value="j_spring_security_check"/> accept-charset="UTF-8">
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="userId" value="" /></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="" /></td>
			</tr>
		</table>
		<input type="submit" />
		<c:if test="${login_error == true}">
			<p style="color: red">Login failed, wrong password or user name.</p>
		</c:if>
	</form>
</body>
</html>