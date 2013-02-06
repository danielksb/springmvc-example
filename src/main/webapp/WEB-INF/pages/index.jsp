<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>Example</title>
</head>
<body>
	<h1>Hello, ${name}</h1>
	<c:choose>
		<c:when test="${isUserLoggedIn == true}">
			<form action="logout" method="post">
				<input type="submit" value="logout" />
			</form>
		</c:when>
		<c:otherwise>
			<a href="login">login</a>
		</c:otherwise>
	</c:choose>
</body>
</html>