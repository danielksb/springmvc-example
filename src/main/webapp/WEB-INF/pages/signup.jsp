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
	<p>
		<h2>Registration</h2>
		<form method="post" action="signup" accept-charset="UTF-8">
			<table>
				<tr>
					<td>Username</td>
					<td><input type="text" name="id" value="" /></td>
					<td><c:if test="${userId_error != null}">
							<p style="color: red">User with id "${userId_error}" already exists.</p>
						</c:if></td>
				</tr>

				<tr>
					<td>Password</td>
					<td><input type="password" name="password" value="" /></td>
					<td><c:if test="${password_error == 'not_equal'}">
							<p style="color: red">Passwords don't match.</p>
						</c:if></td>
				</tr>

				<tr>
					<td>Verify Password</td>
					<td><input type="password" name="confirmedPassword" value="" /></td>
					<td></td>
				</tr>
			</table>

			<input type="submit" />
		</form>
	</p>
</body>
</html>