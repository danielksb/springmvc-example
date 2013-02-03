<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>Example Registration</title>
</head>
<body>
	<a href="login">Login</a>
	<p>
		<h2>Registration</h2>
		<form method="post" action="signup" accept-charset="UTF-8">
			<table>
				<tr>
					<td>Username</td>
					<td><input type="text" name="id" value=""/></td>
				</tr>

				<tr>
					<td>Password</td>
					<td><input type="password" name="password" value=""/></td>
				</tr>

				<tr>
					<td>Verify Password</td>
					<td><input type="password" name="verify" value=""/></td>
				</tr>

				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value=""/></td>
				</tr>
			</table>

			<input type="submit"/>
			<c:choose>
				<c:when  test="${sucess == true}">
					<p style="color:green">success</p>
				</c:when>
				<c:otherwise>
					<p style="color:red">${error} ${errorMsg}</p>
				</c:otherwise>
			</c:choose>
		</form>
	</p>
</body>
</html>