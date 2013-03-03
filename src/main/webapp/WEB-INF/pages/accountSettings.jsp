<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>User Settings</title>
</head>
<body>
	<table>
		<tr>
			<td>Username</td>
			<td><input type="text" value="<c:out value="${user.id}" />" readonly="readonly"/></td>
		</tr>

		<tr>
			<td>Password Size</td>
			<td><input type="text" value="<c:out value="${user.password.length()}" />" readonly="readonly"/></td>
		</tr>
	</table>
</body>
</html>